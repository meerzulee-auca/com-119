package com.meerzulee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends JFrame {

    public static final int MAX_RECT_SIZE = 100;

    class Canvas extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.BLUE);
            for(Shape shape : shapes){
               shape.draw(g);
            }
        }
    }
    ArrayList<Shape> shapes = new ArrayList<>();
    Shape selected = null;
    int prevMouseX,prevMouseY;
    boolean dragging = false;
    public Main() {
        setTitle("Graphics editor");
        setSize(500,500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Canvas canvas = new Canvas();
        canvas.addMouseListener(new MouseAdapter() {


            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3){
                    Shape selectedShape = null;
                    for (Shape shape : shapes){
                        if (shape.contains(e.getX(),e.getY())){
                            selectedShape = shape;
                        }
                    }
                    if (selectedShape != null){
                        JOptionPane.showMessageDialog(null, selectedShape);
                    }
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
               if(e.getButton() == MouseEvent.BUTTON1){
                   if(selected !=null){
                       selected.setSelected(false);
                       selected = null;
                   }

                   Shape selectedShape = null;
                   for (Shape shape : shapes){
                       if (shape.contains(e.getX(),e.getY())){
                           selectedShape = shape;
                       }
                   }
                   if (selectedShape != null){
                       selected = selectedShape;
                       selected.setSelected(true);

                       prevMouseX = e.getX();
                       prevMouseY = e.getY();
                       dragging = true;
                   }

                   repaint();
               }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dragging = false;
            }
        });
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(1,3));

        JButton rectBtn = new JButton("Rectangle");
        rectBtn.addActionListener(e -> {
            int x = (int)(Math.random() * getWidth());
            int y = (int)(Math.random() * getHeight());
            int width = (int)(Math.random()* MAX_RECT_SIZE);
            int height = (int)(Math.random()* MAX_RECT_SIZE);
            shapes.add(new Rect(x,y,width,height));
            canvas.requestFocus();
            repaint();
        });
        rectBtn.setSelected(false);

        JButton circleBtn = new JButton("Circle");
        circleBtn.addActionListener(e -> {
            int x = (int)(Math.random() * getWidth());
            int y = (int)(Math.random() * getHeight());
            int radius = (int)(Math.random()* 100);
            shapes.add(new Circle(x,y,radius));
            canvas.requestFocus();
            repaint();
        });
        circleBtn.setSelected(false);

        JButton crossBtn = new JButton("Cross");
        crossBtn.addActionListener(e -> {
            int x = (int)(Math.random() * getWidth());
            int y = (int)(Math.random() * getHeight());
            int width = (int)(Math.random()* MAX_RECT_SIZE);
            int height = (int)(Math.random()* MAX_RECT_SIZE);
            shapes.add(new Cross(x,y,width,height));
            canvas.requestFocus();
            repaint();
        });
        rectBtn.setSelected(false);

        toolbar.add(rectBtn);
        toolbar.add(circleBtn);
        toolbar.add(crossBtn);

        add(canvas, BorderLayout.CENTER);
        add(toolbar, BorderLayout.SOUTH);

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_DELETE){
                    if (selected != null){
                        shapes.remove(selected);
                        selected = null;
                        repaint();
                    }
                }
            }
        });
        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragging){
                    selected.move(e.getX() - prevMouseX, e.getY() - prevMouseY);
                    prevMouseX = e.getX();
                    prevMouseY = e.getY();
                    repaint();
                }
            }
        });
    }

    public static void main(String[] args) {
        new Main().setVisible(true);




        }
    }

