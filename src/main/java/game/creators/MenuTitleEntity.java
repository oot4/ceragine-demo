package game.creators;

import ecs_architecture.standard.GameComponent;
import game.utilities.TileArrangementLoader;
import utility_library.gameplay.kinetics.TranslationComponent;
import utility_library.gameplay.rendering.core.UserInterfaceSpriteComponent;

import java.util.ArrayList;
import java.util.List;

public class MenuTitleEntity extends ComponentGroup
{

    private static final MenuTitleEntity MENU_TITLE_ENTITY = new MenuTitleEntity( );

    private static final int X = - 4;

    private static final int Y = - 48;

    private static final String NAME = "title";

    private static final String EXTENSION = ".json";

    private static final int WIDTH = 14;

    private static final int HEIGHT = 7;

    private MenuTitleEntity( ) { }

    public static GameComponent[] build( ) { return MENU_TITLE_ENTITY.computeAndGet( ); }

    private TranslationComponent getTranslationComponent( ) { return TranslationComponent.from( X , Y ); }

    private UserInterfaceSpriteComponent getUISpriteComponent( )
    {
        int[] tiles = TileArrangementLoader.load( NAME + EXTENSION );
        UserInterfaceSpriteComponent uiSpriteComponent = UserInterfaceSpriteComponent.from( NAME , WIDTH , HEIGHT );
        uiSpriteComponent.sprite( ).replaceTiles( tiles );
        return uiSpriteComponent;
    }

    @Override
    public GameComponent[] computeAndGet( )
    {
        List< GameComponent > components = new ArrayList<>( );
        components.add( getTranslationComponent( ) );
        components.add( getUISpriteComponent( ) );
        return components.toArray( new GameComponent[ 0 ] );
    }

}