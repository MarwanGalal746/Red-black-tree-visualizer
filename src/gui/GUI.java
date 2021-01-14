package gui;

import javax.swing.JPanel;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class GUI extends JPanel {

	private static final long serialVersionUID = 1250L;

	private RBT tree;
	private Color color = Color.white;
	private node search;
	private int radius = 20;
	private int space = 50;

	public GUI() {}
	public GUI(RBT tree) 
	{
		this.tree = tree;
	}

	public void setSearch(node c) 
	{
		search = c;
	}


	private void join(Graphics2D g, int sid1, int side1, int sid2, int side2) 
	{
		double side = Math.hypot(space, sid2 - sid1);
		int sid11 = (int) (sid1 + radius * (sid2 - sid1) / side);
		int side11 = (int) (side1 - radius * space / side);
		int sid21 = (int) (sid2 - radius * (sid2 - sid1) / side);
		int side21 = (int) (side2 + radius * space / side);
		g.drawLine(sid11, side11, sid21, side21);
	}
	
	@Override
	protected void paintComponent(Graphics graphics) 
	{
		super.paintComponent(graphics);
		Graphics2D graphics2d = (Graphics2D) graphics;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		int height = tree.getDepth();
		int multiplierrr = 30;
		float angle = (float) 1.9;
		if (height > 6) {
			multiplierrr += height * 5;
			angle += .1;
		}
		int inn= (int) Math.pow(height, angle) * multiplierrr;
		paintTree(graphics2d, (node) tree.getRoot(), getWidth() / 2, 45,inn);
	}

	private void paintTree(Graphics2D graph, node root, int i, int j,int offsetNode) {

		if(root !=null)
		{
			if (i < 0)
				setPreferredSize(new Dimension(2 * getWidth(), getHeight()));
	
			if (search != null && node.compare(root, (node) search) == 0)
			{
				graph.setColor(Color.black);
				radius += 10;
				graph.fillOval(i - radius, j - radius, 2 * radius, 2 * radius);
				radius -= 10;
			}
			if(root.getActualColor())
			{
				graph.setColor(Color.RED);
			}
			else
				graph.setColor(Color.BLACK);
			
			String el = Integer.toString(root.info) ;
			graph.fillOval(i - radius, j - radius, 2 * radius, 2 * radius);
			graph.setColor(color);
			FontMetrics fontMet = graph.getFontMetrics();
			double width = fontMet.getStringBounds(el, graph).getWidth();
			graph.drawString(el, (int) (i - width / 2),(int) (j + fontMet.getMaxAscent() / 2));
			graph.setColor(Color.black);
			
			if (root.getRight() != null) 
			{
				join(graph, i + offsetNode, j + space, i, j);
				paintTree(graph, (node) root.getRight(), i + offsetNode, j + space,offsetNode / 2);
			}
			if (root.getLeft() != null) 
			{
				join(graph, i - offsetNode, j + space, i, j);
				paintTree(graph, (node) root.getLeft(), i - offsetNode, j + space,offsetNode / 2);
			}			
		}
		
	}
	

	public static void main(String[] args) {}
}
