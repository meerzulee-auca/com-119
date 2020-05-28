package com.meerzulee;

import java.awt.*;

public class Cross extends Shape {
    private int width,height,x2,y2,w2,h2;


    public Cross(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.w2 = height;
        this.h2 = width;
        this.x2 = (y)-w2/2;
        this.y2 = (x)-h2/2;

    }

    @Override
    public boolean contains(int x, int y) {
        return x >= this.x && x < this.x+width &&
                y >= this.y && x < this.y+height &&
                x2 >= this.x2 && x < this.x2+w2 &&
                y2 >= this.y2 && x < this.y2+h2;
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(x,y,width,height);
        g.fillRect(x2,y2,w2,h2);
        if(isSelected()){
            Color prevColor= g.getColor();
            g.setColor(Color.red);
            g.fillRect(x,y,width,height);
            g.fillRect(x2,y2,w2,h2);
            g.setColor(prevColor);
        }
    }
    @Override
    public String toString() {
        return String.format("Cross: (Rectangle: %d, %d, %d, %d), (Rectangle: %d, %d, %d, %d),",x,y,width,height,x2,y2,w2,h2);
    }

}
