package loaders;

import caching.core.InMemoryCache;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import game_debugger.Alert;
import game_debugger.GameDebugger;
import resource_manager.loader.ResourceIO;
import utility_library.algorithms.api.ReturningParameterizedComputable;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

public final class TileCompositionLoader implements ReturningParameterizedComputable< String, int[] >
{

    private static final String DIRECTORY = "gfx/compositions/";

    private final InMemoryCache< String, int[] > cache = InMemoryCache.of( 32 );

    private static final TileCompositionLoader TILE_COMPOSITION_LOADER = new TileCompositionLoader( );

    private TileCompositionLoader( )
    {
    }

    public static int[] load( String filename )
    {
        if ( TILE_COMPOSITION_LOADER.cache.contains( filename ) )
        {
            Optional< int[] > optional = TILE_COMPOSITION_LOADER.cache.get( filename );
            if ( optional.isPresent( ) )
            {
                return optional.get( );
            }
        }
        int[] tiles = TILE_COMPOSITION_LOADER.computeAndGet( DIRECTORY + filename );
        TILE_COMPOSITION_LOADER.cache.put( filename , tiles );
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