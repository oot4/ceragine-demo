package game.gameplay.map;

import ecs_architecture.standard.GameComponent;
import ecs_architecture.standard.GameSystem;
import game.creators.CerastesForest;
import game.utilities.TileArrangementLoader;

public class MapSystem extends GameSystem
{

    private static final int MAP_WIDTH = 40;

    private static final int TILE_SIZE = 17;

    private static final int ROCK_INDEX = 21;

    @Override
    protected void internalInitialize( )
    {
        //Add FloorTiles
        int[] map = TileArrangementLoader.load( "cerastes-forest-map.json" );
        for ( int i = 0; i < MAP_WIDTH * MAP_WIDTH; i++ )
        {
            int tileIndex = map[ i ];
            if ( tileIndex == 21 )
            {
                tileIndex = 0;
            }
            int x = ( i % MAP_WIDTH ) * TILE_SIZE;
            int y = ( i / MAP_WIDTH ) * TILE_SIZE;
            CerastesForest.TileType type = CerastesForest.tileTypeMap.get( tileIndex );
            GameComponent[] gameComponents = CerastesForest.build( type , x , y );
            add( gameComponents );
        }
        for ( int i = 0; i < MAP_WIDTH * MAP_WIDTH; i++ )
        {
            if ( map[ i ] == ROCK_INDEX )
            {
                int x = ( i % MAP_WIDTH ) * TILE_SIZE;
                int y = ( i / MAP_WIDTH ) * TILE_SIZE;
                add( CerastesForest.build( CerastesForest.TileType.ROCK , x , y ) );
            }
        }
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