package snake;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import general.ui.Button;
import general.ui.TGDComponent;
import general.ui.TGDComponent.OnClickListener;
import general.ui.TextField;
import general.ui.TextField.EnterActionListener;

public class MenuMulti {
	
	public int hauteurMenu=(int)(World.hauteur/1.5);
	public int longueurMenu=World.longueur/3;
	public int debutx=(World.longueur-longueurMenu)/2+longueurMenu/15;
	public int debuty=(World.hauteur-hauteurMenu)/2+hauteurMenu/10;
	public int debutdroiteansx=(World.longueur+longueurMenu)/2-longueurMenu/10-longueurMenu/8;
	public TextField nbrJoueurs;
	public int nJoueur=0;
	public int pas = World.hauteur/20;
	public int yn;
	public String[] nomsJoueurs;
	public TextField[] fieldNomsJoueurs;
	public int debutNom = World.longueur/2 - longueurMenu/10;
	private Button boutonStart;
	private Snake[] joueurs;
	public boolean enleve=false;
	private int[] touchesDefaut = {Input.KEY_RIGHT,Input.KEY_LEFT, Input.KEY_Z, Input.KEY_A, Input.KEY_P, Input.KEY_O, Input.KEY_X, Input.KEY_W, Input.KEY_N, Input.KEY_B};
	private Color[] couleursDefaut =new Color[] {Color.white, Color.blue,Color.red,Color.green,Color.pink};
	
	public MenuMulti() {
		
	}
	
	public void init(final GameContainer container, StateBasedGame game) throws SlickException {
		nbrJoueurs = new TextField(container, debutdroiteansx, debuty,longueurMenu/8, hauteurMenu/15);
		nbrJoueurs.setPlaceHolder("");
		nbrJoueurs.setText("0");
		nbrJoueurs.setPadding(5, 5, 0, 23);
		nbrJoueurs.setOnlyFigures(true);
		nbrJoueurs.setMaxNumberOfLetter(1);
		nbrJoueurs.setEnterActionListener(new EnterActionListener() {

			@Override
			public void onEnterPressed() {
				if (nbrJoueurs.getText().length() ==1) {
					nJoueur = Integer.parseInt(nbrJoueurs.getText());
				}
				nomsJoueurs=new String[nJoueur];
				fieldNomsJoueurs=new TextField[nJoueur];
				for (int i = 0;i<nJoueur;i+=1) {
					yn = debuty + (i+1)*pas;
					fieldNomsJoueurs[i] = new TextField(container , debutNom , yn , longueurMenu/2 , hauteurMenu/15 );
					fieldNomsJoueurs[i].setBackgroundColor(Color.black);
					fieldNomsJoueurs[i].setTextColor(couleursDefaut[i]);
					fieldNomsJoueurs[i].setText("Joueur "+(i+1));
					fieldNomsJoueurs[i].setPlaceHolder("Entrer le nom du joueur");
					fieldNomsJoueurs[i].setPadding(5, 5, 0, 15);
					fieldNomsJoueurs[i].setOnlyFigures(false);
					fieldNomsJoueurs[i].setMaxNumberOfLetter(20);
				}		
			}});
		
		
		boutonStart = new Button("START",container,World.longueur/2-longueurMenu/6,(World.hauteur+hauteurMenu)/2-8*hauteurMenu/75,longueurMenu/3,hauteurMenu/15);
		boutonStart.setBackgroundColor(Color.green);
		boutonStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(TGDComponent componenent) {
				// TODO Auto-generated method stub
				joueurs = new Snake[nJoueur];
				for (int i = 0;i<nJoueur;i+=1) {
					joueurs[i] = new Snake(couleursDefaut[i],(100-nJoueur)/(nJoueur+1) + i*((100-nJoueur)/(nJoueur+1)+1),touchesDefaut[2*i],touchesDefaut[2*i+1],10,fieldNomsJoueurs[i].getText(),10);
				}
				World.setSnakes(joueurs);
				enleve = true;
			}});
		
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		if (!enleve) {
			g.setColor(new Color(0,0,255));
			g.fillRect((World.longueur-longueurMenu)/2, (World.hauteur-hauteurMenu)/2, longueurMenu, hauteurMenu);
			g.setColor(new Color(0,0,0));
			g.drawString("nombre de joueurs : ", debutx, debuty);
			nbrJoueurs.render(container, game, g);
			
			for (int i = 1;i<=nJoueur;i+=1) {
				yn = debuty + i*pas;
				g.setColor(new Color(0,0,0));
				if (fieldNomsJoueurs[i-1]!=null) {
					g.drawString("Nom Joueur n°"+i+" :",debutx,yn+5);
					fieldNomsJoueurs[i-1].render(container, game, g);
				}
			}
			
			boutonStart.render(container, game, g);
		}
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		nbrJoueurs.update(container, game, delta);
		boutonStart.update(container, game, delta);
		
	}

}
