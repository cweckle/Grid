// Elysia Smyers Period 3
// 25 January 2018
// Smoke - extends Rock

import java.util.*;
import java.awt.*;
import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class Smoke extends Rock{
    private int age;
    
    //pre: none
    //post: creates a pink Smoke object that has an age of 0
    public Smoke(){
        super();
        setColor(Color.PINK);
        age = 0;
    }
    
    //pre: none
    //post: adds one to smoke each time and removes the Smoke from grid when age is 10
    public void age(){
        age ++;
        if(age == 10)
            removeSelfFromGrid();
    }
    
    //pre: none
    //post: returns the variable age
    public int getAge(){
        return this.age;
    }
}