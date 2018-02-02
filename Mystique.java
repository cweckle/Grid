// Elysia Smyers Period 3
// 25 January 2018
// Mystique - extends Critter

import java.util.*;
import java.awt.*;
import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class Mystique extends Critter{
    //pre: none
    //post: constructs a Mystique object that is red
    public Mystique(){
        super();
        setColor(Color.RED);
    }
    
    //pre: actors is an ArrayList of adjacent actors
    //post: changes the color of Mystique to match a random adjacent actor
    public void processActors(ArrayList<Actor> actors){
        if(actors.size() == 0)
            return;
        int random = (int)(Math.random()*actors.size());
        Color newCol = actors.get(random).getColor();
        this.setColor(newCol);
    }
    
    //pre: loc is an adjacent location
    //post: turns Mystique 45 degrees and moves it to an adjacent location
    public void makeMove(Location loc){
        if(loc == null)
            removeSelfFromGrid();
        else{
            this.setDirection(this.getDirection() + 45);
            moveTo(loc);
        }
    }
}