/*
 * Implementation class used to implement the two queues of top and standard
 * brokers. Includes a constructor and add, remove, and isEmpty methods to 
 * manage the queues.
 */
package cs310zimmerman;

/**
 * 
 * @author Coby Zimmerman
 */
public class BrokerQueueImpl 
{
    private BrokerNode first;
    private BrokerNode last;

    /**
     * Constructor setting values of first and last to null
     */
    public BrokerQueueImpl() 
    {
        first = null;
        last = null;
    }

    /**
     * Method responsible for checking if the queues are empty
     * 
     * @return - returns true if the queue is empty
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
     * Method used to add a broker object to the queues of brokers waiting for
     * go-karts.
     * 
     * @param broker - passes in a broker object
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
     * Method that removes a broker object from the queues of brokers waiting
     * for go-karts.
     * 
     * @return - returns broker object
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
