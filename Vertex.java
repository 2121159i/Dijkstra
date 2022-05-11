import java.util.LinkedList;//aride


/**
 class to represent a  vertex in a graph
*/
public class Vertex implements Comparable<Vertex>{
   
    private LinkedList<AdjListNode> adjList ; // the adjacency list 
    private int index; // the index of this vertex in the graph
    
    private String word; //same as wordladder
    
    private int weight; //weight of the Vertex for the graph
    
    //possibly other fields, for example representing data
    // stored at the node, whether the vertex has been visited
    // in a traversal, its predecessor in such a traversal, etc.
    
	boolean visited; // whether vertex has been visited in a traversal
    int predecessor; // index of predecessor vertex in a traversal

     
    /**
	 creates a new instance of Vertex
	 */
    public Vertex(int n, String s) {
    	adjList = new LinkedList<AdjListNode>();
    	index = n;
    	
    	visited = false;
    	
    	word = s;
    	weight = Integer.MAX_VALUE;
    	predecessor = 0; //added setting the predecessor to 0 to the constructor of Vertex
    	
    }
    
    /**
	 copy constructor
	*/
    public Vertex(Vertex v){
    	adjList = v.getAdjList();
    	index = v.getIndex();
    	visited = v.getVisited();
    }
     
    
    public LinkedList<AdjListNode> getAdjList(){
        return adjList;
    }
    
    public int getIndex(){
    	return index;
    }
    
    public void setIndex(int n){
    	index = n;
    }
    
    public boolean getVisited(){
    	return visited;
    }
    
    public void setVisited(boolean b){
    	visited = b;
    }
    
    public int getPredecessor(){
    	return predecessor;
    }
    
    public void setPredecessor(int n){
    	predecessor = n;
    }
    
    public void addToAdjList(int n){
        adjList.addLast(new AdjListNode(n));
    }
    
    public int vertexDegree(){
        return adjList.size();
    }
    
  //adding getters and setters for the words which the vertices will contain
    public String getWord(){
    	return word; 
    }
    
    public void setWord(String s){
    	word = s; 
    }
    
    public int getWeight(){
    	return weight; 
    }
    
    public void setWeight(int n){
    	weight = n; 
    }
    
    public int getDistance(Vertex v){
    	for(int i=0;i<this.getWord().length();i++){
    		if (this.getWord().charAt(i)!=v.getWord().charAt(i))
    			return Math.abs(this.getWord().charAt(i) - v.getWord().charAt(i));
    	}
    	return 0;
    }

	@Override
	public int compareTo(Vertex o) {
		if (this.getWeight() == o.getWeight())
			return 0;
		if(this.getWeight() < o.getWeight())
			return -1;
		return 1;
	}
    
}
