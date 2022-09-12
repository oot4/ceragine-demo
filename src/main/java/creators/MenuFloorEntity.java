package creators;

import ecs_architecture.standard.GameComponent;
import utility_library.algorithms.api.ReturningComputable;
import utility_library.gameplay.kinetics.TranslationComponent;
import utility_library.gameplay.rendering.core.FloorSpriteComponent;

import java.util.ArrayList;
import java.util.List;

public final class MenuFloorEntity implements ReturningComputable< GameComponent[] >
{

    private static final int WIDTH = 48;

    private static final int HEIGHT = 54;

    private static final MenuFloorEntity MENU_FLOOR_ENTITY = new MenuFloorEntity( );

    private MenuFloorEntity( ) { }

    public static GameComponent[] build( )
    {
        return MENU_FLOOR_ENTITY.computeAndGet( );
    }

    @Override
    public GameComponent[] computeAndGet( )
    {
        List< GameComponent > gameComponents = new ArrayList<>( );
        FloorSpriteComponent floorSpriteComponent = FloorSpriteComponent.from( "title" , WIDTH , HEIGHT );
        for ( int i = 0; i < WIDTH * HEIGHT; i++ )
        {
            floorSpriteComponent.sprite( ).replaceTile( i , 41 );
        }
        floorSpriteComponent.sprite( ).offset( ).setLocation( - 8 , - 12 );
        gameComponents.add( floorSpriteComponent );
        gameComponents.add( TranslationComponent.from( 0 , 0 ) );
        return gameComponents.toArray( new GameComponent[ 0 ] );
    }

}