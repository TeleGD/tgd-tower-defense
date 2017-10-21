package snake;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import general.ui.TextField;
import general.ui.TextField.EnterActionListener;

public class MenuMulti {
	
	public int hauteurMenu=World.hauteur/2;
	public int longueurMenu=World.longueur/4;
	public int debutx=(World.longueur-longueurMenu)/2+longueurMenu/10;
	public int debuty=(World.hauteur-hauteurMenu)/2+hauteurMenu/10;
	public int debutdroiteansx=(World.longueur+longueurMenu)/2-longueurMenu/10-longueurMenu/8;
	public TextField nbrJoueurs;
	public int nJoueur=0;
	public int pas = World.hauteur/20;
	public int yn;
	public String[] nomsJoueurs;
	public TextField[] fieldNomsJoueurs;
	public int debutNom = World.longueur/2 - longueurMenu/10;
	
	public MenuMulti() {
		
	}
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		nbrJoueurs = new TextField(container, debutdroiteansx, debuty,longueurMenu/8, hauteurMenu/15);
		nbrJoueurs.setPlaceHolder("");
		nbrJoueurs.setText("0");
		nbrJoueurs.setPadding(2, 2, 0, 15);
		nbrJoueurs.setMaxNumberOfLetter(1);
		nbrJoueurs.setEnterActionListener(new EnterActionListener() {

			@Override
			public void onEnterPressed() {
				nJoueur = Integer.parseInt(nbrJoueurs.getText());
				nomsJoueurs=new String[nJoueur];
				fieldNomsJoueurs=new TextField[nJoueur];
				for (int i = 0;i<nJoueur;i+=1) {
					yn = debuty + (i+1)*pas;
					fieldNomsJoueurs[i] = new TextField(container , debutNom , yn , longueurMenu/2 , hauteurMenu/15 );
					fieldNomsJoueurs[i].setText("Joueur "+(i+1));
					fieldNomsJoueurs[i].setPadding(2, 2, 0, 15);
					nbrJoueurs.setMaxNumberOfLetter(30);
				}
			}});
		
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(new Color(0,0,255));
		g.fillRect((World.longueur-longueurMenu)/2, (World.hauteur-hauteurMenu)/2, longueurMenu, hauteurMenu);
		g.setColor(new Color(0,0,0));
		g.drawString("nombre de joueurs : ", debutx, debuty);
		nbrJoueurs.render(container, game, g);
		
		for (int i = 1;i<=nJoueur;i+=1) {
			yn = debuty + i*pas;
			g.setColor(new Color(0,0,0));
			if (fieldNomsJoueurs[i-1]!=null) {
				g.drawString("nom joueur "+i,debutx,yn);
				fieldNomsJoueurs[i-1].render(container, game, g);
			}
		}
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		nbrJoueurs.update(container, game, delta);
		
	}

}
