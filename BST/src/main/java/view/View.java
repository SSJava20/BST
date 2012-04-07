package view;

/**
 * InfiniteBinaryTree
 * @author Daniel Green
 * Superliminal Software
 *
 * A little example program to show the power of the TreeModel interface.
 * Running it displays a binary tree with numbers on each node.
 * Every positive number can be found somewhere in the tree.
 * See if you can find the node labeled "1000".
 * Hint: The base 2 representation of any node value can be read out from
 * the path leading to that node where opening the first child of a node
 * represents a 0 and opening the second child represents a 1.
 * Enjoy!
 */

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import bst.IBST;
import bst.IntBST;

public class View {

	static IBST bst;

	public static void main(String args[]) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}
		fillBST();
		JTree binTree = new JTree(bst);
		binTree.setShowsRootHandles(true);
		binTree.setEditable(true);

		JFrame frame = new JFrame("Infinite Binary Tree");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new JScrollPane(binTree));
		frame.setSize(new java.awt.Dimension(400, 600));
		frame.setVisible(true);
	}

	private static void fillBST() {
		bst = new IntBST();
		Random random = new Random();

		for (int i = 0; i < 20; i++) {
			int toAdd;
			do {
				toAdd = random.nextInt(1000);
			} while (bst.containsKey(toAdd));

			bst.add(toAdd, 1);
		}
	}
}