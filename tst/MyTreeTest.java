import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class MyTreeTest {

    private BinaryTree<LogicNode> logicTree;

    private static Set<String> makeSet(String... objects) {
        return new HashSet<>(Arrays.asList(objects));
    }

    public static <T> BinaryTree<T> tree(T root, BinaryTree<T> left,
                                         BinaryTree<T> right) {

        BinaryTree<T> tree = new BinaryTree<>(root);
        tree.setLeft(left);
        tree.setRight(right);
        return tree;
    }

    public static BinaryTree<LogicNode> var(String variable) {
        return new BinaryTree<>(new LogicNode(LogicNode.LogicType.VARIABLE, variable));
    }

    public static LogicNode node(String type) {
        return new LogicNode(LogicNode.LogicType.valueOf(type));
    }

    @Before
    public void setup() {
        logicTree = tree(node("AND"),
                tree(node("OR"),
                        var("CSSE2310"),
                        tree(node("AND"),
                                var("CSSE1001"), var("CSSE2002"))),
                tree(node("XOR"),
                        var("MATH1051"), var("MATH1071")));
    }

    @Test
    public void testTreeEvaluate() {
        assertTrue(LogicNode.evaluate(logicTree, makeSet("CSSE2310", "MATH1051")));
        assertTrue(LogicNode.evaluate(logicTree,
                makeSet("CSSE2310", "CSSE2002", "MATH1071")));
        assertTrue(LogicNode.evaluate(logicTree,
                makeSet("CSSE2002", "CSSE1001", "MATH1071")));

        assertFalse(LogicNode.evaluate(logicTree, makeSet()));
        assertFalse(LogicNode.evaluate(logicTree, makeSet("CSSE2002")));
        assertFalse(LogicNode.evaluate(logicTree, makeSet("MATH1051")));
    }

    @Test
    public void testTreeFormat() {
        assertEquals("((CSSE2310 or (CSSE1001 and CSSE2002)) and (MATH1051 xor MATH1071))",
                LogicNode.formatTree(logicTree));
    }

    @Test
    public void testBooleanExpressions() {
        assertTrue(LogicNode.LogicType.IFF.op.apply(true, true));
        assertTrue(LogicNode.LogicType.IFF.op.apply(false, false));
        assertFalse(LogicNode.LogicType.IFF.op.apply(false, true));

        assertFalse(LogicNode.LogicType.XOR.op.apply(false, false));
        assertTrue(LogicNode.LogicType.XOR.op.apply(false, true));

        assertFalse(LogicNode.LogicType.NAND.op.apply(true, true));

        assertTrue(LogicNode.LogicType.NOR.op.apply(false, false));

        assertTrue(LogicNode.LogicType.XNOR.op.apply(true, true));

        assertFalse(LogicNode.LogicType.IMPLIES.op.apply(true, false));
        assertTrue(LogicNode.LogicType.IMPLIES.op.apply(false, true));
    }
}
