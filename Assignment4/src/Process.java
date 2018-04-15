/**
 * Creates Process Objects and provides update and access methods for fields of Process Objects
 */
public class Process {
    private Integer pr;             // priority of the process
    private int id;                 // process id
    private int arrivalTime;        // the time when the process arrives at the system
    private int duration;           // execution of the process takes this amount of time
    private int waitTime = 0;       // how long the process has been on queue

    /**
     * Constructor
     * Constructs an empty Process Object.
     */
    public Process() {}

    /**
     * Constructor
     * Constructs a Process Object with given fields.
     *
     * @param id            ID of the Process.
     * @param priority      Priority of the Process.
     * @param duration      How long the Process takes to finish.
     * @param arrivalTime   The time that the Process arrives on the Priority Queue
     */
    public Process(Integer id, Integer priority, Integer duration, Integer arrivalTime) {
        this.pr = priority;
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
    }

    /**
     * Returns the priority of this Process Object.
     * @return  the priority of this Process Object.
     */
    public Integer getPr() {
        return pr;
    }

    /**
     * Sets the priority of this Process Object.
     * @param pr    The priority to set of the Process Object.
     */
    public void setPr(Integer pr) {
        this.pr = pr;
    }

    /**
     * Returns the ID of this Process Object.
     * @return  The ID of this Process Object.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the arrival time of this Process Object.
     * @return  The arrival time of this Process Object.
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Returns the duration of this Process Object.
     * @return  The duration of this Process Object.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Returns the wait time of this Process Object.
     * @return  The wait time of this Process Object.
     */
    public int getWaitTime() {
        return waitTime;
    }

    /**
     * Sets the wait time of this Process Object.
     * @param time    The wait time to set of the Process Object.
     */
    public void setWaitTime(int time) {
        this.waitTime = time;
    }

    /**
     * String of information about this Process Object.
     * @return  String of information about this Process Object.
     */
    public String toList() {
        return "Process ID = " + getId() + " Priority = " + getPr() + " Duration = " + getDuration()
                + " Arrival = " + getArrivalTime();
    }

    /**
     * String of information about this Process Object with formatting.
     * @return  String of information about this Process Object.
     */
    @Override
    public String toString() {
        return "Process ID = " + getId() + "\n" +
                "\tPriority = " + getPr() + "\n" +
                "\tArrival = " + getArrivalTime() + "\n" +
                "\tDuration = " + getDuration();
    }
}
