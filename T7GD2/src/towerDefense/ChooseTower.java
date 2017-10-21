package towerDefense;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class ChooseTower {
	int x;
	int y;
	int lenX;
	int lenY;
	
	public ChooseTower(){
		lenX = (int)(1280 / 32);
		lenY = (int)(720 / 32);
		x = 0 + 2*lenX;
		y = 720 - 4*lenX;
		//x = 0;
		//y = 0;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g){
		g.setColor(Color.red);
		for(int i = 0; i < 4; i++){
			g.fillRect((float)x + i*4*lenX, (float)y, 2*(float)lenX, 2*(float)lenX);
		}
	}

}
