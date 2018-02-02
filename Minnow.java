// Michelle and Elysia Period 3
// 31 January 2018
// Minnow.java
// A minnow breeds and dies

import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.awt.Color;
import java.util.ArrayList;

/* A Minnow is a Critter that breeds and dies 
   //If it has 5 or more actors in neighboring spaces, it will die from overcrowding
   //Each time it acts it has a 50% chance of breeding
   //When it breeds it fills every empty neighboring cell with a minnow
   //You should make private variables to hold those two numbers (5 and 50%), and a constructor that would allow
   //the driver to set them to different values */

public class Minnow extends Critter
{
    private boolean willDie;
    private int crowded;
    private double breedChance;
    
    //pre: none
    //post: constructs Minnow that's green and defaults to 5 for crowded and 0.5 for breedChance 
    public Minnow(){
        super();
        setColor(Color.GREEN);
        willDie = false;
        crowded = 5;
        breedChance = 0.5;
    }
    
    //pre: crowd is between 0 and 9 and breed is between 0 and 1.0 inclusive
    //post: constructs a Minnow that's green and uses crowd as crowded and breed as breedChance
    public Minnow(int crowd, double breed){
        super();
        setColor(Color.GREEN);
        willDie = false;
        crowded = crowd;
        breedChance = breed;
    }
    
    //pre: none
    //post: kills the Minnow if the amount of actors is more than the int crowded
    //continues to breed
    public void processActors(ArrayList<Actor> actors){
        if(actors.size() >= crowded)
            willDie = true;
        breed();
    }
    
    //pre: none
    //post: has a breeding chance of breedChance and fills empty adjacent locations with Minnows
    public void breed(){
        double random = Math.random();
        if(random <= breedChance){
            ArrayList<Location> empties = getGrid().getEmptyAdjacentLocations(getLocation());
            for(Location next : empties){
                Minnow baby = new Minnow();
                baby.putSelfInGrid(getGrid(),next);
            }
        }
    }
    
    //pre: none
    //post: kills the Minnow if willDie is true or it cannot move
    public void makeMove(Location loc){
        if(loc == null || willDie)
            removeSelfFromGrid();
        else
            moveTo(loc);
    }
}