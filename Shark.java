// Elysia and Michelle Period 3
// 31 January 2018
// Shark.java
// A Shark ages, eats, breeds, and dies

import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.awt.Color;
import java.util.ArrayList;

/* A Shark is a Critter that:
 * //ages 1 year each time it acts
 * //eats one random adjacent bug or flower if available
 * //breeds if it is of breeding age and there is a neighboring shark of breeding age
 *    //to breed means to fill all empty adjacent locations with a shark of age 0
 *    //a shark of breeding age is yellow.  A shark of non-breeding age is red
 * //moves to a random empty adjacent location like a Critter
 * //dies and leaves behind a flower in its location if:
 *    //it goes 5 years without eating
 *    //it cannot move
 *    //if it is too old
 * //breeding age: 6 to 10 inclusive
 * //old age: For each year past 10, the shark has an additional 10% chance of dying of old age
 *          (10% chance at 11, 20% at 12, etc.)
 */

public class Shark extends Critter
{
    private int age;
    private int yearsSinceFood;
    private boolean willDie;

    //pre: none
    //post: constructs a Shark that is red
    public Shark(){
        super();
        age = 0;
        yearsSinceFood = 0;
        setColor(Color.RED);
        willDie = false;
    }

    //pre: none
    //post: goes to munchies() and makeBabies() if eating and breeding are possible
    public void processActors(ArrayList<Actor> actors){
        if(actors.size() == 0){
            yearsSinceFood ++; 
            return;
        }
        boolean canBreed = false;
        ArrayList<Actor> canEat = new ArrayList<Actor>();
        for(Actor next : actors)
            if(next instanceof Shark)
                canBreed = checkBreed(next);
            else if(next instanceof Bug || next instanceof Flower || next instanceof Minnow)
                canEat.add(next);
        munchies(canEat);
        makeBabies(canBreed);
    }
    
    //pre: next is a Shark
    //post: returns a boolean that says whether or not it can breed
    public boolean checkBreed(Actor next){
        boolean canBreed = false;
        Shark shark = (Shark)next;
        if(shark.age >= 6 && shark.age <= 10)
            canBreed = true;
        return canBreed;
    }
    
    //pre: canEat is an ArrayList of Bugs, Flowers, and Minnows
    //post: removes a random neighbor from the grid if it can breed and modifies
    //yearsSinceFood accordingly
    public void munchies(ArrayList<Actor> canEat){
        if(canEat.size() != 0){  
            int random = (int)Math.random()*(canEat.size());
            Actor selected = canEat.get(random);
            selected.removeSelfFromGrid();
            yearsSinceFood = 0;
        }
        else
            yearsSinceFood ++;
    }
    
    //pre: none
    //post: puts new Sharks in the empty adjacent locations if it canBreed
    public void makeBabies(boolean canBreed){
        if(canBreed){
            ArrayList<Location> empties = getGrid().getEmptyAdjacentLocations(getLocation());
            for(Location next : empties){
                Shark baby = new Shark();
                baby.putSelfInGrid(getGrid(),next);
            }
        }
    }

    //pre: none
    //post: adds one to age each time and changes color if it is of breeding age
    //kills the Shark of old age
    public void age(){
        age ++;
        if(age == 6)
            setColor(Color.YELLOW);
        else if(age == 11)
            setColor(Color.RED);
        int random = (int)(Math.random()*10 + 1);
        if(age > 10){
            int ageTest = age % 10;
            if(random <= ageTest || ageTest == 0)
                willDie = true;
        }    
    }

    //pre: none
    //post: puts flowers in the place of a dead Shark and moves to new locations
    public void makeMove(Location loc){
        age();
        //System.out.println(yearsSinceFood);
        if(loc == null || yearsSinceFood == 5 || loc.equals(getLocation()) || willDie){
            Location save = this.getLocation();
            Flower place = new Flower();
            place.putSelfInGrid(getGrid(), save);
        }
        else
            moveTo(loc);
    }
}
