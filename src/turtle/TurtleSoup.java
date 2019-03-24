/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;
import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        //throw new RuntimeException("implement me!");
    	
    	
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);

        //turtle.color(PenColor.RED);
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        //throw new RuntimeException("implement me!");
    	double angle;
    	if(sides > 2){
    		angle =((sides-2)*180.0)/sides;
    		if(angle >=0 && angle < 360)
    			return angle;
    	}
    	return 0.0;
    	
    		
    	
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        //throw new RuntimeException("implement me!");
    	if(angle > 0 && angle < 180)
    		return (int)Math.round(360/(180 - angle));
    	return 0;
    	
    	
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
     
    	double angle = calculateRegularPolygonAngle(sides);
    	for(int i=0; i< sides ; i++){
    		turtle.forward(sideLength);
    		turtle.turn(180.0 - angle);
    	}
    	 //  throw new RuntimeException("implement me!");
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
       
    	double Y=targetY - currentY;
    	double angle= Math.atan((targetX - currentX)/Y)*360/(2*Math.PI)-currentHeading;
    	if(angle < 0.0 )
    		angle += 360.0;
    	return angle;
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
    	List<Double> angles = new ArrayList<Double>();
    	double angleinitial = 0.0;
    	
    	if(xCoords.size() == yCoords.size() && xCoords.size()> 1){
    		for(int i=0; i < xCoords.size()-1;i++){
    			angleinitial = calculateHeadingToPoint(angleinitial,xCoords.get(i),yCoords.get(i),xCoords.get(i+1),yCoords.get(i+1));
    			angles.add(angleinitial);                                                                                                                                                                                                                                                                                          
    		}
    	}
    	return angles;
//        throw new RuntimeException("implement me!");
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	int sides = 25;
    	int sidelength = 20;
    	for(int i = sides; i>0; i--){
    		for(int j = 20; j>0; j -=1){
    			drawRegularPolygon(turtle, 5, j);
    		
    		}
    		for(PenColor c : PenColor.values()){
    			turtle.color(c);
    			drawRegularPolygon(turtle, 25, 15);
            	turtle.forward(sidelength);
            	turtle.turn(180.0 - calculateRegularPolygonAngle(sides));
    		}   		
    	}
    	
//        throw new RuntimeException("implement me!");
    }
    

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();
        
       // TurtleSoup tSoup = new TurtleSoup();
        /* test pour le cas du polygone*/
        //drawSquare(turtle,200);
        //TurtleSoup.drawRegularPolygon(turtle, 5 , 100);
       drawPersonalArt(turtle);
         drawSquare(turtle, 100);
      	
        // draw the window
       turtle.draw();
      
        
    }

}
