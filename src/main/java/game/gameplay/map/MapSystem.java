package game.gameplay.map;

import ecs_architecture.standard.GameComponent;
import ecs_architecture.standard.GameSystem;
import game.creators.CerastesForest;
import game.utilities.TileArrangementLoader;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MapSystem extends GameSystem
{

    private static final int MAP_WIDTH = 40;

    private static final int TILE_SIZE = 16;

    private static final int ROCK_INDEX = 21;

    private static final Set< Integer > TRIGGER_TILES = ConcurrentHashMap.newKeySet( );

    @Override
    protected void internalInitialize( )
    {
        TRIGGER_TILES.addAll( List.of( 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 ) );
        //Add FloorTiles
        int[] map = TileArrangementLoader.load( "cerastes-forest-map.json" );
        for ( int i = 0; i < MAP_WIDTH * MAP_WIDTH; i++ )
        {
            int tileIndex = map[ i ];
            if ( tileIndex == ROCK_INDEX )
            {
                tileIndex = 0;
            }
            int x = ( i % MAP_WIDTH ) * TILE_SIZE;
            int y = ( i / MAP_WIDTH ) * TILE_SIZE;
            CerastesForest.TileType type = CerastesForest.tileTypeMap.get( tileIndex );
            GameComponent[] gameComponents;
            if ( TRIGGER_TILES.contains( tileIndex ) )
            {
                gameComponents = CerastesForest.build( type , x , y , true );
            }
            else
            {
                gameComponents = CerastesForest.build( type , x , y , false );
            }
            add( gameComponents );
        }
        for ( int i = 0; i < MAP_WIDTH * MAP_WIDTH; i++ )
        {
            if ( map[ i ] == ROCK_INDEX )
            {
                int x = ( i % MAP_WIDTH ) * TILE_SIZE;
                int y = ( i / MAP_WIDTH ) * TILE_SIZE;
                add( CerastesForest.build( CerastesForest.TileType.ROCK , x , y , false ) );
            }
        }
    }

    @Override
    protected void internalTerminate( )
    {
        //Nothing to terminate
    }

    @Override
    protected void internalPerform( )
    {
        //Nothing to perform
    }

}