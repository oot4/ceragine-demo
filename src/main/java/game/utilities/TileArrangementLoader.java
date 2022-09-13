package game.utilities;

import caching.core.InMemoryCache;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import game_debugger.Alert;
import game_debugger.GameDebugger;
import resource_manager.loader.ResourceIO;
import utility_library.algorithms.api.ReturningParameterizedComputable;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class TileArrangementLoader implements ReturningParameterizedComputable< String, int[] >
{

    private static final String DIRECTORY = "gfx/compositions/";

    private static final InMemoryCache< String, int[] > CACHE = InMemoryCache.of( 32 );

    private static final TileArrangementLoader TILE_ARRANGEMENT_LOADER = new TileArrangementLoader( );

    private TileArrangementLoader( ) { }

    public static int[] load( String filename )
    {
        if ( CACHE.contains( filename ) )
        {
            Optional< int[] > optional = CACHE.get( filename );
            if ( optional.isPresent( ) )
            {
                return optional.get( );
            }
        }
        int[] tiles = TILE_ARRANGEMENT_LOADER.computeAndGet( DIRECTORY + filename );
        CACHE.put( filename , tiles );
        return tiles;
    }

    @Override
    public int[] computeAndGet( String filename )
    {
        String data = ResourceIO.read( filename , StandardCharsets.UTF_8 );
        try
        {
            return new ObjectMapper( ).readValue( data , int[].class );
        }
        catch ( JsonProcessingException e )
        {
            GameDebugger.log( Alert.DIRE , "Failed to read tile composition: " + data , e );
        }
        return new int[ 0 ];
    }

}