import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.*;

public class Forme {

    public Shape dpt;
    public Color cc;
    Random rand = new Random();
    
    double dX = rand.nextInt(650);
    double dY = rand.nextInt(550);
    double width = 50;
    double height = 50;
    
    int figuri = rand.nextInt(100);
    
    public Forme () {
        if(figuri<50){
    dpt = new Rectangle2D.Double(dX, dY, width, height);
    cc = Color.GREEN;}
        else if(figuri<75){
    dpt = new Ellipse2D.Double(dX, dY, width, height);
    cc = Color.YELLOW;}
        else if(figuri<100){
    dpt = new RoundRectangle2D.Double(dX, dY, width, height*1.5, 40, 20);
    cc = Color.GRAY;}
    
    }
   
    public void draw (Shape s, Graphics2D gr) {
               gr.setColor(Color.blue);
		gr.fill(s);
    }
    
}
