
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

import MouseClickDemo.Point;

import java.util.*;

public class ParkerZhang_Paint extends JFrame implements ActionListener {

	private JButton clear;
	private Color currentColor;
	private JColorChooser colorChooser;
	private boolean click;
	private int curSize;
	private JFileChooser jfc;
	private DrawPanel canvas;

	public ParkerZhang_Paint() {

		setSize(375, 590);
		setTitle("Paint");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		colorChooser = new JColorChooser();

		currentColor = Color.black;
		curSize = 10;

		canvas = new DrawPanel();
		canvas.setBounds(0, 0, 375, 485);

		click = false;

		jfc = new JFileChooser();

		JMenuBar jmb = new JMenuBar();

		JMenu jmFile = new JMenu("File");
		JMenuItem jmLoad = new JMenuItem("Load");
		JMenuItem jmSave = new JMenuItem("Save");
		JMenuItem jmImpose = new JMenuItem("Impose");

		jmFile.add(jmLoad);
		jmFile.add(jmSave);
		jmFile.add(jmImpose);

		jmb.add(jmFile);

		JMenu jmOptions = new JMenu("Options");
		JMenuItem jmColor = new JMenuItem("Color");

		JMenu size = new JMenu("Size");
		JMenuItem ten = new JMenuItem("10");
		JMenuItem twentyFive = new JMenuItem("25");
		JMenuItem fifty = new JMenuItem("50");

		jmOptions.add(jmColor);
		size.add(ten);
		size.add(twentyFive);
		size.add(fifty);

		jmOptions.add(size);

		jmb.add(jmOptions);

		setJMenuBar(jmb);

		jmLoad.addActionListener(this);
		jmSave.addActionListener(this);
		jmImpose.addActionListener(this);
		jmColor.addActionListener(this);
		ten.addActionListener(this);
		twentyFive.addActionListener(this);
		fifty.addActionListener(this);

		clear = new JButton("Clear");
		clear.setBounds(0, 485, 375, 45);
		clear.addActionListener(this);

		add(canvas);
		add(clear);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getActionCommand().equals("Load")) {

			canvas.clear();
			
			// opens the file opener
			jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int result = jfc.showOpenDialog(null);

			File f = null;
			Scanner sc = null;
			if (result == JFileChooser.APPROVE_OPTION) {
				f = jfc.getSelectedFile();

				// read and display the data from the file

				try {
					sc = new Scanner(f);
				} catch (FileNotFoundException e) {
					System.out.println("File Not Found!");
					System.exit(-1);
				}
				// adds to string each line in file
				while (sc.hasNextInt()) {
					
					Point p = new Point(sc.nextInt(),sc.nextInt(),new Color(sc.nextInt(),sc.nextInt(),sc.nextInt()),sc.nextInt());
					canvas.addPoint(p);
				}
			}

		}
		
		if (ae.getActionCommand().equals("Save")) {

			// opens the save pop up
			jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int result = jfc.showSaveDialog(null);

			FileWriter f = null;
			if (result == JFileChooser.APPROVE_OPTION) {

				try {

					// writes out to file
					f = new FileWriter(jfc.getSelectedFile());
					
					ArrayList<Point> points = canvas.getPoints();
					
					for(int i =0; i < points.size(); i ++) {
						f.write(points.get(i).toString());
					}
					
					f.close();

				} catch (IOException e) {
					System.out.println("IO Issue");
					System.exit(-1);
				}

			}
		}
		
		if (ae.getActionCommand().equals("Impose")) {

			// opens the file opener
			jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int result = jfc.showOpenDialog(null);

			File f = null;
			Scanner sc = null;
			if (result == JFileChooser.APPROVE_OPTION) {
				f = jfc.getSelectedFile();

				// read and display the data from the file

				try {
					sc = new Scanner(f);
				} catch (FileNotFoundException e) {
					System.out.println("File Not Found!");
					System.exit(-1);
				}
				// adds to string each line in file
				while (sc.hasNextInt()) {
					
					Point p = new Point(sc.nextInt(),sc.nextInt(),new Color(sc.nextInt(),sc.nextInt(),sc.nextInt()),sc.nextInt());
					canvas.addPoint(p);
				}
			}

		}

		if (ae.getActionCommand().equals("Color")) {
			Color newColor = JColorChooser.showDialog(null, "Select a color", currentColor);
			currentColor = newColor;
		}

		if (ae.getActionCommand().equals("10")) {
			curSize = 10;
		}

		if (ae.getActionCommand().equals("25")) {
			curSize = 25;

		}
		if (ae.getActionCommand().equals("50")) {
			curSize = 50;
		}

		if (ae.getActionCommand().equals("Clear")) {
			canvas.clear();
			this.repaint();
		}
	}

	public class Point {

		private int xLoc;
		private int yLoc;
		private Color color;
		private int size;

		public Point(int x, int y, Color c, int s) {
			xLoc = x;
			yLoc = y;
			color = c;
			size = s;

		}

		public String toString() {
			return xLoc + " " +  yLoc + " " + color.getRed() + " " + color.getGreen() + " " + color.getBlue() + " " + size + "\n";
		}

	}

	public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {

		private boolean leftClick;
		private ArrayList<Point> points; // stores points to draw a polygon

		public DrawPanel() {

			this.addMouseListener(this); // listens for mouse clicks
			this.addMouseMotionListener(this);

			points = new ArrayList<Point>();
		}

		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			setBackground(Color.white);
			g.setColor(currentColor);

			// draw a point
			if (leftClick) {

				for (Point nextP : points) {
					
					g.setColor(nextP.color);
					g.fillOval(nextP.xLoc, nextP.yLoc, nextP.size, nextP.size);

				}
			}
		}

		public void addPoint(Point p) {
			points.add(p);
		}
		public ArrayList<Point> getPoints(){
			return points;
		}
		
		public void clear() {
			points.clear();
		}

		public void mouseClicked(MouseEvent me) {
			if (me.getButton() == MouseEvent.BUTTON1) {
				leftClick = true;
				points.add(new Point(me.getX(), me.getY(), currentColor, curSize));

			}

			else if (me.getButton() == MouseEvent.BUTTON3) {

				leftClick = false;

			}
			this.repaint();

		}

		// these methods are stubbed out and not used
		public void mouseEntered(MouseEvent arg0) {

		}

		public void mouseExited(MouseEvent arg0) {

		}

		public void mousePressed(MouseEvent arg0) {

		}

		public void mouseReleased(MouseEvent arg0) {

		}

		public void mouseDragged(MouseEvent me) {

			points.add(new Point(me.getX(), me.getY(), currentColor, curSize));

			this.repaint();

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	public static void main(String[] args) {
		new ParkerZhang_Paint();
	}

}
