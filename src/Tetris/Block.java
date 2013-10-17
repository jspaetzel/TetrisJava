package Tetris;

import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Quad;

public class Block {
	// Defines the individual blocks that make up a Tetromino.
	// The block's locational coords.
	int x=0, y=0;
	Quad q;
	Geometry g;
	
	public Block () {
		q = new Quad(0.25f,0.25f);
		g = new Geometry ("Tetromino", q);
	}

    // move the quad.
    public void move(char dir, int array[][], int id) {
    	if ( dir == 'd' ) {
    		g.move(0,-0.25f,0);
    		// Used to ensure correct placement of 0s otherwise they could accidently erase things that should not be erased.
    		boolean prior = false;
    		if ( y > 0 ) {
    			if ( array[x][y-1] == id ) {
    				// Prior is in it.
    				prior = true;
    			}
    		}
    		y++; 
    		
    		array[x][y] = id;
    		if ( prior == false ) {
    			array[x][y-1] = 0;
    		}
    	} else if ( dir == 'r' ) {
    		g.move(0.25f, 0, 0);
    		boolean prior = false;
    		if ( x > 0 && x < 10) {
    			if ( array[x-1][y] == id ) {
    				// Prior is in it.
    				prior = true;
    			}
    		}
    		x++;
    		
    		array[x][y] = id;
    		if ( prior == false ) {
    			array[x-1][y] = 0;
    		}
    	} else if ( dir == 'l' ) {
    		g.move(-0.25f,0,0);
    		boolean prior = false;
    		if ( x > 0 && x < 10) {
    			if ( array[x+1][y] == id ) {
    				// Prior is in it.
    				prior = true;
    			}
    		}
    		x--;
    		
    		array[x][y] = id;
    		if ( prior == false ) {
    			array[x+1][y] = 0;
    		}
    	}
    }
    
    

}
