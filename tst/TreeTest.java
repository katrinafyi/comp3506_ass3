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

    @Test
    public void testIsNotBST2() {
        BinaryTree<Integer> tree = new BinaryTree<>(2);
        BinaryTree<Integer> left = new BinaryTree<>(3);
        BinaryTree<Integer> right = new BinaryTree<>(3);

        tree.setLeft(left);
        tree.setRight(right);

        left.setRight(new BinaryTree<>(null));
        right.setLeft(new BinaryTree<>(null));
        right.setRight(new BinaryTree<>(null));

        assertEquals(false, BinaryTree.isBST(tree));
    }

    @Test
    public void testIsNotBS3() {
        BinaryTree<Integer> tree = new BinaryTree<>(2);
        BinaryTree<Integer> left = new BinaryTree<>(1);
        BinaryTree<Integer> right = new BinaryTree<>(null);

        tree.setLeft(left);
        tree.setRight(right);

        left.setLeft(new BinaryTree<>(null));
        left.setRight(new BinaryTree<>(null));
        right.setLeft(new BinaryTree<>(null));
        right.setRight(new BinaryTree<>(null));

        assertEquals(false, BinaryTree.isBST(tree));
    }

    @Test
    public void testEqualBST() {
        BinaryTree<Integer> tree = new BinaryTree<>(2);
        BinaryTree<Integer> left = new BinaryTree<>(2);
        BinaryTree<Integer> right = new BinaryTree<>(2);

        tree.setLeft(left);
        tree.setRight(right);

        left.setLeft(new BinaryTree<>(null));
        left.setRight(new BinaryTree<>(null));
        right.setLeft(new BinaryTree<>(null));
        right.setRight(new BinaryTree<>(null));

        assertEquals(true, BinaryTree.isBST(tree));
    }


    @Test
    public void testEmptyBST() {
        BinaryTree<Integer> tree = new BinaryTree<>(null);

        assertEquals(true, BinaryTree.isBST(tree));
    }

    @Test
    public void testIsBSTBigger() {
        BinaryTree<Integer> tree0 = new BinaryTree<Integer>(100);
        BinaryTree<Integer> tree1 = new BinaryTree<Integer>(50);
        BinaryTree<Integer> tree2 = new BinaryTree<Integer>(-1);
        BinaryTree<Integer> tree3 = new BinaryTree<Integer>(-10);
        BinaryTree<Integer> tree4 = new BinaryTree<Integer>(null);
        tree3.setLeft(tree4);
        BinaryTree<Integer> tree5 = new BinaryTree<Integer>(null);
        tree3.setRight(tree5);
        tree2.setLeft(tree3);
        BinaryTree<Integer> tree6 = new BinaryTree<Integer>(49);
        BinaryTree<Integer> tree7 = new BinaryTree<Integer>(null);
        tree6.setLeft(tree7);
        BinaryTree<Integer> tree8 = new BinaryTree<Integer>(null);
        tree6.setRight(tree8);
        tree2.setRight(tree6);
        tree1.setLeft(tree2);
        BinaryTree<Integer> tree9 = new BinaryTree<Integer>(54);
        BinaryTree<Integer> tree10 = new BinaryTree<Integer>(null);
        tree9.setLeft(tree10);
        BinaryTree<Integer> tree11 = new BinaryTree<Integer>(null);
        tree9.setRight(tree11);
        tree1.setRight(tree9);
        tree0.setLeft(tree1);
        BinaryTree<Integer> tree12 = new BinaryTree<Integer>(150);
        BinaryTree<Integer> tree13 = new BinaryTree<Integer>(null);
        tree12.setLeft(tree13);
        BinaryTree<Integer> tree14 = new BinaryTree<Integer>(null);
        tree12.setRight(tree14);
        tree0.setRight(tree12);

        assertTrue(BinaryTree.isBST(tree0));

    }

}
