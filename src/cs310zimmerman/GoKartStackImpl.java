/*
 * Implementation class used to implement the two stacks of go-karts to be used
 * by the brokers. The stacks use the same constructor, and the class includes
 * four other methods including push, pop, isEmpty, and isFull to manage the
 * stacks.
 */
package cs310zimmerman;

/**
 * 
 * @author Coby Zimmerman
 */
public class GoKartStackImpl 
{
    int[] goKartStack;
    int top;
    int startGoKartNumber;
    final int STACK_SIZE;
    
    /**
     * Constructor method using only one parameter. I chose not to use this 
     * constructor.
     * 
     * @param numGoKarts - passes in number of go-karts
     */
    public GoKartStackImpl(int numGoKarts)
    {
        STACK_SIZE = numGoKarts;    
        goKartStack = new int[STACK_SIZE];
        top = 0;
    }
    
    /**
     * Constructor method using two parameters, the number of go karts, and the
     * start go-kart number. I used this constructor to build both the racing
     * kart stack and the basic kart stack.
     * 
     * @param numGoKarts - passes in number of karts
     * @param startGoKartNum - passes in the start go-kart number
     */
    public GoKartStackImpl(int numGoKarts, int startGoKartNum) 
    {
        STACK_SIZE = numGoKarts;
        goKartStack = new int[STACK_SIZE];
        top = numGoKarts - 1;
        
        for (int i = 0; i < STACK_SIZE; i++) {
            goKartStack[i] = startGoKartNum;
            startGoKartNum--;
        }
       
    }  
    
    /**
     * Method responsible for pushing karts back into the stack after a broker
     * returns it.
     * 
     * @param goKartNum - passes in go-kart number
     */
    public void push (int goKartNum) 
    {
        if (top < goKartStack.length) {
            top++;
            goKartStack[top] = goKartNum;
        }
        else {
            System.out.println("the stack is full");
        }
    }

    /**
     * Method that pops a kart off the stack when a broker requests it.
     * 
     * @return - returns the number of the go-kart being popped off
     */
    public int pop() 
    {
        int goKartNum = -1;
        if (!isEmpty()) {
            goKartNum = goKartStack[top];
            top--;
        }
        else {
            System.out.println("the stack is empty");
        }
        
        return goKartNum;
    }

    /**
     * Method that checks if the stack is empty
     * 
     * @return - returns true if the stack is empty
     */
    public boolean isEmpty() 
    {      
        boolean empty = false;
        if (top == -1) {
            empty = true;
        }
        
        return empty;   
    }
      
    /**
     * Method that checks if the stack is full
     * 
     * @return - returns true if the stack is full
     */
    public boolean isFull() 
    {   
        boolean full = false;
        if (top >= STACK_SIZE){ 
            full = true;
        }
        
        return full;
    }
    
    public int getStackSize() {
        return STACK_SIZE;
    }
    
    public int getStartGoKartNumber(int startNumber) {
        startGoKartNumber = startNumber - 1;
        
        return startGoKartNumber;
    }
}
    


