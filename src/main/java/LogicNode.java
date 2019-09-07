import java.util.Collection;
import java.util.function.BinaryOperator;

/**
 * A node representing a boolean logic expression.
 *
 * This is meant to be used as a value in a binary tree structure.
 */
public class LogicNode {
    /**
     * Possible binary logic functions.
     */
    @SuppressWarnings({"SimplifiableBooleanExpression", "unused", "SpellCheckingInspection"})
    // intellij doesn't like our boolean expressions here.
    public enum LogicType {
        VARIABLE(null), // TODO: more elegant way to do variables
        AND((a, b) -> a && b),
        OR((a, b) -> a || b),
        NAND((a, b) -> !(a && b)),
        NOR((a, b) -> !(a || b)),
        XOR((a, b) -> (a ^ b)),
        XNOR((a, b) -> !(a ^ b)),
        IMPLIES((a, b) -> (!a || b)),
        IFF((a, b) -> a == b);

        public final BinaryOperator<Boolean> op;

        LogicType(BinaryOperator<Boolean> op) {
            this.op = op;
        }
    }

    /** Type of this digital logic node. */
    private final LogicType type;
    /** If this is a VARIABLE type, the string of the variable name. */
    private final String variable;

    /**
     * Construct a new LogicNode for the AND or OR types.
     * @param type AND or OR, as enum values.
     */
    public LogicNode(LogicType type) {
        if (type == LogicType.VARIABLE)
            throw new IllegalArgumentException(
                    "Variable node must have a variable name.");
        this.type = type;
        this.variable = null;
    }

    /**
     * Construct a new variable LogicNode for the given variable.
     * @param type Variable type. Must be VARIABLE.
     * @param variable Course code as a string.
     */
    public LogicNode(LogicType type, String variable) {
        if (type != LogicType.VARIABLE)
            throw new IllegalArgumentException(
                    "Only variable nodes can contain variables.");
        this.type = type;
        this.variable = variable;
    }

    /**
     * Evaluate if the boolean logic expression described by tree is true or
     * false given the state of some variables.
     *
     * Computes this by recursing down the expression and considering
     * if all the subexpressions are fulfilled (i.e. both side of an AND node
     * and either side of an OR node). Variable nodes are true if and
     * only if they appear in the true set.
     *
     * Note: this is not logical deduction. That is, given X implies Y
     * and X true, this does not deduce Y is true. It verifies if the given
     * expression is valid. For example if Y is false, this will return false
     * because the expression is not true.
     *
     * @param tree Root prerequisite tree.
     * @param trueVariables Variables which are 'true'.
     * @return Tree if expression evaluates to true.
     */
    public static boolean evaluate(BinaryTree<LogicNode> tree,
                                   Collection<String> trueVariables) {
        LogicNode node = tree.getRoot();
        LogicType type = node.type;

        if (type == LogicType.VARIABLE) {
            if (!tree.isLeaf())
                throw new IllegalArgumentException(
                        "VARIABLE nodes must have no children.");
            return trueVariables.contains(node.variable);
        }

        return node.type.op.apply(
                evaluate(tree.getLeft(), trueVariables),
                evaluate(tree.getRight(), trueVariables));
    }

    /**
     * Formats the given logic tree into a string expression. Operations
     * are written using lowercase (e.g. "and", "or"). Parens are used to
     * group operations.
     * @param tree Root of tree.
     * @return String representation.
     */
    public static String formatTree(BinaryTree<LogicNode> tree) {
        LogicNode node = tree.getRoot();
        LogicType type = node.type;

        if (type == LogicType.VARIABLE) {
            return node.variable;
        }

        return ("(" + formatTree(tree.getLeft())
                + " " + type.name().toLowerCase() + " "
                + formatTree(tree.getRight()) + ")");
    }

}
