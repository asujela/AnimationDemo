

import java.awt.*;
import java.util.*;

import processing.core.PImage;

public class Mario extends Sprite {

	public static final int MARIO_WIDTH = 40;
	public static final int MARIO_HEIGHT = 60;
	private double dt = 1 / 60.0;
	private double dx, dy;
	private double oldDx, oldDy;
	private double ddx, ddy;
	private int maxDx = 400;
	private int maxDy = 240;
	private boolean isTouchingGround;
	private boolean isMoving;
	private double ticksFromZeroToHalf = 4.0;
	private double ticksFromHalfToFull = 8.0;
	private double ticksToStop = 1.0;

	private double fricMod = 0.5;
	
	public Mario(PImage img, int x, int y) {
		super(img, x, y, MARIO_WIDTH, MARIO_HEIGHT);
	}

	// METHODS
	public void walk(int dir) {
		// WALK!
		this.moveByAmount(dir, 0);//hey
	}

	public void jump() {
		// JUMP!
		accelerate(0, -640); 
	}

	public void act(ArrayList<Shape> obstacles) {
		// FALL (and stop when a platform is hit)
		accelerate(0,1440);
		x += dt * (oldDx + ((ddx/2) * dt));
		y += dt * (oldDy + ((ddy/2) * dt));
	}
	
	public void accelerate (double dx, double dy) {
		// This is a simple acceleate method that adds dx and dy to the current velocity.
		this.dx += dx;
		this.dy += dy;

		// This part of the method is used to remember how much the player accelerated in the frame
		// It is only useful for calculating distance moved :)
		ddx += dx;
		ddy += dy;
	}
	
	public void doInput(int direction) {
		// for this, up = 2, right = 1, down = 3, left = -1;
		double accelAmt = 0;
		isMoving = true;
		if (direction == 1) {
			if (isTouchingGround) {
				accelerate(0, -640); 
			}
		}

		else if (direction == 1) {

			if (dx >= 0 && dx <=maxDx/2) {
				accelAmt = maxDx / ticksFromZeroToHalf;
			}
			else if (dx >= maxDx/2 && dx <= maxDx) {
				double accelerationFromZeroToHalf = maxDx / 2 / ticksFromZeroToHalf;
				double accelerationCoefficient = accelerationFromZeroToHalf / ticksFromHalfToFull;
				double tickCount = ticksFromHalfToFull + Math.sqrt(Math.pow(ticksFromHalfToFull, 2) / (maxDx / 2) * (maxDx-dx));        
				accelAmt =  (accelerationCoefficient * tickCount) + accelerationFromZeroToHalf;
			}
			else if (dx >= -maxDx && dx <=0) {
				accelAmt = maxDx / ticksToStop;
			}


			if (isTouchingGround) {

			}
			else {
				accelAmt = accelAmt * 2 / 3;
			}
		}

		else if (direction == 3) {
			if (isTouchingGround) {
				accelerate(0, 0); 
			}
		}

		else if (direction == -1) {

			if (dx <= 0 && dx >= -maxDx/2) {
				accelAmt = -maxDx / ticksFromZeroToHalf;
			}
			else if (dx <= -maxDx/2 && dx >= -maxDx) {	 
				double accelerationFromZeroToHalf = -maxDx/ 2 / ticksFromZeroToHalf;
				double accelerationCoefficient = accelerationFromZeroToHalf / ticksFromHalfToFull;
				double tickCount = ticksFromHalfToFull + Math.sqrt(Math.pow(ticksFromHalfToFull, 2) / (-maxDx / 2) * (-maxDx+dx));        
				accelAmt =  (accelerationCoefficient * tickCount) + accelerationFromZeroToHalf;

			}
			else if (dx <= maxDx && dx >= 0) {
				accelAmt = -maxDx / ticksToStop;
			}
			if (isTouchingGround) {

			}
			else {
				accelAmt = accelAmt * 2 / 3;
			}
		}
		else {}
		if (accelAmt + dx > maxDx) {accelAmt = maxDx - dx;}
		else if(accelAmt + dx < -maxDx) {accelAmt = -maxDx - dx;}
		accelerate(accelAmt, 0); 
	}

	

	
}
