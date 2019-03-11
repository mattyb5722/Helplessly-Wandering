import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;

public class MainBody extends Canvas{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 790;
	public static final int HEIGHT = 675;
	public final String TITLE = "HopelesslyWandering";
	private BufferedImage BackGround;
	
	private boolean GameEnding [] = {false,false,false,false};
	private boolean LockedDoors [] = {false,false,false};
	//private boolean LockedDoors [] = {true,true,true};
	private boolean Beasts [] = {true,true,true,true,true,true,true,true,true,true};

	Movement move;
	Text text;
	Reponses rep;
	Render ren;
	Monster mon;
	Random r = new Random();

	private MENU menu = MENU.MENU;
	private PHASE Phase = PHASE.MOVING;
	private PLACE place = PLACE.START;
	private PLACE pastplace = PLACE.x2y3;
	private INSIDES insides = INSIDES.NONE;
	private INSIDES pastinsides = INSIDES.NONE;

	private int TileX = 2, TileY = 3, TileZ = 0;
	private int InTileX = 0, InTileY = 0, InTileZ = 0;

	private int hunger = 100, thirst = 100, health = 100;	

	private String [] bag = new String [10];
	//							 Item Name	 x	  y    z   InX  InY  InZ   Taken    Place 	Durability
	private String[] Engine =	{"engine",	"1", "7", "0", "0", "0", "0", "false", "engine", "1"};
	private String[] Gas =		{"gas",		"3", "5", "0", "1", "0", "1", "false", "gas", 	 "1"};
	private String[] BoatKey =	{"boat key","3", "5", "0", "2", "0", "0", "false", "key",	 "1"};
	private String[] Rudder =	{"rudder",	"4", "7", "0", "0", "0", "1", "false", "rudder", "1"};
	
	/*
	private String[] Engine =	{"engine",	"1", "0", "0", "0", "0", "0", "false"};
	private String[] Gas =		{"gas",		"1", "0", "0", "0", "0", "0", "false"};
	private String[] BoatKey =	{"boat key",	"1", "0", "0", "0", "0", "0", "false"};
	private String[] Rudder =	{"rudder",	"1", "0", "0", "0", "0", "0", "false"};
	*/
	
	private String[] Map = 			{"map",				"3", "2", "1", "0", "0", "0", "false", "onself", "-1"};
	private String[] PocketKnife = 	{"pocketknife",		"2", "3", "0", "0", "0", "0", "true",  "onself", "-1"};
	private String[] Wrench = 		{"wrench",			"2", "2", "0", "0", "2", "0", "false", "onground", "-1"};
	private String[] Sword = 		{"sword", 			"4", "2", "0", "3", "0", "0", "false", "onground", "-1"};
	
	private String[] HouseKey = 	{"house key", 		"0", "5", "0", "0", "0", "0", "false", "onground", "1"};
	private String[] ShackKey = 	{"shack key", 		"3", "5", "0", "2", "0", "0", "false", "key", "1"};
	private String[] TreeHouseKey = {"treehouse key", 	"4", "2", "0", "2", "0", "1", "false", "key", "1"};
	
	private String[] Sandwich = 	{"sandwich", 	 	"2", "3", "0", "0", "0", "0", "true",  "onself", "3"};
	private String[] Chips = 		{"chips", 	"3", 	"5", "0", "1", "0", "0", "false", "onground", "1"};
	private String[] CandyBar = 	{"candybar", 	 	"2", "2", "0", "0", "1", "0", "false", "onground", "1"};
	private String[] Peanuts = 		{"peanuts", 		"4", "2", "0", "0", "0", "1", "false", "onground", "1"};
	private String[] Pretzel = 		{"pretzels", 		"2", "2", "0", "0", "2", "0", "false", "onground", "1"};
	private String[] Blueberries = 	{"blueberries",  	"3", "1", "0", "0", "0", "0", "false", "berry", "1"};
	private String[] Strawberries = {"strawberries", 	"3", "3", "0", "0", "0", "0", "false", "berry", "1"};
	private String[] Raspberries = 	{"raspberries",  	"3", "4", "0", "0", "0", "0", "false", "berry", "1"};
	private String[] Blackberries = {"blackberries", 	"4", "3", "0", "0", "0", "0", "false", "berry", "1"};
	private String[] Pomegranate = 	{"pomegranate",  	"4", "4", "0", "0", "0", "0", "false", "berry", "1"};

