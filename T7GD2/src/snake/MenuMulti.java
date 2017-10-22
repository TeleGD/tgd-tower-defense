package snake;

import general.utils.FontUtils;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import general.ui.Button;
import general.ui.ColorPicker;
import general.ui.TGDComponent;
import general.ui.TGDComponent.OnClickListener;
import general.ui.TextField;
import general.ui.TextField.EnterActionListener;

public class MenuMulti {
	
	private int longueurJeu=(int)(World.longueur*0.8);
	
	public int hauteurMenu=(int)(World.hauteur/1.45);
	public int longueurMenu=World.longueur/2;
	public int debutx=(longueurJeu-longueurMenu)/2+longueurMenu/15;
	public int debuty=(World.hauteur-hauteurMenu)/2+hauteurMenu/15;
	public int debutdroiteansx=(longueurJeu+longueurMenu)/2-longueurMenu/10-longueurMenu/16;
	public TextField nbrJoueurs;
	public int nJoueur=9;
	public int pas = World.hauteur/20;
	public int yn;
	public String[] nomsJoueurs;
	public TextField[] fieldNomsJoueurs;

	public int debutNom = longueurJeu/2 - longueurMenu/10;
	private Button boutonStart;
	private Snake[] joueurs;
	public boolean enleve=false;
	private int[] touchesDefaut = {Input.KEY_RIGHT,Input.KEY_LEFT, Input.KEY_Z, Input.KEY_A, Input.KEY_P, Input.KEY_O, Input.KEY_X, Input.KEY_W, Input.KEY_N, Input.KEY_B, Input.KEY_NUMPAD2,Input.KEY_NUMPAD1,Input.KEY_NUMPAD9,Input.KEY_NUMPAD8,Input.KEY_U,Input.KEY_Y,Input.KEY_G,Input.KEY_F};
	private String[] valTouchesDefaut = {"->","<-","Z","A","P","O","X","W","N","B","2","1","9","8","U","Y","G","F"};
	private Color[] couleursDefaut =new Color[] {Color.white, Color.blue,Color.red,Color.green,Color.pink,Color.yellow,Color.cyan,Color.orange,Color.magenta};
	private Color[] couleursJoueurs = couleursDefaut;
	private Button[] choixCouleur;
	private ColorPicker picker;
    private TrueTypeFont fontTitle = FontUtils.loadSystemFont("Arial", java.awt.Font.BOLD,25);
    private TrueTypeFont fontNbJoueurs = FontUtils.loadSystemFont("Arial", java.awt.Font.BOLD,20);

    public MenuMulti() {
		
	}
	
