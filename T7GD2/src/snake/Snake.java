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
	public static int nbcasesh=72;
	public static int nbcasesl=128;
	public int horizontal=World.longueur/nbcasesl;
	public int vertical=World.hauteur/nbcasesh;
	public ArrayList<Point> body=new ArrayList<Point>(); 
	public Color couleur;
	public int TDroite;
	public int TGauche; 
	protected boolean rightPress,leftPress;
	public String nom;
	public int speed;
	public int dir;
	public int score;
	
	
	public Snake(Color couleur,int x_init, int TDroite, int TGauche,int taille_init, String nom,int speed) {
		this.couleur = couleur;
		this.dir = 0;
		this.TDroite = TDroite;
		this.TGauche = TGauche;
		this.body = new ArrayList<Point>();
		this.nom = nom;
		this.score = 0;
		this.speed = speed;
		for (int i = 0;i<taille_init;i++){
			body.add(new Point(x_init,(nbcasesh-i)));
		}
	}
	
	public void move() {
		Point ajout = null;
		if (dir == 0) { 
			if (body.get(0).y != 0) {
				ajout = new Point((body.get(0).x) , (body.get(0).y - 1));
				}
			else {
				ajout = new Point((body.get(0).x) , nbcasesh);}
		}
		if (dir == 1) { 
			if (body.get(0).x != nbcasesl-28 ) {
				ajout = new Point((body.get(0).x + 1) , (body.get(0).y));
			}else {
				ajout = new Point(0 , body.get(0).y) ;
				}
		}
		if (dir == 2) { 
			if (body.get(0).y != nbcasesh) {
				ajout = new Point((body.get(0).x) , (body.get(0).y + 1));}
			else {
				ajout = new Point((body.get(0).x) , 0);
				}
		}
		if (dir == 3) { 
			if (body.get(0).x != 0) {
				ajout = new Point((body.get(0).x-1) , (body.get(0).y));
				}
			else {
				ajout = new Point(nbcasesl-28 , (body.get(0).y));}
		}
		body.remove((body.size()-1)); 
		body.add(0,ajout);
	}
	
	/*public void turn() {
		if (rightPress) { 
			dir = 1 ;
			rightPress=false;
		}
		if (leftPress) { 
			dir = dir -1;
			leftPress=false;
		} 
	}*/
	
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
		if (key == TDroite) {
			rightPress = true;
			dir += 1;
			dir = dir%4;
		}
		if (key == TGauche) {
			leftPress = true;
			dir -= 1;
			dir+=4;
			dir = dir%4;
			
		}
	}
	
	public void grandir(){
		
	}
		
	
	
	
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		for  (int i = 0 ; i<body.size(); i++) {
			g.getColor();
			g.fillRect(body.get(i).x*horizontal,body.get(i).y*vertical,horizontal,vertical);
		}
	}
	int compteur = 0;
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		compteur += speed;
		if (compteur >= 20){	
			move();
			compteur =0;
		}
			//turn();
	}	
	
}

