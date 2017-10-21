package snake;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import general.ui.TextField;

public class MenuMulti {
	
	public int hauteurMenu=World.hauteur/2;
	public int longueurMenu=World.longueur/4;
	public int debutx=(World.longueur-longueurMenu)/2+longueurMenu/10;
	public int debuty=(World.hauteur-hauteurMenu)/2+hauteurMenu/10;
	public int debutdroiteansx=(World.longueur+longueurMenu)/2-longueurMenu/10;
	public TextField nbrJoueurs;
	
	public MenuMulti() {
		
	}
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		nbrJoueurs = new TextField(container, debutdroiteansx-longueurMenu/8, debuty,longueurMenu/8, hauteurMenu/15);
		nbrJoueurs.setPlaceHolder("");
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(new Color(0,0,255));
		g.fillRect((World.longueur-longueurMenu)/2, (World.hauteur-hauteurMenu)/2, longueurMenu, hauteurMenu);
		g.setColor(new Color(0,0,0));
		g.drawString("nombre de joueurs : ", debutx, debuty);
		nbrJoueurs.render(container, game, g);
		
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		nbrJoueurs.update(container, game, delta);
		
	}

}