	public void init(final GameContainer container, StateBasedGame game) throws SlickException {
		nbrJoueurs = new TextField(container, debutdroiteansx, debuty+pas-5,longueurMenu/20, TGDComponent.AUTOMATIC);
		nbrJoueurs.setPlaceHolder("");
		nbrJoueurs.setHasFocus(true);
		nbrJoueurs.setText(""+nJoueur);
		//nbrJoueurs.setPadding(5, 5, 0, 23);
		nbrJoueurs.setOnlyFigures(true);
		nbrJoueurs.setMaxNumberOfLetter(1);
		nbrJoueurs.setEnterActionListener(new EnterActionListener() {

			@Override
			public void onEnterPressed() {
				if (nbrJoueurs.getText().length() ==1) {
					nJoueur = Integer.parseInt(nbrJoueurs.getText());
				}

				createJoueurs(container);
			}});
		
		
		boutonStart = new Button("START",container,longueurJeu/2-longueurMenu/6,(World.hauteur+hauteurMenu)/2-8*hauteurMenu/75,longueurMenu/3,hauteurMenu/15);
		boutonStart.setBackgroundColor(new Color(0,200,0));
		boutonStart.setVisible(true);
		boutonStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(TGDComponent componenent) {
				if (nJoueur!=0) {
					// TODO Auto-generated method stub
					joueurs = new Snake[nJoueur];
					for (int i = 0;i<nJoueur;i+=1) {
						joueurs[i] = new Snake(couleursJoueurs[i],(100-nJoueur)/(nJoueur+1) + i*((100-nJoueur)/(nJoueur+1)+1),touchesDefaut[2*i],touchesDefaut[2*i+1],10,fieldNomsJoueurs[i].getText(),10);
					}
					World.setSnakes(joueurs);
					enleve = true;
				}
                boutonStart.setVisible(false);

            }});

        picker = new ColorPicker(container,debutx,0,World.longueur/5,World.hauteur/4);
        picker.setVisible(false);
        createJoueurs(container);
    }

    private void createJoueurs(GameContainer container) {
        nomsJoueurs=new String[nJoueur];
        fieldNomsJoueurs=new TextField[nJoueur];
        choixCouleur = new Button[nJoueur];

        for (int i = 0;i<nJoueur;i+=1) {
            yn = debuty + (i+2)*pas+10;
            fieldNomsJoueurs[i] = new TextField(container , debutNom , yn , longueurMenu/3 , TGDComponent.AUTOMATIC );
            fieldNomsJoueurs[i].setBackgroundColor(Color.black);
            fieldNomsJoueurs[i].setTextColor(couleursJoueurs[i]);
            fieldNomsJoueurs[i].setText("Joueur "+(i+1));
            fieldNomsJoueurs[i].setPlaceHolder("Entrer le nom du joueur");
            fieldNomsJoueurs[i].setMaxNumberOfLetter(20);

            final int h=i;
            choixCouleur[i] = new Button(container,longueurJeu/2+longueurMenu/4,yn,hauteurMenu/15,hauteurMenu/15);
            choixCouleur[i].setBackgroundColor(couleursJoueurs[i]);
            choixCouleur[i].setBackgroundColorEntered(new Color(255,255,255,100));
            choixCouleur[i].setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(TGDComponent componenent) {

                    System.out.println("aller  clic");
                    picker.setY(choixCouleur[h].getY());
                    picker.setColorSelected(couleursJoueurs[h]);
                    picker.setX(longueurJeu/2+longueurMenu/3);
                    picker.setVisible(true);
                    picker.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(TGDComponent componenent) {

                            couleursJoueurs[h]=picker.getColorSelected();
                            choixCouleur[h].setBackgroundColor(picker.getColorSelected());
                            fieldNomsJoueurs[h].setTextColor(picker.getColorSelected());
                            picker.setVisible(false);
                        }});
                }});
        }
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		if (!enleve) {

            g.setColor(new Color(255,255,255));
            g.fillRect((longueurJeu-longueurMenu)/2-4, (World.hauteur-hauteurMenu)/2-4, longueurMenu+8, hauteurMenu+9);
            g.setColor(new Color(100,100,200));
			g.fillRect((longueurJeu-longueurMenu)/2, (World.hauteur-hauteurMenu)/2, longueurMenu, hauteurMenu);
            g.setColor(new Color(255,255,255));
            g.setFont(fontTitle);
            g.drawString("Configuration", longueurJeu/2-g.getFont().getWidth("Configuration")/2, debuty-pas/2);
            g.setFont(fontNbJoueurs);

			g.setColor(new Color(0,0,0));
			g.drawString("Nombre de joueurs : ", debutx, debuty+pas);
			nbrJoueurs.render(container, game, g);
			g.resetFont();
			for (int i = 1;i<=nJoueur;i+=1) {
				yn = debuty + (i+1)*pas+10;
				g.setColor(new Color(0,0,0));
				if (fieldNomsJoueurs[i-1]!=null) {
					g.drawString("Nom Joueur "+i+" :",debutx,yn+5);
					g.drawString(valTouchesDefaut[2*i-1]+" - "+valTouchesDefaut[2*i-2], longueurJeu/2+longueurMenu/3, yn+5);
					fieldNomsJoueurs[i-1].render(container, game, g);
					choixCouleur[i-1].render(container, game, g);
                    picker.render(container, game, g);

                }
				
				
			}
			
			boutonStart.render(container, game, g);
		}
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		nbrJoueurs.update(container, game, delta);
		boutonStart.update(container, game, delta);
		picker.update(container, game, delta);
	}

}
