package ua.com.finaly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShipClass {
    private List<Integer> position;
    private List<Integer> aura = new ArrayList<>();
    private boolean life;
    private int health;

    public ShipClass(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        this.position = new ArrayList(Arrays.asList(x1, y1, x2, y2, x3, y3, x4, y4));
        this.life = true;
        this.health = 4;
        createAura();
    }

    public ShipClass(int x1, int y1, int x2, int y2, int x3, int y3) {
        this.position = new ArrayList(Arrays.asList(x1, y1, x2, y2, x3, y3));
        this.life = true;
        this.health = 3;
        createAura();
    }

    public ShipClass(int x1, int y1, int x2, int y2) {
        this.position = new ArrayList(Arrays.asList(x1, y1, x2, y2));
        this.life = true;
        this.health = 2;
        createAura();
    }

    public ShipClass(int x1, int y1) {
        this.position = new ArrayList(Arrays.asList(x1, y1));
        this.life = true;
        this.health = 1;
        createAura();
    }

    public void createAura() {
        ArrayList<Integer> xcoord = new ArrayList<>();
        ArrayList<Integer> ycoord = new ArrayList<>();
        int xauramin;
        int xauramax;
        int yauramin;
        int yauramax;

        for (int i = 0; i < position.size(); i++) {
            if (i % 2 == 0) {
                xcoord.add(position.get(i));
            } else {
                ycoord.add(position.get(i));
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
        for (int i = yauramin; i < yauramax + 1; i++) {
            for (int j = xauramin; j < xauramax + 1; j++) {
                if (auraChecked(j, i)) {
                    aura.add(j);
                    aura.add(i);
                }
            }
        }

    }

    public boolean auraChecked(int j, int i) {
        boolean rever = true;
        for (int x = 0; x < position.size(); x += 2) {
            if (position.get(x) == j && position.get(x + 1) == i) {
                return false;
            }
        }
        return rever;
    }

    public boolean shipChecked(List<Integer> aura) {
        for (int i = 0; i < position.size(); i += 2) {
            for (int j = 0; j < aura.size(); j += 2) {
                if (position.get(i) == aura.get(j) && position.get(i + 1) == aura.get(j + 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Integer> getAura() {
        return aura;
    }

    public void setAura(List<Integer> aura) {
        this.aura = aura;
    }

    public List<Integer> getPosition() {
        return position;
    }

    public void setPosition(List<Integer> position) {
        this.position = position;
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
        return "ShipClass{" + "ship=" + position +
                ",aura=" + aura +
                '}';
    }
}
