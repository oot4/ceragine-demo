package game.creators;

import ecs_architecture.standard.GameComponent;
import game.utilities.TileArrangementLoader;
import utility_library.gameplay.kinetics.TranslationComponent;
import utility_library.gameplay.rendering.core.FloorSpriteComponent;

import java.util.ArrayList;
import java.util.List;

public class GrassTileEntity extends ComponentGroup
{

    private static final String NAME = "grass";

    private static final String JSON = ".json";

    private static final int WIDTH = 2;

    private static final int HEIGHT = 2;

    private final int x;

    private final int y;

    private GrassTileEntity( int x , int y )
    {
        this.x = x;
        this.y = y;
    }

    public static GameComponent[] build( int x , int y )
    {
        GrassTileEntity grassTileEntity = new GrassTileEntity( x , y );
        return grassTileEntity.computeAndGet( );
    }

    private TranslationComponent getTranslationComponent( )
    {
        return TranslationComponent.from( x , y );
    }

    private FloorSpriteComponent getFloorSpriteComponent( )
    {
        int[] tiles = TileArrangementLoader.load( NAME + JSON );
        FloorSpriteComponent floorSpriteComponent = FloorSpriteComponent.from( "cerastes-forest" , WIDTH , HEIGHT );
        floorSpriteComponent.sprite( ).replaceTiles( tiles );
        return floorSpriteComponent;
    }

    @Override
    public GameComponent[] computeAndGet( )
    {
        List< GameComponent > components = new ArrayList<>( );
        components.add( getTranslationComponent( ) );
        components.add( getFloorSpriteComponent( ) );
        return components.toArray( new GameComponent[ y ] );
    }

}