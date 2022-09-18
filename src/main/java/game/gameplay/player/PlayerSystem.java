package game.gameplay.player;

import ecs_architecture.standard.GameComponent;
import ecs_architecture.standard.GameSystem;
import game.components.PlayerComponent;
import game.creators.OldHunterEntity;
import game.gameplay.entitystate.StateComponent;
import game.gameplay.input.InputSystem;
import game.gameplay.input.KeyComponent;

import java.util.HashMap;
import java.util.Map;

public class PlayerSystem extends GameSystem
{

    private final Map< Class< ? extends GameComponent >, GameComponent > entityComponentMap = new HashMap<>( );

    @Override
    protected void internalInitialize( )
    {
        GameComponent[] oldHunter = OldHunterEntity.build( );
        for ( GameComponent gameComponent : oldHunter )
        {
            entityComponentMap.put( gameComponent.getClass( ) , gameComponent );
        }
        add( entityComponentMap.values( ).toArray( new GameComponent[ 0 ] ) );
    }

    @Override
    protected void internalTerminate( )
    {
    }

    @Override
    protected void internalPerform( )
    {
        var parameter = new Object( )
        {
            KeyComponent keyComponent;
        };
        Map< String, GameComponent > players = get( PlayerComponent.class );
        Map< String, GameComponent > keys = get( KeyComponent.class );
        keys.forEach( ( entity , component ) -> {
            if ( component.getName( ).equals( InputSystem.STANDARD_KEYS ) )
            {
                parameter.keyComponent = ( KeyComponent ) component;
            }
        } );
        players.forEach( ( entity , component ) -> {
            if ( component.getName( ).equals( OldHunterEntity.NAME ) )
            {
                StateComponent stateComponent = ( StateComponent ) get( StateComponent.class ).get( entity );
                if ( stateComponent != null && stateComponent.getCurrentState( ) != null && parameter.keyComponent != null )
                {
                    stateComponent.getCurrentState( ).handleInput( entityComponentMap , parameter.keyComponent );
                    stateComponent.getCurrentState( ).update( entityComponentMap );
                }
            }
        } );

    }

}