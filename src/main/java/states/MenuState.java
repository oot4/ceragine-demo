package states;

import creators.MenuFloorEntity;
import creators.MenuTitleEntity;
import state_machine.api.StateChanger;
import state_machine.standard.GameState;
import systems.MenuOptionSystem;

public class MenuState extends GameState
{

    protected MenuState( StateChanger stateChanger )
    {
        super( stateChanger );
    }

    @Override
    protected void internalInitialize( )
    {
        add( MenuFloorEntity.build( ) );
        add( MenuTitleEntity.build( ) );
        add( new MenuOptionSystem( ) );
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