/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs310zimmerman;

/**
 * 
 * @author Coby Zimmerman
 */
public class GoKartStackImpl 
{
    int[] goKartStack;      // the array that actually contains the stack data
    int top;                // the index of the top of the stack data 

    final int STACK_SIZE;   // the size of the stack
    
    /**
     * 
     * @param numGoKarts 
     */
    public GoKartStackImpl(int numGoKarts)
    {
        STACK_SIZE = numGoKarts;    
        goKartStack = new int[STACK_SIZE];
        top = 0;
        
        /*
        GoKartStackImpl goKartBasicStack = new GoKartStackImpl(4);
        for (int i = goKartBasicStack.STACK_SIZE; i >= 0; i--) {
            goKartBasicStack.push(i);
        }
        
        GoKartStackImpl goKartRacingStack = new GoKartStackImpl(3);
        for (int i = goKartRacingStack.STACK_SIZE; i >= 0; i--) {
            goKartRacingStack.push(i + goKartBasicStack.STACK_SIZE);
        }
        */
    }
    
    /**
     * 
     * @param numGoKarts
     * @param startGoKartNum 
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
     * 
     * @param goKartNum 
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
     * 
     * @return 
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
     * 
     * @return 
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
     * 
     * @return 
     */
    public boolean isFull() 
    {   
        boolean full = false;
        if (top >= STACK_SIZE){ 
            full = true;
        }
        
        return full;
    }    
}
    


