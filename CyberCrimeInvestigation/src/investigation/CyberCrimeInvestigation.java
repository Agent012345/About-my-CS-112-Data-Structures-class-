package investigation;

import java.util.ArrayList;

/*  
 * This class represents a cyber crime investigation.  It contains a directory of hackers, which is a resizing
 * hash table. The hash table is an array of HNode objects, which are linked lists of Hacker objects.  
 * 
 * The class contains methods to add a hacker to the directory, remove a hacker from the directory.
 * You will implement these methods, to create and use the HashTable, as well as analyze the data in the directory.
 * 
 * @author Colin Sullivan
 */
public class CyberCrimeInvestigation {

    private HNode[] hackerDirectory;
    private int numHackers = 0;

    public CyberCrimeInvestigation() {
        hackerDirectory = new HNode[10];
    }

    /**
     * Initializes the hacker directory from a file input.
     * 
     * @param inputFile
     */
    public void initializeTable(String inputFile) {
        // DO NOT EDIT
        StdIn.setFile(inputFile);
        while (!StdIn.isEmpty()) {
            addHacker(readSingleHacker());
        }
    }

    /**
     * Reads a single hackers data from the already set file,
     * Then returns a Hacker object with the data, including
     * the incident data.
     * 
     * StdIn.setFile() has already been called for you.
     * 
     * @param inputFile The name of the file to read hacker data from.
     */
    public Hacker readSingleHacker() {
        String hackerName = StdIn.readLine();
        String ipAddress = StdIn.readLine();
        String location = StdIn.readLine();
        String os = StdIn.readLine();
        String webServer = StdIn.readLine();
        String date = StdIn.readLine();
        String url = StdIn.readLine();

        Incident newIncident = new Incident(os, webServer, date, location, ipAddress, url);

        Hacker hacker = new Hacker(hackerName);

        hacker.addIncident(newIncident);

        return hacker;
    }

    /**
     * Adds a hacker to the directory. If the hacker already exists in the
     * directory,
     * instead adds the given Hacker's incidents to the existing Hacker's incidents.
     * 
     * After a new insertion (NOT if a hacker already exists), checks if the number
     * of
     * hackers in the table is >= table length divided by 2. If so, calls resize()
     * 
     * @param toAdd
     */
    public void addHacker(Hacker toAdd) {
        int tableLength = hackerDirectory.length;
        int indexOfHacker = toAdd.hashCode() % tableLength;
        HNode toAddHacker = new HNode(toAdd);

        HNode curr = hackerDirectory[indexOfHacker];

        while (curr != null) {
            if (curr.getHacker().equals(toAdd)) {
                curr.getHacker().getIncidents().addAll(toAdd.getIncidents());
                return;
            }

            curr = curr.getNext();
        }

        if (hackerDirectory[indexOfHacker] == null) {
            hackerDirectory[indexOfHacker] = toAddHacker;
            numHackers++;
        }

        else {
            HNode pointer = hackerDirectory[indexOfHacker];
            while (pointer.getNext() != null) {
                pointer = pointer.getNext();
            }
            pointer.setNext(toAddHacker);

            numHackers++;
        }

        if (numHackers >= tableLength / 2) {
            resize();
        }

    }

    /**
     * Resizes the hacker directory to double its current size. Rehashes all hackers
     * into the new doubled directory.
     */
    private void resize() {
        HNode[] temporaryDirectory = hackerDirectory;

        numHackers = 0;
        HNode[] newDirectory = new HNode[2 * hackerDirectory.length];
        hackerDirectory = newDirectory;

        for (int i = 0; i < temporaryDirectory.length; i++) {
            HNode pointer = temporaryDirectory[i];

            while (pointer != null) { // Searching for existing value. Otherwise, if adding, we would be using "while
                                      // (pointer.getNext() != null)"
                addHacker(pointer.getHacker());
                pointer = pointer.getNext();
            }
        }

    }

    /**
     * Searches the hacker directory for a hacker with the given name.
     * Returns null if the Hacker is not found
     * 
     * @param toSearch
     * @return The hacker object if found, null otherwise.
     */
    public Hacker search(String toSearch) {

        int indexToSearch = Math.abs(toSearch.hashCode()) % hackerDirectory.length;

        HNode pointer = hackerDirectory[indexToSearch];

        while (pointer != null) {
            if (pointer.getHacker().getName().equals(toSearch)) {
                Hacker targethacker = pointer.getHacker();
                return targethacker;
            }
            pointer = pointer.getNext();
        }

        return null;
    }

