import java.util.Arrays;
import java.util.EmptyStackException;

/**
    A class of stacks whose entries are stored in an array.
    @author Warren Maxwell
*/
public final class ResizableArrayStack<T> implements StackInterface<T>{
    private T[] stack; // Array of stack entries
    private int topIndex; // Index of top entry
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ResizableArrayStack(){
        this(DEFAULT_CAPACITY);
    }// end Default Constructor

    // The cast is safe because the new array contains null entries.
    public ResizableArrayStack(int initialCapacity){
        integrityOK = false;
        checkCapacity(initialCapacity);

        //  The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        integrityOK = true;
    } // end Constructor

    /**
     * Insert a new item onto the stack.
     * @param newEntry The item to be inserted.
     */
    public void push(T newEntry){
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    } // end push

    /**
     * Remove the top item from the stack.
     * @return The item that was removed.
     */
    public T pop(){
        checkIntegrity();
        if(isEmpty())
            throw new EmptyStackException();
        else{
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        } // end if
    } // end pop

    /**
     * Retrieve the most recent entry of the stack.
     * @return The top item of the stack.
     */
    public T peek(){
        checkIntegrity();
        if(isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    } // end peek

    /**
     * Determine whether the stack is empty.
     * @return Whether or not the stack is empty.
     */
    public boolean isEmpty(){
        return topIndex < 0;
    } // end isEmpty

    /**
     * Remove all entries from the stack.
     */
    public void clear(){

        checkIntegrity();

        while(topIndex > -1){
            stack[topIndex] = null;
            topIndex--;
        } // end while
    } // end clear

    /**
     * Verify that the stack can hold more entries
     */
    private void ensureCapacity(){
        if(topIndex >= stack.length - 1){
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        } // end if
    } // end ensureCapacity

    /**
     * Verify the integrity of the stack
     */
    private void checkIntegrity(){

        if(!integrityOK)
            throw new SecurityException("ArrayStack object is corrupt.");
    } // end checkIntegrity

    /**
     * Verify the capacity of the stack
     * @param capacity The capacity of the new stack.
     */
    private void checkCapacity(int capacity){
        if(capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a stack " +
                                            "whose capacity exceeds " +
                                            "allowed maximum of " + MAX_CAPACITY);
    }

}