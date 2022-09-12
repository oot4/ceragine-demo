package creators;

import ecs_architecture.standard.GameComponent;
import utility_library.algorithms.api.ReturningComputable;

public final class CursorEntity implements ReturningComputable< GameComponent[] >
{

    private static final CursorEntity CURSOR_ENTITY = new CursorEntity( );

    private CursorEntity( ) { }

    public static GameComponent[] build( )
    {
        return CURSOR_ENTITY.computeAndGet( );
    }

    @Override
    public GameComponent[] computeAndGet( )
    {
        return new GameComponent[ 0 ];
    }

}