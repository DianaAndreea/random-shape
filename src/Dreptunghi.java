import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Vector;

public class Dreptunghi extends JPanel{
    JLabel label;
    RectangleArea rectangleArea = new RectangleArea(this);
    public int scor = 0;
    
    void buildUI(Container container) {
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
               
        container.add(rectangleArea);

        label = new JLabel("Click within the framed area.");
        container.add(label);

        //Align the left edges of the components.
        rectangleArea.setAlignmentX(LEFT_ALIGNMENT);
        label.setAlignmentX(LEFT_ALIGNMENT); //unnecessary, but doesn't hurt
    }

    public void updateLabel(Integer scor) {
        label.setText("Scor actual " + scor);
    }
    //Called only when this is run as an application.
    public static void main(String[] args) {
        JFrame f = new JFrame("Aplicatie - Laborator 1 - IP");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Dreptunghi controller = new Dreptunghi();
        controller.buildUI(f.getContentPane());
        f.pack();
        f.setVisible(true);
    }
}

class RectangleArea extends JPanel implements ActionListener{
   
    Random random = new Random();
    
    Dreptunghi controller;
    Dimension preferredSize = new Dimension(700,700);
    
    int rectWidth = 50;
    int rectHeight = 50;
   
    javax.swing.Timer timer;
    int fps = 10;
    
    
    public Vector<Forme> forma = new Vector <Forme>(); 
    public int scor = 0;
    public double time = 60000.0;
   

public void actionPerformed (ActionEvent e)
{          time -=100;
 
		if(time<0)
		{timer.stop();
                    for(int i=0; i<forma.size();i++){
                        forma.remove(i);
                        JOptionPane.showMessageDialog(controller, "Felicitari! Ai obtinut " + scor + " puncte.", "Scor", JOptionPane.PLAIN_MESSAGE);
                    System.exit(0);}
                }
		
		if(time/10000 >= 0 && time/1000 <= 60 && forma.size()<50)
                        forma.add(new Forme());
                        repaint();
                    	
     }
//final Container panou;
    
    public RectangleArea(Dreptunghi controller) {
        this.controller = controller;
        
        int delay = (fps > 0) ? (1000 / fps) : 100;     
        timer = new javax.swing.Timer(delay, this);
        timer.setInitialDelay(0);
        timer.setCoalesce(true);
        timer.start();
        
                Border raisedBevel = BorderFactory.createRaisedBevelBorder();
                Border loweredBevel = BorderFactory.createLoweredBevelBorder();
                Border compound = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);
                setBorder(compound);
              
      addMouseListener(new MouseAdapter() {
          
            public void mousePressed(MouseEvent e) {
            
            for(int i=0; i<forma.size(); i++)
            if(forma.get(i).dpt.contains(e.getPoint())){
                if (time>0)
                {
                    forma.remove(i);
                    scor++;
                   
                }
                repaint();
            }
            }
            
        });
    }

    public Dimension getPreferredSize() {
        return preferredSize;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.yellow);
	super.paintComponent(g); // paint background
	Graphics2D gr = (Graphics2D)g;
	gr.setColor(Color.black);
		
	gr.drawString("Timpul ramas: ", 585, 690);
	
        
        gr.setColor(Color.black);
        gr.drawString(Double.toString(time/1000), 670, 690);//20
       
        
        //se adauga formele in fereastra
		for(int i=0; i<forma.size(); i++) {
                    gr.setColor(forma.get(i).cc);
                    gr.fill(forma.get(i).dpt);
		}
		
		if(time < 0)
            super.paintComponent(gr);
	 controller.updateLabel(scor);
        }
    }