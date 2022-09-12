package creators;

import ecs_architecture.standard.GameComponent;
import loaders.TileCompositionLoader;
import utility_library.algorithms.api.ReturningComputable;
import utility_library.gameplay.kinetics.TranslationComponent;
import utility_library.gameplay.rendering.core.UserInterfaceSpriteComponent;

import java.util.ArrayList;
import java.util.List;

public final class CopyrightLogoEntity implements ReturningComputable< GameComponent[] >
{

    private static final CopyrightLogoEntity COPYRIGHT_LOGO_ENTITY = new CopyrightLogoEntity( );

    private CopyrightLogoEntity( )
    {
    }

    public static GameComponent[] build( )
    {
        return COPYRIGHT_LOGO_ENTITY.computeAndGet( );
    }

    private TranslationComponent getTranslationComponent( )
    {
        return TranslationComponent.from( 0 , 0 );
    }

    private UserInterfaceSpriteComponent getUISpriteComponent( )
    {
        int[] tiles = TileCompositionLoader.load( "copyright-logo.json" );
        UserInterfaceSpriteComponent uiSpriteComponent = UserInterfaceSpriteComponent.from( "copyright-logo" , 12 , 5 );
        uiSpriteComponent.sprite( ).replaceTiles( tiles );
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