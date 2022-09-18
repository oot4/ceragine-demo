package game.gameplay.entitystate;

import ecs_architecture.standard.GameComponent;
import game.gameplay.input.KeyComponent;

import java.util.Map;

public interface EntityState
{

    void handleInput( Map< Class< ? extends GameComponent >, GameComponent > entityComponentMap , KeyComponent keyComponent );

    void update( Map< Class< ? extends GameComponent >, GameComponent > entityComponentMap );

}