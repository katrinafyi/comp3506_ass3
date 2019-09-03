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

    @Before
    public void setup() {
        and = new PrereqNode(PrereqNode.PrereqType.AND);
        or = new PrereqNode(PrereqNode.PrereqType.OR);
        prereqTree = PrereqNode.makeTree(or,
                PrereqNode.makeCourse("CSSE2310"),
                PrereqNode.makeTree(and,
                        PrereqNode.makeCourse("CSSE1001"),
                        PrereqNode.makeCourse("CSSE2002")
                )
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
