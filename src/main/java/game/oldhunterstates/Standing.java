package game.oldhunterstates;

import ecs_architecture.standard.GameComponent;
import game.components.DirectionComponent;
import game.creators.OldHunterEntity;
import game.gameplay.entitystate.EntityState;
import game.gameplay.entitystate.StateComponent;
import game.gameplay.input.KeyComponent;
import utility_library.gameplay.animation.AnimationComponent;

import java.util.Map;

public class Standing implements EntityState
{

    @Override
    public void onEnter( Map< Class< ? extends GameComponent >, GameComponent > entityComponentMap )
    {
        DirectionComponent directionComponent = ( DirectionComponent ) entityComponentMap.get( DirectionComponent.class );
        AnimationComponent animationComponent = ( AnimationComponent ) entityComponentMap.get( AnimationComponent.class );
        switch ( directionComponent.getCurrent( ) )
        {
            case NORTH:
                animationComponent.setCurrentAnimation( OldHunterEntity.STANDING_BACKWARD );
                break;
            case EAST:
                animationComponent.setCurrentAnimation( OldHunterEntity.STANDING_RIGHT );
                break;
            case WEST:
                animationComponent.setCurrentAnimation( OldHunterEntity.STANDING_LEFT );
                break;
            default:
                animationComponent.setCurrentAnimation( OldHunterEntity.STANDING_FORWARD );
                break;
        }
    }

    @Override
    public void updateState( Map< Class< ? extends GameComponent >, GameComponent > entityComponentMap , KeyComponent keyComponent )
    {
        StateComponent stateComponent = ( StateComponent ) entityComponentMap.get( StateComponent.class );
        DirectionComponent directionComponent = ( DirectionComponent ) entityComponentMap.get( DirectionComponent.class );
        if ( keyComponent.isDown( KeyComponent.LEFT ) )
        {
            directionComponent.setWest( );
            stateComponent.setCurrentState( OldHunterEntity.WALKING );
        }
        else if ( keyComponent.isDown( KeyComponent.RIGHT ) )
        {
            directionComponent.setEast( );
            stateComponent.setCurrentState( OldHunterEntity.WALKING );
        }
        else if ( keyComponent.isDown( KeyComponent.UP ) )
        {
            directionComponent.setNorth( );
            stateComponent.setCurrentState( OldHunterEntity.WALKING );
        }
        else if ( keyComponent.isDown( KeyComponent.DOWN ) )
        {
            directionComponent.setSouth( );
            stateComponent.setCurrentState( OldHunterEntity.WALKING );
        }
    }

}