	private String[] WaterBottle =  {"water bottle",  	"2", "3", "0", "0", "0", "0", "true",  "onself", "5"};
	private String[] BeerBottle =   {"bottle of beer",	"4", "5", "0", "0", "0", "1", "false", "ontable", "1"};
	private String[] WineBottle =   {"bottle of wine",	"3", "5", "0", "1", "0", "0", "false", "ontable", "1"};
	private String[] SodaCan = 		{"sodacan", 	  	"4", "2", "0", "2", "0", "1", "false", "onground", "1"};
	private String[] JuiceBox = 	{"juicebox", 	  	"2", "2", "0", "0", "1", "0", "false", "onground", "1"};
	private String[] Coffee = 		{"coffee", 		  	"1", "7", "0", "0", "0", "1", "false", "ontable", "1"};
	
	private String[] Newspaper = 	{"newspaper",  		"2", "2", "0", "0", "0", "0", "false", "ontable", "-1"};
	private String[] Kidnote = 		{"note", 	   		"2", "2", "0", "0", "1", "0", "false", "ontable", "-1"};
	private String[] Journal = 		{"journal",   		"3", "5", "0", "0", "0", "1", "false", "onground", "-1"};
	private String[] NoteBook = 	{"notebook",  		"1", "7", "0", "0", "0", "1", "false", "ontable", "-1"};
	private String[] FlightCourse = {"flight course",   "2", "2", "0", "0", "0", "0", "false", "ontable", "-1"};	
	private String[] MazeNotebook = {"maze notebook",   "4", "2", "0", "0", "0", "1", "false", "ontable", "-1"};	
	
	private String[] BandAids = 	{"band aids", 	  	"2", "2", "0", "0", "2", "0", "false", "onground", "5"};
	private String[] Wrap = 		{"wrap", 		  	"4", "7", "0", "0", "0", "1", "false", "ontable", "2"};
	private String[] Firstaidkit = 	{"first aid kit", 	"4", "2", "0", "2", "3", "0", "false", "ontable", "1"};

	private String[][] items = {Engine,Gas,BoatKey,Rudder,Map,PocketKnife,Wrench,Sword,HouseKey,ShackKey,TreeHouseKey,
			Sandwich,Chips,CandyBar,Peanuts,Pretzel,Blueberries,Raspberries,Strawberries,Blackberries,Pomegranate,
			WaterBottle,BeerBottle,WineBottle,SodaCan,JuiceBox,Coffee,
			Newspaper,Kidnote,Journal,NoteBook,FlightCourse,MazeNotebook,BandAids,Wrap,Firstaidkit};
	
