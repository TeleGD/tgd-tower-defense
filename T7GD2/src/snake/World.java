package snake;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class World extends BasicGameState {

	public MenuMulti menu;
	public static int longueur=1280;
	public static int hauteur=720;
	
	public static int ID=1;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		menu = new MenuMulti();
		menu.init(container, game);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		menu.render(container, game, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		menu.update(container, game, delta);
	}

	@Override
	public int getID() {
		return 1;
	}
	
	public void keyReleased(int key, char c){
	}
	
	public void keyPressed(int key, char c){

	}
	
	public static void reset() {
		
	}

}
