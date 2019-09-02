import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * An iterator for the tree ADT that performs a preorder traversal
 */
public class TreeIterator<E> implements Iterator<E> {

    private Stack<Tree<E>> stack = new Stack<>();

    /**
     * Constructs a new tree iterator from the root of the tree to iterate over
     *
     * You are welcome to modify this constructor but cannot change its signature
     * This method should have O(1) time complexity
     */
    public TreeIterator(Tree<E> root) {
        stack.add(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public E next() {
        if (!hasNext())
            throw new NoSuchElementException("Iterator has no next.");
        Tree<E> nextTree = stack.pop();
        // pop current node before iterating over its children, because
        // this is a preorder.
        List<Tree<E>> children = nextTree.getChildren();
        for (int i = 0; i < children.size(); i++) {
            // iterate backwards so leftmost child is on top of the stack.
            int index = children.size()-1 - i;
            stack.add(children.get(index));
        }
        return nextTree.getRoot();
    }
}
