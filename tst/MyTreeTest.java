import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class MyTreeTest {

    private BinaryTree<PrereqNode> prereqTree;
    private PrereqNode and;
    private PrereqNode or;

    private static Set<String> makeSet(String... objects) {
        return new HashSet<>(Arrays.asList(objects));
    }

    public static <T> BinaryTree<T> makeTree(T root, BinaryTree<T> left,
                                             BinaryTree<T> right) {

        BinaryTree<T> tree = new BinaryTree<>(root);
        tree.setLeft(left);
        tree.setRight(right);
        return tree;
    }

    public static BinaryTree<PrereqNode> makeCourse(String course) {
        return new BinaryTree<>(new PrereqNode(course));
    }

    @Before
    public void setup() {
        prereqTree = makeTree(PrereqNode.or(),
                makeCourse("CSSE2310"),
                makeTree(PrereqNode.and(),
                        makeCourse("CSSE1001"), makeCourse("CSSE2002"))
        );
    }

    @Test
    public void testPrereqTreeEvaluate() {
        assertTrue(PrereqNode.evaluate(prereqTree, makeSet("CSSE2310")));
        assertTrue(PrereqNode.evaluate(prereqTree,
                makeSet("CSSE2310", "CSSE2002")));
        assertTrue(PrereqNode.evaluate(prereqTree,
                makeSet("CSSE2002", "CSSE1001")));

        assertFalse(PrereqNode.evaluate(prereqTree, makeSet()));
        assertFalse(PrereqNode.evaluate(prereqTree, makeSet("CSSE2002")));
        assertFalse(PrereqNode.evaluate(prereqTree, makeSet("MATH1051")));
    }
}
