package worldGeometry;

import java.awt.Color;
import java.awt.Graphics;

public class Platform {

	private int width;
	private int height;
	private double x;
	private double y;

	public Platform(double xInit, double yInit) {
		x = xInit;
		y = yInit;
		width = 40;
		height = 40;
	}

	public Platform(double xInit, double yInit, int widthInit, int heightInit) {
		x = xInit;
		y = yInit;
		width = widthInit;
		height = heightInit;
	}

	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect((int) x - width/2, (int)y - height/2, width, height);
	}

	public double[] getCBoxDimensions() {
		double[] dims = {x,y,width,height};
		return dims;
	}
	
	public int[] getColDir(double px, double py) {
		double xdif, ydif;
		// returns an int in the form: {x dir, y dir}
		int[] result = new int[2];
		xdif = px-x;
		ydif = py-y;
		if ( xdif > ydif) {
			if (xdif > 0) {
				// right
				result[0] = 1;
			}
			else {
				// left
				result[0] = -1;
			}
		}
		else {
			if (ydif > 0) {
				//down
				result[1] = 1;
			}
			else {
				// up
				result[1] = -1;
			}
			
		}
		return result;
	}

}
