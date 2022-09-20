package game.gameplay.collision;

import ecs_architecture.standard.GameComponent;
import ecs_architecture.standard.GameSystem;
import game.components.DynamicComponent;
import game.components.StaticComponent;
import utility_library.gameplay.kinetics.TranslationComponent;

import java.util.Map;

public class CollisionSystem extends GameSystem
{

    @Override
    protected void internalInitialize( )
    {

    }

    @Override
    protected void internalTerminate( )
    {

    }

    @Override
    protected void internalPerform( )
    {
        Map< String, GameComponent > staticObjects = get( StaticComponent.class );
        Map< String, GameComponent > dynamicObjects = get( DynamicComponent.class );
        Map< String, GameComponent > collisionObjects = get( CollisionComponent.class );
        Map< String, GameComponent > translationObjects = get( TranslationComponent.class );
        dynamicObjects.forEach( ( entity1 , component1 ) -> staticObjects.forEach( ( entity2 , component2 ) -> {
            if ( collisionObjects.containsKey( entity1 ) && collisionObjects.containsKey( entity2 ) && translationObjects.containsKey( entity1 ) && translationObjects.containsKey( entity2 ) )
            {

            }
        } ) );
    }

}