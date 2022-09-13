package game.systems;

import ecs_architecture.standard.GameSystem;
import game.creators.MenuTitleBGEntity;
import game.creators.MenuTitleEntity;
import utility_library.gameplay.kinetics.TranslationComponent;
import utility_library.gameplay.text.TextComponent;

public class MenuOptionSystem extends GameSystem
{

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
        add( TextComponent.from( "START" , "font" , 1 ) , TranslationComponent.from( -4 , 0 ) );
        add( TextComponent.from( "QUIT" , "font" , 1 ) , TranslationComponent.from( 0 , 12 ) );
    }

    @Override
    protected void internalTerminate( ) { }

    @Override
    protected void internalPerform( ) { }

}