import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class MoreTreeTest {

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

    @Test(timeout=1000)
    public void testIsBSTNulls() {
        BinaryTree<Integer> tree = new BinaryTree<>(2);
        BinaryTree<Integer> left = new BinaryTree<>(1);
        BinaryTree<Integer> right = new BinaryTree<>(3);

        tree.setLeft(left);
        tree.setRight(right);
        left.setLeft(null);
        left.setRight(null);
        right.setLeft(null);
        right.setRight(null);

        assertFalse(BinaryTree.isBST(tree)); // CHANGED TO FALSE
    }

    @Test
    public void testEmptyBST() {
        BinaryTree<Integer> emptyTree = new BinaryTree<>(null);

        assertTrue(BinaryTree.isBST(emptyTree));
    }

    @Test
    public void testNullBST() {
        assertFalse(BinaryTree.isBST(null)); // CHANGED TO UNCOMMENT
    }

    @Test
    public void testBSTWithNonNull() {
        BinaryTree<Integer> tree = new BinaryTree<>(2);
        BinaryTree<Integer> left = new BinaryTree<>(1);
        BinaryTree<Integer> right = new BinaryTree<>(3);

        tree.setLeft(left);
        tree.setRight(right);
        left.setLeft(new BinaryTree<>(null));
        left.setRight(new BinaryTree<>(null));
        right.setLeft(new BinaryTree<>(null));
        //right.setRight(new BinaryTree<>(null));

        assertFalse(BinaryTree.isBST(tree));

    }

    @Test
    public void testBiggerBST() {
        BinaryTree<Integer> tree = new BinaryTree<>(6);
        BinaryTree<Integer> left = new BinaryTree<>(2);
        BinaryTree<Integer> right = new BinaryTree<>(9);
        BinaryTree<Integer> leftleft = new BinaryTree<>(1);
        BinaryTree<Integer> leftright = new BinaryTree<Integer>(4);
        BinaryTree<Integer> rightleft = new BinaryTree<Integer>(8);

        tree.setLeft(left);
        tree.setRight(right);
        left.setLeft(leftleft);
        left.setRight(leftright);
        right.setLeft(rightleft);
        leftleft.setLeft(new BinaryTree<>(null));
        leftleft.setRight(new BinaryTree<>(null));
        leftright.setLeft(new BinaryTree<>(null));
        leftright.setRight(new BinaryTree<>(null));
        rightleft.setLeft(new BinaryTree<>(null));
        rightleft.setRight(new BinaryTree<>(null));
        right.setRight(new BinaryTree<>(null));

        assertTrue(BinaryTree.isBST(tree));

    }


    @Test
    public void testNotBST1() {
        BinaryTree<Integer> tree = new BinaryTree<>(10);
        BinaryTree<Integer> left = new BinaryTree<>(2);
        BinaryTree<Integer> right = new BinaryTree<>(9);
        BinaryTree<Integer> leftleft = new BinaryTree<>(1);
        BinaryTree<Integer> leftright = new BinaryTree<Integer>(4);
        BinaryTree<Integer> rightleft = new BinaryTree<Integer>(8);

        tree.setLeft(left);
        tree.setRight(right);
        left.setLeft(leftleft);
        left.setRight(leftright);
        right.setLeft(rightleft);
        leftleft.setLeft(new BinaryTree<>(null));
        leftleft.setRight(new BinaryTree<>(null));
        leftright.setLeft(new BinaryTree<>(null));
        leftright.setRight(new BinaryTree<>(null));
        rightleft.setLeft(new BinaryTree<>(null));
        rightleft.setRight(new BinaryTree<>(null));
        right.setRight(new BinaryTree<>(null));

        assertFalse(BinaryTree.isBST(tree));
    }


    @Test
    public void testNotBST2() {
        BinaryTree<Integer> tree = new BinaryTree<>(6);
        BinaryTree<Integer> left = new BinaryTree<>(2);
        BinaryTree<Integer> right = new BinaryTree<>(8);
        BinaryTree<Integer> leftleft = new BinaryTree<>(1);
        BinaryTree<Integer> leftright = new BinaryTree<Integer>(4);
        BinaryTree<Integer> rightleft = new BinaryTree<Integer>(8);

        tree.setLeft(left);
        tree.setRight(right);
        left.setLeft(leftleft);
        left.setRight(leftright);
        right.setLeft(rightleft);
        leftleft.setLeft(new BinaryTree<>(null));
        leftleft.setRight(new BinaryTree<>(null));
        leftright.setLeft(new BinaryTree<>(null));
        leftright.setRight(new BinaryTree<>(null));
        rightleft.setLeft(new BinaryTree<>(null));
        rightleft.setRight(new BinaryTree<>(null));
        right.setRight(new BinaryTree<>(null));

        assertTrue(BinaryTree.isBST(tree));

    }
}