package ua.com.finaly.Player;

import ua.com.finaly.Anketa;
import ua.com.finaly.ShipClass;

import java.util.List;
import java.util.stream.Collectors;

public class CreateShip {
    public static void Create(List<ButtonOnGrid> button, Anketa player) {
        player.getShipList().clear();
        player.setCount1(0);
        player.setCount2(0);
        player.setCount3(0);
        player.setCount4(0);
        for (int i=1;i<11;i++){
            if (player.getCount4()==1) break;
            int finalI = i;
            for (int j=1;j<8;j++) {
                int finalJ = j;
                if (button.stream().filter(x->x.getX()==finalI).filter(y->y.getY()==finalJ||
                        y.getY()==finalJ+1||y.getY()==finalJ+2||y.getY()==finalJ+3).count()==4){
                    player.getShipList().add(new ShipClass(finalI,finalJ,finalI,finalJ+1,
                            finalI,finalJ+2,finalI,finalJ+3));
                    button.removeAll(button.stream().filter(x->x.getX()==finalI).filter(y->y.getY()==finalJ||
                            y.getY()==finalJ+1||y.getY()==finalJ+2||y.getY()==finalJ+3).collect(Collectors.toList()));
                    player.setCount4(player.getCount4()+1);
                }
                if (player.getCount4()==1) break;
                if (button.stream().filter(x->x.getY()==finalI).filter(y->y.getX()==finalJ||
                        y.getX()==finalJ+1||y.getX()==finalJ+2||y.getX()==finalJ+3).count()==4){
                    player.getShipList().add(new ShipClass(finalJ,finalI,finalJ+1,finalI,
                            finalJ+2,finalI,finalJ+3,finalI));
                    button.removeAll(button.stream().filter(x->x.getY()==finalI).filter(y->y.getX()==finalJ||
                            y.getX()==finalJ+1||y.getX()==finalJ+2||y.getX()==finalJ+3).collect(Collectors.toList()));
                    player.setCount4(player.getCount4()+1);
                }
                if (player.getCount4()==1) break;
            }
        }

        for (int i=1;i<11;i++){
            if (player.getCount3()==2) break;
            int finalI = i;
            for (int j=1;j<9;j++) {
                int finalJ = j;
                if (button.stream().filter(x->x.getX()==finalI).filter(y->y.getY()==finalJ||
                        y.getY()==finalJ+1||y.getY()==finalJ+2).count()==3){
                    player.getShipList().add(new ShipClass(finalI,finalJ,finalI,finalJ+1,
                            finalI,finalJ+2));
                    button.removeAll(button.stream().filter(x->x.getX()==finalI).filter(y->y.getY()==finalJ||
                            y.getY()==finalJ+1||y.getY()==finalJ+2).collect(Collectors.toList()));
                    player.setCount3(player.getCount3()+1);
                }
                if (player.getCount3()==2) break;
                if (button.stream().filter(x->x.getY()==finalI).filter(y->y.getX()==finalJ||
                        y.getX()==finalJ+1||y.getX()==finalJ+2).count()==3){
                    player.getShipList().add(new ShipClass(finalJ,finalI,finalJ+1,finalI,
                            finalJ+2,finalI));
                    button.removeAll(button.stream().filter(x->x.getY()==finalI).filter(y->y.getX()==finalJ||
                            y.getX()==finalJ+1||y.getX()==finalJ+2).collect(Collectors.toList()));
                    player.setCount3(player.getCount3()+1);
                }
                if (player.getCount3()==2) break;
            }
        }
        for (int i=1;i<11;i++){
            if (player.getCount2()==3) break;
            int finalI = i;
            for (int j=1;j<10;j++) {
                int finalJ = j;
                if (button.stream().filter(x->x.getX()==finalI).filter(y->y.getY()==finalJ||
                        y.getY()==finalJ+1).count()==2){
                    player.getShipList().add(new ShipClass(finalI,finalJ,finalI,finalJ+1));
                    button.removeAll(button.stream().filter(x->x.getX()==finalI).filter(y->y.getY()==finalJ||
                            y.getY()==finalJ+1).collect(Collectors.toList()));
                    player.setCount2(player.getCount2()+1);
                }
                if (player.getCount2()==3) break;
                if (button.stream().filter(x->x.getY()==finalI).filter(y->y.getX()==finalJ||
                        y.getX()==finalJ+1).count()==2){
                    player.getShipList().add(new ShipClass(finalJ,finalI,finalJ+1,finalI));
                    button.removeAll(button.stream().filter(x->x.getY()==finalI).filter(y->y.getX()==finalJ||
                            y.getX()==finalJ+1).collect(Collectors.toList()));
                    player.setCount2(player.getCount2()+1);
                }
                if (player.getCount2()==3) break;
            }
        }

        for (int i=1;i<11;i++){
            if (player.getCount1()==4) break;
            int finalI = i;
            for (int j=1;j<11;j++) {
                int finalJ = j;
                if (button.stream().filter(x->x.getX()==finalI).filter(y->y.getY()==finalJ).count()==1){
                    player.getShipList().add(new ShipClass(finalI,finalJ));
                    button.removeAll(button.stream().filter(x->x.getX()==finalI).filter(y->y.getY()==finalJ)
                            .collect(Collectors.toList()));
                    player.setCount1(player.getCount1()+1);
                }
                if (player.getCount1()==4) break;
//                if (button.stream().filter(x->x.getY()==finalI).filter(y->y.getX()==finalJ).count()==1){
//                    player.getShipList().add(new ShipClass(finalJ-1,finalI-1));
//                    button.removeAll(button.stream().filter(x->x.getY()==finalI).filter(y->y.getX()==finalJ)
//                            .collect(Collectors.toList()));
//                    player.setCount1(player.getCount1()+1);
//                }
//                if (player.getCount1()==4) break;
            }
        }
        for(ShipClass x:player.getShipList()){
        System.out.println(x);
        }
    }
}
