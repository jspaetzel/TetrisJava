package Tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.system.AppSettings;
public class Tetris extends SimpleApplication {
	
	// Global Color Materials
	Material cyan,blue, orange, yellow, green, purple, red;
	boolean Debug = false;
	
	long startTime;
	int decendingCount=99;
	static int id = 1;
	int currentTetromino = 0;
	boolean GameOver = false;
	static int array[][] = new int[10][25]; // 10*20 board
	static Tetris app = new Tetris();
	static BitmapText hudText;
	static int xResolution = 420;
	static int yResolution = 640;
	
	Tetromino t[] = new Tetromino[50]; // Most possible is 50 on 10x20 board.
	
	public static void main(String[] args) {
		Logger.getLogger("").setLevel(Level.SEVERE); // Stop JME3 Outputs
		
		app.setShowSettings(false); // Dont display Splash Window
		
		AppSettings appset = new AppSettings(true); // Define Settings of Application
		appset.setResolution(xResolution, yResolution);
		appset.setTitle("Tetris, by John Spaetzel");
		appset.setRenderer(AppSettings.LWJGL_OPENGL_ANY);
		appset.setVSync(true);
		
		app.setSettings(appset);
		app.start();
	}

	// Initializes the App
	@Override
	public void simpleInitApp() {
		
		Node Board = new Node(); // Create 3d Plane
		rootNode.attachChild(Board); // Attach 3d Plan to rootNode
		
		// Disable/Enable to stats stuff.
		setDisplayFps(false);
		setDisplayStatView(false);

		// Disable 3d camera.
		flyCam.setEnabled(false);	    

		// Initialize the game heads up display.
		initHUD();
		initBoard();
		
		
		// Initiate keyboard mapping
		inputManager.addMapping("Left",   new KeyTrigger(KeyInput.KEY_LEFT));
	    inputManager.addMapping("Right",  new KeyTrigger(KeyInput.KEY_RIGHT));
	    inputManager.addMapping("Up",  new KeyTrigger(KeyInput.KEY_UP));
	    inputManager.addMapping("Down",  new KeyTrigger(KeyInput.KEY_DOWN));
	    inputManager.addMapping("Space",  new KeyTrigger(KeyInput.KEY_SPACE));
	    inputManager.addMapping("F3",  new KeyTrigger(KeyInput.KEY_F3));
		
		inputManager.addListener(actionListener, new String[] { "Left" });
		inputManager.addListener(actionListener, new String[] { "Right" });
		inputManager.addListener(actionListener, new String[] { "Up" });
		inputManager.addListener(actionListener, new String[] { "Down" });
		inputManager.addListener(actionListener, new String[] { "Space" });
		inputManager.addListener(actionListener, new String[] { "F3" });
	

       // Material mat = new Material(assetManager,
       //   "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
       // mat.setColor("Color", ColorRGBA.Blue);   // set color of material to blue
       // geom.setMaterial(mat);                   // set the cube's material
        
        //rootNode.attachChild(geom);
		
		for ( int i = 0; i < 10; i++ ) {
			for ( int j = 0; j < 25; j++ ) {
				array[i][j] = 0;
			}
		}
		
		
		
		// This thing generates new tetrominos. Why is this done here?
		//for ( int i = 1; i < 50; i++ ) {
		//	t[i] = new Tetromino(array, i);
		//}
		
		// Attach First Tetromino
		/*Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
		mat.setColor("Color", t[0].color);   // set color of material to correct color
		t[0].a.g.setMaterial(mat);                   // set the material of objects
		t[0].b.g.setMaterial(mat);
		t[0].c.g.setMaterial(mat);
		t[0].d.g.setMaterial(mat);
		
		rootNode.attachChild(t[0].a.g);
		rootNode.attachChild(t[0].b.g);
		rootNode.attachChild(t[0].c.g);
		rootNode.attachChild(t[0].d.g);
		array[5][0] = t[0].id;*/
		
	
	}

	// Initilize the HUD
	private void initHUD() {
		guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
		// HUD TEXT
		hudText = new BitmapText(guiFont, false);
		hudText.setSize(guiFont.getCharSet().getRenderedSize()); // font size
		hudText.setColor(ColorRGBA.Orange); // font color
		guiNode.attachChild(hudText); // Attach HudText
	}
	
	private void updateHUD() {
		hudText.setText("Tetris");
		hudText.setLocalTranslation(xResolution*0.025f, yResolution*0.95f + hudText.getHeight(), 0); // Repostition
	}
	
	// Initialized the Board
	private void initBoard() {
		// whatever.getobject().setmaterial(color);
		// Board.attachchild(whatever.getobject())
		//guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");	
		// Draw the user interface        
	}
	
	// Updater function. Does all the fun stuff while program is running.
	@Override
	public void simpleUpdate(float tpf) {
		if ( decendingCount >= 24 ) {
			// Row has reached bottom. Reset.
			decendingCount = 0;
			id++;
			if ( array[5][0] != 0 ) {
				//Woa. Block in the way. Game OVER!
				app.stop();
			}
			t[id] = new Tetromino(array, id);
			
			// Create the new block. :D
			Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
			mat.setColor("Color", t[id].color);   // set color of material to correct color
			t[id].a.g.setMaterial(mat);                   // set the material of objects
			t[id].b.g.setMaterial(mat);
			t[id].c.g.setMaterial(mat);
			t[id].d.g.setMaterial(mat);
			
			rootNode.attachChild(t[id].a.g);
			rootNode.attachChild(t[id].b.g);
			rootNode.attachChild(t[id].c.g);
			rootNode.attachChild(t[id].d.g);
			
			startTime = timer.getTime();
		}
		
		// get time, if more then a second (100ms for testing) has passed. Drop it down one.
		if ( timer.getTime() >= startTime + 100 ) { 
			//10 Seconds have passed. Move the block down one.
			if ( t[id].moveTetrominoDown(array, id) ) {
				t[id].moveTetromino('d', array, id);
				decendingCount++;
			} else {
				decendingCount = 99;
			}
			
			startTime = timer.getTime();
		}
		updateHUD();
	}
	
	private ActionListener actionListener = new ActionListener() {
		public void onAction(String name, boolean keyPressed, float tpf) {
			if (name.equals("Left") && !keyPressed) {
				//geom.move((float)-0.25,0,0);
				//updateHud(); // Update HUD for any point changes and such.
				t[id].moveTetromino('l', array, id);
			} else if (name.equals("Right") && !keyPressed) {
				//geom.move((float)0.25,0,0);
				t[id].moveTetromino('r', array, id);
			} else if (name.equals("Up") && !keyPressed) {
				t[id].rotateTetromino(array,id);
				// Rotate the current block 90 Degrees to the right.
			} else if (name.equals("Down") && !keyPressed) {
				// Adjust Falling Speed variable x2 while pressed.
			} else if (name.equals("Space") && !keyPressed) {
				// Drop block to the bottom.
			} else if (name.equals("F3") && !keyPressed) {
				// Toggle Debugging Mode
				if ( Debug == false ) {
					setDisplayFps(true);
					setDisplayStatView(true);
					Debug = true;
				} else {
					setDisplayFps(false);
					setDisplayStatView(false);
					Debug = false;
				}
			}
		}
	};
}