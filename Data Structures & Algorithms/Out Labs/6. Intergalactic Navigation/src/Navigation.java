
import java.io.FileReader;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

/**
 *
 * @author Josh and Gunnar
 */
public class Navigation extends JFrame {

    private final int NUM_PLANETS = 35;
    private final int NUM_ASTEROID_FIELDS = 10;
    private static ArrayList<Location> locations;
    private static ArrayList<Asteroid> asteroidFields;
    private Graph map;
    private ArrayList<Edge> edgeArray;
    private JButton enterButton;
    private JLabel startLabel, destLabel;
    private JTextField startTF, destTF;
    private JTextArea pathTextArea, planetsTextArea;
    DecimalFormat df= new DecimalFormat("0.00");

    /**
     * Constructor for class Navigation
     */
    public Navigation() throws FileNotFoundException {
        super("Intergalactic Navigation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 1));
        Container contentPane = getContentPane();

        edgeArray = new ArrayList();
        locations = new ArrayList();
        asteroidFields = new ArrayList();
        generateAsteroids();
        generateLocations();
        generateEdgeArray();
        map = AbstractGraph.createGraph(locations, false, "Matrix", edgeArray);

        JPanel buttonTextFieldPanel = new JPanel();
        buttonTextFieldPanel.setLayout(new GridLayout(3, 2));
        startLabel = new JLabel("Start Location:");
        destLabel = new JLabel("Destination:");
        startTF = new JTextField(20);
        destTF = new JTextField(20);
        buttonTextFieldPanel.add(startLabel);
        buttonTextFieldPanel.add(startTF);
        buttonTextFieldPanel.add(destLabel);
        buttonTextFieldPanel.add(destTF);
        enterButton = new JButton("Find Path");
        enterButton.addActionListener(new EnterPressed());
        buttonTextFieldPanel.add(enterButton);

        contentPane.add(buttonTextFieldPanel);
        
        JFrame pAndBFrame = new JFrame("List of Planets and Bases in the Galaxy");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        planetsTextArea = new JTextArea(10,50);
        planetsTextArea.setEditable(false);
        for(int i = 0; i < locations.size(); i++){
            planetsTextArea.append(i + ". " + locations.get(i).getName() + "\n");
        }
        pAndBFrame.add(planetsTextArea);
        pAndBFrame.setVisible(true);
        pAndBFrame.pack();

        setVisible(true);
        pack();

    }
    
    /**
     * Method to tell you whether a line between two points passes through a circle.
     * 
     */
    public boolean blockedByCircle(double x1, double y1,
            double x2, double y2,
            double x3, double y3, double r) {
        double t = 0.0;
        if (x1 != x2 || y1 != y2) {
            t = ((x2 - x1) * (x3 - x1) + (y2 - y1) * (y3 - y1)) / ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        }

        if (t > 1) {
            t = 1.0;
        }
        if (t < 0) {
            t = 0.0;
        }
        // find closest point (x,y)
        double x = x1 + t * (x2 - x1);
        double y = y1 + t * (y2 - y1);

        double distSq = (x - x3) * (x - x3) + (y - y3) * (y - y3);
        return (distSq <= r * r);


    }

    /**
     * Computes the bearing between two Locations
     */
    public double computeBearing(double x1, double y1,
            double x2, double y2) {
        double length = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        double angleRadians = Math.acos((y2 - y1) / length);     //gives the value of the angle in radians

        double angleDegrees = Math.toDegrees(angleRadians);
        if (x2 < x1) {
            return (360.0 - angleDegrees);
        } else {
            return angleDegrees;
        }
    }
    
    /**
     * Generates the asteroid fields 
     */
    private void generateAsteroids() {
        Random r = new Random();
        for (int i = 0; i < NUM_ASTEROID_FIELDS; i++) {
            boolean entered = false;
            while (!entered){
                double x = r.nextDouble() * 1000;
                double y = r.nextDouble() * 1000;
                if (validCoord(x, y)) {
                    asteroidFields.add(new Asteroid(x, y));
                    entered = true;
                }
            }
        }
    }

    /**
     * Method to tell if a line between to Locations is free from all asteroid fields
     * @param start         start location
     * @param dest          destination location
     * @return              if the line is free from all asteroid fields
     */
    private boolean freeOfAllAsteroids(Location start, Location dest) {
        int count = 0;
        
        double x1 = start.getXCoor();
        double x2 = dest.getXCoor();
        double y1 = start.getYCoor();
        double y2 = dest.getYCoor();
        
        for (int i = 0; i < asteroidFields.size(); i++) {
            if (!blockedByCircle(x1, y1, x2, y2, asteroidFields.get(i).getXCoor(), asteroidFields.get(i).getYCoor(), asteroidFields.get(i).getRadius())) {
                count++;
            }
        }
        
        return (count == asteroidFields.size());
    }

    /**
     * Method to generate 10 bases and 25 planets
     */
    private void generateLocations() throws FileNotFoundException {
        try {
            Scanner in = new Scanner(new FileReader("src\\Locations.txt"));
            while (in.hasNextLine()) {
                for (int i = 0; i < NUM_PLANETS; i++) {
                    boolean entered = false;
                    while (!entered) {
                        Random r = new Random();
                        double x = r.nextDouble() * 1000.0;
                        double y = r.nextDouble() * 1000.0;
                        if (validCoord(x, y)) {
                            String name = in.nextLine();
                            Location tempLocation = new Location(name, x, y);
                            locations.add(tempLocation);
                            entered = true;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
        }
    }
    
    /**
     * Generates the array of Edges that is used to import the edges into the graph
     */
    private void generateEdgeArray() {
        for (int i = 0; i < locations.size(); i++) {
            for (int j = i + 1; j < locations.size(); j++) {
                double difXSq = (locations.get(j).getXCoor() - locations.get(i).getXCoor()) * 
                                (locations.get(j).getXCoor() - locations.get(i).getXCoor());
                double difYSq = (locations.get(j).getYCoor() - locations.get(i).getYCoor()) * 
                                (locations.get(j).getYCoor() - locations.get(i).getYCoor());
                double weight = ((Math.random() * .5) + 4.5) * (Math.sqrt((difXSq + difYSq)));

                if (freeOfAllAsteroids(locations.get(i), locations.get(j))) {
                    edgeArray.add(new Edge(i, j, weight));
                }
            }
        }
    }
    
    /**
     * Returns whether or not the coordinates passed in intersect with an asteroid field or other locations
     */
    private boolean validCoord(double x, double y) {
        int count = 0;
        
//        Checks to see if the point is in any of the asteroid fields
        for (int i = 0; i < asteroidFields.size(); i++) {
            if (!blockedByCircle(x, y, x + 0.0001, y + 0.0001, asteroidFields.get(i).getXCoor(), asteroidFields.get(i).getYCoor(), asteroidFields.get(i).getRadius())) {
                count++;
            }
        }
        
//        Checks to see if the point is already another location
        for (int j = 0; j < locations.size(); j++) {
            if (x != locations.get(j).getXCoor() && y != locations.get(j).getYCoor()) {
                count++;
            }
        }
        
        return count == (asteroidFields.size() + locations.size());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Navigation frame = new Navigation();
    }

    private class EnterPressed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame pathFrame = new JFrame("Intergalactic Path");
            pathTextArea = new JTextArea(30, 50);
            pathTextArea.setEditable(false);
            pathTextArea.setText("");
            pathFrame.add(pathTextArea);
            pathFrame.pack();
            
            String start = startTF.getText();
            String dest = destTF.getText();
            startTF.setText("");
            destTF.setText("");
            int startVertex = -1;
            int destVertex = -1;
            
            for (int i = 0; i < locations.size(); i++){
                if (locations.get(i).getName().equals(start)){
                    startVertex = i;
                }
            }
            
            for (int i = 0; i < locations.size(); i++){
                if (locations.get(i).getName().equals(dest)){
                    destVertex = i;
                }
            }

            // Perform Dijkstra's algorithm:
            int numV = locations.size();
            int parent[] = new int[numV];
            double dist[] = new double[numV];
            DijkstrasAlgorithm.dijkstrasAlgorithm(map, startVertex, parent, dist);

            int destination = destVertex;

            // Construct the path.
            Deque<Integer> thePath = new ArrayDeque();
            int v = destination;
            while (parent[v] != -1) {
                thePath.push(new Integer(v));
                v = parent[v];
            }
            
            pathFrame.setVisible(true);
            int counter = 0;
            if (thePath.isEmpty()){
                pathTextArea.append("I am sorry but it is not safe to travel from " + start + " to " + dest + "...");
            }
            else{
                while(!thePath.isEmpty()){
                    if (counter == 0){
                        pathTextArea.append(counter + ". Starting at : " + start + "\n");
                        counter++;
                    }
                    else {
                        int vertex = thePath.pop();
                        double bearing = computeBearing(locations.get(edgeArray.get(vertex).getSource()).getXCoor(), locations.get(edgeArray.get(vertex).getSource()).getYCoor(), 
                                                        locations.get(edgeArray.get(vertex).getDest()).getXCoor(), locations.get(edgeArray.get(vertex).getDest()).getYCoor());
                        pathTextArea.append(counter + ". " + "Go to: " + locations.get(vertex).getName() + ", effective distance " + 
                                            df.format(edgeArray.get(vertex).getWeight()) + ", heading = " + df.format(bearing) + " degrees\n");
                        counter++;
                    }
                }
                pathTextArea.append(counter + ". Arrive at final destination, " + dest);
            }
        }
    }
}
