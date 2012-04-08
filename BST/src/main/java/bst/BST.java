package bst;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class BST extends IBST
{

    private Node<Integer, Integer> root = null;

    public boolean containsKey(Integer k)
    {
        Node<Integer, Integer> x = root;
        while (x != null)
        {
            int cmp = k.compareTo(x.key);
            if (cmp == 0)
            {
                return true;
            }
            if (cmp < 0)
            {
                x = x.left;
            } else
            {
                x = x.right;
            }
        }
        return false;
    }

    public Integer get(Integer k)
    {
        Node<Integer, Integer> x = root;
        while (x != null)
        {
            int cmp = k.compareTo(x.key);
            if (cmp == 0)
            {
                return x.value;
            }
            if (cmp < 0)
            {
                x = x.left;
            } else
            {
                x = x.right;
            }
        }
        return null;
    }

    public void add(Integer k, Integer v)
    {
        int nHeight = 1;
        Node<Integer, Integer> x = root, y = null;
        while (x != null)
        {
            int cmp = k.compareTo(x.key);
            if (cmp == 0)
            {
                x.value = v;
                return;
            } else
            {
                y = x;
                if (cmp < 0)
                {
                    x = x.left;
                } else
                {
                    x = x.right;
                }
                nHeight++;
            }
        }
        Node<Integer, Integer> newNode = new Node<Integer, Integer>(k, v);
        if (y == null)
        {
            root = newNode;
        } else
        {
            if (k.compareTo(y.key) < 0)
            {
                y.left = newNode;
            } else
            {
                y.right = newNode;
            }
        }
        size++;
        if(nHeight > height)
            height = nHeight;
    }

    public void remove(Integer k)
    {
        Node<Integer, Integer> x = root, y = null;
        while (x != null)
        {
            int cmp = k.compareTo(x.key);
            if (cmp == 0)
            {
                break;
            } else
            {
                y = x;
                if (cmp < 0)
                {
                    x = x.left;
                } else
                {
                    x = x.right;
                }
            }
        }
        if (x == null)
        {
            return;
        }
        if (x.right == null)
        {
            if (y == null)
            {
                root = x.left;
            } else
            {
                if (x == y.left)
                {
                    y.left = x.left;
                } else
                {
                    y.right = x.left;
                }
            }
        } else
        {
            Node<Integer, Integer> leftMost = x.right;
            y = null;
            while (leftMost.left != null)
            {
                y = leftMost;
                leftMost = leftMost.left;
            }
            if (y != null)
            {
                y.left = leftMost.right;
            } else
            {
                x.right = leftMost.right;
            }
            x.key = leftMost.key;
            x.value = leftMost.value;
        }
        size--;
    }

    public void traverseAll(ConsoleTreeVisitor visitor)
    {
        root.traverse(visitor);

    }

    public void addTreeModelListener(TreeModelListener l)
    {
        // TODO Auto-generated method stub

    }

    public Object getChild(Object parent, int index)
    {
        Node nparent = (Node) parent;
        Object toReturn;
        toReturn = (index == 0 ? nparent.left : nparent.right);
        toReturn = (toReturn == null ? "NULL" : toReturn);
        return toReturn;
    }

    public int getChildCount(Object parent)
    {
        return 2;
    }

    public int getIndexOfChild(Object parent, Object child)
    {
        return 0;
    }

    public Object getRoot()
    {
        return root;
    }

    public boolean isLeaf(Object node)
    {
        if (node instanceof Node)
        {
            Node n = (Node) node;
            if (n.left == null && n.right == null)
            {
                return true;
            }
            return false;
        }
        return true;
    }

    public void removeTreeModelListener(TreeModelListener l)
    {
        // TODO Auto-generated method stub

    }

    public void valueForPathChanged(TreePath path, Object newValue)
    {
        // TODO Auto-generated method stub

    }
}