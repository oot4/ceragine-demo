package game.creators;

import ecs_architecture.standard.GameComponent;
import game.utilities.TileArrangementLoader;
import utility_library.gameplay.animation.AnimationComponent;
import utility_library.gameplay.kinetics.TranslationComponent;
import utility_library.gameplay.rendering.core.CharacterSpriteComponent;
import utility_library.structures.Animation;
import utility_library.structures.Sprite;

import java.util.ArrayList;
import java.util.List;

public class OldHunterEntity extends ComponentGroup
{

    private static final OldHunterEntity OLD_HUNTER_ENTITY = new OldHunterEntity( );

    private static final String NAME = "old-hunter";

    private static final String EXTENSION = ".json";

    private static final int WIDTH = 2;

    private static final int HEIGHT = 3;

    public static final String STANDING_FORWARD = "standing-forward";

    public static final String STANDING_BACKWARD = "standing-backward";

    public static final String STANDING_LEFT = "standing-left";

    public static final String STANDING_RIGHT = "standing-right";

    public static final String WALKING_FORWARD = "walking-forward";

    public static final String WALKING_BACKWARD = "walking-backward";

    public static final String WALKING_LEFT = "walking-left";

    public static final String WALKING_RIGHT = "walking-right";

    public static final String BOAT_FORWARD = "boat-forward";

    public static final String BOAT_BACKWARD = "boat-backward";

    public static final String BOAT_LEFT = "boat-left";

    public static final String BOAT_RIGHT = "boat-right";

    private int x = 0;

    private int y = 0;

    private OldHunterEntity( ) { }

    public static GameComponent[] build( ) { return OLD_HUNTER_ENTITY.computeAndGet( ); }

    private TranslationComponent getTranslationComponent( ) { return TranslationComponent.from( x , y ); }

    private CharacterSpriteComponent getCharacterSpriteComponent( )
    {
        return CharacterSpriteComponent.from( NAME , WIDTH , HEIGHT );
    }

