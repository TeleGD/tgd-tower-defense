package towerDefense;

import java.util.ArrayList;

import org.lwjgl.Sys;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class World extends BasicGameState {
	public static int ID = 2;
	static ArrayList<Enemy> enemies, tempEnemies;
	static ArrayList<Projectile> projectiles, tempProjectiles;
	static ArrayList<Tower> towers, tempTowers;
	
	//A VIRER
	private Level l;
	private Tower t;
	private ChooseTower c;

	private Enemy e;
	private Projectile p;
	
	int ab;
	int or;
	boolean change;
	Input input;
	
	public int getID(){
		return ID;
	}
	
	public static void reset(){
		
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		enemies = new ArrayList<Enemy>();
		projectiles = new ArrayList<Projectile>();
		towers = new ArrayList<Tower>();
		tempTowers = new ArrayList<Tower>();
		tempEnemies = new ArrayList<Enemy>();
		tempProjectiles = new ArrayList<Projectile>();
		
		l = new Level();
		t = new Tower((double)10, (double)10, (double)1, (double)1, (double)10, 1);
		c = new ChooseTower();
		
		towers.add(t);
		e = new Enemy(2, 1, l);
		enemies.add(e);
		p = new Projectile(10,10,e,1);
		projectiles.add(p);
		
		ab = 0;
		or = 0;
		change = false;
		input = container.getInput();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		l.render(container,game,g);
		c.render(container, game, g);
		
		for(Tower t : towers){
			t.render(container, game, g);
		}
		for(Enemy e : enemies){
			e.render(container,game,g);
		}
		for(Projectile p : projectiles){
			p.render(container,game,g);
		}
		
	}

	public void updateArrays(){
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
		updateArrays();
		for(Tower t: towers){
			t.update(container, game, delta);
		}
		for(Enemy e : tempEnemies){
			e.update(container, game, delta);
		}
		for(Projectile p : tempProjectiles){
			p.update(container, game, delta);
		}
		//System.out.println(delta);
		
		change = false;
		
		
		if(input.isMousePressed(0)){
			
			changeMouse();
			change = c.clik(ab, or);
			if(change){
				System.out.println("chhhhhhhhhhhhhhhhhhhhhhhhhaaaaaaaaaaaaaatttttt");
			}
		}
		
	}
	
	public void keyReleased(int key, char c){
	}
	
	public void keyPressed(int key, char c){

	}
	
	public void changeMouse(){
		ab = input.getMouseX();
		or = input.getMouseY();
	}
}
