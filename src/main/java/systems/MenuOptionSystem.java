package systems;

import ecs_architecture.standard.GameSystem;
import utility_library.gameplay.kinetics.TranslationComponent;
import utility_library.gameplay.text.TextComponent;

public class MenuOptionSystem extends GameSystem
{

    @Override
    protected void internalInitialize( )
    {
        add( TextComponent.from( "START" , "font" , 2 ) , TranslationComponent.from( 2 , 0 ) );
        add( TextComponent.from( "QUIT" , "font" , 2 ) , TranslationComponent.from( 12 , 32 ) );

    }

    @Override
    protected void internalTerminate( )
    {

    }

    @Override
    protected void internalPerform( )
    {

    }

}