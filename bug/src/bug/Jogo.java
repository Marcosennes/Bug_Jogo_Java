package bug;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import JPlay.Window;
import JPlay.GameImage;
import JPlay.Keyboard;
import JPlay.Sound;
import JPlay.Time;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;

public class Jogo{
    protected static int WIDTH = 800, HEIGHT = 600,
            errPct;
    private long cTime, rTime, bTime,
            respTime, bugTime;
    private double safeZone;
    
    public Jogo(){
        Window window = new Window(WIDTH, HEIGHT);
        Font f= new Font("Arial",1,30);
        Font f2= new Font("Arial",1,17);
        GameImage background = new GameImage("images/tela2.png");
        Keyboard keyb = window.getKeyboard();
        Time time = new Time(0,0,true);
        Sysadm sysadm = new Sysadm();
        ArrayList<Bug> bug = new ArrayList();
      
        window.setTitle("BUG");
        setSafeZone(sysadm.height + sysadm.getY());
        
        setRespTime(3);
        setBugTime(1);
        setCTime(0);
        setBTime(0);
        setRTime(0);
        setErrPct(30);
        
        boolean exec = true, over = false;

        while(exec){

            background.draw();
            window.drawText(String.valueOf(sysadm.getKills()), 710, 40, Color.red,f);
            window.drawText(String.valueOf(sysadm.getLives()), 610, 40, Color.green,f);    
            sysadm.draw();
            if(keyb.keyDown(Keyboard.SPACE_KEY)) sysadm.shoot();
            else sysadm.moveX();
            
            if((getCTime() - getRTime() >= getRespTime())){setRTime(getCTime()); bug.add(new Bug());}
            
            for(int i = 0; i < bug.size(); i++){
                Bug b = bug.get(i);
                b.moveToUp();
                b.draw();
                b.runAnimation();
                if(getSafeZone() >= b.getY()){
                    bug.remove(b);
                    sysadm.die();
                }else if(sysadm.kill(b))
                    if(4 > sysadm.getLives() && (getCTime() - getBTime() >= getBugTime())){
                        if(Math.abs(new Random().nextInt(100)) >= getErrPct()){
                        bug.remove(b); 
                        setBTime(getCTime());
                        }
                    }else bug.remove(b);
            }
    
            if((3 > sysadm.getLives()) && (getCTime() - getBTime() >= getBugTime()) && (!bug.isEmpty())){
                if(Math.abs(new Random().nextInt(100)) >= (getErrPct())*2){
                    int i = Math.abs(new Random().nextInt(bug.size()));
                    Bug b = bug.get(i);
                    if(!b.getTele() && (b.getY()<500 && b.getY()>200)){
                        
                        b.setX(Math.abs(new Random().nextInt(WIDTH - b.getWidth())));
                        b.setTele(true);
                    }
                    setBTime(getCTime());
                }
            }
            if((2 > sysadm.getLives()) && (getCTime() - getBTime() >= getBugTime()) && (!bug.isEmpty())){
                if(Math.abs(new Random().nextInt(100)) >= (getErrPct())*2){
                    int i = Math.abs(new Random().nextInt(bug.size()));
                    Bug b = bug.get(i);
                    if(!b.getClone()){
                        Bug c = new Bug();    
                        if((b.getX() + (2 * b.getWidth())) > WIDTH) c.setX(b.getX() - b.getWidth());
                        else c.setX(b.getX() + b.getWidth());
                        c.setY(b.getY());
                        b.setClone(true);
                        c.setClone(true);
                        bug.add(c);
                    }
                }
                setBTime(getCTime());
            }
          
            setCTime(time.getTotalSecond());
            if(sysadm.dead()){
                background.loadImage("images/tela-azul.jpg");
                new Sound("images/desligando.wav").play();                                  
                background.draw();
                window.delay(1024);
                window.drawText(String.valueOf(sysadm.getKills()), 60,450 , Color.white,f2);

                over = true; exec = false;
            }
            window.display();
            if(keyb.keyDown(Keyboard.ESCAPE_KEY)) exec = false;
        }
        bug.clear();
        while(!keyb.keyDown(Keyboard.ESCAPE_KEY) && over){window.display();}
        window.exit();
    }
    
    private void setRespTime(long x){respTime = x;}
    private long getRespTime(){return respTime;}
    private void setBugTime(long x){bugTime = x;}
    private long getBugTime(){return bugTime;}
    private void setCTime(long x){cTime = x;}
    private long getCTime(){return cTime;}
    private void setRTime(long x){rTime = x;}
    private long getRTime(){return rTime;}
    private void setBTime(long x){bTime = x;}
    private long getBTime(){return bTime;}
    private void setErrPct(int x){errPct = x;}
    private int getErrPct(){return errPct;}
    private void setSafeZone(double x){safeZone = x;}
    private double getSafeZone(){return safeZone;}
    
}
