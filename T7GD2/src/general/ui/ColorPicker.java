package general.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ColorPicker extends TGDComponent {


    private Color colorSelected ;

    private int[] c = new int[]{0,0,0,255};

    public ColorPicker(GameContainer container, float x, float y, float width, float height) {
        super(container, x, y, width, height);

    }



    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        super.render(container, game, g);
        float h = height-paddingTop-paddingBottom;
        float w = width -paddingLeft-paddingRight;

        for(int i=0;i<4;i++){
            g.setColor(Color.black);
            g.fillRect(x+paddingLeft,y+paddingTop+h/5*i,w,h/5);
            if(i == 0)
                g.setColor(new Color(c[i],0,0,255));
            else if (i==1)
                g.setColor(new Color(0,c[i],0,255));
            else if (i==2)
                g.setColor(new Color(0,0,c[i],255));
            else if (i==3)
                g.setColor(new Color(255,255,255,c[i]));

            g.fillRect(x+paddingLeft+1,y+paddingTop+h/5*i+1,w-2,h/5-2);
            g.setColor(Color.black);
            g.fillRect((float) (x+paddingLeft+1+c[i]*w/255.0)-2, (float) (y+paddingTop+h/5*i+1),4,h/5-2);
            g.setColor(Color.white);

            g.drawString(""+c[i],x+width/2-10,y+paddingTop+h/5*i+20);

        }


        g.setColor(Color.black);
        g.fillRect(x+paddingLeft,y+paddingTop+4*h/5,w,h/5);

        g.setColor(new Color(c[0],c[1],c[2],c[3]));
        g.fillRect(x+paddingLeft+1,y+paddingTop+4*h/5+1,w-2,h/5-2);


    }


    public void setColorSelected(Color colorSelected) {
        this.colorSelected = colorSelected;
        this.c[0] = colorSelected.getRed();
        this.c[1] = colorSelected.getGreen();
        this.c[2] = colorSelected.getBlue();
        this.c[3] = colorSelected.getAlpha();

    }

    public Color getColorSelected() {
        colorSelected = new Color(c[0],c[1],c[2],c[3]);
        return colorSelected;
    }

    @Override
    public void mousePressed(int arg0, int xM, int yM) {
        super.mousePressed(arg0, xM, yM);
        changeColor(xM,yM);
    }

    @Override
    public void mouseDragged(int arg0, int arg1, int xM, int yM) {
        super.mouseDragged(arg0, arg1, xM, yM);
        changeColor(xM,yM);
    }

    private void changeColor(int xM, int yM) {
        if(contains(xM,yM)){

            if(xM>x+paddingLeft && xM<x+width-paddingLeft-paddingRight){
                int row = (int) ((yM-y)/((height-paddingTop-paddingBottom)/5));
                int etat = (int) (255*((double)(xM-x)/(double)(width-paddingLeft-paddingRight)));

                System.out.println("etat = "+etat);
                if(row<4){
                    c[row] = etat;
                }
            }


            /*

            if((yM-y)/(height-paddingLeft-paddingRight)<4){
                c[(int) ((yM-y)/(height))] = (int) (255*(x/width));
            }
            */
        }
    }
}
