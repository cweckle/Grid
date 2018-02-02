// Elysia Smyers Period 3
// 25 January 2018
// Magneto - extends Critter

import java.util.*;
import java.awt.*;
import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class Magneto extends Critter{
    //pre: none
    //post: constructs a Magneto object that is yellow
    public Magneto(){
        super();
        setColor(Color.YELLOW);
    }

    //pre: none
    //post: returns an ArrayList of Actors that only contains Rocks
    public ArrayList<Actor> getActors(){
        ArrayList<Location> occupied = getGrid().getOccupiedLocations();
        ArrayList<Actor> rocks = new ArrayList<Actor>();
        for(int i = 0; i < occupied.size(); i ++){
            Actor here = getGrid().get(occupied.get(i));
            if(here instanceof Rock)
                rocks.add(here);
        }
        return rocks;
    }

    //pre: the ArrayList of actors only contains Rock objects
    //post: moves all the Rock objects in actors one square closer to Magneto
    public void processActors(ArrayList<Actor> actors){
        for(Actor rock : actors){
            Location ofRock = rock.getLocation();
            Location ofMagneto = this.getLocation();
            int direction = ofRock.getDirectionToward(ofMagneto);
            Location locMove = ofRock.getAdjacentLocation(direction);
            ArrayList<Location> occupied = getGrid().getOccupiedLocations();
            boolean isOccupied = false;
            for(Location check : occupied){
                if(locMove.equals(check))
                    isOccupied = true;
            }
            if(isOccupied == false)
                rock.moveTo(locMove);
        }
    }

    //pre: loc is an adjacent location
    //post: moves Magneto to a new location or removes Magneto from the grid if Magneto
    //can't move
    public void makeMove(Location loc){
        if(loc == null || loc.equals(getLocation()))
            this.removeSelfFromGrid();
        else
            moveTo(loc);
    }
}