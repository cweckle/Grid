/* SharkRunner for Sharks and Minnows (and a few Bugs and Rocks
 * Mike Bollhorst 1/24/18 
 */

import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.awt.Color;

public class SharkRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld(new BoundedGrid<Actor>(10,10));
        world.add(new Bug());
        world.add(new Bug());
        for(int i = 1; i < 7; i ++)
            world.add(new Minnow());
        for (int i = 1; i < 6; i++)
            world.add(new Shark());    
        world.show();
    }
}