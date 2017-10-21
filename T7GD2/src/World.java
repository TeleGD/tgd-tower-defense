

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class World extends BasicGameState {

	private Player robert;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		robert = new Player();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		robert.render(container, game, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		robert.update(container,game,delta);
	}

	@Override
	public int getID() {
		// Aucune idée
		return 0;
		
	}
	
	public void keyReleased(int key, char c){
		robert.keyReleased(key,c);
	}
	
	public void keyPressed(int key, char c){
		robert.keyPressed(key, c);
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}
	}
	
	

}
