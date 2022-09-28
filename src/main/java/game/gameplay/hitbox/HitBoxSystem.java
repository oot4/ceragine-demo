package game.gameplay.hitbox;

import ceragine.Game;
import ecs_architecture.standard.GameComponent;
import ecs_architecture.standard.GameSystem;
import game.components.DynamicComponent;
import game.components.StaticComponent;
import game.components.TriggerComponent;
import render_engine.core.RenderLayer;
import utility_library.algorithms.core.Quotient;
import utility_library.gameplay.kinetics.TranslationComponent;
import utility_library.gameplay.rendering.api.SpriteContainer;
import utility_library.gameplay.rendering.core.CharacterSpriteComponent;
import utility_library.gameplay.rendering.core.EnvironmentSpriteComponent;
import utility_library.gameplay.rendering.core.FloorSpriteComponent;
import utility_library.gameplay.rendering.core.OverlaySpriteComponent;
import utility_library.gameplay.rendering.core.UserInterfaceSpriteComponent;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class HitBoxSystem extends GameSystem
{

    private static final int MODIFIER = 2;

    private SpriteContainer getSpriteContainer( String entity )
    {
        if ( this.get( OverlaySpriteComponent.class ).containsKey( entity ) )
        {
            return ( SpriteContainer ) this.get( OverlaySpriteComponent.class ).get( entity );
        }
        else if ( this.get( UserInterfaceSpriteComponent.class ).containsKey( entity ) )
        {
            return ( SpriteContainer ) this.get( UserInterfaceSpriteComponent.class ).get( entity );
        }
        else if ( this.get( CharacterSpriteComponent.class ).containsKey( entity ) )
        {
            return ( SpriteContainer ) this.get( CharacterSpriteComponent.class ).get( entity );
        }
        else if ( this.get( EnvironmentSpriteComponent.class ).containsKey( entity ) )
        {
            return ( SpriteContainer ) this.get( EnvironmentSpriteComponent.class ).get( entity );
        }
        else
        {
            return this.get( FloorSpriteComponent.class ).containsKey( entity ) ? ( SpriteContainer ) this.get( FloorSpriteComponent.class ).get( entity ) : null;
        }
    }

    private static Rectangle2D calculateLastHitBox( TranslationComponent translation , HitBoxComponent hitBox , SpriteContainer spriteContainer )
    {
        TranslationComponent lastTranslation = TranslationComponent.from( 0 , 0 );
        lastTranslation.setLocation( translation.getLastX( ) , translation.getLastY( ) );
        return calculateHitBox( lastTranslation , hitBox , spriteContainer );
    }

    private static Rectangle2D calculateHitBox( TranslationComponent translation , HitBoxComponent hitBox , SpriteContainer spriteContainer )
    {
        int tileSize = RenderLayer.TILE_SIZE * spriteContainer.sprite( ).scale( );
        int widthModifier = spriteContainer.sprite( ).width( ) % MODIFIER;
        int heightModifier = spriteContainer.sprite( ).height( ) % MODIFIER;
        int width = tileSize * widthModifier;
        int height = tileSize * heightModifier;
        double x = translation.getX( ) + ( spriteContainer.sprite( ).offset( ).x / 2.0D );
        double y = translation.getY( ) - tileSize + ( spriteContainer.sprite( ).offset( ).y / 2.0D );
        Rectangle2D initial = new Rectangle2D.Double( x , y , width , height );
        Rectangle2D hitBoxFrame = new Rectangle2D.Double( 0 , 0 , hitBox.getWidth( ) , hitBox.getHeight( ) );
        double expandedX = initial.getCenterX( ) - hitBoxFrame.getCenterX( );
        double expandedY = initial.getCenterY( ) - hitBoxFrame.getCenterY( );
        return new Rectangle2D.Double( expandedX , expandedY , hitBoxFrame.getWidth( ) , hitBoxFrame.getHeight( ) );
    }

    private static double quotient( double dividend , double divisor )
    {
        return Quotient.toDouble( dividend , divisor );
    }

    private static void swap( AtomicReference< Double > value1 , AtomicReference< Double > value2 )
    {
        if ( value1.get( ) > value2.get( ) )
        {
            double temp = value1.get( );
            value1.set( value2.get( ) );
            value2.set( temp );
        }
    }

    private boolean intersects( Rectangle2D source , Rectangle2D target , Point2D velocity , Point2D contact , Point2D normal , AtomicReference< Double > time )
    {
        //Reject early if velocity is 0
        if ( velocity.getX( ) == 0 && velocity.getY( ) == 0 )
        {
            return false;
        }

        //Expand source
        double expandedTargetX = target.getX( ) - source.getWidth( ) / 2.0;
        double expandedTargetY = target.getY( ) - source.getHeight( ) / 2.0;
        double expandedTargetWidth = target.getWidth( ) + source.getWidth( );
        double expandedTargetHeight = target.getHeight( ) + source.getHeight( );
        Rectangle2D expandedTarget = new Rectangle2D.Double( expandedTargetX , expandedTargetY , expandedTargetWidth , expandedTargetHeight );

        Game.drawOutline( expandedTarget );
        //Get origin
        Point2D origin = new Point2D.Double( source.getCenterX( ) , source.getCenterY( ) );

        //Resolve
        return intersects( expandedTarget , origin , velocity , contact , normal , time ) && time.get( ) <= 1.0D;
    }

    private boolean intersects( Rectangle2D target , Point2D origin , Point2D direction , Point2D contact , Point2D normal , AtomicReference< Double > time )
    {
        //Setup
        double xNearDiff = target.getX( ) - origin.getX( );
        double yNearDiff = target.getY( ) - origin.getY( );
        double xFarDiff = target.getMaxX( ) - origin.getX( );
        double yFarDiff = target.getMaxY( ) - origin.getY( );
        if ( xNearDiff == 0 || yNearDiff == 0 || xFarDiff == 0 || yFarDiff == 0 )
        {
            return false;
        }
        AtomicReference< Double > tNearX = new AtomicReference<>( quotient( xNearDiff , direction.getX( ) ) );
        AtomicReference< Double > tNearY = new AtomicReference<>( quotient( yNearDiff , direction.getY( ) ) );
        AtomicReference< Double > tFarX = new AtomicReference<>( quotient( xFarDiff , direction.getX( ) ) );
        AtomicReference< Double > tFarY = new AtomicReference<>( quotient( yFarDiff , direction.getY( ) ) );
        swap( tNearX , tFarX );
        swap( tNearY , tFarY );

        //Reject early if condition not met
        if ( tNearX.get( ) > tFarY.get( ) || tNearY.get( ) > tFarX.get( ) )
        {
            return false;
        }

        //Reject if time to far contact is negative
        double tNearContact = Math.max( tNearX.get( ) , tNearY.get( ) );
        double tFarContact = Math.min( tFarX.get( ) , tFarY.get( ) );
        time.set( tNearContact );
        if ( tFarContact < 0 )
        {
            return false;
        }

        //Get contact info
        double contactX = origin.getX( ) + tNearContact * direction.getX( );
        double contactY = origin.getY( ) + tNearContact * direction.getY( );
        contact.setLocation( contactX , contactY );
        if ( tNearX.get( ) > tNearY.get( ) )
        {
            if ( direction.getX( ) < 0 )
            {
                normal.setLocation( 1 , 0 );
            }
            else
            {
                normal.setLocation( - 1 , 0 );
            }
        }
        else if ( tNearX.get( ) < tNearY.get( ) )
        {
            if ( direction.getY( ) < 0 )
            {
                normal.setLocation( 0 , 1 );
            }
            else
            {
                normal.setLocation( 0 , - 1 );
            }
        }

        return true;
    }

    private void resolve( TranslationComponent translation , Point2D normal , boolean intersects )
    {
        if ( intersects )
        {
            double adjustX = translation.getX( ) + normal.getX( );
            double adjustY = translation.getY( ) + normal.getY( );
            translation.setLocation( adjustX , adjustY );
        }
    }

    private boolean detectCollision( TranslationComponent translation1 , TranslationComponent translation2 , HitBoxComponent hitBox1 , HitBoxComponent hitBox2 , SpriteContainer spriteContainer1 , SpriteContainer spriteContainer2 , Point2D normal )
    {
        //Calculate hit boxes
        Rectangle2D lastDynamicHitBox = calculateLastHitBox( translation1 , hitBox1 , spriteContainer1 );
        Rectangle2D dynamicHitBox = calculateHitBox( translation1 , hitBox1 , spriteContainer1 );
        Rectangle2D staticHitBox = calculateHitBox( translation2 , hitBox2 , spriteContainer2 );

        //Draw outline
        Game.drawOutline( dynamicHitBox );
        Game.drawOutline( staticHitBox );

        //Setup parameters
        AtomicReference< Double > time = new AtomicReference<>( );
        Point2D contact = new Point2D.Double( 0 , 0 );
        Point2D velocity = new Point2D.Double( dynamicHitBox.getCenterX( ) - lastDynamicHitBox.getCenterX( ) , dynamicHitBox.getCenterY( ) - lastDynamicHitBox.getCenterY( ) );

        //Calculate intersection
        return intersects( dynamicHitBox , staticHitBox , velocity , contact , normal , time );
    }

    @Override
    protected void internalInitialize( )
    {
        //Nothing to initialize
    }

    @Override
    protected void internalTerminate( )
    {
        //Nothing to terminate
    }

    @Override
    protected void internalPerform( )
    {
        Map< String, GameComponent > staticEntities = get( StaticComponent.class );
        Map< String, GameComponent > dynamicEntities = get( DynamicComponent.class );
        Map< String, GameComponent > triggerEntities = get( TriggerComponent.class );
        Map< String, GameComponent > hitBoxEntities = get( HitBoxComponent.class );
        Map< String, GameComponent > translationEntities = get( TranslationComponent.class );
        dynamicEntities.forEach( ( entity1 , component1 ) -> triggerEntities.forEach( ( entity2 , component2 ) -> {
            if ( hitBoxEntities.containsKey( entity1 ) && hitBoxEntities.containsKey( entity2 ) && translationEntities.containsKey( entity1 ) && translationEntities.containsKey( entity2 ) )
            {
                SpriteContainer spriteContainer1 = getSpriteContainer( entity1 );
                SpriteContainer spriteContainer2 = getSpriteContainer( entity2 );
                if ( spriteContainer1 != null && spriteContainer2 != null )
                {
                    //Get component data
                    TranslationComponent translation1 = ( TranslationComponent ) translationEntities.get( entity1 );
                    TranslationComponent translation2 = ( TranslationComponent ) translationEntities.get( entity2 );
                    HitBoxComponent hitBox1 = ( HitBoxComponent ) hitBoxEntities.get( entity1 );
                    HitBoxComponent hitBox2 = ( HitBoxComponent ) hitBoxEntities.get( entity2 );

                    //Calculate intersection
                    Point2D normal = new Point2D.Double( 0 , 0 );
                    boolean intersects = detectCollision( translation1 , translation2 , hitBox1 , hitBox2 , spriteContainer1 , spriteContainer2 , normal );

                    //Resolve
                    if ( intersects )
                    {
                        hitBox1.setCollidedWithTrigger( component2.getName( ) );
                    }
                }
            }
        } ) );
        dynamicEntities.forEach( ( entity1 , component1 ) -> staticEntities.forEach( ( entity2 , component2 ) -> {
            if ( hitBoxEntities.containsKey( entity1 ) && hitBoxEntities.containsKey( entity2 ) && translationEntities.containsKey( entity1 ) && translationEntities.containsKey( entity2 ) )
            {
                SpriteContainer spriteContainer1 = getSpriteContainer( entity1 );
                SpriteContainer spriteContainer2 = getSpriteContainer( entity2 );
                if ( spriteContainer1 != null && spriteContainer2 != null )
                {
                    //Get component data
                    TranslationComponent translation1 = ( TranslationComponent ) translationEntities.get( entity1 );
                    TranslationComponent translation2 = ( TranslationComponent ) translationEntities.get( entity2 );
                    HitBoxComponent hitBox1 = ( HitBoxComponent ) hitBoxEntities.get( entity1 );
                    HitBoxComponent hitBox2 = ( HitBoxComponent ) hitBoxEntities.get( entity2 );

                    //Calculate intersection
                    Point2D normal = new Point2D.Double( 0 , 0 );
                    boolean intersects = detectCollision( translation1 , translation2 , hitBox1 , hitBox2 , spriteContainer1 , spriteContainer2 , normal );

                    //Resolve
                    hitBox1.setCollidedWithStatic( intersects );
                    resolve( translation1 , normal , hitBox1.isCollidedWithStatic( ) );
                }
            }
        } ) );

    }

}