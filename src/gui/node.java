package gui;

import java.awt.Color;

public class node {
    node  left;
    node  right;
    node  parent;
    int info;
    boolean color;
	public boolean getActualColor() {
		// TODO Auto-generated method stub
		return color;
	}
	public Object getLeft() {
		// TODO Auto-generated method stub
		return left;
	}
	public Object getRight() {
		// TODO Auto-generated method stub
		return right;
	}
	public static int compare(node root, node toSearch) {
		// TODO Auto-generated method stub
		if(root.equals(toSearch))
			return 1;
		else
			return 0;
	}
	public void setColor(boolean c)
	{
		color=c;
	}
	
}
