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

//This class contains all the logic to make the simulation run. 
public class PredatorPreySimulator {
     int numCol;
     int numRow;
     int numBug;
     int numAnt;
     int timeStep = 0;
     CellPanel[][] grid;
     Organism[][] orgArr;
     
     
     //Constructor that takes 4 args: # of rows, columns, doodlebugs and ants.
     //Creates the array that contains all the bugs
     public PredatorPreySimulator(int row, int col, int bug, int ant){
        this.numCol = col;
        this.numRow = row;
        this.numBug = bug;
        this.numAnt = ant;
        this.orgArr = new Organism[this.numCol][this.numRow];
        
     }
     
     //This method fills the array with the initial amount of doodlebugs and ants.
     //It randomly chooses spots in the array for doodlebugs first and the ants.
     public void fillSim(){
        for (int i = 0; i < this.numBug; i++){
            new Doodlebug(this, startSpot(), -1);
        }
        for (int i = 0; i < this.numAnt; i ++){
            new Ant(this, startSpot(), -1);
        }
     }
     
     
     //This method randomly chooses the starting position of a new doodlebug or ant.
     //It is comprised of a while loop that randomly chooses spots in the array until 
     //it finds a spot that is unoccupied. It returns this spot in the for of a Point object.
     public Point startSpot(){
         int x = 0;
         int y = 0;
         Point startPoint = new Point(x, y);
         boolean open = false;
         
         while(open == false){
             x = (int)(Math.random() * numCol);
             y = (int)(Math.random() * numRow);
             if (!(this.orgArr[y][x] instanceof Doodlebug) && (!(this.orgArr[y][x] instanceof Ant))){
                 startPoint.x = x;
                 startPoint.y = y;
                 open = true;
             }
             
         }
         return startPoint;
     }
     
     //This method handles all the things that happen when the user presses the next button.
     //It uses a nested for loop to go through the array, first looking for doodlebugs. 
     //When a doodlebug is found, it has that doodlebug carry out each of the things that
     //it can do. Ants do their actions after all doodlebugs have gone. This also increments
     //the timeStep variable and passes it to the organism so that it can keep track of 
     //whether the organims needs to starve or if it needs to breed.
     public void nextStep(){
         for(int i = 0; i < numRow; i++){
             for(int j = 0; j < numCol; j++){
                 if(this.orgArr[i][j] instanceof Doodlebug && this.orgArr[i][j].lastTimeStep < this.timeStep){
                     Doodlebug bug = (Doodlebug)this.orgArr[i][j];
                     bug.move();
                     bug.starve();
                     bug.breed();
                     bug.setTime();
                 }
                 else if(this.orgArr[i][j] instanceof Ant && this.orgArr[i][j].lastTimeStep < this.timeStep){
                     Ant ant = (Ant)this.orgArr[i][j];
                     ant.move();
                     ant.breed();
                     ant.setTime();
                 }
             }
         }
         this.timeStep += 1;
     }

}
