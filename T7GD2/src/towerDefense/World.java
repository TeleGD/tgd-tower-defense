package towerDefense;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class World extends BasicGameState {
	public static int ID = 2;
	public static ArrayList<Enemy> enemies, tempEnemies;
	public static ArrayList<Projectile> projectiles, tempProjectiles;
	public static ArrayList<Tower> towers, tempTowers;
	private Player player;
	
	//A VIRER
	private Level l;
	private Tower t;
	private ChooseTower c;
	
	
	int ligne, colonne;
	private int incomeDelay;
	private Enemy e;
	private Projectile p;
	
	int ab;
	int or;
	Input input;
	boolean atarashi;
	
	@Override
	public int getID(){
		return ID;
	}
	
	public static void reset(){
		enemies = new ArrayList<Enemy>();
		projectiles = new ArrayList<Projectile>();
		towers = new ArrayList<Tower>();
		tempTowers = new ArrayList<Tower>();
		tempEnemies = new ArrayList<Enemy>();
		tempProjectiles = new ArrayList<Projectile>();
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		reset();		
		player = new Player(20,200);
		l = new Level(player);
		c = new ChooseTower(player);
		ab = 0;
		or = 0;
		input = container.getInput();
		atarashi = false;
		incomeDelay = 1000;
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		l.render(container,game,g);
		c.render(container, game, g);
		player.render(container, game, g);
		for(Tower t : towers){
			t.render(container, game, g);
		}
		for(Enemy e : enemies){
			e.render(container,game,g);
		}
		for(Projectile p : projectiles){
			p.render(container,game,g);
		}
		g.drawString("Vagues : "+l.getnVague(), 1150, 656);

	}

	public void updateArrays(){
		//Useless method
		tempTowers.clear();
		tempTowers.addAll(towers);
		tempEnemies.clear();
		tempEnemies.addAll(enemies);
		tempProjectiles.clear();
		tempProjectiles.addAll(projectiles);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		l.update(container, game, delta);
		player.update(container, game, delta);
		tempTowers.clear();
		tempTowers.addAll(towers);
		for(Tower t: tempTowers){
			t.update(container, game, delta);
		}
		
		tempEnemies.clear();
		tempEnemies.addAll(enemies);
		for(Enemy e : tempEnemies){
			e.update(container, game, delta);
		}
		
		tempProjectiles.clear();
		tempProjectiles.addAll(projectiles);
		for(Projectile p : tempProjectiles){
			p.update(container, game, delta);
		}
				
		
		if(input.isMousePressed(0)){
			changeMouse();
			atarashi = c.update(ab, or);
			
			if(ab > 0 && ab < 1280 && or > 0 && or < 616 && atarashi == true){				
				getTile(ab, or);

				if(l.getCase(ligne, colonne) == 1){
					Tower newtour = new Tower(32*colonne, 32*ligne, c.choose);
					if(!(newtour.existeDeja(towers))){
						if(player.getGold() - c.choose * 50 >= 0){
							towers.add(newtour);
							player.earnGold(-(c.choose*50));
						}
					}
				}
			}
		}
		
		incomeDelay -= delta;
		if(incomeDelay <=0){
			incomeDelay = 1000;
			player.earnGold(5);
		}
		
		if(player.getLives() == 0) {
			((TowerEnd) game.getState(20)).changeWave(l.getnVague());
			game.enterState(TowerEnd.ID);
		}

	}
	
	public void changeMouse(){
		ab = input.getMouseX();
		or = input.getMouseY();
	}
	
	public void getTile (int X, int Y) {
		// Indique les coordonn�e ligne colonne dans un tableau de 2 �l�ments
		ligne = Y / 32;
		colonne = X /32;
		
	}
}
