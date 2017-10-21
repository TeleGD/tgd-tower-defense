package towerDefense;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ChooseTower {
	int x;
	int y;
	int lenX;
	int lenY;

	private Image sprite;
	private int type = 1;
	private int nbTower = 4;
	
	public ChooseTower() throws SlickException{
		lenX = 16;
		x = 0 + 2*lenX;
		y = 720 - 6*lenX;
		try{
			sprite = new Image("images/TowerDefense/TowerModel"+type+".png");
		}catch (SlickException e) {
			e.printStackTrace();
		}
		//x = 0;
		//y = 0;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g){
		g.setColor(Color.red);
		int i;
		for(i = 0; i < nbTower; i++){
			g.fillRect((float)x + i*7*lenX, (float)y, (float)lenX*5, (float)lenX*5);
		}
		
		for(i = 0; i < nbTower; i++){
			g.drawImage(sprite, (float)x + i*7*lenX + 8, (float)y + 8);
		}
	}
	
	public boolean clik(int abs, int ord){
		if(ord > y){
			if(abs > x){
				for(int i = 0; i < nbTower; i++){
					if(abs > ((float)x + i*7*lenX) && abs < ((float)x + i*7*lenX + 5*lenX)){
						System.out.println("ok");
						return true;
					}
				}
			}
		}
		return false;
	}

}
