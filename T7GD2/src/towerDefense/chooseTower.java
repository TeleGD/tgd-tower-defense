package towerDefense;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class chooseTower {
	int x;
	int y;
	
	public chooseTower(int abs, int ord){
		x = abs;
		y = ord;
	}
	
	
	public void render(GameContainer container, StateBasedGame game, Graphics g){
		g.setColor(Color.red);
		for(int i = 0; i < 4; i++){
			g.fillRect((float)x, (float)y, 10, 10);
		}
	}

}
