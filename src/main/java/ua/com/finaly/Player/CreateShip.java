package ua.com.finaly.Player;

import ua.com.finaly.Anketa;

import java.util.List;

public class CreateShip {
    public static void Create(List<ButtonOnGrid> button, Anketa player) {
        player.getShipList().clear();
        for (int i=1;i<11;i++){
            int finalI = i;
            for (int j=1;j<8;j++) {
                int finalJ = j;
                if (button.stream().filter(x->x.getX()==finalI).filter(y->y.getY()==finalJ||
                        y.getY()==finalJ+1||y.getY()==finalJ+2||y.getY()==finalJ+3).count()==4){
                }
            }
        }


    }

}
