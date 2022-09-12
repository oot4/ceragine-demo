package creators;

import ecs_architecture.standard.GameComponent;
import loaders.TileCompositionLoader;
import utility_library.algorithms.api.ReturningComputable;
import utility_library.gameplay.effects.FadeComponent;
import utility_library.gameplay.kinetics.TranslationComponent;
import utility_library.gameplay.rendering.core.UserInterfaceSpriteComponent;

import java.util.ArrayList;
import java.util.List;

public final class CopyrightLogoEntity implements ReturningComputable< GameComponent[] >
{

    private static final CopyrightLogoEntity COPYRIGHT_LOGO_ENTITY = new CopyrightLogoEntity( );

    private CopyrightLogoEntity( ) { }

    public static GameComponent[] build( )
    {
        return COPYRIGHT_LOGO_ENTITY.computeAndGet( );
    }

    private TranslationComponent getTranslationComponent( )
    {
        TranslationComponent translationComponent = TranslationComponent.from( "copyright-logo" );
        translationComponent.setLocation( 0 , 0 );
        return translationComponent;
    }

    private FadeComponent getFadeComponent( )
    {
        FadeComponent fadeComponent = FadeComponent.from( 0 , 255 , false , false );
        fadeComponent.setAllTimers( 180 , 60 , 240 , 60 );
        return fadeComponent;
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
        gameComponents.add( CopyRightLogoTag.COPY_RIGHT_LOGO_TAG );
        gameComponents.add( getTranslationComponent( ) );
        gameComponents.add( getFadeComponent( ) );
        gameComponents.add( getUISpriteComponent( ) );
        return gameComponents.toArray( new GameComponent[ 0 ] );
    }

    public static class CopyRightLogoTag extends GameComponent
    {

        private final static CopyRightLogoTag COPY_RIGHT_LOGO_TAG = new CopyRightLogoTag( "copyright-logo" );

        private CopyRightLogoTag( String name )
        {
            super( name );
        }

    }

}