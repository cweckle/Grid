// Elysia Smyers Period 3
// 25 January 2018
// Nightcrawler - extends Critter

import java.util.*;
import java.awt.*;
import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class Nightcrawler extends Critter{
    private ArrayList<Smoke> smokes = new ArrayList<Smoke>();
    
    //pre: none
    //post: constructs a Nightcrawler object that is green
    public Nightcrawler(){
        super();
        setColor(Color.GREEN);
    }
    
    //pre: none
    //post: returns an ArrayList of empty locations in the grid
    public ArrayList<Location> getMoveLocations(){
        ArrayList<Location> list = new ArrayList<Location>();
        for(int row = 0; row < getGrid().getNumRows(); row ++){
            for(int col = 0; col < getGrid().getNumCols(); col ++){
                if(getGrid().get(new Location(col, row)) == null)
                    list.add(new Location(row, col)); 
            }
        }
        return list;
    }
    
    //pre: loc is an empty location
    //post: moves Nightcrawler to the location loc and adds a Smoke object in the previous location
    public void makeMove(Location loc){
        if(loc == null){
            removeSelfFromGrid();
        }
        else{
            Location save = this.getLocation();
            moveTo(loc);
            Smoke smoke = new Smoke();
            smokes.add(smoke);
            for(Smoke a : smokes)
                a.age();
            smoke.putSelfInGrid(getGrid(), save);
        }
    }
}