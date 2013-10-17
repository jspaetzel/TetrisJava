package Tetris;

import java.util.Random;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;

public class Tetromino {
	// Creates them. Defines them.
	Block a = new Block();
	Block b = new Block();
	Block c = new Block();
	Block d = new Block();
	ColorRGBA color;
	char config; // I, J, L, O, S, T, Z
    
	static float s = 3f;
	static float o = 0.25f;
	
	// Generate this new Tetromino
	public Tetromino(int array[][], int id) {
		
		// Blocks are 0.25 apart. 
		Random randomGenerator = new Random();
		int randomConfig = randomGenerator.nextInt(7); // 1-6, to determine shape.
		int randomRotation = randomGenerator.nextInt(4); // 1-4 to determine rotation
		//System.out.println("ID2: " + i);
		// Switched used only for setting config, setting initial starting point, and 
		switch ( randomConfig ) {
			case 0: config = 'I';
					color = new ColorRGBA(0,1,1,1.0f);
					a.x = 5; a.y = 3;  //Bottom
					b.x = 5; b.y = 2;  
					c.x = 5; c.y = 1;  
					d.x = 5; d.y = 0;  //TOP
					break;
			case 1: config = 'J';
					color = new ColorRGBA(0, 0, 1, 1.0f);
					a.x = 5; a.y = 3; 
					b.x = 5; b.y = 2; 
					c.x = 5; c.y = 1; 
					d.x = 4; d.y = 1; 
					break;
			case 2: config = 'L';
					color = new ColorRGBA(1, 0.5f, 0, 1.0f);
					a.x=5;a.y=3;  
					b.x=5;b.y=2; 
					c.x=5;c.y=1; 
					d.x=6;d.y=1;
					break;
			case 3: config = 'O';
					color = new ColorRGBA(1, 1, 0, 1.0f);
					a.x=5;a.y=3; 
					b.x=6;b.y=3;  
					c.x=5;c.y=2; 
					d.x=6;d.y=2;
					break;
			case 4: config = 'S';
					color = new ColorRGBA(0, 1, 0, 1.0f);
					a.x=5;a.y=3; 
					b.x=6;b.y=3;  
					c.x=5;c.y=2; 
					d.x=4;d.y=2;
					break;
			case 5: config = 'T';
					color = new ColorRGBA(0.5f, 0, 0.5f, 1.0f);
					a.x=5;a.y=3; 
					b.x=5;b.y=2; 
					c.x=6;c.y=2; 
					d.x=4; d.y=2;
					break;
			case 6: config = 'Z';
					color = new ColorRGBA(1, 0, 0, 1.0f);
					a.x=5;a.y=3;
					b.x=4;b.y=3; 
					c.x=5;c.y=2;
					d.x=6;d.y=2;
					break;
		}
		// Change so that the movement, and array setting to i is done OUTSIDE of the switch.
		array[a.x][a.y] = id;
		array[b.x][b.y] = id;
		array[c.x][c.y] = id;
		array[d.x][d.y] = id;
		
		a.g.move((a.x-5)*o, s-((a.y-3)*o), 0);
		b.g.move((b.x-5)*o, s-((b.y-3)*o), 0);
		c.g.move((c.x-5)*o, s-((c.y-3)*o), 0);
		d.g.move((d.x-5)*o, s-((d.y-3)*o), 0);
		
		//rotateTetromino(randomRotation, array, id);
		// Give this tetromino an id and increase count of them.
		
		//System.out.println("Count: " + count + ", id: " + id ) ;
	}
	
	// Can the tetromino move down one?, return false if it is unable to move down.
	// Check each block. If ANY of them cannot go down it will stop.
	public boolean moveTetrominoDown(int array[][], int id) {
		// Check if each block can move down one without overlapping an existing block
		// Existing blocks do not include blocks of the same id.
		
		for ( int i = 0; i < 10; i++ ) {
			for ( int j = 0; j < 25; j++ ){
				System.out.print(array[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
		if ( a.y < 24 && b.y < 24 && c.y < 24 && d.y < 24 ) {
			/*System.out.println("ID: " + id);
			System.out.println("ID: " + array[a.x][a.y] + " " + array[a.x][a.y+1]);
			System.out.println("ID: " + array[b.x][b.y] + " " + array[b.x][b.y+1]);
			System.out.println("ID: " + array[c.x][c.y] + " " + array[c.x][c.y+1]);
			System.out.println("ID: " + array[d.x][d.y] + " " + array[d.x][d.y+1]);*/
		if ( array[a.x][a.y+1] == 0 || array[a.x][a.y+1] == id ) {
			if ( array[b.x][b.y+1] == 0 || array[b.x][b.y+1] == id ) {System.out.println("True");
				if ( array[c.x][c.y+1] == 0 || array[c.x][c.y+1] == id ) {
					if ( array[d.x][d.y+1] == 0 || array[d.x][d.y+1] == id ) {
						return true;
					}
				}
			}
		}
		}
		return false;
	}
	
	//Moves the four blocks that make up a tetromino
	public void moveTetromino(char dir, int array[][], int id) {
		a.move(dir, array, id); 
		b.move(dir, array, id);
		c.move(dir, array, id);
		d.move(dir, array, id);
	}
	
	public void rotateTetromino(int array[][], int id) {
		// Might be a good idea to create a general offset for each of the 4 blocks from an initial starting point.
		// This offset could be used for the 90 degree rotation and also for simplifying the initial location setting? Maybe not really. Idk. Cant really get simpler then that.
		// Draw out each part and determine offset needed for the rotation.. I think it may need to be manually entered which is lame.
		// Create a "fake" rotation to test if rotation can be done before actually doing it.....
		// There should also be a better check for movement both left/right.
				
		//We shall pivot around b.
		
		int px,py;
		if ( config == 'Z' || config == 'T') {
			px = c.x; py = c.y;
		} else if ( config == 'S' ) {
			px = d.x; py = d.y;
		} else {
			px = b.x; py = b.y;
		}
		
	
		//x2 = (y1 + px - py)
		//y2 = (px + py - x1 - q)
		
		
		array[a.x][b.y] = 0;
		array[b.x][b.y] = 0;
		array[c.x][c.y] = 0;
		array[d.x][d.y] = 0;
		
		int tmp = a.x; a.x = a.y+px-py; a.y = px+py-tmp;
		tmp = b.x; b.x = b.y+px-py; b.y = px+py-tmp;
		if ( config != 'Z' || config != 'T' ) {
			tmp = c.x; a.x = c.y+px-py; c.y = px+py-tmp;
		}
		if ( config != 'S' ) {
			tmp = d.x; d.x = d.y+px-py; d.y = px+py-tmp;

		}
		array[a.x][a.y] = id;
		array[b.x][b.y] = id;
		array[c.x][c.y] = id;
		array[d.x][d.y] = id;
		
		// Manually reset the translation of object.
		a.g.setLocalTranslation((a.x-5)*o, s-((a.y-3)*o), 0);
		b.g.setLocalTranslation((b.x-5)*o, s-((b.y-3)*o), 0);
		c.g.setLocalTranslation((c.x-5)*o, s-((c.y-3)*o), 0);
		d.g.setLocalTranslation((d.x-5)*o, s-((d.y-3)*o), 0);
	}
	
	// Rotate by number of times, to rotate more then once.
	// Each 90, is one right rotation so call the other function the necessary number of times.
	public void rotateTetromino(int count, int array[][], int id) {
		for ( int i = 0; i < count; i++ ) {
			rotateTetromino(array, id);
		}
	}
	
}
