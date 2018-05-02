

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
		accelerate(0,-4);
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
	

	
}
