import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class TreeTest {

    @Test
    public void testIterator() {
        StandardTree<Integer> tree = new StandardTree<>(10);

        StandardTree<Integer> child1 = new StandardTree<>(1);
        StandardTree<Integer> child2 = new StandardTree<>(2);
        StandardTree<Integer> child3 = new StandardTree<>(3);
        tree.addChild(child1);
        tree.addChild(child2);
        tree.addChild(child3);

        StandardTree<Integer> child4 = new StandardTree<>(100);
        child2.addChild(child4);

        TreeIterator<Integer> it = new TreeIterator<>(tree);
        assertEquals(10, (long)it.next());
        assertEquals(1, (long)it.next());
        assertEquals(2, (long)it.next());
        assertEquals(100, (long)it.next());
        assertEquals(3, (long)it.next());
        assertFalse(it.hasNext());
    }

    @Test(timeout=1000)
    public void testHasNext() {
        BinaryTree<Integer> root = new BinaryTree<>(1);

        Iterator<Integer> iter = root.iterator();
        assertTrue(iter.hasNext());
        iter.next();
        assertFalse(iter.hasNext());
    }

    @Test(timeout=1000)
    public void testNext() {
        BinaryTree<Integer> root = new BinaryTree<>(1);
        BinaryTree<Integer> left = new BinaryTree<>(2);
        BinaryTree<Integer> leftLeft = new BinaryTree<>(3);

        root.setLeft(left);
        left.setLeft(leftLeft);

        Iterator<Integer> iter = root.iterator();
        for (int i = 1; i <= 3; i++) {
            assertEquals(i, (int)iter.next());
        }
    }

    @Test(timeout=1000)
    public void testIsBST() {
        BinaryTree<Integer> tree = new BinaryTree<>(2);
        BinaryTree<Integer> left = new BinaryTree<>(1);
        BinaryTree<Integer> right = new BinaryTree<>(3);

        tree.setLeft(left);
        tree.setRight(right);
        left.setLeft(new BinaryTree<>(null));
        left.setRight(new BinaryTree<>(null));
        right.setLeft(new BinaryTree<>(null));
        right.setRight(new BinaryTree<>(null));

        assertTrue(BinaryTree.isBST(tree));
    }

    @Test
    public void testIsNotBST() {
        BinaryTree<Integer> tree = new BinaryTree<>(2);
        BinaryTree<Integer> left = new BinaryTree<>(3);
        BinaryTree<Integer> right = new BinaryTree<>(3);

        tree.setLeft(left);
        tree.setRight(right);

        left.setLeft(new BinaryTree<>(null));
        left.setRight(new BinaryTree<>(null));
        right.setLeft(new BinaryTree<>(null));
        right.setRight(new BinaryTree<>(null));

        assertEquals(false, BinaryTree.isBST(tree));

    }
}