    private AnimationComponent getAnimationComponent( )
    {
        int[][] tiles = TileArrangementLoader.loadMap( NAME + EXTENSION );
        Sprite[] sprites = new Sprite[ tiles.length ];
        for ( int i = 0; i < sprites.length; i++ )
        {
            Sprite sprite = Sprite.from( NAME , WIDTH , HEIGHT );
            sprite.replaceTiles( tiles[ i ] );
            sprites[ i ] = sprite;
        }

        //Stationary
        Animation standingForward = Animation.newInstance( );
        standingForward.add( sprites[ 0 ] , sprites[ 0 ] );
        Animation standingBackward = Animation.newInstance( );
        standingBackward.add( sprites[ 1 ] , sprites[ 1 ] );
        Animation standingLeft = Animation.newInstance( );
        standingLeft.add( sprites[ 2 ] , sprites[ 2 ] );
        Animation standingRight = Animation.newInstance( );
        Sprite mirroredSR = Sprite.from( NAME , WIDTH , HEIGHT );
        mirroredSR.replaceTiles( new int[]{
                sprites[ 2 ].tiles( )[ 1 ] , sprites[ 2 ].tiles( )[ 0 ] ,
                sprites[ 2 ].tiles( )[ 3 ] , sprites[ 2 ].tiles( )[ 2 ] ,
                sprites[ 2 ].tiles( )[ 5 ] , sprites[ 2 ].tiles( )[ 4 ]
        } );
        int[] mirrors = new int[]{
                1 , 1 ,
                1 , 1 ,
                1 , 1
        };
        mirroredSR.replaceMirrored( mirrors );
        standingRight.add( mirroredSR , mirroredSR );

        //Motion
        Animation walkingForward = Animation.newInstance( );
        walkingForward.add( sprites[ 0 ] , sprites[ 3 ] , sprites[ 0 ] , sprites[ 4 ] );
        Animation walkingBackward = Animation.newInstance( );
        walkingBackward.add( sprites[ 1 ] , sprites[ 5 ] , sprites[ 1 ] , sprites[ 6 ] );
        Animation walkingLeft = Animation.newInstance( );
        walkingLeft.add( sprites[ 2 ] , sprites[ 7 ] , sprites[ 2 ] , sprites[ 8 ] );
        Animation walkingRight = Animation.newInstance( );
        Sprite mirrorWR5 = Sprite.from( NAME , WIDTH , HEIGHT );
        mirrorWR5.replaceTiles( new int[]{
                sprites[ 7 ].tiles( )[ 1 ] , sprites[ 7 ].tiles( )[ 0 ] ,
                sprites[ 7 ].tiles( )[ 3 ] , sprites[ 7 ].tiles( )[ 2 ] ,
                sprites[ 7 ].tiles( )[ 5 ] , sprites[ 7 ].tiles( )[ 4 ]
        } );
        Sprite mirrorWR7 = Sprite.from( NAME , WIDTH , HEIGHT );
        mirrorWR7.replaceTiles( new int[]{
                sprites[ 8 ].tiles( )[ 1 ] , sprites[ 8 ].tiles( )[ 0 ] ,
                sprites[ 8 ].tiles( )[ 3 ] , sprites[ 8 ].tiles( )[ 2 ] ,
                sprites[ 8 ].tiles( )[ 5 ] , sprites[ 8 ].tiles( )[ 4 ]
        } );
        mirrorWR5.replaceMirrored( mirrors );
        mirrorWR7.replaceMirrored( mirrors );
        walkingRight.add( mirroredSR , mirrorWR5 , mirroredSR , mirrorWR7 );

        //Standing Boat
        Animation boatForward = Animation.newInstance( );
        boatForward.add( sprites[ 9 ] , sprites[ 10 ] );
        Animation boatBackward = Animation.newInstance( );
        boatBackward.add( sprites[ 11 ] , sprites[ 12 ] );
        Animation boatLeft = Animation.newInstance( );
        boatLeft.add( sprites[ 13 ] , sprites[ 14 ] );
        Animation boatRight = Animation.newInstance( );
        Sprite mirrorBR13 = Sprite.from( NAME , WIDTH , HEIGHT );
        mirrorBR13.replaceTiles( new int[]{
                sprites[ 13 ].tiles( )[ 1 ] , sprites[ 13 ].tiles( )[ 0 ] ,
                sprites[ 13 ].tiles( )[ 3 ] , sprites[ 13 ].tiles( )[ 2 ] ,
                sprites[ 13 ].tiles( )[ 5 ] , sprites[ 13 ].tiles( )[ 4 ]
        } );
        Sprite mirrorBR14 = Sprite.from( NAME , WIDTH , HEIGHT );
        mirrorBR14.replaceTiles( new int[]{
                sprites[ 14 ].tiles( )[ 1 ] , sprites[ 14 ].tiles( )[ 0 ] ,
                sprites[ 14 ].tiles( )[ 3 ] , sprites[ 14 ].tiles( )[ 2 ] ,
                sprites[ 14 ].tiles( )[ 5 ] , sprites[ 14 ].tiles( )[ 4 ]
        } );
        mirrorBR13.replaceMirrored( mirrors );
        mirrorBR14.replaceMirrored( mirrors );
        boatRight.add( mirrorBR13 , mirrorBR14 );

        AnimationComponent animationComponent = AnimationComponent.newInstance( );
        animationComponent.addAnimation( STANDING_FORWARD , standingForward );
        animationComponent.addAnimation( STANDING_BACKWARD , standingBackward );
        animationComponent.addAnimation( STANDING_LEFT , standingLeft );
        animationComponent.addAnimation( STANDING_RIGHT , standingRight );
        animationComponent.addAnimation( WALKING_FORWARD , walkingForward );
        animationComponent.addAnimation( WALKING_BACKWARD , walkingBackward );
        animationComponent.addAnimation( WALKING_LEFT , walkingLeft );
        animationComponent.addAnimation( WALKING_RIGHT , walkingRight );
        animationComponent.addAnimation( BOAT_FORWARD , boatForward );
        animationComponent.addAnimation( BOAT_BACKWARD , boatBackward );
        animationComponent.addAnimation( BOAT_LEFT , boatLeft );
        animationComponent.addAnimation( BOAT_RIGHT , boatRight );
        animationComponent.setCurrentAnimation( STANDING_FORWARD);
        animationComponent.setAsLoopedAnimation( true );
        animationComponent.setInterval( 9 );
        return animationComponent;
    }

    @Override
    public GameComponent[] computeAndGet( )
    {
        List< GameComponent > components = new ArrayList<>( );
        components.add( getTranslationComponent( ) );
        components.add( getCharacterSpriteComponent( ) );
        components.add( getAnimationComponent( ) );
        return components.toArray( new GameComponent[ 0 ] );
    }

    public static void main( String[] args )
    {
        OLD_HUNTER_ENTITY.computeAndGet( );
    }

}