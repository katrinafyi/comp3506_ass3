import java.util.Set;
import java.util.function.BinaryOperator;

public class PrereqNode {
    public enum PrereqType {
        COURSE, AND, OR
    }

    private final PrereqType type;
    private final String course;

    public PrereqNode(PrereqType type) {
        if (type == PrereqType.COURSE)
            throw new IllegalArgumentException("Course node must have a course.");
        this.type = type;
        this.course = null;
    }

    public PrereqNode(String course) {
        this.type = PrereqType.COURSE;
        this.course = course;
    }

    public static BinaryTree<PrereqNode> makeTree(PrereqNode root,
                  BinaryTree<PrereqNode> left, BinaryTree<PrereqNode> right) {

        BinaryTree<PrereqNode> tree = new BinaryTree<>(root);
        tree.setLeft(left);
        tree.setRight(right);

        return tree;
    }

    public static BinaryTree<PrereqNode> makeCourse(String course) {
        BinaryTree<PrereqNode> tree = new BinaryTree<>(new PrereqNode(course));
        return tree;
    }

    public static boolean evaluate(BinaryTree<PrereqNode> tree,
                                   Set<String> courses) {
        BinaryOperator<Boolean> op = null;
        switch (tree.getRoot().type) {
            case COURSE:
                return courses.contains(tree.getRoot().course);
            case AND:
                op = (a, b) -> a && b;
                break;
            case OR:
                op = (a, b) -> a || b;
                break;
        }

        return op.apply(evaluate(tree.getLeft(), courses),
                evaluate(tree.getRight(), courses));
    }

}
