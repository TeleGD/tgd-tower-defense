package snake;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MenuMulti {
	
	public int hauteurMenu=World.hauteur/2;
	public int longueurMenu=World.longueur/4;
	public int debutx=(World.longueur-longueurMenu)/2+longueurMenu/10;
	public int debuty=(World.hauteur-hauteurMenu)/2+hauteurMenu/10;
	public MenuMulti() {
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(new Color(0,0,255));
		g.fillRect((World.longueur-longueurMenu)/2, (World.hauteur-hauteurMenu)/2, longueurMenu, hauteurMenu);
		g.setColor(new Color(0,0,0));
		g.drawString("nombre de joueurs : ", debutx, debuty);
		
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
	}

}
