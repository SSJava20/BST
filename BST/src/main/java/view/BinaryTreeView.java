/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
/*
 * Programming graphical user interfaces Example: BinaryTreeView.java Jarkko
 * Leponiemi 2003
 */

import bst.BST;
import bst.Node;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * A class representing a graphical view of a binary tree.
 */
public class BinaryTreeView extends JPanel implements ActionListener {

    // the binary tree
    private BST tree = null;
    // the node location of the tree
    private HashMap nodeLocations = null;
    // the sizes of the subtrees
    private HashMap subtreeSizes = null;
    // do we need to calculate locations
    private boolean dirty = true;
    // space between nodes
    private int parent2child = 20, child2child = 30;
    // helpers
    private Dimension empty = new Dimension(0, 0);
    private FontMetrics fm = null;

    public BinaryTreeView(BST tree) {
        this.tree = tree;
        nodeLocations = new HashMap();
        subtreeSizes = new HashMap();
        registerKeyboardAction(this, "add", KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), WHEN_IN_FOCUSED_WINDOW);
    }
    
    public void setTree(BST tree){
        this.tree = tree;
        nodeLocations = new HashMap();
        subtreeSizes = new HashMap();
        dirty = true;
        repaint();
    }

    public void addNode(int a,int b) {
        tree.add(a,b);
        dirty = true;
        repaint();
    }
    
    public void removeNode(int a) {
        tree.remove(a);
        dirty = true;
        repaint();
    }
    // event handler for pressing "A"
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add")) {
            String c = JOptionPane.showInputDialog("Add a new node:");
            if (c != null && !"".equals(c)) {
                tree.add(1,1);
                dirty = true;
                repaint();
            }
        }
    }

    // calculate node locations
    private void calculateLocations() {
        nodeLocations.clear();
        subtreeSizes.clear();
        Node root = (Node) tree.getRoot();
        if (root != null) {
            calculateSubtreeSize(root);
            calculateLocation(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
        }
    }

    // calculate the size of a subtree rooted at n
    private Dimension calculateSubtreeSize(Node n) {
        if (n == null) {
            return new Dimension(0, 0);
        }
        String s = n.key.toString();
        Dimension ld = calculateSubtreeSize(n.getLNode());
        Dimension rd = calculateSubtreeSize(n.getRNode());
        int h = fm.getHeight() + parent2child + Math.max(ld.height, rd.height);
        int w = ld.width + child2child + rd.width;
        Dimension d = new Dimension(w, h);
        subtreeSizes.put(n, d);
        return d;
    }

    // calculate the location of the nodes in the subtree rooted at n
    private void calculateLocation(Node n, int left, int right, int top) {
        if (n == null) {
            return;
        }
        Dimension ld = (Dimension) subtreeSizes.get(n.getLNode());
        if (ld == null) {
            ld = empty;
        }
        Dimension rd = (Dimension) subtreeSizes.get(n.getRNode());
        if (rd == null) {
            rd = empty;
        }
        int center = 0;
        if (right != Integer.MAX_VALUE) {
            center = right - rd.width - child2child / 2;
        } else if (left != Integer.MAX_VALUE) {
            center = left + ld.width + child2child / 2;
        }
        int width = fm.stringWidth(n.key.toString());
        Rectangle r = new Rectangle(center - width / 2 - 3, top, width + 6, fm.getHeight());
        nodeLocations.put(n, r);
        calculateLocation(n.getLNode(), Integer.MAX_VALUE, center - child2child / 2, top + fm.getHeight() + parent2child);
        calculateLocation(n.getRNode(), center + child2child / 2, Integer.MAX_VALUE, top + fm.getHeight() + parent2child);
    }

    // draw the tree using the pre-calculated locations
    private void drawTree(Graphics2D g, Node n, int px, int py, int yoffs) {
        if (n == null) {
            return;
        }
        Rectangle r = (Rectangle) nodeLocations.get(n);
        g.setStroke(new BasicStroke(2.0f));
        g.setColor(Color.red);
        g.draw(r);
        g.drawString(n.key.toString(), r.x + 3, r.y + yoffs);
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(3.5f));
        if (px != Integer.MAX_VALUE) {
            g.drawLine(px, py, r.x + r.width / 2, r.y);
        }
        drawTree(g, n.getLNode(), r.x + r.width / 2, r.y + r.height, yoffs);
        drawTree(g, n.getRNode(), r.x + r.width / 2, r.y + r.height, yoffs);
    }

    public void paint(Graphics g) {
        super.paint(g);
        fm = g.getFontMetrics();
        // if node locations not calculated
        if (dirty) {
            calculateLocations();
            dirty = false;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth() / 2, parent2child);
        drawTree(g2d, (Node)tree.getRoot(), Integer.MAX_VALUE, Integer.MAX_VALUE, fm.getLeading() + fm.getAscent());
        fm = null;
    }
}
