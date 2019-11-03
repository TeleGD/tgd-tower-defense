package general;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import menus.MainMenu;
import menus.WelcomeMenu;

public class Main extends StateBasedGame{

	public static int longueur=1280;
	public static int hauteur=720;

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main(),longueur, hauteur, false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(true);
		app.start();
	}


	public Main() {
		super("R2-T7G-D2");
	}



	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new WelcomeMenu());
		addState(new MainMenu());
		addState(new games.towerDefense.World());
		addState(new games.towerDefense.TowerEnd());

		this.enterState(WelcomeMenu.ID);
	}
}
