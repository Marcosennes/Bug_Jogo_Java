package bug;

import JPlay.Sprite;
import java.util.ArrayList;

public final class Sysadm extends Sprite{
    protected static double X,Y,
            WIDTH, HEIGHT;
    private int deaths, kills, lives;
    private final ArrayList<Shot> shot = new ArrayList();
    
    public Sysadm(){
        super("images/sysadm.png",12);
        WIDTH = this.width;
        HEIGHT = this.height;
        setX((Jogo.WIDTH - this.width) / 2);
        setKills(0);
        setDeaths(0);
        setVel(0.3);
        setY(50);
        setLives(5);
        this.setPosition(X, Y);
        this.setInitialFrame(0);
    }
    
    protected void setVel(double x){this.setVelocityX(x);}
    protected double getVel(){return this.getVelocityX();}
    protected void setLives(int x){lives = x;}
    protected int getLives(){return lives - deaths;}
    protected void setX(double x){X = x;}
    protected double getX(){return X;}
    protected void setY(double y){Y = y;}
    protected double getY(){return Y;}
    private void setDeaths(int x){deaths = x;}
    public int getDeaths(){return deaths;}
    private void setKills(int x){kills = x;}
    protected int getKills(){return kills;}
    
     
    protected boolean kill(Bug b){
        for(int i = 0; i < shot.size(); i++){
            Shot t = shot.get(i);
            if(t.collided(b)){
                shot.remove(t);
                kills += 1;
                return true;
            }
        }
        return false;
    }
    
    protected void die(){setDeaths(getDeaths() + 1);}
    protected boolean dead(){return !(getLives() > 0);}
    protected void shoot(){shot.add(new Shot());}
    
    @Override 
    public void draw(){
        super.draw();
        for(int i = 0; i < shot.size(); i++){
            Shot t = shot.get(i);
            t.fall();
            t.draw();
            if(t.isOnFloor()) shot.remove(t);
        }
    }
    
    @Override
    public void moveX(){
        super.moveX();
        X = this.x;
        Y = this.y;
        if(this.getStateOfX() == Sprite.RIGHT && (this.getX() + this.width) < Jogo.WIDTH){
            this.setCurrFrame((this.getCurrFrame() + 1)%4);
        }
        else if(this.getStateOfX() == Sprite.LEFT && this.getX() > 1){
                this.setCurrFrame((this.getCurrFrame() + 1)%4 + 6);
            }
            else{
                if(this.getCurrFrame() >= 6) this.setCurrFrame(6);
                else this.setCurrFrame(0);
            }
        }
}