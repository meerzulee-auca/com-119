package com.meerzulee;

import java.awt.*;

public abstract class Shape {
    protected int x,y;
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract boolean contains(int x,int y);

    public  void move(int dx,int dy){
        x+=dx;
        y+=dy;
    }

    public abstract void draw(Graphics g);
}
