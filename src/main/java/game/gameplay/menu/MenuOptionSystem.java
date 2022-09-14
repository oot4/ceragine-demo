package game.gameplay.menu;

import ecs_architecture.standard.GameComponent;
import ecs_architecture.standard.GameSystem;
import game.creators.MenuTitleBGEntity;
import game.creators.MenuTitleEntity;
import game.gameplay.input.InputSystem;
import game.gameplay.input.KeyComponent;
import utility_library.gameplay.effects.FadeComponent;
import utility_library.gameplay.kinetics.TranslationComponent;
import utility_library.gameplay.rendering.api.SpriteContainer;
import utility_library.gameplay.rendering.core.UserInterfaceSpriteComponent;
import utility_library.gameplay.text.TextComponent;

import java.util.Map;

public class MenuOptionSystem extends GameSystem
{

    private static final String START = "START";

    private static final String QUIT = "QUIT";

    private static final int OPTION_SIZE = 2;

    private int currentOption = 0;

    private void addTile( int x , int y )
    {
        MenuTitleBGEntity.setPosition( x , y );
        add( MenuTitleBGEntity.build( ) );
    }

    private void addRow( int y )
    {
        for ( int x = 0; x < 6; x++ )
        {
            addTile( x , y );
        }
    }

    private void addBgTiles( )
    {
        for ( int y = 0; y < 7; y++ )
        {
            addRow( y );
        }
    }

    @Override
    protected void internalInitialize( )
    {
        addBgTiles( );
        add( MenuTitleEntity.build( ) );
        add( new OptionTag( START ) , TextComponent.from( START , "font" , 1 ) , TranslationComponent.from( - 4 , 0 ) );
        add( new OptionTag( QUIT ) , TextComponent.from( QUIT , "font" , 1 ) , TranslationComponent.from( 0 , 12 ) );
    }

    @Override
    protected void internalTerminate( ) { }

    private void handleUpInput( KeyComponent keys )
    {
        if ( keys.isPressed( KeyComponent.UP ) && currentOption > 0 )
        {
            currentOption--;
            System.out.println( currentOption );
        }
    }

    private void handleDownInput( KeyComponent keys )
    {
        if ( keys.isPressed( KeyComponent.DOWN ) && currentOption < OPTION_SIZE - 1 )
        {
            currentOption++;
            System.out.println( currentOption );
        }
    }

    private void handleInput( )
    {
        Map< String, GameComponent > inputGrabbers = get( KeyComponent.class );
        inputGrabbers.forEach( ( entity , component ) -> {
            if ( component.getName( ).equals( InputSystem.STANDARD_KEYS ) )
            {
                handleDownInput( ( KeyComponent ) component );
                handleUpInput( ( KeyComponent ) component );
            }
        } );
    }

    private void handleOptions( )
    {
        Map< String, GameComponent > options = get( OptionTag.class );
        Map< String, GameComponent > fadeEffects = get( FadeComponent.class );
        Map< String, GameComponent > uiSprites = get( UserInterfaceSpriteComponent.class );
        options.forEach( ( entity , component ) -> {

        } );

    }


    @Override
    protected void internalPerform( )
    {
        handleInput( );
        handleOptions( );
    }

}