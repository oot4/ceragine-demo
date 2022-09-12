package creators;

import ecs_architecture.standard.GameComponent;
import loaders.TileCompositionLoader;
import utility_library.algorithms.api.ReturningComputable;
import utility_library.gameplay.kinetics.TranslationComponent;
import utility_library.gameplay.rendering.core.UserInterfaceSpriteComponent;

import java.util.ArrayList;
import java.util.List;

public final class MenuTitleEntity implements ReturningComputable< GameComponent[] >
{

    private static final MenuTitleEntity MENU_TITLE_ENTITY = new MenuTitleEntity( );

    private MenuTitleEntity( ) { }

    public static GameComponent[] build( )
    {
        return MENU_TITLE_ENTITY.computeAndGet( );
    }

    private TranslationComponent getTranslationComponent( )
    {
        TranslationComponent translationComponent = TranslationComponent.from( "title" );
        translationComponent.setLocation( -12 , -128 );
        return translationComponent;
    }

    private UserInterfaceSpriteComponent getUISpriteComponent( )
    {
        int[] tiles = TileCompositionLoader.load( "title.json" );
        UserInterfaceSpriteComponent uiSpriteComponent = UserInterfaceSpriteComponent.from( "title" , 14 , 5 );
        uiSpriteComponent.sprite( ).replaceTiles( tiles );
        uiSpriteComponent.sprite().setScale( 3 );
        return uiSpriteComponent;
    }

    @Override
    public GameComponent[] computeAndGet( )
    {
        List< GameComponent > gameComponents = new ArrayList<>( );
        gameComponents.add( getTranslationComponent( ) );
        gameComponents.add( getUISpriteComponent( ) );
        return gameComponents.toArray( new GameComponent[ 0 ] );
    }

}