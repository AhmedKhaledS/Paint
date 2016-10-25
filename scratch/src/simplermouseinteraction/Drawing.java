/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simplermouseinteraction;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * Simple drawing program.
 * 
 * @author pat
 */
public class Drawing extends JPanel implements MouseListener, MouseMotionListener {

	GeneralPath p = new GeneralPath();

	LinkedList<GeneralPath> old_paths = new LinkedList<GeneralPath>();
	/**
	 * @wbp.nonvisual location=17,319
	 */
	private static int clicks;
	private static Point prev;

	/**
	 * @wbp.nonvisual location=-23,19
	 */
	/**
	 * @wbp.nonvisual location=-23,29
	 */
	public Drawing() {
		setBackground(Color.WHITE); // Make the background color white
		clicks = 0;
		prev = new Point();
		// Necessary for mouse interaction:
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	///// DRAWING
	/**
	 * Draw the disks.
	 */
	@Override
	public void paintComponent(Graphics gfx) {
		// This stuff is standard, and should be in any paintComponent method.
		super.paintComponent(gfx);
		Graphics2D g = (Graphics2D) gfx;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.BLACK);
		g.drawString("Draw something by dragging the mouse!", 10, 20);

		g.setColor(Color.RED);
		g.setStroke(new BasicStroke(2));
		for (GeneralPath old_path : old_paths) {
			g.draw(old_path);
			// System.out.println(old_path.getCurrentPoint());
		}

		g.setColor(Color.GREEN);
		g.setStroke(new BasicStroke(2));
		g.draw(p);
	}

	///// MOUSE INTERACTION
	// Implementation of MouseListener
	@Override
	public void mouseClicked(MouseEvent me) {
		clicks++;
		if (clicks == 2) {
			double curX = me.getX();
			double curY = me.getY();
			drawRectangle(curX, curY, prev.getX(), prev.getY());
			clicks = 0;
		} else {
			prev = new Point(me.getX(), me.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent me) {
	}

	@Override
	public void mouseExited(MouseEvent me) {
	}

	@Override
	public void mousePressed(MouseEvent me) {
		old_paths.add(p);
		p = new GeneralPath();
		p.moveTo(me.getX(), me.getY());
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent me) {
	}

	// Implementation of MouseMotionListener
	@Override
	public void mouseDragged(MouseEvent me) {
		p.lineTo(me.getX(), me.getY());
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent me) {
	}

		
	public void drawRectangle(double xleft, double ytop, double xright, double ybottom) {
		p.lineTo(xleft, ytop);
		repaint();
		p.lineTo(xright, ytop);
		repaint();
		p.lineTo(xright, ybottom);
		repaint();
		p.lineTo(xleft, ybottom);
		repaint();
		p.lineTo(xleft, ytop);
		repaint();
	}

	public static void main(String[] args) {
		// Construct a new window:
		JFrame frame = new JFrame("Simple Interaction Demo");
		// Dimensions of the window in pixels:
		frame.setSize(640, 480);
		// Quit the program when the window is closed:
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// The window will contain only our panel:
		frame.getContentPane().add(new Drawing());
		// Make the window visible:
		frame.setVisible(true);
	}
}