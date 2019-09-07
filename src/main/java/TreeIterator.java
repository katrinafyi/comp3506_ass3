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

    /**
     * Checks if the iterator has items remaining.
     *
     * This method has complexity of O(1) (always), because it only needs to
     * test whether a stack has items. We assume the stack isEmpty is O(1).
     *
     * @return True if next() can be called successfully.
     */
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * Returns the next item in the iterator in a preorder traversal of a tree.
     *
     * This method has worst-case complexity of O(k) where k is maximum number
     * of children one node can have in the tree. However, its amortised time
     * complexity is O(1) because the O(k) call occurs once for each parent
     * node, then is O(1) for the k child nodes (unless they are also
     * parent nodes, in which case the amortisation flows onto their
     * children as well.)
     *
     * Overall, repeatedly calling next() and exhausting the iterator will
     * take O(n) time, where n is the number of nodes in the tree.
     *
     * We assume that the stack and tree operations are O(1), with the exception
     * of the tree's getChildren() which may be O(k) worst-case (remember,
     * k is number of children). However, this is amortised O(1) for the same
     * reason as above.
     *
     * @return Next value in tree.
     * @throws NoSuchElementException If iterator is empty.
     */
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
