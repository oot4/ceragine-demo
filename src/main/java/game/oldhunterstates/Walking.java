package game.oldhunterstates;

import ceragine.Game;
import ecs_architecture.standard.GameComponent;
import game.components.DirectionComponent;
import game.creators.CerastesForest;
import game.creators.OldHunterEntity;
import game.gameplay.entitystate.EntityState;
import game.gameplay.entitystate.StateComponent;
import game.gameplay.hitbox.HitBoxComponent;
import game.gameplay.input.KeyComponent;
import utility_library.gameplay.animation.AnimationComponent;
import utility_library.gameplay.kinetics.TranslationComponent;

import java.util.Map;

public class Walking implements EntityState
{

    @Override
    public void onEnter( Map< Class< ? extends GameComponent >, GameComponent > entityComponentMap )
    {
        DirectionComponent directionComponent = ( DirectionComponent ) entityComponentMap.get( DirectionComponent.class );
        AnimationComponent animationComponent = ( AnimationComponent ) entityComponentMap.get( AnimationComponent.class );
        switch ( directionComponent.getCurrent( ) )
        {
            case NORTH:
                animationComponent.setCurrentAnimation( OldHunterEntity.WALKING_BACKWARD );
                break;
            case EAST:
                animationComponent.setCurrentAnimation( OldHunterEntity.WALKING_RIGHT );
                break;
            case WEST:
                animationComponent.setCurrentAnimation( OldHunterEntity.WALKING_LEFT );
                break;
            default:
                animationComponent.setCurrentAnimation( OldHunterEntity.WALKING_FORWARD );
                break;
        }
    }

    @Override
    public void updateState( Map< Class< ? extends GameComponent >, GameComponent > entityComponentMap , KeyComponent keyComponent )
    {
        HitBoxComponent hitBox = ( HitBoxComponent ) entityComponentMap.get( HitBoxComponent.class );
        StateComponent stateComponent = ( StateComponent ) entityComponentMap.get( StateComponent.class );
        TranslationComponent translationComponent = ( TranslationComponent ) entityComponentMap.get( TranslationComponent.class );
        DirectionComponent directionComponent = ( DirectionComponent ) entityComponentMap.get( DirectionComponent.class );
        if ( keyComponent.isDown( KeyComponent.LEFT ) )
        {
            translationComponent.moveLeft( );
            directionComponent.setWest( );
            Game.centerViewportAt( translationComponent.getX( ) , translationComponent.getY( ) );
            handleTrigger( hitBox , stateComponent );
        }
        else if ( keyComponent.isDown( KeyComponent.RIGHT ) )
        {
            translationComponent.moveRight( );
            directionComponent.setEast( );
            Game.centerViewportAt( translationComponent.getX( ) , translationComponent.getY( ) );
            handleTrigger( hitBox , stateComponent );
        }
        else if ( keyComponent.isDown( KeyComponent.UP ) )
        {
            translationComponent.moveUp( );
            directionComponent.setNorth( );
            Game.centerViewportAt( translationComponent.getX( ) , translationComponent.getY( ) );
            handleTrigger( hitBox , stateComponent );
        }
        else if ( keyComponent.isDown( KeyComponent.DOWN ) )
        {
            translationComponent.moveDown( );
            directionComponent.setSouth( );
            Game.centerViewportAt( translationComponent.getX( ) , translationComponent.getY( ) );
            handleTrigger( hitBox , stateComponent );
        }
        else if ( ! keyComponent.isDown( KeyComponent.LEFT ) && ! keyComponent.isDown( KeyComponent.RIGHT ) && ! keyComponent.isDown( KeyComponent.UP ) && ! keyComponent.isDown( KeyComponent.DOWN ) )
        {
            handleTrigger( hitBox , stateComponent );
            stateComponent.setCurrentState( OldHunterEntity.STANDING );
        }
    }

    private static void handleTrigger( HitBoxComponent hitBox , StateComponent stateComponent )
    {
        if ( hitBox.isCollidedWithTrigger( ) )
        {
            CerastesForest.TileType type = CerastesForest.TileType.valueOf( hitBox.getTriggerName( ) );
            if ( type == CerastesForest.TileType.WATER )
            {
                stateComponent.setCurrentState( OldHunterEntity.ROWING );
            }
        }
    }

}