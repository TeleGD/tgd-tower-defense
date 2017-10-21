package towerDefense;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
	private int wave;
	private int gold;
	private int totalGold;
	private int lives;
	private int boughtTowers;
	
	public Player(int lives, int startGold) {
		this.lives=lives;
		this.gold= startGold;
		this.totalGold = startGold;
		boughtTowers = 0;
		wave = 1;
		
	}
	
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.black);
		g.drawString("Or : "+this.gold, 1100, 628);
		g.drawString("Vies : "+this.lives, 1100, 656);
		g.drawString("Vagues : "+this.wave, 1100, 684);
	}
	
	
	public void buyTower(int price) {
		// Vérifier si on a l'argent puis la retirer
		
		boughtTowers+=1;
	}
	
	
	public void earnGold(int piece) {
		this.gold += piece;
		totalGold += piece;
	}
	
	
	public void damaged(int damage) {
		lives -= damage;
		if (lives <= 0) {
			endGame();
		}
	}
	
	
	public void endGame() {
		// Pop-up : or total, vagues, nb de tours construites/upgrades, 
	}
	
}
