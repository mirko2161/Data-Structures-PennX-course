package data_structures;

import java.util.Queue;

/**
 * A simple use case of a queue - a system to track requests for tickets to an event.
 *
 */
public class EventAvailableCapacity {

    protected Queue<Integer> ticketRequests;
    protected int availableCapacity;

    public EventAvailableCapacity(int maxCapacity) {
        ticketRequests = new java.util.LinkedList<>();
        this.availableCapacity = maxCapacity;
    }

    /**
     * Adds a new request for tickets to the rear of the queue.
     *
     * @param numPeople number of tickets for the request
     */
    public void addTicketRequest(int numPeople) {
        ticketRequests.add(numPeople);
    }

    /**
     * Processes the requests for tickets in the order they where added. Returns when no more
     * tickets are available.
     *
     * @return total number of requests processed
     */
    public int processUntilNoCapacity() {
        int numRequestsProcessed = 0;
        while (!ticketRequests.isEmpty()) {
            int remainAfterRequest = (availableCapacity - ticketRequests.peek());
            if (remainAfterRequest < 0) {
                return numRequestsProcessed;
            }
            availableCapacity -= ticketRequests.remove();
            numRequestsProcessed++;
        }
        return 0;
    }

}
