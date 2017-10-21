package snake;


import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import snake.Point;


public class Snake {
	public int horizontal=World.longueur/100;
	public int vertical=World.hauteur/60;
	public ArrayList<Point> body=new ArrayList<Point>(4); 
	public int dir;
	public Color couleur;
	public int TDroite;
	public int TGauche; 
	protected boolean rightPress,leftPress;
	public String nom;
	
	public Snake(Color couleur, int TDroite, int TGauche, int dir, ArrayList<Point> list, String nom) {
		this.couleur = couleur;
		this.dir = dir;
		this.TDroite = TDroite;
		this.TGauche = TGauche;
		this.body = list;
		this.nom = nom;
	}
	
	public void move() {
		if (dir == 0) { 
			Point ajout = new Point((body.get(0).x) , (body.get(0).y + 1));
			body.remove((body.size()-1)); 
			body.add(0,ajout);
		}
		if (dir == 1) { 
			Point ajout = new Point((body.get(0).x + 1) , (body.get(0).y) );
			body.remove((body.size()-1)); 
			body.add(0,ajout);
		}
		if (dir == 2) { 
			Point ajout = new Point((body.get(0).x) , (body.get(0).y -1) );
			body.remove((body.size()-1)); 
			body.add(0,ajout);
		}
		if (dir == 3) { 
			Point ajout = new Point((body.get(0).x -1) , (body.get(0).y));
			body.remove((body.size()-1)); 
			body.add(0,ajout);
		}
	}
	
	public void turn() {
		if (rightPress) { 
			dir = 1 ;
			rightPress=false;
		}
		if (leftPress) { 
			dir = dir -1;
			leftPress=false;
		} 
	}
	
	public void keyReleased(int key, char c) {

		switch (key) {
		case Input.KEY_RIGHT:
			rightPress = false;
			break;

		}
		switch (key) {
		case Input.KEY_LEFT:
			leftPress = false;
			break;

		}
	}
	
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_RIGHT:
			rightPress = true;
			dir += 1;
			dir = dir%4;
			break;

		}
		switch (key) {
		case Input.KEY_LEFT:
			leftPress = true;
			dir -= 1;
			dir = dir%4;
			break;

		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		for  (int i = 0 ; i<body.size(); i++) {
			g.getColor();
			g.fillRect(body.get(0).x*horizontal,body.get(0).y*vertical,horizontal,vertical);
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		move();
		System.out.println(dir);
		//turn();
	}	
	
}

