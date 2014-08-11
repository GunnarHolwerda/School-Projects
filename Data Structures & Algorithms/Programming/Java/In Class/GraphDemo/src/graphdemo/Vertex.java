package graphdemo;

/**
 *
 * @author Gunnar Holwerda
 */
public class Vertex {
    private String id;
    private boolean visited;
    private int index;
    private Vertex previous;
    
    public Vertex(String id, int index){
        this.id = id;
        this.index = index;
        previous = null;
    }
    
    @Override
    public String toString(){
        return id;
    }
    
    public boolean getVisited(){
        return visited;
    }
    
    public void setVisited(boolean value){
        visited = value;
    }
    
    public int getIndex(){
        return index;
    }
    
    public Vertex getPrevious(){
        return previous;
    }
    
    public void setPrevious(Vertex newPrev){
        previous = newPrev;
    }
    
}
