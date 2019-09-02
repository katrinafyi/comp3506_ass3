import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A binary tree, where each node contains at most two children
 * Each root node contains a value and references to its left and right children (if they exist)
 *
 * @param <E> the type of the tree's elements
 */
public class BinaryTree<E> implements Tree<E> {
    private E root; // the element sitting at this tree's root node
    private BinaryTree<E> left; // the left child (subtree)
    private BinaryTree<E> right; // the right child (subtree)

    /**
     * Constructs a new binary tree with a single node
     *
     * @param root the element to store at this tree's root
     */
    public BinaryTree(E root) {
        this.root = root;
        this.left = null;
        this.right = null;
    }

    @Override
    public int size() {
        return 1 + (left != null ? left.size() : 0) + (right != null ? right.size() : 0);
    }

    @Override
    public E getRoot() {
        return root;
    }

    @Override
    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public List<Tree<E>> getChildren() {
        List<Tree<E>> children = new ArrayList<>();
        if (left != null) {
            children.add(left);
        }
        if (right != null) {
            children.add(right);
        }
        return children;
    }

    @Override
    public boolean contains(E elem) {
        if (root.equals(elem)) {
            return true;
        }
        return (left != null && left.contains(elem)) || (right != null && right.contains(elem));
    }

    @Override
    public Iterator<E> iterator() {
        return new TreeIterator<>(this);
    }

    /**
     * Sets the left child of this tree's root to the given subtree
     * Any existing left child will be overridden
     *
     * @param left the new left child
     */
    public void setLeft(BinaryTree<E> left) {
        this.left = left;
    }

    /**
     * Sets the right child of this tree's root to the given subtree
     * Any existing right child will be overridden
     *
     * @param right the new right child
     */
    public void setRight(BinaryTree<E> right) {
        this.right = right;
    }

    /**
     * @return the left child subtree of this tree's root node
     */
    public BinaryTree<E> getLeft() {
        return left;
    }

    /**
     * @return the right child subtree of this tree's root node
     */
    public BinaryTree<E> getRight() {
        return right;
    }

    /**
     * Tests whether the given tree can be a subtree of a BST. More specifically,
     * it tests if tree is a BST and its elements are >= lb and <= ub.
     *
     * By definition, the last value-containing node in a branch must have two
     * child nodes, both with null values and no children. Yes, this definition
     * sucks. Direct your complaint to Goodrich, Tamassia and Goldwasser.
     *
     * This implementation will reject (i.e. return false for) any tree
     * which does not meet the above criteria.
     *
     * @param tree Root node of tree. Value of this node must be non-null.
     * @param lb Lower bound of values in the BST.
     * @param ub Upper bound of values in the BST.
     * @param <T> Comparable type of values in BST.
     * @return True if tree is a BST, false otherwise.
     */
    private static <T extends Comparable<T>> boolean
            isBSTSubtree(BinaryTree<T> tree, T lb, T ub) {
        if (tree == null)
            return false;
        if (tree.getRoot() == null)
            return tree.isLeaf();

        if (lb != null && tree.getRoot().compareTo(lb) < 0)
            return false;
        if (ub != null && tree.getRoot().compareTo(ub) > 0)
            return false;

        BinaryTree<T> left = tree.getLeft();
        if (!isBSTSubtree(left, lb, tree.getRoot()))
            return false;

        BinaryTree<T> right = tree.getRight();
        if (!isBSTSubtree(right, tree.getRoot(), ub))
            return false;

        return true;
    }

    /**
     * Determines whether the parameter tree is a binary search tree or not
     * This is determined by the definition of a binary search tree provided in the lectures
     *
     * @param tree the tree to check
     * @return true if this tree is a BST, otherwise false
     */
    public static <T extends Comparable<T>> boolean isBST(BinaryTree<T> tree) {
        return isBSTSubtree(tree, null, null);
    }
}
