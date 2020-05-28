package com.meerzulee;

import java.awt.*;

public class Rect extends Shape{
    private final int width,height;

    public Rect(int x, int y, int width, int height) {
        super(x,y);
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean contains(int x, int y){
        return x >= this.x && x < this.x+width &&
                y >= this.y && x < this.y+height;
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(x,y,width,height);
        if(isSelected()){
            Color prevColor= g.getColor();
            g.setColor(Color.red);
            g.fillRect(x,y,width,height);
            g.setColor(prevColor);
        }
    }

    @Override
    public String toString() {
        return String.format("Rectangle: %d, %d, %d, %d",x,y,width,height);
    }
}
