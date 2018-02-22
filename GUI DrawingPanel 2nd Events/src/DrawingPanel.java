//A simple interface for drawing persistent images.
//Final version with events.
import java.awt.*;			// for Dimension
import java.awt.event.*;	// for ActionListener
import java.awt.image.*;	// for BufferedImage

import javax.swing.*;		// for GUI components
import javax.swing.event.*; // for MouseInputListener
public class DrawingPanel extends MouseInputAdapter
					implements ActionListener{
/*	public static final int WIDTH = 1275;
	public static final int HEIGHT = 720;
	@SuppressWarnings("unused")
	public static void main(String[] args){
		DrawingPanel panel = new DrawingPanel(WIDTH, HEIGHT);
	}*/
	private JFrame frame; 		// overall window frame
	private JPanel panel; 		// drawing surface
	private JLabel statusBar;	// status bar
	private Graphics g;			// drawing pen.
	
	//constructs a drawing panel of given size
	public DrawingPanel(int width, int height){
	//sets up the empty image onto which we will draw
		BufferedImage image = new BufferedImage(width, height,
							BufferedImage.TYPE_INT_ARGB);
		g = image.getGraphics();
		g.setColor(Color.BLACK);
			
		//enclose the image in a label inside a panel
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(image));
		panel = new JPanel(new FlowLayout());
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(width, height));
		panel.add(label);
		
		//The status bar that shows the mouse position
		statusBar = new JLabel(" ");
		
		//attach listener to observe mouse movement
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		
		//sets up the JFrame
		frame = new JFrame("Drawing Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		frame.add(statusBar, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		
		//starts a repaint timer to refresh the screen
		Timer timer = new Timer(250, this);
		timer.start();
	}
	//obtains the Graphics object to draw on the panel
	public Graphics getGraphics(){
		return g;
	}
		
	//set the background color of the drawing panel
	public void setBackground(Color c){
		panel.setBackground(c);
	}
		
	//shows or hide the drawing panel on the screen
	public void setVisible(boolean visible){
		frame.setVisible(visible);
	}
	
	//used for timer that repeatedly repaints screen
	public void actionPerformed(ActionEvent e){
		panel.repaint();
	}
	//override
	//draws status bar text when mouse moves
	public void mouseMoved(MouseEvent e){
		statusBar.setText("(" + e.getX() + ", " +
							e.getY() + ")");
	}

}