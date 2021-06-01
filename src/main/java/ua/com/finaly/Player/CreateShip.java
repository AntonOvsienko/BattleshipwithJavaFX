package ua.com.finaly.Player;

import ua.com.finaly.Anketa;

import java.util.List;

public class CreateShip {
    public static void Create(List<ButtonOnGrid> button, Anketa player) {
        player.getShipList().clear();
        for (int i=1;i<11;i++){
            int finalI = i;
            if (button.stream().filter(y->y.getY()== finalI).count()>=4){

            }
        }
//        List<ButtonOnGrid> ship4x=button.stream().filter(x->x.getX()).collect(Collectors.toList());
        List<ButtonOnGrid> ship3x=button;
        List<ButtonOnGrid> ship3=button;
        List<ButtonOnGrid> ship2=button;
        List<ButtonOnGrid> ship1=button;
        int count4=1;
        int count3=2;
        int count2=3;
        int count1=4;
    }
    public static void xstep4(List <ButtonOnGrid> buttonx,int count){
        int line4;
        for (int i=1;i<11-count;i++){
            for (ButtonOnGrid x1:buttonx){
                if (x1.getX()==i){
                    for (ButtonOnGrid x2:buttonx){
                        if (x2.getX()==i+1){
                            for (ButtonOnGrid x3:buttonx){
                                if (x2.getX()==i+2){

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
