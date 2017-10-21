package towerDefense;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class ChooseTower {
	int x;
	int y;
	int taille;
	
	public ChooseTower(int abs, int ord, int tai){
		x = abs;
		y = ord;
		taille = tai;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g){
		for(int i = 0; i < 4; i++){
			g.fillRect((float)x, (float)y, taille, taille);
		}
	}

}
