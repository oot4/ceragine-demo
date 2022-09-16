package game.creators;

import ecs_architecture.standard.GameComponent;
import utility_library.algorithms.api.ReturningComputable;

public class ComponentGroup implements ReturningComputable< GameComponent[] >
{

    @Override
    public GameComponent[] computeAndGet( )
    {
        return new GameComponent[ 0 ];
    }

}