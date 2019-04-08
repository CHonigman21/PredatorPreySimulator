/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predatorpreysimulator;

/**
 *
 * @author Honigmaster
 */


import java.awt.Point;

//This class outlines the basic functions of any organims in our simulation.
public class Organism {
    int posX;
    int posY;
    int lastBreed;
    int lastTimeStep;
    PredatorPreySimulator sim;
    
    //Constructor that takes three args: a predatorpreysimulator object, a point in the array and the previous time step.
    //It sets the type of object in the array to organism.
    public Organism(PredatorPreySimulator sim, Point pos, int lastTimeStep){
        this.posX = pos.x;
        this.posY = pos.y;
        this.sim = sim;
        sim.orgArr[posY][posX] = this;
        this.lastTimeStep = lastTimeStep;
        
    }
    
    
    //This method is a basic move function. It gets the next position through the nextPoint method.
    //It sets the previous position in the array to null and sets the type of the next position to organism.
    public void move(){
        Point nextPoint = nextPoint();
        sim.orgArr[posY][posX] = null;
        this.posX = nextPoint.x;
        this.posY = nextPoint.y;
        sim.orgArr[posY][posX] = this;
    }
    
    //This method gets an adjacent point for the organism to move to. It only checks to see
    //if there is no organism in the spot and that the spot is in the grid, using the inGrid method.
    //If none of the adjacent spots are open, it returns the point that the organism is already in.
    public Point nextPoint(){
        Point nextPoint = new Point(0, 0);
        boolean moved = false;
        
        if(sim.orgArr[posY][posX - 1].inGrid(posX - 1, posY) == true && moved == false){
            if(sim.orgArr[posY][posX - 1] == null){
                nextPoint.x = posX - 1;
                nextPoint.y = posY;
                moved = true;
            }
        }
        else if(sim.orgArr[posY][posX + 1].inGrid(posX + 1, posY) == true && moved == false){
            if(sim.orgArr[posY][posX + 1] == null){
                nextPoint.x = posX + 1;
                nextPoint.y = posY;
                moved = true;
            }
        }
        else if(sim.orgArr[posY - 1][posX].inGrid(posX, posY + 1) == true && moved == false){
            if(sim.orgArr[posY - 1][posX] == null){
                nextPoint.x = posX;
                nextPoint.y = posY - 1;
                moved = true;
            }
        }
        else if(sim.orgArr[posY + 1][posX].inGrid(posX, posY + 1) == true && moved == false){
            if(sim.orgArr[posY + 1][posX] == null){
                nextPoint.x = posX;
                nextPoint.y = posY + 1;
                moved = true;
            }
        }
        else{
            nextPoint.x = posX;
            nextPoint.y = posY;
        }
        return nextPoint;
    }
    
    //This method checks if a given point is within the array.
    //It does 4 checks: if x or y is less than 0, or if x is 
    //greater than the number of columns, or if y is greater 
    //than the number of rows. It returns a boolean value.
     public boolean inGrid(int x, int y){
         boolean answer = true;
         if(x < 0 || y < 0 || x > sim.numCol || y > sim.numRow){
             answer = false;
         }
         return answer;
     }
         
    //This method is a basic breed function. It simply creates a new 
    //anonymous organism object.
    public void breed(){
        new Organism(sim, sim.startSpot(), -1);
    }
    
    
    //This method removes a dead organism from the game by setting
    //the type at their last position to null and by setting their 
    //icon to null as well.
    public void die(){
        sim.orgArr[posY][posX] = null;
        sim.grid[posY][posX].setType(null);
    }
    
    //This method sets the previous time step to the current time step 
    //in the simulation. This is used as a way to make sure that an organism 
    //doesn't move or breed twice in one time step.
    public void setTime(){
        this.lastTimeStep = sim.timeStep;
    }
}
