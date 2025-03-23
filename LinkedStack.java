import java.util.EmptyStackException;
/**
    A class of stacks whose entries are stored in a chain of nodes.
    @author Josiah Degeneffe
*/
public final class LinkedStack<T> implements StackInterface<T>
{
    private Node topNode; // References the first node in the chain
    /* Default constructor for linked stack */
    public LinkedStack()
    {
        topNode = null;
    } // end default constructor

    /**
     * Adds a new entry to the top of the stack
     * @param newEntry The item to be addded to the stack
     */
    @Override
    public void push(T newEntry)
    {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    } // end push

    /**
     * Looks at the top item of the stack without removing it
     * @return The value of the stack's top item
     */
    @Override
    public T peek()
    {
        if (isEmpty())
            throw new EmptyStackException();
        else
            return topNode.getData();
    } // end peek

    /**
     * Remove an item from the top of the stack
     * @return The contents of the removed item
     */
    @Override
    public T pop()
    {
        T top = peek();  // Might throw EmptyStackException
    
        // Assertion: topNode != null
        topNode = topNode.getNextNode();
    
        return top;
    } // end pop
    
    /**
     * Determines whether or not the stack is empty
     * @return True if empty, false if not
     */
    @Override
    public boolean isEmpty()
    {
        return topNode == null;
    } // end isEmpty

    /** Removes all items from the stack */
    @Override
    public void clear()
    {
        topNode = null;
    } // end clear

	private class Node
	{
        private final T    data; // Entry in stack
        private final Node next; // Link to next node
      
        private Node(T dataPortion, Node linkPortion)
        {
            data = dataPortion;
            next = linkPortion;
        } // end constructor
      
        private T getData()
        {
            return data;
        } // end getData
      
        private Node getNextNode()
        {
            return next;
        } // end getNextNode
    } // end Node
} // end LinkedStack