	/*
	private Item engine = new Item("engine", 1, 7, 0, 0, 0, 0, false, "engine", 1);
	private Item gas = new Item("gas", 3, 5, 0, 1, 0, 1, false, "gas", 1);
	private Item boatKey = new Item("boat key", 3, 5, 0, 2, 0, 0, false, "key", 1);
	private Item rudder = new Item("rudder", 4, 7, 0, 0, 0, 1, false, "rudder", 1);
	
	private Item map = new Item("map", 3, 2, 1, 0, 0, 0, false, "onself", -1);
	private Item pocketKnife = new Item("pocketknife", 2, 3, 0, 0, 0, 0, true,  "onself", -1);
	private Item wrench = new Item("wrench", 2, 2, 0, 0, 2, 0, false, "onground", -1);
	private Item sword = new Item("sword", 4, 2, 0, 3, 0, 0, false, "onground", -1);
	
	private Item houseKey = new Item("house key", 0, 5, 0, 0, 0, 0, false, "onground", 1);
	private Item shackKey = new Item("shack key", 3, 5, 0, 2, 0, 0, false, "key", 1);
	private Item treeHouseKey = new Item("treehouse key", 4, 2, 0, 2, 0, 1, false, "key", 1);
	
	private Item sandwich = new Item("sandwich", 2, 3, 0, 0, 0, 0, true, "onself", 3);
	private Item chips = new Item("chips", 3, 5, 0, 1, 0, 0, false, "onground", 1);
	private Item candyBar = new Item("candybar", 2, 2, 0, 0, 1, 0, false, "onground", 1);
	private Item peanuts =  new Item("peanuts", 4, 2, 0, 0, 0, 1, false, "onground", 1);
	private Item pretzel = new Item("pretzels", 2, 2, 0, 0, 2, 0, false, "onground", 1);
	private Item blueberries = new Item("blueberries", 3, 1, 0, 0, 0, 0, false, "berry", 1);
	private Item strawberries = new Item("strawberries", 3, 3, 0, 0, 0, 0, false, "berry", 1);
	private Item raspberries = 	new Item("raspberries", 3, 4, 0, 0, 0, 0, false, "berry", 1);
	private Item blackberries = new Item("blackberries", 4, 3, 0, 0, 0, 0, false, "berry", 1);
	private Item pomegranate = new Item("pomegranate", 4, 4, 0, 0, 0, 0, false, "berry", 1);

	private Item waterBottle = new Item("water bottle", 2, 3, 0, 0, 0, 0, true,  "onself", 5);
	private Item beerBottle = new Item("bottle of beer", 4, 5, 0, 0, 0, 1, false, "ontable", 1);
	private Item wineBottle = new Item("bottle of wine", 3, 5, 0, 1, 0, 0, false, "ontable", 1);
	private Item sodaCan = new Item("sodacan", 4, 2, 0, 2, 0, 1, false, "onground", 1);
	private Item juiceBox = new Item("juicebox", 2, 2, 0, 0, 1, 0, false, "onground", 1);
	private Item coffee = new Item("coffee", 1, 7, 0, 0, 0, 1, false, "ontable", 1);
	
	private Item newspaper = new Item("newspaper", 2, 2, 0, 0, 0, 0, false, "ontable", -1);
	private Item kidnote = new Item("note", 2, 2, 0, 0, 1, 0, false, "ontable", -1);
	private Item journal = new Item("journal", 3, 5, 0, 0, 0, 1, false, "onground", -1);
	private Item noteBook = new Item("notebook", 1, 7, 0, 0, 0, 1, false, "ontable", -1);
	private Item flightCourse = new Item("flight course", 2, 2, 0, 0, 0, 0, false, "ontable", -1);	
	private Item mazeNotebook = new Item("maze notebook", 4, 2, 0, 0, 0, 1, false, "ontable", -1);	
	
	private Item bandAids = new Item("band aids", 2, 2, 0, 0, 2, 0, false, "onground", 5);
	private Item wrap = new Item("wrap", 4, 7, 0, 0, 0, 1, false, "ontable", 2);
	private Item firstaidkit = new Item("first aid kit", 4, 2, 0, 2, 3, 0, false, "ontable", 1);
	
	private Item[] items2 = {};
	*/
	
	private LinkedList<Message> mess = new LinkedList<Message>();
	private String Statement = "";
	
	public enum MENU{
		MENU,GAME,GAME_OVER
	};
	
	public enum PHASE{
		MOVING,COMBAT
	};
	
	public enum PLACE{
		START,END,
		PLANE,SHACK,HOUSE,TEMPLE,BOAT,TREEHOUSE,TREE_GROUND,TREE_TREE,
			 	 	   x0y3,x0y4,x0y5,x0y6,
			 x1y1,x1y2,x1y3			 ,x1y6,
		x2y0,x2y1,     x2y3			 ,x2y6,x2y7,
			 x3y1,	   x3y3,x3y4,     x3y6,x3y7,
			 x4y1,     x4y3,x4y4,x4y5,x4y6,
			 		   x5y3,x5y4,x5y5
	};
	
	public enum INSIDES{
		NONE,
		/**Boat*/BOAT_LAND,BOAT_BOAT,
		/**Plane*/PLANE_COCKPIT, PLANE_PASSENGER, PLANE_STORAGE,
		/**Shack*/SHACK_MAINROOM,SHACK_CELLAR,
		/**Temple*/TEMPLE_MAINROOM,TEMPLE_WESTROOM,TEMPLE_EASTROOM,
		MAZE_x0y0,MAZE_x0y1, MAZE_x0y2,MAZE_x0y3,
		MAZE_x1y0,MAZE_START,MAZE_x1y2,MAZE_x1y3,
		MAZE_x2y0,MAZE_x2y1, MAZE_x2y2,MAZE_x2y3,
		MAZE_x3y0,MAZE_x3y1, MAZE_x3y2,MAZE_x3y3,
		/**House*/HOUSE_MAINROOM, HOUSE_ATTIC, HOUSE_TRAPDOORROOM, HOUSE_BOXROOM,
		/**TreeHouse*/TREEHOUSE_GROUND,TREEHOUSE_TREEHOUSE,	
	};
		
	public void init(){
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			BackGround = loader.loadImage("/Scroll Small.png");
		} catch (IOException e) {e.printStackTrace();}
		
