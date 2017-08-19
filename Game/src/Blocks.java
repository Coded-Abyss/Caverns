import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Blocks extends JFrame {
    private int blockXY = 9;
    private int Thing = 0;
    private Vector Center = new Vector(0, 0);
    private boolean timer1 = false;
    private float timer01 = 0;
    private Vector mapMax = new Vector(5000,5000);
	private Vector mapMin = new Vector(5000,5000);
    private Vector mapMaxCurrent = new Vector(5000,5000);
    private Vector mapMinCurrent = new Vector(5000,5000);
    private boolean colOn = true;
    private boolean Colapse = false;
    private int BlocksBroken = 0;
    private int stability = 100;
    private int speed = 500;
    private int slots[] = new int[9];
    private String slotState[] = new String[9];
    public int highlight0 = 0;
    public int page = 0;
    private BufferedImage slotID[] = new BufferedImage[9];
    //private boolean skip[] = new boolean[9];
    private int nextOpen[] = new int[100];
    
    //stats
    private String statJustBroke = "";
    private String statSelectedBlock = "";
    private int statSelectedCount = 1;
    
    String stone_file = "Resources\\blocks\\stone.png";
    BufferedImage stone;
    String ground_file = "Resources\\blocks\\ground.png";
    BufferedImage ground;
    String iron_file = "Resources\\blocks\\iron.png";
    BufferedImage iron;
    String gold_file = "Resources\\blocks\\gold.png";
    BufferedImage gold;
    String copper_file = "Resources\\blocks\\copper.png";
    BufferedImage copper;
    
    String test_file = "Resources\\GUI\\test.png";
    BufferedImage test;
    String sidebar_file = "Resources\\GUI\\sidebar.png";
    BufferedImage sidebar;
    String sidebarGUI_file = "Resources\\GUI\\sidebarGUI.png";
    BufferedImage sidebarGUI;
    String highlight_file = "Resources\\GUI\\highlight.png";
    BufferedImage highlight;
    
    String stoneI_file = "Resources\\items\\stone.png";
    BufferedImage stoneI;
    BufferedImage stoneF;
    String ironI_file = "Resources\\items\\iron.png";
    BufferedImage ironI;
    String drillI_file = "Resources\\items\\drill.png";
    BufferedImage drillI;
    String goldI_file = "Resources\\items\\gold.png";
    BufferedImage goldI;
    String copperI_file = "Resources\\items\\copper.png";
    BufferedImage copperI;

    public String[][] StoreB = new String[10000][10000];

    public Blocks() {
    	slots[0] = 1;
    	slots[1] = 0;
    	slots[2] = 0;
		StoreB[5000][5000] = "a";
		stone = loadTexture(stone_file);
		ground = loadTexture(ground_file);
		iron = loadTexture(iron_file);
		gold = loadTexture(gold_file);
		copper = loadTexture(copper_file);
		
		test = loadTexture(test_file);
		sidebar = loadTexture(sidebar_file);
		sidebarGUI = loadTexture(sidebarGUI_file);
		highlight = loadTexture(highlight_file);
		
		stoneI = loadTexture(stoneI_file);
		stoneF = stoneI;
		ironI = loadTexture(ironI_file);
		drillI = loadTexture(drillI_file);
		goldI = loadTexture(goldI_file);
		copperI = loadTexture(copperI_file);
		slotID[0] = drillI;
    }

    public void draw(Graphics2D g, Vector p, Vector v, int pSize, boolean Space, String Key, float dt) {
		mapMinCurrent.setX((int) Math.ceil((-p.y - 250 + Center.y) / 100));
		mapMinCurrent.setY((int) Math.ceil((-p.x - 250 + Center.x) / 100));
		mapMaxCurrent.setX(blockXY + (int) Math.ceil((-p.y - 250 + Center.y) / 100));
		mapMaxCurrent.setY(blockXY + (int) Math.ceil((-p.x - 250 + Center.x) / 100));
		

		if(mapMinCurrent.ix <= mapMin.ix) {
			mapMin.setX(mapMinCurrent.ix);
		}
		if(mapMinCurrent.iy <= mapMin.iy) {
			mapMin.setY(mapMinCurrent.iy);
		}
		if(mapMaxCurrent.ix >= mapMax.ix) {
			mapMax.setX(mapMaxCurrent.ix);
		}
		if(mapMaxCurrent.iy >= mapMax.iy) {
			mapMax.setY(mapMaxCurrent.iy);
		}



		for (int e = Thing; e < blockXY + Thing; e++) {
			for (int i = Thing; i < blockXY + Thing; i++) {
				float rand = Math.round(Math.random() * 9999 + 1);

				int e1 = e + (int) Math.ceil((-p.y - 150 + Center.y) / 100);
				int i1 = i + (int) Math.ceil((-p.x - 150 + Center.x) / 100);
				//percentages
				float per0 = 0;
				float per1 = 80;
				float per2 = 10;
				float per3 = 5;
				float per4 = 5;

				float per01 = per0 + per1;
				float per02 = per01 + per2;
				float per03 = per02 + per3;
				float per04 = per03 + per4;

				if (rand / 100 <= per01 && rand / 100 > per0 && StoreB[e1][i1] == null) {
					StoreB[e1][i1] = "b";
				}
				if (rand / 100 <= per02 && rand / 100 > per01 && StoreB[e1][i1] == null) {
					StoreB[e1][i1] = "c";
				}
				if (rand / 100 <= per03 && rand / 100 > per02 && StoreB[e1][i1] == null) {
					StoreB[e1][i1] = "d";
				}
				if (rand / 100 <= per04 && rand / 100 > per03 && StoreB[e1][i1] == null) {
					/*
					for (int k = 0; k < 2; k++) {
						for (int l = 0; l < 2; l++) {
							StoreB[e1 + k][i1 + l] = "e";
						}
					}
					*/
					StoreB[e1][i1] = "e";
				}
				//g.rotate(Math.toRadians(359.8));


				if (StoreB[e1][i1].equals("a")) {
					g.drawImage(ground, p.ix + i1 * 100, p.iy + e1 * 100, pSize, pSize, null);
				}
				if (StoreB[e1][i1].equals("b")) {
					g.drawImage(stone, p.ix + i1 * 100, p.iy + e1 * 100, pSize, pSize, null);
				}
				if (StoreB[e1][i1].equals("c")) {
					g.drawImage(iron, p.ix + i1 * 100, p.iy + e1 * 100, pSize, pSize, null);
				}
				if (StoreB[e1][i1].equals("d")) {
					g.drawImage(gold, p.ix + i1 * 100, p.iy + e1 * 100, pSize, pSize, null);
				}
				if (StoreB[e1][i1].equals("e")) {
					g.drawImage(copper, p.ix + i1 * 100, p.iy + e1 * 100, pSize, pSize, null);
				}
			}
		}
		g.drawImage(sidebar, 700, 0, 150, 700, null);
		g.drawImage(sidebarGUI, 700, 0, 150, 700, null);
		g.drawImage(test, 0, 0, 700, 700, null);
				
		inventory(g);
	}
    public void test(Vector p, Vector v, boolean Space, String Key, float dt) {
		if(Space) {
			if (slotID[highlight0 + 3 * page] == drillI) Break(p, Space, Key, dt);
			else Place(p, Space, Key);
		}
    	else {
	    	switch(Key) {
	    		case "space":
	    			break;
	    			
				case "down":
					if(colOn) {
						if (!StoreB[(int) Math.ceil((-p.y + 180 + Center.y) / 100) + 1]
								[(int) Math.ceil((-p.x + 250 + Center.x) / 100)].equals("a")) {
							v.setX(0);
							v.setY(0);
						} else {
							v.setY(-speed);
						}
					}
					else v.setY(-speed);
					break;
				case "up":
					if(colOn) {
						if (!StoreB[(int) Math.ceil((-p.y + 320 + Center.y) / 100) - 1]
								[(int) Math.ceil((-p.x + 250 + Center.x) / 100)].equals("a")) {
							v.setX(0);
							v.setY(0);
						} else {
							v.setY(speed);
						}
					}
					else v.setY(speed);
					break;
	
				case "right":
					if(colOn) {
						if (!StoreB[(int) Math.ceil((-p.y + 250 + Center.y) / 100)]
								[(int) Math.ceil((-p.x + 180 + Center.x) / 100) + 1].equals("a")) {
							v.setX(0);
							v.setY(0);
						} else {
							v.setX(-speed);
						}
					}
					else v.setX(-speed);
					break;
				case "left":
					if(colOn) {
						if (!StoreB[(int) Math.ceil((-p.y + 250 + Center.y) / 100)]
								[(int) Math.ceil((-p.x + 320 + Center.x) / 100) - 1].equals("a")) {
							v.setX(0);
							v.setY(0);
						} else {
							v.setX(speed);
						}
					}
					else v.setX(speed);
					break;
			}
    	}
	}
	public void Break(Vector p, boolean Space, String Key, float dt) {

    	timer01 += dt;
    	if(timer01 >= 0.5f) {timer1 = true;}

    	if(timer1) {
			int a = (int) Math.ceil((-p.y + 250 + Center.y) / 100);
			int b = (int) Math.ceil((-p.x + 250 + Center.x) / 100);
			switch (Key) {
				case "down":
					statJustBroke = StoreB[a + 1][b];
					StoreB[a + 1][b] = "a";
					break;
				case "up":
					statJustBroke = StoreB[a - 1][b];
					StoreB[a - 1][b] = "a";
					break;
				case "right":
					statJustBroke = StoreB[a][b + 1];
					StoreB[a][b + 1] = "a";
					break;
				case "left":
					statJustBroke = StoreB[a][b - 1];
					StoreB[a][b - 1] = "a";
					break;
			}
			BlocksBroken++;
			stability -= (int) Math.round(Math.random() * 2 + 1);
			if(stability <= 0) Colapse = true;
			timer1 = false;
			timer01 = 0.00f;
			//System.out.println(statJustBroke);
		}
	}
	public void Place(Vector p, boolean Space, String Key) {
		int a = (int) Math.ceil((-p.y + 250 + Center.y) / 100);
		int b = (int) Math.ceil((-p.x + 250 + Center.x) / 100);
		switch (Key) {
			case "down":
				if(StoreB[a + 1][b] == "a") {
					StoreB[a + 1][b] = statSelectedBlock;
					slots[highlight0 + 3 * page]--;
				}
				break;
			case "up":
				if(StoreB[a - 1][b] == "a") {
					StoreB[a - 1][b] = statSelectedBlock;
					slots[highlight0 + 3 * page]--;
				}
				break;
			case "right":
				if(StoreB[a][b + 1] == "a") {
					StoreB[a][b + 1] = statSelectedBlock;
					slots[highlight0 + 3 * page]--;
				}
				break;
			case "left":
				if(StoreB[a][b - 1] == "a") {
					StoreB[a][b - 1] = statSelectedBlock;
					slots[highlight0 + 3 * page]--;
				}
				break;
		}
	}
	public void save(Vector p) {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Resources\\maps\\map.txt")));
			String output = "";
			//for (int o = 0; o < 5; o++) {
				for (int i = mapMin.ix + 1; i < mapMax.ix + 1; i++) {
					for (int e = mapMin.iy + 1; e < mapMax.iy + 1; e++) {
						if(StoreB[i][e] != null) output = output + StoreB[i][e] + ",";
						else output = output + "N,";
					}
					output = output + "\n";
				}
				output = (mapMin.ix + 1) + "," + (mapMin.iy + 1) + "," + mapMax.ix + "," + mapMax.iy + "," + (int) Math.ceil((-p.x + 250 + Center.x) / 100) + "," + (int) Math.ceil((-p.y + 250 + Center.y) / 100) + "," + "\n" + output;
			writer.write(output);
			writer.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void load(Vector p) {
		try{
			BufferedReader reader = new BufferedReader(new FileReader(new File("Resources\\maps\\map.txt")));
			String[][] array = new String[10000][10000];
			int[] n = new int[6];
			int n3 = 0;
			int n2 = 0;
			String line = "";
			String output = "";
			String lengthX = "";
			for(int i = 0; (line = reader.readLine()) != null; i++) {
				String[] split = line.split(",");
				for(String s : split) {
					try {
						n[n2] = Integer.parseInt(s);
						n2++;
					}
					catch (Exception e) {
						if(!s.equals("N")) array[i + n[0] - 1][n3 + n[1]] = s;
						else array[i + n[0] - 1][n3 + n[1]] = null;
						n3++;
					}
				}
				n3 = 0;
			}
			mapMin = new Vector(n[0], n[1]);
			mapMax = new Vector(n[2], n[3]);
			p.setX(-100 * (n[4] - 3));
			p.setY(-100 * (n[5] - 3));
			StoreB = array;
			reader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
    public BufferedImage loadTexture(String filepath){
        try {
            //return ImageIO.read(getClass().getResource(filepath));
            return ImageIO.read(new File(filepath));
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    
    
    public void inventory(Graphics2D g) {
    	String Indent[] = new String[9];
		Font myFont = new Font("Franklin Gothic",Font.BOLD,24);
		g.setColor(Color.BLACK);
		g.setFont(myFont);
		//*
		for(int i = 0; i < 9; i++) {
			//inventory stack logic
			int stones = 0;
			int irons = 0;
			int golds = 0;
			int coppers = 0;
			for(int e = 0; e < 9; e++) {
					if(slotID[e] != stoneI) stones++;
					if(slotID[e] != ironI) irons++;
					if(slotID[e] != goldI) golds++;
					if(slotID[e] != copperI) coppers++;
			}
			//System.out.println((stones - 9) * -1);
			//System.out.println(slotState[highlight0 + 3 * page]);
			if(slotID[i] == null && statJustBroke == "b" && stones == 9) slotID[i] = stoneI;
			if(slotID[i] == null && statJustBroke == "c" && irons == 9) slotID[i] = ironI;
			if(slotID[i] == null && statJustBroke == "d" && golds == 9) slotID[i] = goldI;
			if(slotID[i] == null && statJustBroke == "e" && coppers == 9) slotID[i] = copperI;
			//slotID[1] = stoneI;
			//slotID[2] = stoneI;
		}
		/*
		//stack size
		for(int i = 0; i < 9; i++) {
			if(slots[i] - 1 >= 10) slotState[i] = "full";
			else slotState[i] = "empty";
			if(slots[i] < 10) skip[i] = false;
		}
		//add blocks to inventory
		for(int i = 0; i < 9; i++) {
			if(slotState[i] == "full" && !skip[i]) {
				for(int e = 0; e < 9; e++) {
					skip[i] = true;
					if(slotID[e] == null) {
						slotID[e] = stoneI;
						slots[i]--;
						statJustBroke = "b";
						break;
					}
				}
			}
			if(!skip[i]) {
				if(slotID[i] == stoneI && statJustBroke == "b") slots[i]++;
				if(slotID[i] == ironI && statJustBroke == "c") slots[i]++;
				if(slotID[i] == goldI && statJustBroke == "d") slots[i]++;
				if(slotID[i] == copperI && statJustBroke == "e") slots[i]++;
			}
			if(slots[i] == 0) slotID[i] = null;
		}
			//add new stacks
		*/
		
		//stone
		/*
		for(int i = 0; i < 9; i++)
			for(int e = 0; e < 9; e++)
				if(slotID[i] == stoneI && slotID[e] == stoneI)
					if (i <= e) {
						System.out.println("i: " + i);
						System.out.println("e: " + e);
						System.out.println("");
						e = 10;
						i = 10;
					}
					*/
		
		//open slots
		
		slotID[1] = stoneI;
		for(int i = 0; i < 9; i++)
			for(int e = 0; e < 9; e++)
				if(slotID[i] == null && slotID[e] == null)
					if (i <= e) {
						System.out.println("i: " + i);
						System.out.println("e: " + e);
						System.out.println("");
						e = 10;
						i = 10;
						nextOpen[0] = 1; 
					}
		

		
		for(int i = 0; i < 9; i++) {
			if(slotID[i] == stoneI && statJustBroke == "b") slots[i]++;
			if(slotID[i] == ironI && statJustBroke == "c") slots[i]++;
			if(slotID[i] == goldI && statJustBroke == "d") slots[i]++;
			if(slotID[i] == copperI && statJustBroke == "e") slots[i]++;						
		}
		
		statJustBroke = "";
		//indents for single digit #s
		for(int i = 0; i < 9; i++) {
			if (slots[i] < 10) Indent[i] = "  ";
			else Indent[i] = "";
			if (slots[i] == 0) Indent[i] = "              ";
		}
		
		//selected block & selected count
		
		if (slotID[highlight0 + 3 * page] == stoneI) statSelectedBlock = "b";
		if (slotID[highlight0 + 3 * page] == ironI) statSelectedBlock = "c";
		if (slotID[highlight0 + 3 * page] == goldI) statSelectedBlock = "d";
		if (slotID[highlight0 + 3 * page] == copperI) statSelectedBlock = "e";
		
		statSelectedCount = slots[highlight0 + 3 * page];
		//System.out.println(slots[highlight0 + 3 * page]);

		//drawing to screen:
		g.drawImage(highlight, 700, 286 + highlight0 * 132, 150, 150, null);
		switch(page) {
			case 0:
				g.drawImage(slotID[0], 700, 286, 150, 150, null);
				g.drawImage(slotID[1], 700, 418, 150, 150, null);
				g.drawImage(slotID[2], 700, 550, 150, 150, null);
				g.drawString(Indent[0] + Long.toString(slots[0]), 796, 408);
				g.drawString(Indent[1] + Long.toString(slots[1]), 796, 540);
				g.drawString(Indent[2] + Long.toString(slots[2]), 796, 672);
				break;
			case 1:
				g.drawImage(slotID[3], 700, 286, 150, 150, null);
				g.drawImage(slotID[4], 700, 418, 150, 150, null);
				g.drawImage(slotID[5], 700, 550, 150, 150, null);
				g.drawString(Indent[3] + Long.toString(slots[3]), 796, 408);
				g.drawString(Indent[4] + Long.toString(slots[4]), 796, 540);
				g.drawString(Indent[5] + Long.toString(slots[5]), 796, 672);
				break;
			case 2:		
				g.drawImage(slotID[6], 700, 286, 150, 150, null);
				g.drawImage(slotID[7], 700, 418, 150, 150, null);
				g.drawImage(slotID[8], 700, 550, 150, 150, null);
				g.drawString(Indent[6] + Long.toString(slots[6]), 796, 408);
				g.drawString(Indent[7] + Long.toString(slots[7]), 796, 540);
				g.drawString(Indent[8] + Long.toString(slots[8]), 796, 672);
				break;
		}
    }
}