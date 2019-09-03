import java.util.Collection;
import java.util.function.BinaryOperator;

/**
 * A node representing a relationship between a course and its prerequisites.
 *
 * This is meant to be used as a value in a binary tree structure. For simplicity,
 * we restrict it to binary trees.
 */
public class PrereqNode {
    /**
     * Possible combinations of prerequisites.
     */
    private enum PrereqType {
        COURSE, AND, OR
    }

    /** Type of this prereq node. */
    private final PrereqType type;
    /** Course of this prereq. Should only be set if type == COURSE. */
    private final String course;

    /** Reference to an AND node. */
    private static final PrereqNode andNode = new PrereqNode(PrereqType.AND);
    /** Reference to an OR node. */
    private static final PrereqNode orNode = new PrereqNode(PrereqType.OR);

    /**
     * Construct a new PrereqNode for the AND or OR types.
     * @param type AND or OR, as enum values.
     */
    private PrereqNode(PrereqType type) {
        if (type == PrereqType.COURSE)
            throw new IllegalArgumentException("Course node must have a course.");
        this.type = type;
        this.course = null;
    }

    /**
     * Construct a new course PrereqNode for the given course.
     * @param course Course code as a string.
     */
    public PrereqNode(String course) {
        this.type = PrereqType.COURSE;
        this.course = course;
    }

    /**
     * Gets a precomputed OR PrereqNode.
     * @return PrereqNode with OR type.
     */
    public static PrereqNode or() {
        return orNode;
    }

    /**
     * Gets a precomputed AND PrereqNode.
     * @return PrereqNode with AND type.
     */
    public static PrereqNode and() {
        return andNode;
    }

    /**
     * Evaluate if the prerequisites described by tree have been met if the
     * given courses have been completed.
     *
     * Computes this by recursing down the prerequisite and considering
     * if all the PrereqNodes are fulfilled (i.e. both side of an AND node
     * and either side of an OR node). Course nodes are fulfilled it they
     * appear in the courses set.
     *
     * @param tree Root prerequisite tree.
     * @param courses Completed courses.
     * @return Tree if prerequisites are met.
     */
    public static boolean evaluate(BinaryTree<PrereqNode> tree,
                                   Collection<String> courses) {
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
