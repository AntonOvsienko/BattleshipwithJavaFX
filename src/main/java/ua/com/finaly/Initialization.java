package ua.com.finaly;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import ua.com.finaly.Player.ButtonOnGrid;

import java.util.ArrayList;

public class Initialization {
    @FXML

    public static void GridStart(Anketa player1,GridPane grid){
        ArrayList<ButtonOnGrid> tempbutton=new ArrayList<>();
        for(int row = 1; row < 11; row++){
            for(int column = 1; column < 11; column++){
                ButtonOnGrid tf = new ButtonOnGrid(column,row);
                grid.setMargin(tf,new Insets(1.2,1.2,1.2,1.2));
                grid.setRowIndex(tf,row);
                grid.setColumnIndex(tf,column);
                grid.getChildren().add(tf);
                tempbutton.add(tf);
            }
        }
        player1.setButtonplayer(tempbutton);
    }
//    public static void shipInitial(Anketa player1) {
//
//        Scanner scanner=new Scanner(System.in);
//        int[][] ship=new int[10][10];
//
//        System.out.println("Начнём расставлять корабли на поле игрока " + player1.getName() +
//                ", " + " не подсматривай");
//
//        while (player1.getCount1()+player1.getCount2()+player1.getCount3()+player1.getCount4()!=0) {
//            Vizualization.Vizual(player1.getField());
//            System.out.println("У вас в наличии:|" + player1.getCount4() + " четырёхпалубник|" +
//                    player1.getCount3() + " трёхпалубник(-а)|" + player1.getCount2() + " двухпалубник(-а)|" +
//                    player1.getCount1() + " однопалубник(-а)|");
//            System.out.println("Введите координаты в формате (x1,y1;x2,y2;x3,y3;x4,y4)");
//
//            String s = scanner.nextLine();
//            String[] massiv = s.split(";");
//            int[] massivfinal=new int[massiv.length*2];
//            try {
//                switch (massiv.length) {
//                    case 1: {
//                        massivfinal[0] = Integer.valueOf(massiv[0].substring(0, 1));
//                        massivfinal[1] = Integer.valueOf(massiv[0].substring(2));
//                    }
//                    break;
//                    case 2: {
//                        massivfinal[0] = Integer.valueOf(massiv[0].substring(0, 1));
//                        massivfinal[1] = Integer.valueOf(massiv[0].substring(2));
//                        massivfinal[2] = Integer.valueOf(massiv[1].substring(0, 1));
//                        massivfinal[3] = Integer.valueOf(massiv[1].substring(2));
//                    }
//                    break;
//                    case 3: {
//                        massivfinal[0] = Integer.valueOf(massiv[0].substring(0, 1));
//                        massivfinal[1] = Integer.valueOf(massiv[0].substring(2));
//                        massivfinal[2] = Integer.valueOf(massiv[1].substring(0, 1));
//                        massivfinal[3] = Integer.valueOf(massiv[1].substring(2));
//                        massivfinal[4] = Integer.valueOf(massiv[2].substring(0, 1));
//                        massivfinal[5] = Integer.valueOf(massiv[2].substring(2));
//                    }
//                    break;
//                    case 4: {
//                        massivfinal[0] = Integer.valueOf(massiv[0].substring(0, 1));
//                        massivfinal[1] = Integer.valueOf(massiv[0].substring(2));
//                        massivfinal[2] = Integer.valueOf(massiv[1].substring(0, 1));
//                        massivfinal[3] = Integer.valueOf(massiv[1].substring(2));
//                        massivfinal[4] = Integer.valueOf(massiv[2].substring(0, 1));
//                        massivfinal[5] = Integer.valueOf(massiv[2].substring(2));
//                        massivfinal[6] = Integer.valueOf(massiv[3].substring(0, 1));
//                        massivfinal[7] = Integer.valueOf(massiv[3].substring(2));
//                    }
//                    break;
//                }
//            } catch (NumberFormatException e) {
//                System.out.println("Не корректно введены координаты, должны попадать в диапозон (0-9)");
//                continue;
//            }catch (ArrayIndexOutOfBoundsException e){
//                System.out.println("Не корректно введены координаты, должны попадать в диапозон (0-9)");
//                continue;
//            }
//            for (int x:massivfinal){
//                if (!(x>=0&&x<10)){
//                    System.out.println("Не корректно введены координаты, должны попадать в диапозон (0-9)");
//                    break;
//                }
//            }
//            switch (massiv.length) {
//                case 1: {
//                    if (player1.getCount1()==0) {
//                        System.out.println("Однопалубники закончились");
//                        break;
//                    }
//                    if (!Checked.Fire(massivfinal,player1)){
//                        System.out.println("Данные координаты недопустимы потому что поле занято");
//                        break;
//                    }
//                    int x1 = massivfinal[0];
//                    int y1 = massivfinal[1];
//                    ship[x1][y1] = 1;
//                    player1.setField(ship);
//                    player1.setCount1(player1.getCount1()-1);
//                    Vizualization.Aura(massivfinal,player1);
//                }
//                break;
//                case 2: {
//                    if (player1.getCount2()==0) {
//                        System.out.println("Двухпалубники закончились");
//                        break;
//                    }
//                    if (!Checked.Fire(massivfinal,player1)){
//                        System.out.println("Данные координаты недопустимы потому что поле занято");
//                        break;
//                    }
//                    if (!Checked.ShipCorrected(massivfinal)){
//                        System.out.println("Форма корабля неверна");
//                        break;
//                    }
//                    player1.setCount2(player1.getCount2()-1);
//                    int x1 = massivfinal[0];
//                    int y1 = massivfinal[1];
//                    int x2 = massivfinal[2];
//                    int y2 = massivfinal[3];
//                    ship[x1][y1] = 1;
//                    ship[x2][y2] = 1;
//                    player1.setField(ship);
//                    Vizualization.Aura(massivfinal,player1);
//                }
//                break;
//                case 3: {
//                    if (player1.getCount3()==0) {
//                        System.out.println("Трёхпалубники закончились");
//                        break;
//                    }
//                    if (!Checked.Fire(massivfinal,player1)){
//                        System.out.println("Данные координаты недопустимы потому что поле занято");
//                        break;
//                    }
//                    if (!Checked.ShipCorrected(massivfinal)){
//                        System.out.println("Форма коробля неверна");
//                        break;
//                    }
//                    player1.setCount3(player1.getCount3()-1);
//                    int x1 = massivfinal[0];
//                    int y1 = massivfinal[1];
//                    int x2 = massivfinal[2];
//                    int y2 = massivfinal[3];
//                    int x3 = massivfinal[4];
//                    int y3 = massivfinal[5];
//                    ship[x1][y1] = 1;
//                    ship[x2][y2] = 1;
//                    ship[x3][y3] = 1;
//                    player1.setField(ship);
//                    Vizualization.Aura(massivfinal,player1);
//                }
//                break;
//                case 4: {
//                    if (player1.getCount4()==0) {
//                        System.out.println("Четырёхпалубники закончились");
//                        break;
//                    }
//                    if (!Checked.Fire(massivfinal,player1)){
//                        System.out.println("Данные координаты недопустимы потому что поле занято");
//                        break;
//                    }
//                    if (!Checked.ShipCorrected(massivfinal)){
//                        System.out.println("Форма коробля неверна");
//                        break;
//                    }
//                    player1.setCount4(player1.getCount4()-1);
//                    int x1 = massivfinal[0];
//                    int y1 = massivfinal[1];
//                    int x2 = massivfinal[2];
//                    int y2 = massivfinal[3];
//                    int x3 = massivfinal[4];
//                    int y3 = massivfinal[5];
//                    int x4 = massivfinal[6];
//                    int y4 = massivfinal[7];
//                    ship[x1][y1] = 1;
//                    ship[x2][y2] = 1;
//                    ship[x3][y3] = 1;
//                    ship[x4][y4] = 1;
//                    player1.setField(ship);
//                    Vizualization.Aura(massivfinal,player1);
//                }
//                break;
//            }
//        }
//    }
}
