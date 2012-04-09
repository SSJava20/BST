package bst;

public class ConsoleTreeVisitor {

    public void visit(Node node) {
        System.out.println(node.key + " : " + node.value);
    }
}
