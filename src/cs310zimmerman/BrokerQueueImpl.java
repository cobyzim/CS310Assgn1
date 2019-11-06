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
public class BrokerQueueImpl 
{
    private BrokerNode first;   // reference to the first item in the queue
    private BrokerNode last;    // reference to the last item in the queue

    /**
     *
     */
    public BrokerQueueImpl() 
    {
        first = null;
        last = null;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty() 
    {
        boolean empty = false;
        if (first == null) {
            empty = true;
        }
        return empty;
    }

    /**
     *
     * @param broker
     */
    public void add(Broker broker) 
    {
        BrokerNode newNode = new BrokerNode(broker);
        if (last != null) {
            last.setNext(newNode);
        }
        else {
            first = newNode;
        }
        last = newNode;
    }

    /**
     *
     * @return
     */
    public Broker remove() 
    {
        Broker broker = null;
        if (first != null) {
            broker = first.getData();
            first = first.getNext();
            if (isEmpty()) {
                last = null;
            }
        }
        else {
            System.out.println("Queue was empty - cannot return broker");
        }
        return broker;
    }
    
}