    /**
     * Removes a hacker from the directory. Returns the removed hacker object.
     * If the hacker is not found, returns null.
     * 
     * @param toRemove
     * @return The removed hacker object, or null if not found.
     */
    public Hacker remove(String toRemove) {
        
        int indexToRemove = Math.abs(toRemove.hashCode()) % hackerDirectory.length;

        HNode prev = null;
        HNode pointer = hackerDirectory[indexToRemove];

        while (pointer != null) {
            if (pointer.getHacker().getName().equals(toRemove)) {
                Hacker targetHacker = pointer.getHacker();
                if (prev == null) {
                    hackerDirectory[indexToRemove] = pointer.getNext();
                } else {
                    prev.setNext(pointer.getNext());
                }
                pointer = null;
                numHackers--;

                return targetHacker;
            }
            prev = pointer;
            pointer = pointer.getNext();
        }

        return null;
    }

    /**
     * Merges two hackers into one based on number of incidents.
     * 
     * @param hacker1 One hacker
     * @param hacker2 Another hacker to attempt merging with
     * @return True if the merge was successful, false otherwise.
     */
    public boolean mergeHackers(String hacker1, String hacker2) {
        Hacker one = search(hacker1);
        Hacker two = search(hacker2);

        ArrayList<Incident> hackerOneIncidents = one.getIncidents();
        ArrayList<Incident> hackerTwoIncidents = two.getIncidents();

        if (one == null || two == null){
            return false;
        }
        else{
            if (one.numIncidents() < two.numIncidents()){
                for (int i = 0; i < hackerOneIncidents.size(); i++){
                    two.addIncident(hackerOneIncidents.get(i));
                }
                two.addAlias(hacker1);
                remove(hacker1);
                return true;
            }
            else{
                if (one.numIncidents() > two.numIncidents()){
                    for (int j = 0; j < hackerTwoIncidents.size(); j++){
                        one.addIncident(hackerTwoIncidents.get(j));
                    }
                    one.addAlias(hacker2);
                    remove(hacker2);
                    return true;
                }
                else{
                    if (one.numIncidents() == two.numIncidents()){
                        for (int k = 0; k < hackerTwoIncidents.size(); k++){
                            one.addIncident(hackerTwoIncidents.get(k));
                        }
                        one.addAlias(hacker2);
                        remove(hacker2);
                        return true;
                    }
                }
            }
        }
        
        return false;

    }

    /**
     * Gets the top n most wanted Hackers from the directory, and
     * returns them in an arraylist.
     * 
     * You should use the provided MaxPQ class to do this. You can
     * add all hackers, then delMax() n times, to get the top n hackers.
     * 
     * @param n
     * @return Arraylist containing top n hackers
     */
    public ArrayList<Hacker> getNMostWanted(int n) {
        MaxPQ<Hacker> mostWantedPQ = new MaxPQ<>();
        for (int i = 0; i < hackerDirectory.length; i++){
            HNode pointer = hackerDirectory[i];
            while (pointer != null){
                mostWantedPQ.insert(pointer.getHacker());
                pointer = pointer.getNext();
            } 
        }
        ArrayList<Hacker> mostWantedArrayList = new ArrayList<>(n);

        int j = 0;
        while (j < n){
            Hacker mostWantedHacker = mostWantedPQ.delMax();
            mostWantedArrayList.add(j, mostWantedHacker);
            j++;
        }

        return mostWantedArrayList;
    }

    /**
     * Gets all hackers that have been involved in incidents at the given location.
     * 
     * You should check all hackers, and ALL of each hackers incidents.
     * You should not add a single hacker more than once.
     * 
     * @param location
     * @return Arraylist containing all hackers who have been involved in incidents
     *         at the given location.
     */
    public ArrayList<Hacker> getHackersByLocation(String location) {
        ArrayList<Hacker> hackersByLocation = new ArrayList<>();


        for (int i = 0; i < hackerDirectory.length; i++){
            HNode pointer = hackerDirectory[i];
            while (pointer != null){
                ArrayList<Incident> hackerIncidents = pointer.getHacker().getIncidents();
                for (int j = 0; j < hackerIncidents.size(); j++){
                    if (hackerIncidents.get(j).getLocation().equals(location)){
                        hackersByLocation.add(pointer.getHacker());
                        break;
                    }
                }
                pointer = pointer.getNext();
            } 
        }

        return hackersByLocation;
    }

    /**
     * PROVIDED--DO NOT MODIFY!
     * Outputs the entire hacker directory to the terminal.
     */
    public void printHackerDirectory() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.hackerDirectory.length; i++) {
            HNode headHackerNode = hackerDirectory[i];
            while (headHackerNode != null) {
                if (headHackerNode.getHacker() != null) {
                    sb.append(headHackerNode.getHacker().toString()).append("\n");
                    ArrayList<Incident> incidents = headHackerNode.getHacker().getIncidents();
                    for (Incident incident : incidents) {
                        sb.append("\t" + incident.toString()).append("\n");
                    }
                }
                headHackerNode = headHackerNode.getNext();
            }
        }
        return sb.toString();
    }

    public HNode[] getHackerDirectory() {
        return hackerDirectory;
    }
}
