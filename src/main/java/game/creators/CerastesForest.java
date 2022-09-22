package game.creators;

import ecs_architecture.standard.GameComponent;
import game.components.StaticComponent;
import game.gameplay.collision.CollisionComponent;
import game.utilities.TileArrangementLoader;
import utility_library.gameplay.kinetics.TranslationComponent;
import utility_library.gameplay.rendering.core.CharacterSpriteComponent;
import utility_library.gameplay.rendering.core.FloorSpriteComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CerastesForest extends ComponentGroup
{

    private static final String EXTENSION = ".json";

    private static final String NAME = "cerastes-forest";

    private static final int HEIGHT = 2;

    private static final int WIDTH = 2;

    private final int x;

    private final int y;

    private final TileType type;

    public static final Map< Integer, TileType > tileTypeMap;

    public enum TileType
    {
        TREE, TREE_LS, TREE_RS, ROCK, GRASS, WATER, WATER_BANK_TL, WATER_BANK_TR, WATER_BANK_BL, WATER_BANK_BR, WATER_BANK_L, WATER_BANK_R, WATER_BANK_T, WATER_BANK_B
    }

    static
    {
        tileTypeMap = new HashMap<>( );
        tileTypeMap.put( 0 , TileType.GRASS );
        tileTypeMap.put( 1 , TileType.WATER );
        tileTypeMap.put( 2 , TileType.WATER_BANK_TL );
        tileTypeMap.put( 3 , TileType.WATER_BANK_TR );
        tileTypeMap.put( 4 , TileType.WATER_BANK_L );
        tileTypeMap.put( 5 , TileType.WATER_BANK_R );
        tileTypeMap.put( 6 , TileType.WATER_BANK_BL );
        tileTypeMap.put( 7 , TileType.WATER_BANK_BR );
        tileTypeMap.put( 8 , TileType.WATER_BANK_T );
        tileTypeMap.put( 9 , TileType.WATER_BANK_B );
    }

    private CerastesForest( TileType type , int x , int y )
    {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public static GameComponent[] build( TileType type , int x , int y )
    {
        CerastesForest cerastesForest = new CerastesForest( type , x , y );
        return cerastesForest.computeAndGet( );
    }

    private TranslationComponent getTranslationComponent( )
    {
        return TranslationComponent.from( x , y );
    }

    private FloorSpriteComponent getGrass( )
    {
        return getFloorSpriteComponent( 0 );
    }

    private FloorSpriteComponent getWater( )
    {
        return getFloorSpriteComponent( 1 );
    }

    private FloorSpriteComponent getWaterTL( )
    {
        return getFloorSpriteComponent( 2 );
    }

    private FloorSpriteComponent getWaterTR( )
    {
        return getMirroredFloorSpriteComponent( 3 , new int[]{
                0 , 1 ,
                0 , 0
        } );
    }

    private FloorSpriteComponent getWaterL( )
    {
        return getFloorSpriteComponent( 4 );
    }

    private FloorSpriteComponent getWaterR( )
    {
        return getMirroredFloorSpriteComponent( 5 , new int[]{
                0 , 1 ,
                0 , 1
        } );
    }

    private FloorSpriteComponent getWaterBL( )
    {
        return getFloorSpriteComponent( 6 );
    }

    private FloorSpriteComponent getWaterBR( )
    {
        return getMirroredFloorSpriteComponent( 7 , new int[]{
                0 , 0 ,
                0 , 1
        } );
    }

    private FloorSpriteComponent getWaterT( )
    {
        return getMirroredFloorSpriteComponent( 8 , new int[]{
                2 , 0 ,
                0 , 0
        } );
    }

    private FloorSpriteComponent getWaterB( )
    {
        return getMirroredFloorSpriteComponent( 9 , new int[]{
                0 , 0 ,
                2 , 0
        } );
    }

    private void getRockComponentList( List< GameComponent > components )
    {
        CollisionComponent collisionComponent = CollisionComponent.from( 16 , 16 );
        components.add( new StaticComponent( ) );
        components.add( collisionComponent );
        components.add( getRock( ) );
    }

    private CharacterSpriteComponent getRock( )
    {
        FloorSpriteComponent floorSpriteComponent = getFloorSpriteComponent( 10 );
        CharacterSpriteComponent characterSpriteComponent = CharacterSpriteComponent.from( NAME , WIDTH , HEIGHT );
        characterSpriteComponent.sprite( ).replaceTiles( floorSpriteComponent.sprite( ).tiles( ) );
        return characterSpriteComponent;
    }

    private FloorSpriteComponent getFloorSpriteComponent( int index )
    {
        int[][] tiles = TileArrangementLoader.loadMap( NAME + EXTENSION );
        FloorSpriteComponent floorSpriteComponent = FloorSpriteComponent.from( NAME , WIDTH , HEIGHT );
        floorSpriteComponent.sprite( ).replaceTiles( tiles[ index ] );
        return floorSpriteComponent;
    }

    private FloorSpriteComponent getMirroredFloorSpriteComponent( int index , int[] mirrored )
    {
        FloorSpriteComponent floorSpriteComponent = getFloorSpriteComponent( index );
        floorSpriteComponent.sprite( ).replaceMirrored( mirrored );
        return floorSpriteComponent;
    }

    @Override
    public GameComponent[] computeAndGet( )
    {
        List< GameComponent > components = new ArrayList<>( );
        components.add( getTranslationComponent( ) );
        if ( type != null )
        {
            switch ( type )
            {
                case GRASS:
                    components.add( getGrass( ) );
                    break;
                case WATER:
                    components.add( getWater( ) );
                    break;
                case WATER_BANK_TL:
                    components.add( getWaterTL( ) );
                    break;
                case WATER_BANK_TR:
                    components.add( getWaterTR( ) );
                    break;
                case WATER_BANK_L:
                    components.add( getWaterL( ) );
                    break;
                case WATER_BANK_R:
                    components.add( getWaterR( ) );
                    break;
                case WATER_BANK_BL:
                    components.add( getWaterBL( ) );
                    break;
                case WATER_BANK_BR:
                    components.add( getWaterBR( ) );
                    break;
                case WATER_BANK_T:
                    components.add( getWaterT( ) );
                    break;
                case WATER_BANK_B:
                    components.add( getWaterB( ) );
                    break;
                case ROCK:
                    getRockComponentList( components );
                    break;
                default:
                    break;
            }
        }
        return components.toArray( new GameComponent[ 0 ] );
    }

}