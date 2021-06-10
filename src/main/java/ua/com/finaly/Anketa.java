package ua.com.finaly;

import ua.com.finaly.Player.ButtonOnGrid;
import ua.com.finaly.Player.ButtonOnPlay;

import java.io.Serializable;
import java.util.ArrayList;

public class Anketa implements Serializable {
    private int[][] field;
    private String name;
    private int count4;
    private int count3;
    private int count2;
    private int count1;
    private int hitpoint;
    private ArrayList<ShipClass> shipList = new ArrayList<>();
    private ArrayList<Integer> AILogic = new ArrayList<>();
    private ArrayList<ButtonOnGrid> buttonplayer = new ArrayList<>();
    private ArrayList<ButtonOnPlay> buttonplay = new ArrayList<>();
    private boolean AILogicOn = false;
    private boolean AIturn = false;

    public Anketa() {
        this.field = new int[10][10];
        this.count1 = 4;
        this.count2 = 3;
        this.count3 = 2;
        this.count4 = 1;
        this.hitpoint = 20;
    }

    public Anketa(String name) {
        this.field = new int[10][10];
        this.count1 = 4;
        this.count2 = 3;
        this.count3 = 2;
        this.count4 = 1;
        this.hitpoint = 20;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount4() {
        return count4;
    }

    public void setCount4(int count4) {
        this.count4 = count4;
    }

    public int getCount3() {
        return count3;
    }

    public void setCount3(int count3) {
        this.count3 = count3;
    }

    public int getCount2() {
        return count2;
    }

    public void setCount2(int count2) {
        this.count2 = count2;
    }

    public int getCount1() {
        return count1;
    }

    public void setCount1(int count1) {
        this.count1 = count1;
    }

    public int[][] getField() {
        return field;
    }

    public void setField(int[][] field) {
        this.field = field;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public void setHitpoint(int hitpoint) {
        this.hitpoint = hitpoint;
    }

    public ArrayList<ShipClass> getShipList() {
        return shipList;
    }

    public void setShipList(ArrayList<ShipClass> shipList) {
        this.shipList = shipList;
    }

    public ArrayList<Integer> getAILogic() {
        return AILogic;
    }

    public void setAILogic(ArrayList<Integer> AILogic) {
        this.AILogic = AILogic;
    }

    public boolean isAILogicOn() {
        return AILogicOn;
    }

    public void setAILogicOn(boolean AILogicOn) {
        this.AILogicOn = AILogicOn;
    }

    public boolean isAIturn() {
        return AIturn;
    }

    public void setAIturn(boolean AIturn) {
        this.AIturn = AIturn;
    }

    public ArrayList<ButtonOnGrid> getButtonplayer() {
        return buttonplayer;
    }

    public void setButtonplayer(ArrayList<ButtonOnGrid> buttonplayer) {
        this.buttonplayer = buttonplayer;
    }

    public ArrayList<ButtonOnPlay> getButtonplay() {
        return buttonplay;
    }

    public void setButtonplay(ArrayList<ButtonOnPlay> buttonplay) {
        this.buttonplay = buttonplay;
    }
}
