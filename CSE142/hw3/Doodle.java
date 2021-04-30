import java.awt.*;
public class Doodle {
//I was going to make a star but I don't think trig is allowed yet so 
//I'm going really basic here with good ol' rectangles and ovals
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(500, 500);
        Graphics g = panel.getGraphics();
        panel.setBackground(Color.lightGray);
        drawRobot(g, 75, 75);
    }
//This method creates the eyes and mouth of the robot. Best not to take parameters larger than
//100 or else the robot's existence is pain
    public static void drawRobot(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        g.fillOval(x, y, x, y*2);
        g.setColor(Color.BLUE);
        g.fillOval(500-x*2, y, x, y*2);
        g.setColor(Color.WHITE);
        g.fillRect(x, 500-y*2, 500-2*x, y);
    }
}
