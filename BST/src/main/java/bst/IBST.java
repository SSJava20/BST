package bst;

import javax.swing.tree.TreeModel;

public interface IBST extends TreeModel{

	public abstract boolean containsKey(Integer k);

	public abstract Integer get(Integer k);

	public abstract void add(Integer k, Integer v);

	public abstract void remove(Integer k);

	void traverseAll(ConsoleTreeVisitor visitor);

}