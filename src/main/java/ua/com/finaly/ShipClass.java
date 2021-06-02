package ua.com.finaly;

import java.util.ArrayList;
import java.util.Arrays;

public class ShipClass {
    private ArrayList<Integer> ship;
    private ArrayList<Integer> aura=new ArrayList<>();
    private boolean life;
    private int health;

    public ShipClass (int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
        this.ship = new ArrayList(Arrays.asList(x1, y1, x2, y2, x3, y3, x4, y4));
        this.life = true;
        this.health = 4;
        createAura();
    }

    public ShipClass (int x1, int y1, int x2, int y2, int x3, int y3){
        this.ship = new ArrayList(Arrays.asList(x1, y1, x2, y2, x3, y3));
        this.life = true;
        this.health = 3;
        createAura();
    }

    public ShipClass (int x1, int y1, int x2, int y2){
        this.ship = new ArrayList(Arrays.asList(x1, y1, x2, y2));
        this.life = true;
        this.health = 2;
        createAura();
    }

    public ShipClass (int x1, int y1){
        this.ship = new ArrayList(Arrays.asList(x1, y1));
        this.life = true;
        this.health = 1;
        createAura();
    }

    public void createAura(){
        ArrayList<Integer> xcoord = new ArrayList<>();
        ArrayList<Integer> ycoord = new ArrayList<>();
        int xauramin;
        int xauramax;
        int yauramin;
        int yauramax;

        for (int i=0;i<ship.size();i++){
            if (i%2==0){
                xcoord.add(ship.get(i));
            }else{
                ycoord.add(ship.get(i));
            }
        }
            int xmax = xcoord.stream().max(Integer::compareTo).get();
            int xmin = xcoord.stream().min(Integer::compareTo).get();
            int ymax = ycoord.stream().max(Integer::compareTo).get();
            int ymin = ycoord.stream().min(Integer::compareTo).get();
            if (xmin == 1) {
                xauramin = xmin;
            } else {
                xauramin = xmin - 1;
            }

            if (xmax == 10) {
                xauramax = xmax;
            } else {
                xauramax = xmax + 1;
            }

            if (ymin == 1) {
                yauramin = ymin;
            } else {
                yauramin = ymin - 1;
            }

            if (ymax == 10) {
                yauramax = ymax;
            } else {
                yauramax = ymax + 1;
            }
        for (int i=yauramin;i<yauramax+1;i++){
            for (int j=xauramin;j<xauramax+1;j++){
                if (auraChecked(j,i)){
                aura.add(j);
                aura.add(i);
                }
            }
        }

    }

    public boolean auraChecked(int j,int i){
        boolean rever=true;
        for (int x=0;x<ship.size();x+=2){
            if (ship.get(x)==j&&ship.get(x+1)==i){
                return false;
            }
        }
        return rever;
    }

    public boolean shipChecked(ArrayList<Integer> aura){
        for (int i=0;i<ship.size();i+=2){
            for (int j=0;j<aura.size();j+=2){
                if (ship.get(i)==aura.get(j)&&ship.get(i+1)==aura.get(j+1)){
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<Integer> getAura() {
        return aura;
    }

    public void setAura(ArrayList<Integer> aura) {
        this.aura = aura;
    }

    public ArrayList<Integer> getShip() {
        return ship;
    }

    public void setShip(ArrayList<Integer> ship) {
        this.ship = ship;
    }

    public boolean isLife() {
        return life;
    }

    public void setLife(boolean life) {
        this.life = life;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "ShipClass{" + "ship=" + ship +
                ",aura=" + aura +
                '}';
    }
}
