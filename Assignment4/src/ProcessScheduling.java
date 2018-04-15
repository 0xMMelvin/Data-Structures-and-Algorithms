import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ProcessScheduling {

    public static void main(String[] args) {
        // current logical time
        int currentTime = 0;
        // maximum wait time
        int maxTime = 10;
        // is there a process executing
        boolean running = false;
        // total wait time
        double totalWaitTime = 0;
        // total processes
        int numProcesses = 0;
        // create an empty PQ
        HeapAdaptablePriorityQueue<Integer, Process> pq = new HeapAdaptablePriorityQueue<>();

        // get data from the file and store them as Process Objects in an DoublyLinkedList
        Scanner data;
        String[] process;
        DoublyLinkedList<Process> processList = new DoublyLinkedList<>();
        // create a string that will eventually be written to a file
        StringBuilder content = new StringBuilder();

        try {
            // get data
            data = new Scanner(new File("process_scheduling_input_1.txt"));
            while (data.hasNextLine()) {
                String line = data.nextLine();
                // split on spaces
                process = line.split("\\s+");
                // create new Process from data
                Process p = new Process(Integer.parseInt(process[0]), Integer.parseInt(process[1]),
                        Integer.parseInt(process[2]), Integer.parseInt(process[3]));
                // increment
                numProcesses++;
                // add info to content
                content.append(p.toList() + "\n");
                // add new Process to the DoublyLinkedList
                processList.addLast(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create new empty Process
        Process executing = new Process();
        // loop through the list
        while (!processList.isEmpty()) {
            // get the arrival time of the next incoming process
            int nextArrivalTime = processList.first().getArrivalTime();
            // if there is a Process ready
            if (nextArrivalTime <= currentTime) {
                // set wait time to 0
                processList.first().setWaitTime(0);
                // get the key and value
                Integer key = processList.first().getPr();
                Process p = processList.removeFirst();
                // insert key and value into pq
                pq.insert(key, p);
            }
            // if there are process in pq and no processes are currently running
            if (!pq.isEmpty() && !running) {
                // now there is a process running
                running = true;
                // set currently executing process
                executing = pq.removeMin().getValue();
                // display that the process is running and increment totalWaitTime
                totalWaitTime = displayInfo(executing, currentTime, totalWaitTime, content);
            }
            // increment current logical time
            currentTime++;
            // if there are processes waiting in pq update their wait times
            if (!pq.isEmpty()) {
                pq = updateWaitTimes(pq);
            }
            // if the process has finished
            if (currentTime - executing.getArrivalTime() >= executing.getDuration() + executing.getWaitTime()
                    && running) {
                // no processes are executing now
                running = false;
                // update content
                content.append("Process " + executing.getId() + " finished at " + currentTime + "\n");
                // update priorities of processes if necessary
                if (!pq.isEmpty()) {
                    pq = updatePriorities(pq, maxTime, content);
                }
            }
        }
        // used to print "D is empty"
        int i = 0;
        while (!pq.isEmpty()) {
            // if there is no process running
            if (!running) {
                // now there is a process running
                running = true;
                // set currently executing process
                executing = pq.removeMin().getValue();
                // display that the process is running and increment totalWaitTime
                totalWaitTime = displayInfo(executing, currentTime, totalWaitTime, content);
            }
            // increment current logical time
            ++currentTime;
            // update wait times of processes on queue
            pq = updateWaitTimes(pq);
            // if the process has finished
            if (currentTime - executing.getArrivalTime() >= executing.getDuration() + executing.getWaitTime()
                    && running) {
                // no processes are executing now
                running = false;
                // update content
                content.append("Process " + executing.getId() + " finished at " + currentTime + "\n");
            }
            if (!pq.isEmpty() && !running) {
                // update priorities if necessary
                pq = updatePriorities(pq, maxTime, content);
                // processList is empty
                if (i == 0) {
                    // update content
                    content.append("\n");
                    content.append("D is empty" + "\n");
                    // no longer print "D is empty"
                    i++;
                }
            }
        }
        // add wait time stats to content
        content.append("Total wait time = " + totalWaitTime + "\n");
        content.append("Average wait time = " + totalWaitTime / numProcesses + "\n");
        // write to file
        try {
            Files.write(Paths.get("output.txt"), content.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the information for the executing Process Object.
     *
     * @param process The currently executing Process Object.
     * @param time    Current logical time of the program.
     * @return The executed Process Object.
     */
    public static double displayInfo(Process process, int time, double totalWaitTime, StringBuilder content) {
        // display currently executing process
        content.append("\n");
        content.append("Process removed from queue is: id = " + process.getId() +
                ", at time " + time + ", wait time = " + process.getWaitTime() + "\n");
        totalWaitTime += process.getWaitTime();
        content.append("Total wait time = " + totalWaitTime + "\n");
        content.append(process.toString() + "\n");
        return totalWaitTime;
    }

    /**
     * Updates the wait times of Process Objects in the PriorityQueue.
     *
     * @param pq The PriorityQueue of Process Objects.
     * @return The PriorityQueue of Process Objects with incremented wait times.
     */
    public static HeapAdaptablePriorityQueue<Integer, Process> updateWaitTimes(HeapAdaptablePriorityQueue<Integer, Process> pq) {
        // create a list to hold Entry Objects
        DoublyLinkedList<Entry<Integer, Process>> list = new DoublyLinkedList<>();
        // create a temporary PriorityQueue to store the updated Process Objects and return them
        HeapAdaptablePriorityQueue<Integer, Process> temp = new HeapAdaptablePriorityQueue<>();
        while (!pq.isEmpty()) {
            // get the Entry with lowest key
            Entry<Integer, Process> entry = pq.removeMin();
            // increment wait time
            int newWaitTime = entry.getValue().getWaitTime() + 1;
            entry.getValue().setWaitTime(newWaitTime);
            // add to the list
            list.addLast(entry);
        }
        while (!list.isEmpty()) {
            // get the first Entry from the list
            Entry<Integer, Process> updatedEntry = list.removeFirst();
            // get the key and Process
            Integer key = updatedEntry.getKey();
            Process p = updatedEntry.getValue();
            // insert updated Process and key into temp
            temp.insert(key, p);
        }
        return temp;
    }

    /**
     * Decrements the priority of a Process if it has been on the PriorityQueue longer than the maximum wait time.
     *
     * @param pq  The PriorityQueue of Process Objects.
     * @param max The maximum wait time.
     * @return The PriorityQueue of Process Objects with decremented priorities (if necessary).
     */
    public static HeapAdaptablePriorityQueue<Integer, Process> updatePriorities(HeapAdaptablePriorityQueue<Integer, Process> pq,
                                                                                Integer max, StringBuilder content) {
        Iterator iter = pq.heap.iterator();
        while (iter.hasNext()) {
            HeapAdaptablePriorityQueue.AdaptablePQEntry<Integer, Process> e =
                    (HeapAdaptablePriorityQueue.AdaptablePQEntry<Integer, Process>) iter.next();
            if (e.getValue().getWaitTime() >= max && e.getValue().getPr() > 1) {
                int newPriority = e.getValue().getPr() - 1;
                e.getValue().setPr(newPriority);
                pq.replaceKey(e, newPriority);
                content.append("Priority of process " + e.getValue().getId() + " decremented, " +
                        "New priority is " + e.getValue().getPr() + "\n");
            }
        }
        return pq;
    }
}