package bug;

import JPlay.Sprite;

public class Shot extends Sprite{
    public Shot(){
        super("images/teclado.png");
        this.setPosition(Sysadm.X, Sysadm.Y + Sysadm.HEIGHT);
        this.setFloor(Jogo.HEIGHT);
        this.setGravity(0.000098);
    }
}
