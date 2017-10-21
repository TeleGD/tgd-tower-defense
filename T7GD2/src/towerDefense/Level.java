package towerDefense;

import java.util.Arrays;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Level {
	
	int[][] map;
	float width, height;
	int lenX, lenY;
	private Image sprite0,sprite1,sprite2;
	

	public Level() {
		width = 32;
		height = 32;
		lenX = (int)(1280 / width);
		lenY = (int)(616 / height);
		try {
			sprite0 = new Image("images/TowerDefense/chemin.png");
			sprite1 = new Image("images/TowerDefense/mur.png");
			sprite2 = new Image("images/TowerDefense/spawn.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		genereMap();
		
	}
	
	public int[] genereLine(int element1, int element2, int debut, int fin) {
		//Genere une ligne avec element1 à partir de la case debut jusqu'à la case fin exclu, complète le reste avec element2
		int[] line = new int[lenX];
		for (int i = 0; i < lenX; i++)
		{
			line[i] = element2;
		}
		
		for (int i = debut; i < fin; i++) 
		{
			line[i] = element1;
		}
		return line;
	}
	
	public void genereMap() {
		// Generer la carte du jeu
		map = new int[lenY][lenX];
		
		int [] line1 = new int[lenX];
		Arrays.fill(line1, 1);
		map[0] = line1; //Murs du haut
		
		for (int i=1; i < (int)(lenY - 4); i+=4)
		{
			map[i+3] = genereLine(0,1,1,2);
			map[i+2] = genereLine(0,1,1,lenX - 1);
			map[i+1] = genereLine(0,1,lenX - 2,lenX - 1);
			map[i] = genereLine(0,1,1,lenX - 1);
		}

		map[lenY -2] = genereLine(0,1,1,lenX - 1); //avant dernière ligne
		map[1][1] = 2;    //spawn
		map[lenY - 2][lenX - 2 ] = 3;  //fin
		map[lenY-1] = line1;	//Murs du bas
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		double x,y;
		
		for ( int i = 0; i < lenY; i++)
		{
			for(int j = 0; j < lenX; j++)
			{
				x = 32*j;
				y = 32*i;						
				switch (map[i][j]) {
				case 0 :  g.drawImage(sprite0, (float)x, (float)y);
				break;
				case 1 :  g.drawImage(sprite1, (float)x, (float)y);
				break;
				case 2 :  g.drawImage(sprite2, (float)x, (float)y);
				break;
				case 3 :  g.setColor(Color.blue);g.fillRect((float)(x), (float)(y),(float) width, (float)height);
				break;
				}
			}
		}

	}

	protected void update(GameContainer container, StateBasedGame game, int delta) {

	}
	
	public int getCase(int ligne, int colonne) {
		if (ligne > lenY -1|| colonne > lenX - 1) {
			return -1; //Dépassement de la carte
		} else {
			return map[ligne][colonne];
		}
	}
	
	
	
	public double getX(int colonne) {
		//Donne le x du point en haut à gauche de la case à la colonne 'colonne'
		return 32*colonne;
	}
	
	public double getY(int ligne) {
		//Donne le y du point en haut à gauche de la case à la ligne 'ligne'
		return (32*ligne);
	}

}
