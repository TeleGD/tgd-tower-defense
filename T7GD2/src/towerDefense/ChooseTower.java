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
	private Image sprite2;
	
	private int type = 1;
	private int nbTower = 5;
	public int choose;
	private int chooseOld;
	private boolean deselect;
	
	public ChooseTower() throws SlickException{
		lenX = 16;
		x = 0 + 2*lenX;
		y = 720 - 6*lenX;
		
		choose = 0;
		chooseOld = 0;
		deselect = false;
		
		try{
			sprite = new Image("images/TowerDefense/TowerModel"+type+".png");
			sprite2 = new Image("image/TowerDefence/rockpng");
		}catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g){
		int i;
		
		g.setColor(Color.yellow);		
		g.fillRect(0, (float)y-8, (float)1280, (float)(720 - ((float)y-8)));
		
		for(i = 0; 700 + i*32 < 1280; i++){
			for(int j = 0; y + j * 32 < 720; j++){
				g.drawImage(sprite2, (float)700 + i*32, (float)y +j*32);
			}
		}

		for(i = 0; i < nbTower; i++){
			if(deselect &&  i == choose -1){
				g.setColor(Color.green);
			}else{
				g.setColor(Color.red);
			}
			g.fillRect((float)x + i*7*lenX, (float)y, (float)lenX*5, (float)lenX*5);
		}
		
		for(i = 0; i < nbTower; i++){
			g.drawImage(sprite, (float)x + i*7*lenX + 8, (float)y + 8);
		}
	}
	
	public boolean update(int abs, int ord) throws SlickException {
		return click(abs, ord);
	}
	
	public boolean click(int abs, int ord){
		if(ord >= y){
			if(abs >= x){
				for(int i = 0; i < nbTower; i++){
					if(abs >= ((float)x + i*7*lenX) && abs <= ((float)x + i*7*lenX + 5*lenX)){
						chooseOld = choose;
						choose = i+1;
						if(choose != chooseOld || deselect == false){
							System.out.println("Vous avez choisi la tour n°"+choose);
							deselect = true;
							return true;
						}else{
							deselect = false;
							System.out.println("Vous avez déselectionné une tour");
							return false;
						}
					}
				}
			}
		}
		return deselect;
	}

	public int getChoose() {
		return choose;
	}

	public int getChooseOld() {
		return chooseOld;
	}
}
