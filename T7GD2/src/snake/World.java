package snake;

import general.ui.Button;
import general.utils.FontUtils;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Arrays;

public class World extends BasicGameState {

    public MenuMulti menu;
    public static int longueur=1280;
    public static int hauteur=720;

    public static int ID=1;
    private float widthBandeau = 280;

    private static ArrayList<Snake> snakes;

    private TrueTypeFont font = FontUtils.loadSystemFont("Arial", java.awt.Font.BOLD,20);
    private Button replay;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        menu = new MenuMulti();
        menu.init(container, game);


        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(30,30));
        points.add(new Point(30,31));
        points.add(new Point(30,32));
        points.add(new Point(30,33));
        points.add(new Point(30,34));
        points.add(new Point(30,35));
        points.add(new Point(30,36));
        points.add(new Point(30,37));
        points.add(new Point(30,38));


        snakes = new ArrayList<Snake>();

        replay = new Button(container,widthBandeau*World.longueur+20, World.hauteur-100,(1-widthBandeau)*World.longueur-40,40);
        replay.setText("RETOUR AU MENU");
        replay.setBackgroundColor(Color.black);
        replay.setBackgroundColorEntered(Color.white);
        replay.setTextColor(Color.white);
        replay.setTextColorEntered(Color.black);
        replay.setCornerRadius(25);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setColor(new Color(150,150,150));
        g.fillRect(World.longueur-widthBandeau-2,0,widthBandeau,World.hauteur);
        g.setColor(new Color(170,170,170));
        g.fillRect(World.longueur-widthBandeau-4,0,widthBandeau,World.hauteur);
        g.setColor(new Color(200,200,200));
        g.fillRect(World.longueur-widthBandeau,0,widthBandeau,World.hauteur);

        g.setFont(font);
        g.setColor(Color.black);
        g.drawString("SNAKE 3000 ! ",World.longueur-widthBandeau+20,20);
        g.resetFont();


        for(int i=0;i<snakes.size();i++){
            g.drawString(snakes.get(i).nom+" : ",World.longueur-widthBandeau+20,100+30*i);
            snakes.get(i).render(container, game, g);
        }

        replay.render(container, game, g);
        menu.render(container, game, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        menu.update(container, game, delta);
        replay.update(container, game,delta);

        for(int i=0;i<snakes.size();i++){
            snakes.get(i).update(container, game,delta);
            for(int j = i;j<snakes.size();j++){

                if(collide(snakes.get(i).body.get(0),snakes.get(j))){
                    
                }
            }
        }

    }

    private boolean collide(Point point, Snake snake) {
        for(int i=0;i<snake.body.size();i++)
        {
            if(snake.body.get(i).x==point.x && snake.body.get(i).y==point.y){
                return true;
            }
        }
        return false;
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

    public static void setSnakes(Snake[] snake){
        snakes = new ArrayList<Snake>(Arrays.asList(snake));
    }

}
