package bst;

import javax.swing.tree.TreeModel;

public abstract class IBST implements TreeModel
{
    int size = 0;
    int height = 0;

    public abstract boolean containsKey(Integer k);

    public abstract Integer get(Integer k);

    public abstract void add(Integer k, Integer v);

    public abstract void remove(Integer k);

    public abstract void traverseAll(ConsoleTreeVisitor visitor);

    public int getSize()
    {
        return size;
    }

    public int getHeight()
    {
        return height;
    }


}