		ren = new Render(this);
		move = new Movement(this);
		text = new Text(this);
		mon = new Monster(this,text);
		rep = new Reponses(this,move,text,mon);
		this.addKeyListener(new KeyInput(this,mon));
		this.addMouseListener(new MouseInput(this,text));
		bag[0] = "pocketknife";
		bag[1] = "sandwich";
		bag[2] = "water bottle";
		render();
		render();
		render();
	}
						
	private void FindLocation(){
		pastplace = place;
		pastinsides = insides;
		if(pastplace == PLACE.START)
			pastplace = PLACE.x2y3;
		if (insides == INSIDES.NONE){
			if ((TileX == 0) && (TileY == 3)&& (TileZ == 0)){place = PLACE.x0y3;}
			else if ((TileX == 0) && (TileY == 4)&& (TileZ == 0)){place = PLACE.x0y4;}
			else if ((TileX == 0) && (TileY == 5)&& (TileZ == 0)){place = PLACE.x0y5;}
			else if ((TileX == 0) && (TileY == 6)&& (TileZ == 0)){place = PLACE.x0y6;}
			else if ((TileX == 1) && (TileY == 0)&& (TileZ == 0)){place = PLACE.BOAT;}
			else if ((TileX == 1) && (TileY == 1)&& (TileZ == 0)){place = PLACE.x1y1;}
			else if ((TileX == 1) && (TileY == 2)&& (TileZ == 0)){place = PLACE.x1y2;}
			else if ((TileX == 1) && (TileY == 3)&& (TileZ == 0)){place = PLACE.x1y3;}
			else if ((TileX == 1) && (TileY == 6)&& (TileZ == 0)){place = PLACE.x1y6;}
			else if ((TileX == 1) && (TileY == 7)&& (TileZ == 0)){place = PLACE.SHACK;}
			else if ((TileX == 2) && (TileY == 0)&& (TileZ == 0)){place = PLACE.x2y0;}
			else if ((TileX == 2) && (TileY == 1)&& (TileZ == 0)){place = PLACE.x2y1;}
			else if ((TileX == 2) && (TileY == 2)&& (TileZ == 0)){place = PLACE.PLANE;}
			else if ((TileX == 2) && (TileY == 3)&& (TileZ == 0)){place = PLACE.x2y3;}
			else if ((TileX == 2) && (TileY == 6)&& (TileZ == 0)){place = PLACE.x2y6;}
			else if ((TileX == 2) && (TileY == 7)&& (TileZ == 0)){place = PLACE.x2y7;}
			else if ((TileX == 3) && (TileY == 1)&& (TileZ == 0)){place = PLACE.x3y1;}
			else if ((TileX == 3) && (TileY == 2)&& (TileZ == 1)){place = PLACE.TREE_TREE;}
			else if ((TileX == 3) && (TileY == 2)&& (TileZ == 0)){place = PLACE.TREE_GROUND;}
			else if ((TileX == 3) && (TileY == 3)&& (TileZ == 0)){place = PLACE.x3y3;}
			else if ((TileX == 3) && (TileY == 4)&& (TileZ == 0)){place = PLACE.x3y4;}
			else if ((TileX == 3) && (TileY == 5)&& (TileZ == 0)){place = PLACE.HOUSE;}
			else if ((TileX == 3) && (TileY == 6)&& (TileZ == 0)){place = PLACE.x3y6;}
			else if ((TileX == 3) && (TileY == 7)&& (TileZ == 0)){place = PLACE.x3y7;}
			else if ((TileX == 4) && (TileY == 1)&& (TileZ == 0)){place = PLACE.x4y1;}
			else if ((TileX == 4) && (TileY == 2)&& (TileZ == 0)){place = PLACE.TEMPLE;}
			else if ((TileX == 4) && (TileY == 3)&& (TileZ == 0)){place = PLACE.x4y3;}
			else if ((TileX == 4) && (TileY == 4)&& (TileZ == 0)){place = PLACE.x4y4;}
			else if ((TileX == 4) && (TileY == 5)&& (TileZ == 0)){place = PLACE.x4y5;}
			else if ((TileX == 4) && (TileY == 6)&& (TileZ == 0)){place = PLACE.x4y6;}
			else if ((TileX == 4) && (TileY == 7)&& (TileZ == 0)){place = PLACE.TREEHOUSE;}
			else if ((TileX == 5) && (TileY == 3)&& (TileZ == 0)){place = PLACE.x5y3;}
			else if ((TileX == 5) && (TileY == 4)&& (TileZ == 0)){place = PLACE.x5y4;}
			else if ((TileX == 5) && (TileY == 5)&& (TileZ == 0)){place = PLACE.x5y5;}
		}
		if (insides != INSIDES.NONE){
			if (place == PLACE.PLANE){
				if ((InTileX == 0) && (InTileY == 0) && (InTileZ == 0)){insides = INSIDES.PLANE_COCKPIT;}
				else if ((InTileX == 0) && (InTileY == 1) && (InTileZ == 0)){insides = INSIDES.PLANE_PASSENGER;}
				else if ((InTileX == 0) && (InTileY == 2) && (InTileZ == 0)){insides = INSIDES.PLANE_STORAGE;}
			}else if (place == PLACE.HOUSE){
				if ((InTileX == 0) && (InTileY == 0) && (InTileZ == 0)){insides = INSIDES.HOUSE_TRAPDOORROOM;}
				else if ((InTileX == 1) && (InTileY == 0) && (InTileZ == 0)){insides = INSIDES.HOUSE_MAINROOM;}
				else if ((InTileX == 2) && (InTileY == 0) && (InTileZ == 0)){insides = INSIDES.HOUSE_BOXROOM;}
				else if ((InTileX == 1) && (InTileY == 0) && (InTileZ == 1)){insides = INSIDES.HOUSE_ATTIC;}
			}else if (place == PLACE.BOAT){
				if ((InTileX == 0) && (InTileY == 1) && (InTileZ == 0)){insides = INSIDES.BOAT_LAND;}
				else if ((InTileX == 0) && (InTileY == 0) && (InTileZ == 0)){insides = INSIDES.BOAT_BOAT;}
			}else if (place == PLACE.SHACK){
				if ((InTileX == 0) && (InTileY == 0) && (InTileZ == 1)){insides = INSIDES.SHACK_MAINROOM;}
				else if ((InTileX == 0) && (InTileY == 0) && (InTileZ == 0)){insides = INSIDES.SHACK_CELLAR;}
			}else if (place == PLACE.TREEHOUSE){
				if ((InTileX == 0) && (InTileY == 0) && (InTileZ == 0)){insides = INSIDES.TREEHOUSE_GROUND;}
				else if ((InTileX == 0) && (InTileY == 0) && (InTileZ == 1)){insides = INSIDES.TREEHOUSE_TREEHOUSE;}	
			}else if (place == PLACE.TEMPLE){
				if ((InTileX == 1) && (InTileY == 0) && (InTileZ == 1)){insides = INSIDES.TEMPLE_MAINROOM;}
				else if ((InTileX == 0) && (InTileY == 0) && (InTileZ == 1)){insides = INSIDES.TEMPLE_WESTROOM;}
				else if ((InTileX == 2) && (InTileY == 0) && (InTileZ == 1)){insides = INSIDES.TEMPLE_EASTROOM;}
				
				else if ((InTileX == 0) && (InTileY == 0) && (InTileZ == 0)){insides = INSIDES.MAZE_x0y0;}
				else if ((InTileX == 0) && (InTileY == 1) && (InTileZ == 0)){insides = INSIDES.MAZE_x0y1;}
				else if ((InTileX == 0) && (InTileY == 2) && (InTileZ == 0)){insides = INSIDES.MAZE_x0y2;}
				else if ((InTileX == 0) && (InTileY == 3) && (InTileZ == 0)){insides = INSIDES.MAZE_x0y3;}
				else if ((InTileX == 1) && (InTileY == 0) && (InTileZ == 0)){insides = INSIDES.MAZE_x1y0;}
				else if ((InTileX == 1) && (InTileY == 1) && (InTileZ == 0)){insides = INSIDES.MAZE_START;}
				else if ((InTileX == 1) && (InTileY == 2) && (InTileZ == 0)){insides = INSIDES.MAZE_x1y2;}
				else if ((InTileX == 1) && (InTileY == 3) && (InTileZ == 0)){insides = INSIDES.MAZE_x1y3;}
				else if ((InTileX == 2) && (InTileY == 0) && (InTileZ == 0)){insides = INSIDES.MAZE_x2y0;}
				else if ((InTileX == 2) && (InTileY == 1) && (InTileZ == 0)){insides = INSIDES.MAZE_x2y1;}
				else if ((InTileX == 2) && (InTileY == 2) && (InTileZ == 0)){insides = INSIDES.MAZE_x2y2;}
				else if ((InTileX == 2) && (InTileY == 3) && (InTileZ == 0)){insides = INSIDES.MAZE_x2y3;}
				else if ((InTileX == 3) && (InTileY == 0) && (InTileZ == 0)){insides = INSIDES.MAZE_x3y0;}
				else if ((InTileX == 3) && (InTileY == 1) && (InTileZ == 0)){insides = INSIDES.MAZE_x3y1;}
				else if ((InTileX == 3) && (InTileY == 2) && (InTileZ == 0)){insides = INSIDES.MAZE_x3y2;}
				else if ((InTileX == 3) && (InTileY == 3) && (InTileZ == 0)){insides = INSIDES.MAZE_x3y3;}
			}
		}
	}
		
	public void SetLocation(){
		if (insides == INSIDES.NONE){
			if (place == PLACE.x0y3){TileX = 0; TileY = 3; TileZ = 0;}
			else if (place == PLACE.x0y4){TileX = 0; TileY = 4;TileZ = 0;}
			else if (place == PLACE.x0y5){TileX = 0; TileY = 5;TileZ = 0;}	
			else if (place == PLACE.x0y5){TileX = 0; TileY = 5;TileZ = 0;}	
			else if (place == PLACE.x0y6){TileX = 0; TileY = 6;TileZ = 0;}	
			else if (place == PLACE.BOAT){TileX = 1; TileY = 0;TileZ = 0;}	
			else if (place == PLACE.x1y1){TileX = 1; TileY = 1; TileZ = 0;}
			else if (place == PLACE.x1y2){TileX = 1; TileY = 2; TileZ = 0;}
			else if (place == PLACE.x1y3){TileX = 1; TileY = 3; TileZ = 0;}
			else if (place == PLACE.x1y6){TileX = 1; TileY = 6; TileZ = 0;}
			else if (place == PLACE.SHACK){TileX = 1; TileY = 7; TileZ = 0;}
			else if (place == PLACE.x2y0){TileX = 2; TileY = 0; TileZ = 0;}
			else if (place == PLACE.x2y1){TileX = 2; TileY = 1; TileZ = 0;}
			else if (place == PLACE.PLANE){TileX = 2; TileY = 2; TileZ = 0;}
			else if (place == PLACE.x2y3){TileX = 2; TileY = 3; TileZ = 0;}
			else if (place == PLACE.x2y6){TileX = 2; TileY = 6; TileZ = 0;}
			else if (place == PLACE.x2y7){TileX = 2; TileY = 7; TileZ = 0;}
			else if (place == PLACE.x3y1){TileX = 3; TileY = 1; TileZ = 0;}
			else if (place == PLACE.TREE_TREE){TileX = 3; TileY = 2; TileZ = 1;}
			else if (place == PLACE.TREE_GROUND){TileX = 3; TileY = 2; TileZ = 0;}
			else if (place == PLACE.x3y3){TileX = 3; TileY = 3; TileZ = 0;}
			else if (place == PLACE.x3y4){TileX = 3; TileY = 4; TileZ = 0;}
			else if (place == PLACE.HOUSE){TileX = 3; TileY = 5; TileZ = 0;}
			else if (place == PLACE.x3y6){TileX = 3; TileY = 6; TileZ = 0;}
			else if (place == PLACE.x3y7){TileX = 3; TileY = 7; TileZ = 0;}
			else if (place == PLACE.x4y1){TileX = 4; TileY = 1; TileZ = 0;}
			else if (place == PLACE.TEMPLE){TileX = 4; TileY = 2; TileZ = 0;}
			else if (place == PLACE.x4y3){TileX = 4; TileY = 3; TileZ = 0;}
			else if (place == PLACE.x4y4){TileX = 4; TileY = 4; TileZ = 0;}
			else if (place == PLACE.x4y5){TileX = 4; TileY = 5; TileZ = 0;}
			else if (place == PLACE.x4y6){TileX = 4; TileY = 6; TileZ = 0;}
			else if (place == PLACE.TREEHOUSE){TileX = 4; TileY = 7; TileZ = 0;}
			else if (place == PLACE.x5y3){TileX = 5; TileY = 3; TileZ = 0;}
			else if (place == PLACE.x5y4){TileX = 5; TileY = 4; TileZ = 0;}
			else if (place == PLACE.x5y5){TileX = 5; TileY = 5; TileZ = 0;}
		}else if (place == PLACE.TEMPLE){
			if (insides == INSIDES.MAZE_x0y0){InTileX = 0; InTileY = 0; InTileZ = 0;}
			else if (insides == INSIDES.MAZE_x0y1){InTileX = 0; InTileY = 1; InTileZ = 0;}
			else if (insides == INSIDES.MAZE_x0y2){InTileX = 0; InTileY = 2; InTileZ = 0;}
			else if (insides == INSIDES.MAZE_x0y3){InTileX = 0; InTileY = 3; InTileZ = 0;}
			else if (insides == INSIDES.MAZE_x1y0){InTileX = 1; InTileY = 0; InTileZ = 0;}
			else if (insides == INSIDES.MAZE_START){InTileX = 1; InTileY = 1; InTileZ = 0;}
			else if (insides == INSIDES.MAZE_x1y2){InTileX = 1; InTileY = 2; InTileZ = 0;}
			else if (insides == INSIDES.MAZE_x1y3){InTileX = 1; InTileY = 3; InTileZ = 0;}
			else if (insides == INSIDES.MAZE_x2y0){InTileX = 2; InTileY = 0; InTileZ = 0;}
			else if (insides == INSIDES.MAZE_x2y1){InTileX = 2; InTileY = 1; InTileZ = 0;}
			else if (insides == INSIDES.MAZE_x2y2){InTileX = 2; InTileY = 2; InTileZ = 0;}
			else if (insides == INSIDES.MAZE_x2y3){InTileX = 2; InTileY = 3; InTileZ = 0;}
			else if (insides == INSIDES.MAZE_x3y0){InTileX = 3; InTileY = 0; InTileZ = 0;}
			else if (insides == INSIDES.MAZE_x3y1){InTileX = 3; InTileY = 1; InTileZ = 0;}
			else if (insides == INSIDES.MAZE_x3y2){InTileX = 3; InTileY = 2; InTileZ = 0;}
			else if (insides == INSIDES.MAZE_x3y3){InTileX = 3; InTileY = 3; InTileZ = 0;}
		}

		text.location_text();
	}
		
	public void dying(){
		hunger -= 5;
		thirst -= 5;
		if(thirst<25){
	   		addMessage("You are very thirsty. Try to drink something.","AI");
		}if(hunger<25){
	   		addMessage("You are very hungery. Try to eat something.","AI");
		}
		if((thirst<25)&&(hunger<25)){
			health -= 25;
		}else if ((thirst<25)||(hunger<25)){
			health -=10;
		}
		if((health <= 50)&&(health > 0)){
	   		addMessage("You are feeling very weak. Try using some first aid supplies on yourself.","AI");
		}else if(health <= 0){
	   		addMessage("You have died","AI");
	   		menu = MENU.GAME_OVER;
			System.exit(1);
		}
	}
		
	public void getResponse(String statement){
		statement = statement.trim().toLowerCase();
		if ((findKeyword(statement, "north")>=0)||(findKeyword(statement, "south")>=0)||(findKeyword(statement, "east")>=0)
				||(findKeyword(statement, "west")>=0)||(findKeyword(statement, "up")>=0)||(findKeyword(statement, "down")>=0)){
			rep.move(statement);
			FindLocation();
			text.location_text();
			text.item_text();
			move.itemsmoving();
			dying();
			mon.location();
		}else if ((statement.indexOf("check bag") >= 0)){
			rep.checkbag();
		}else if (findKeyword(statement, "take") >= 0){
			rep.takeitem(statement);
		}else if (findKeyword(statement, "drop") >= 0){
			rep.dropitem(statement);
		}else if (findKeyword(statement, "use") >= 0){
			rep.useitem(statement);
			if((GameEnding[0] == true)&&(GameEnding[1] == true)&&(GameEnding[2] == true)&&(GameEnding[3] == true)){
		   		addMessage("","AI");
				addMessage("Congratulations. You've completed the game.", "AI");
				addMessage("You've escaped the island.", "AI");
			}
		}else if (findKeyword(statement, "examine") >= 0){
			rep.examineitem(statement);
		}else if (findKeyword(statement, "diagnose") >= 0){
			rep.diagnose();
		}else if (findKeyword(statement, "help") >= 0){
			rep.help();
		}else{
			addMessage("What are you trying to say?", "AI");
		}addMessage("","AI");
	}
 		
	public int findKeyword(String phrase, String goal){
		if (phrase != null){
			int psn = phrase.indexOf(goal, 0);
			while (psn >= 0){
				String before = " ", after = " ";
				if (psn > 0){
					before = phrase.substring(psn - 1, psn);
				}if (psn + goal.length() < phrase.length()){
					after = phrase.substring(
							psn + goal.length(),
							psn + goal.length() + 1);
				}if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0)) && ((after.compareTo("a") < 0) || (after.compareTo("z") > 0))){
					return psn;
				}
				psn = phrase.indexOf(goal, psn + 1);
			}
		}
		return -1;
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Font fnt0 = new Font("arial", Font.BOLD,20);
		g.setFont(fnt0);
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(BackGround, 0, 0, null);
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		 if(menu == MENU.MENU){
			 ren.MENU(g);
		 }else if(menu == MENU.GAME){
			ren.INGAME(g,mess,Statement);	
	    }else if(menu == MENU.GAME_OVER){
			 ren.GAME_OVER(g);
		 }
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		g.dispose();
		bs.show();
	}	
			
	public void addMessage(String message, String from){
		addEntity(new Message(message,from,0));
		//System.out.println(message);
		for(int i = mess.size()-1; i >= 0; i--){
			mess.get(i).setRow(mess.get(i).getRow()+1);
			if(mess.get(i).getRow() >= (HEIGHT-25)/25){
				mess.remove(i);
			}
		}
		render();
	}
		    	    	
	public void additemwithoutdamage(String item){
		rep.takeitem("take " +item);
	}
	
	public static void main(String[] args) {
    	MainBody main = new MainBody();
    	main.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    	main.setMaximumSize(new Dimension(WIDTH, HEIGHT));
    	main.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    	main.setBackground(Color.BLACK);
		JFrame frame = new JFrame(main.TITLE);
		frame.add(main);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);		
   		main.init();
	}
	
	public void addEntity(Message block){
		mess.add(block);
	}
	public void removeEntity(Message block){
		mess.remove(block);
	} 
	public int getTileX() {
		return TileX;
	}
	public void setTileX(int TileX) {
		this.TileX = TileX;
	}
	public int getTileY() {
		return TileY;
	}
	public void setTileY(int TileY) {
		this.TileY = TileY;
	}
	public int getTileZ() {
		return TileZ;
	}
	public void setTileZ(int tileZ) {
		TileZ = tileZ;
	}
	public int getInTileX() {
		return InTileX;
	}
	public void setInTileX(int inTileX) {
		InTileX = inTileX;
	}
	public int getInTileY() {
		return InTileY;
	}
	public void setInTileY(int inTileY) {
		InTileY = inTileY;
	}
	public int getInTileZ() {
		return InTileZ;
	}
	public void setInTileZ(int inTileZ) {
		InTileZ = inTileZ;
	}
	public MENU getMenu() {
		return menu;
	}
	public void setMenu(MENU menu) {
		this.menu = menu;
	}
	public PHASE getPhase() {
		return Phase;
	}
	public void setPhase(PHASE phase) {
		Phase = phase;
	}
	public PLACE getPlace() {
		return place;
	}
	public void setPlace(PLACE place) {
		this.place = place;
	}
	public PLACE getPastplace() {
		return pastplace;
	}
	public void setPastplace(PLACE pastplace) {
		this.pastplace = pastplace;
	}
	public INSIDES getInsides() {
		return insides;
	}
	public void setInsides(INSIDES insides) {
		this.insides = insides;
	}
	public INSIDES getPastinsides() {
		return pastinsides;
	}
	public void setPastinsides(INSIDES pastinsides) {
		this.pastinsides = pastinsides;
	}
	public String[] getBag() {
		return bag;
	}
	public void setBag(String bag, int i) {
		this.bag[i] = bag;
	}
	public int getHunger() {
		return hunger;
	}
	public void setHunger(int hunger) {
		this.hunger = hunger;
	}
	public int getThirst() {
		return thirst;
	}
	public void setThirst(int thirst) {
		this.thirst = thirst;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public String[][] getItems() {
		return items;
	}
	public void setItems(String items, int a, int b) {
		this.items[a][b] = items;
	}
	public boolean[] getGameEnding() {
		return GameEnding;
	}
	public void setGameEnding(boolean gameEnding, int a) {
		this.GameEnding[a] = gameEnding;
	}
	public boolean[] getLockedDoors() {
		return LockedDoors;
	}
	public void setLockedDoors(boolean lockedDoors, int a) {
		this.LockedDoors[a] = lockedDoors;
	}

	public boolean[] getBeasts() {
		return Beasts;
	}

	public void setBeasts(boolean beasts,int a) {
		Beasts[a] = beasts;
	}

	public String getStatement() {
		return Statement;
	}

	public void setStatement(String statement) {
		Statement = statement;
	}

}