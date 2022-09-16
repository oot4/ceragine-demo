package game.components;

import ecs_architecture.standard.GameComponent;

public class TileComponent extends GameComponent
{

    private final String map;

    public TileComponent( String name , String map )
    {
        super( name + map );
        this.map = map;
    }

    public String getMap( ) { return map; }

}