package bug;

import JPlay.Sprite;
import java.util.Random;

public class Bug extends Sprite{
    private boolean tele, clone;
    
    public Bug(){
        super("images/bug.png",5);
        setX(Math.abs(new Random().nextInt(Jogo.WIDTH - getHeight())));
        setY(Jogo.HEIGHT - getHeight());
        setVel(0.1);
        setClone(false);
        setTele(false);
        this.setTimeChangeFrame(2);
        this.setRepeatAnimation(true);
    }
    
    protected void setTele(boolean x){tele = x;}
    protected boolean getTele(){return tele;}
    protected void setClone(boolean x){clone = x;}
    protected boolean getClone(){return clone;}
    protected void setX(double x){this.x = x;}
    protected double getX(){return this.x;}
    protected void setY(double y){this.y = y;}
    protected double getY(){return this.y;}
    protected void setVel(double x){this.setVelocityY(x);}
    protected double getVel(){return this.getVelocityY();}
    protected int getWidth(){return this.width;}
    protected int getHeight(){return this.height;}
    
    
    
 
}
