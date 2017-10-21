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
	int ligne, colonne;

	private Image sprite;
	private int type = 1;
	private int nbTower = 4;
	private int choose;
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
		}catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g){
		g.setColor(Color.yellow);
		int i;
		
		g.fillRect(0, (float)y-8, (float)1280, (float)(720 - ((float)y-8)));

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
	
	public void update(int abs, int ord) throws SlickException {
		click(abs, ord);
	}
	
	public void click(int abs, int ord){
		if(ord >= y){
			if(abs >= x){
				for(int i = 0; i < nbTower; i++){
					if(abs >= ((float)x + i*7*lenX) && abs <= ((float)x + i*7*lenX + 5*lenX)){
						chooseOld = choose;
						choose = i+1;
						if(choose != chooseOld || deselect == false){
							System.out.println("Vous avez choisi la tour nÂ°"+choose);
							deselect = true;
						}else{
							deselect = false;
							System.out.println("Vous avez dÃ©selectionnÃ© une tour");
						}
					}
				}
			}
		}
	}

	public int getChoose() {
		return choose;
	}

	public int getChooseOld() {
		return chooseOld;
	}
	
	public void getTile (double X, double Y) {
		// Indique les coordonnée ligne colonne dans un tableau de 2 éléments
		ligne = (int)(X / 32);
		colonne = (int)(Y /32);
		
	}
}
