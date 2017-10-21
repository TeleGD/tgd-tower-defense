package towerDefense;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Level {
	
	int[][] map;
	float width, height;
	int lenX, lenY;
	
	public Level() {
		width = 32;
		height = 32;
		lenX = (int)(1280 / width);
		lenY = (int)(720 / height);
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
		for (int i=0; i < (int)(lenY / 4); i++)
		{
			map[i] = genereLine(0,1,1,2);
			map[i+1] = genereLine(0,1,1,lenX - 1);
			map[i+2] = genereLine(0,1,lenX - 2,lenX - 1);
			map[i+3] = genereLine(0,1,1,lenX - 1);
		}
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.green);
		for ( int i = 0; i < lenY; i++)
		{
			for(int j = 0; j < lenX; i++)
			{
				g.fillRect((float)(32*j), (float)(720-32*(i+1)),(float) width, (float)height);
			}
		}

	}

	protected void update(GameContainer container, StateBasedGame game, int delta) {

	}
	
	public int getCase(int ligne, int colonne) {
		return map[ligne][colonne];
	}
	

}
