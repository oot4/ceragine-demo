package game.creators;

import ecs_architecture.standard.GameComponent;
import game.utilities.TileArrangementLoader;
import utility_library.algorithms.api.ReturningComputable;
import utility_library.gameplay.kinetics.TranslationComponent;
import utility_library.gameplay.rendering.core.FloorSpriteComponent;
import utility_library.gameplay.rendering.core.UserInterfaceSpriteComponent;

import java.util.ArrayList;
import java.util.List;

public class MenuTitleBGEntity implements ReturningComputable< GameComponent[] >
{

    private static final MenuTitleBGEntity MENU_TITLE_ENTITY = new MenuTitleBGEntity( );

    private static int X = 0;

    private static int Y = 0;

    private static final String NAME = "title";

    private static final String COMPOSITION = "title-bg";

    private static final String EXTENSION = ".json";

    private static final int WIDTH = 4;

    private static final int HEIGHT = 4;

    private MenuTitleBGEntity( ) { }

    public static GameComponent[] build( ) { return MENU_TITLE_ENTITY.computeAndGet( ); }

    public static void setPosition( int x , int y )
    {
        X = ( x * 8 * WIDTH ) - 84;
        Y = ( y * 8 * HEIGHT ) - 95;
    }

    private TranslationComponent getTranslationComponent( ) { return TranslationComponent.from( X , Y ); }

    private FloorSpriteComponent getFloorSpriteComponent( )
    {
        int[] tiles = TileArrangementLoader.load( COMPOSITION + EXTENSION );
        FloorSpriteComponent floorSpriteComponent = FloorSpriteComponent.from( NAME , WIDTH , HEIGHT );
        floorSpriteComponent.sprite( ).replaceTiles( tiles );
        return floorSpriteComponent;
    }

    @Override
    public GameComponent[] computeAndGet( )
    {
        List< GameComponent > components = new ArrayList<>( );
        components.add( getTranslationComponent( ) );
        components.add( getFloorSpriteComponent( ) );
        return components.toArray( new GameComponent[ 0 ] );
    }

}