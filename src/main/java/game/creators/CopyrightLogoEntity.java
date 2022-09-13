package game.creators;

import ecs_architecture.standard.GameComponent;
import game.components.CopyrightLogoTag;
import game.utilities.TileArrangementLoader;
import utility_library.algorithms.api.ReturningComputable;
import utility_library.gameplay.effects.FadeComponent;
import utility_library.gameplay.kinetics.TranslationComponent;
import utility_library.gameplay.rendering.core.UserInterfaceSpriteComponent;

import java.util.ArrayList;
import java.util.List;

public class CopyrightLogoEntity implements ReturningComputable< GameComponent[] >
{

    private static final int X = 0;

    private static final int Y = 0;

    private static final int WIDTH = 12;

    private static final int HEIGHT = 5;

    private static final String NAME = "copyright-logo";

    private static final String EXTENSION = ".json";

    private static final int MINIMUM = 0;

    private static final int MAXIMUM = 255;

    private static final boolean LOOP = false;

    private static final boolean REVERSE = false;

    private static final int START_DELAY_TIMER = 120;

    private static final int START_TRANSITION_TIMER = 60;

    private static final int STOP_DELAY_TIMER = 140;

    private static final int STOP_TRANSITION_TIMER = 80;

    private static final CopyrightLogoEntity COPYRIGHT_LOGO_ENTITY = new CopyrightLogoEntity( );

    private CopyrightLogoEntity( ) { }

    public static GameComponent[] build( ) { return COPYRIGHT_LOGO_ENTITY.computeAndGet( ); }

    private TranslationComponent getTranslationComponent( )
    {
        return TranslationComponent.from( X , Y );
    }

    private UserInterfaceSpriteComponent getUISpriteComponent( )
    {
        int[] tiles = TileArrangementLoader.load( NAME + EXTENSION );
        UserInterfaceSpriteComponent component = UserInterfaceSpriteComponent.from( NAME , WIDTH , HEIGHT );
        component.sprite( ).replaceTiles( tiles );
        return component;
    }

    private FadeComponent getFadeComponent( )
    {
        FadeComponent fadeComponent = FadeComponent.from( MINIMUM , MAXIMUM , LOOP , REVERSE );
        fadeComponent.setAllTimers( START_DELAY_TIMER , START_TRANSITION_TIMER , STOP_DELAY_TIMER , STOP_TRANSITION_TIMER );
        return fadeComponent;
    }

    @Override
    public GameComponent[] computeAndGet( )
    {
        List< GameComponent > components = new ArrayList<>( );
        components.add( new CopyrightLogoTag( NAME ) );
        components.add( getTranslationComponent( ) );
        components.add( getUISpriteComponent( ) );
        components.add( getFadeComponent( ) );
        return components.toArray( new GameComponent[ 0 ] );
    }

}