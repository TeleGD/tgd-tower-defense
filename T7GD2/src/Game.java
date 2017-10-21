package snake;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;


public class Game extends StateBasedGame {

	public static void main(String[] args) throws SlickException {
        System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
		AppGameContainer app = new AppGameContainer(new Game(),1280,720,false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(true);
		app.start();
	}

	public Game() {
		super("POULETZ0R");
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {

		addState(new World());

	}

}
