// Kim-Long Do
// 10/19/2020
// CSE142
// TA: Aiden Thaler
// Take-Home Assessment #3
//
// This program will make use of parameters to create rows and grids, which results 
//in the cafe wall illusion at the bottom right. 
import java.awt.*;
public class CafeWall {

//constant use to create spacing illusion in drawGrid   
    public static final int MORTAR = 2;

    public static void main(String[] args){
        DrawingPanel panel = new DrawingPanel(650,400);
        Graphics g = panel.getGraphics();
        panel.setBackground(Color.GRAY);

        drawRow(g, 0, 0, 4, 20);
        drawRow(g, 50, 70, 5, 30);
        drawGrid(g, 400, 20, 2, 35, 35);
        drawGrid(g, 10, 150, 4, 25, 0);
        drawGrid(g, 250, 200, 3, 25, 10);
        drawGrid(g, 425, 180, 5, 20, 10);
    }  

    //drawRow takes in the graphics g parameter that allows for coloring of the squares and 'X'.
    //The x and y paramters are the coordinates for where to draw, pairs is the number of
    //black and white squares will be created, and size will determine the size of the square 
    //and 'X'.
    public static void drawRow(Graphics g, int x, int y, int pairs, int size){      
        for(int i = 0; i< pairs; i++) {
            g.setColor(Color.BLACK);
            g.fillRect(x+2*size*i, y , size, size);
            g.setColor(Color.BLUE);
            g.drawLine(x+2*size*i, y+size, x+2*i*size+size, y);
            g.drawLine(x+2*size*i, y, x+2*i*size+size, y+size);
            g.setColor(Color.WHITE);
            g.fillRect(x+2*i*size+size, y, size, size);

        }
    }

    //drawGrid takes in the graphics g parameter that allows for coloring of the squares and 'X'.
    //The x and y paramters are the coordinates for where to draw, pairs is the number of
    //black and white squares will be created, and size will determine the size of the square 
    //and 'X'. The offset parameter determines how much the squares are moved from the right.
     public static void drawGrid(Graphics g, int x, int y, int pairs, int size, int offset) {
        int x1 = x;
        for(int i =0; i<pairs*2; i++) {
             x = x1+offset*(i%2);
             drawRow(g, x, y, pairs, size);
             y = y+ size + MORTAR;
         }
     }

}
