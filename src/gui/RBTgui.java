package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class RBTgui extends JPanel {

	private static final long serialVersionUID = 900L;

	private RBT tree = new RBT();
	private GUI treePanel = new GUI(tree);

	public RBTgui() {
		treePanel.setBackground(new Color(204, 204, 255));
		//////////////////
		super.setLayout(new BorderLayout());
		treePanel.setPreferredSize(new Dimension(7000, 5000));
		
		JPanel screen = new JPanel();
		screen.add(treePanel);
		screen.setPreferredSize(new Dimension(800, 600));
		add(screen, BorderLayout.CENTER);
	
		final JTextField inputText = new JTextField(15);

		JFrame frame=new JFrame("Button");  
		final JButton insertButton = new JButton("Insert");
		frame.add(insertButton);
		frame.setSize(444,444);
		
		final JButton deleteButton = new JButton("Delete");
		frame.add(deleteButton);
		frame.setSize(444,444);

		final JButton clearButton = new JButton("Clear");	
		frame.add(clearButton);
		frame.setSize(444,444);

		JPanel panel = new JPanel();
		
		panel.add(insertButton);
		panel.add(inputText);
		panel.add(deleteButton);
		panel.add(clearButton);
		
		add(panel, BorderLayout.NORTH);

		insertButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (inputText.getText().equals(""))
					return;

				node nodeToInsert = new node();
				nodeToInsert.info = Integer.parseInt(inputText.getText());
				if (tree.isContain(nodeToInsert.info)) {
					JOptionPane.showMessageDialog(null,"Node " + nodeToInsert.info+ " Already EXISTS in the tree");
				} else {
					tree.insertRBT(nodeToInsert.info);
					treePanel.repaint();
					inputText.requestFocus();
					inputText.selectAll();
			}

			}

		});

		deleteButton.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (inputText.getText().equals(""))
					return;

				node nodeToDelete = new node();
				nodeToDelete.info = Integer.parseInt(inputText.getText());
				if (!tree.isContain(nodeToDelete.info)) {
					JOptionPane.showMessageDialog(null,"Node " + nodeToDelete.info+ " doesNOT exist in the tree");
				} else {
					tree.deletionRBT(nodeToDelete.info);
					treePanel.repaint();
					inputText.requestFocus();
					inputText.selectAll();
					}

			}

		});
		
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {				
			tree.clearTree();
			treePanel.repaint();
			}
		});



		inputText.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				insertButton.doClick();
			}

		});

		///////////////////
	}


	public static void main(String[] args) 
	{
	
		JFrame j = new JFrame();
		j.setTitle("Red black tree visualizer");

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(700, 700);
		j.add(new RBTgui());
		j.pack();
		j.setVisible(true);
	}
}