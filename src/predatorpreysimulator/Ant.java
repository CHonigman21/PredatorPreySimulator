/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predatorpreysimulator;

import java.awt.Point;

/**
 *
 * @author Honigmaster
 */
//This class contains all the methods of organism and a few overriden methods specific to ants.
public class Ant extends Organism{
    
    //Constructor takes the same args as doodlebug, sets icon to ant.
    public Ant(PredatorPreySimulator sim, Point pos, int lastTimeStep){
        super(sim, pos, lastTimeStep);
        sim.grid[posY][posX].setType("ant");
    }
    
    @Override
    //This method is the same method as the move method
    //in doodlebug, except that it sets the icon to ant.
    public void move(){
        Point nextPoint = nextPoint();
        sim.orgArr[posY][posX] = null;
        sim.grid[posY][posX].setType(null);
        this.posX = nextPoint.x;
        this.posY = nextPoint.y;
        sim.orgArr[posY][posX] = this; 
        sim.grid[posY][posX].setType("ant");
    }
    
    @Override
    //This method creates a new anonymous ant object, if it has been 3 time
    //steps since this ant last bred.
    public void breed(){
        if(lastTimeStep - lastBreed == 3){
            new Ant(sim, sim.startSpot(), -1);
            sim.numAnt += 1;
        }
    }
}
