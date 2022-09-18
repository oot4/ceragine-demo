package game.gameplay.entitystate;

import ecs_architecture.standard.GameComponent;
import game.gameplay.input.KeyComponent;

import java.util.Map;

public interface EntityState
{

    void onEnter( Map< Class< ? extends GameComponent >, GameComponent > entityComponentMap );

    void updateState( Map< Class< ? extends GameComponent >, GameComponent > entityComponentMap , KeyComponent keyComponent );

}