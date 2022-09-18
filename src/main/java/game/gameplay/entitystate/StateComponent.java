package game.gameplay.entitystate;

import ecs_architecture.standard.GameComponent;

import java.util.Map;
import java.util.Optional;

public class StateComponent extends GameComponent
{

    private EntityState currentState;

    private final Map< String, EntityState > stateMap;

    private StateComponent( Map< String, EntityState > stateMap )
    {
        this.stateMap = stateMap;
    }

    public static StateComponent from( Map< String, EntityState > stateMap )
    {
        StateComponent stateComponent = new StateComponent( stateMap );
        Optional< String > state = stateMap.keySet( ).stream( ).findAny( );
        state.ifPresent( stateComponent::setCurrentState );
        return stateComponent;
    }

    public EntityState getCurrentState( )
    {
        return currentState;
    }

    public void setCurrentState( String stateName )
    {
        if ( stateMap.containsKey( stateName ) )
        {
            EntityState state = stateMap.get( stateName );
            if ( state != currentState )
            {
                this.currentState = state;
            }
        }
    }

}