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
	static ArrayList<Enemy> enemies;
	static ArrayList<Projectile> projectiles;
	static ArrayList<Tower> towers;
	
	//A VIRER
	private Level l;
	private Tower t;
	
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
		l = new Level();
		t = new Tower((double)10, (double)10, (double)1, (double)1, (double)10, 1);
		towers.add(t);
		//System.out.println("Level created");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		l.render(container,game,g);
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

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		l.update(container, game, delta);
		for(Tower t: towers){
			t.update(container, game, delta);
		}
		for(Enemy e : enemies){
			e.update(container, game, delta);
		}
		for(Projectile p : projectiles){
			p.update(container, game, delta);
		}
	}
	
	public void keyReleased(int key, char c){
	}
	
	public void keyPressed(int key, char c){

	}
}
