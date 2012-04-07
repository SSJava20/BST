package bst;

public class Node<T1, T2> {
	T1 key;
	T2 value;
	Node<T1, T2> left, right;

	Node(T1 key, T2 value) {
		this.key = key;
		this.value = value;
	}

	public void traverse(ConsoleTreeVisitor visitor) {
		if (left != null)
		{
			left.traverse(visitor);
		}

		visitor.visit(this);

		if (right != null)
			right.traverse(visitor);
	}

	@Override
	public String toString() {
		return "Key : " + key + " Value : " + value;
	}
}
