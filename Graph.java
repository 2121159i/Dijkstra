import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 class to represent an undirected graph using adjacency lists
 */
public class Graph { //ne graph a grafika

	private Vertex[] vertices; // the (array of) vertices
	private int numVertices = 0; // number of vertices

	// possibly other fields representing properties of the graph

	/**
	 creates a new instance of Graph with n vertices
	*/
	public Graph(int n) {
		numVertices = n;
		vertices = new Vertex[n];
		for (int i = 0; i < n; i++)
			vertices[i] = new Vertex(i, "");
	}

	public int size() {
		return numVertices;
	}

	public Vertex getVertex(int i) {
		return vertices[i];
	}

	public void setVertex(int i) {
		vertices[i] = new Vertex(i, "");
	}

	/**
	 visit vertex v, with predecessor index p,
	 during a depth first traversal of the graph
	 */
	private void Visit(Vertex v, int p) {
		v.setVisited(true);
		v.setPredecessor(p);
		LinkedList<AdjListNode> L = v.getAdjList();
		for (AdjListNode node : L) {
			int n = node.getVertexNumber();
			if (!vertices[n].getVisited()) {
				Visit(vertices[n], v.getIndex());
			}
		}
	}

	/**
     carry out a depth first search/traversal of the graph
	 */
	public void dfs() {
		for (Vertex v : vertices)
			v.setVisited(false);
		for (Vertex v : vertices)
			if (!v.getVisited())
				Visit(v, -1);
	}

	/**
	 carry out a breadth first search/traversal of the graph
	 */
	public void bfs() {
		
		for (Vertex v : vertices) v.setVisited(false); // initialise (all vertices unvisted)
  		LinkedList<Vertex> queue = new LinkedList<Vertex>();
  		
		for (Vertex v : vertices) { // go through vertices of the graph
    		if (!v.getVisited()) { // if vertex not visited then visit the vertex
      			v.setVisited(true);
      			v.setPredecessor(v.getIndex()); // v was initial/starting vertex
      			queue.add(v); // add to queue for processing
      			while (!queue.isEmpty()) {
        			Vertex u = queue.remove(); // get next vertex to process
					LinkedList<AdjListNode> list = u.getAdjList(); // get adjacency list of the vertex
        			for (AdjListNode node : list) {
						Vertex w = vertices[node.getVertexNumber()];
						if (!w.getVisited()) { // if vertex has not been visited
							w.setVisited(true);
							w.setPredecessor(u.getIndex()); // w was found using u so this is the predecessor
							queue.add(w); // add to queue for processing
						}
					}
				}
			}
		}
	}
	
	//implements Dijkstra's algoritm
	public Vertex findSmallestWeightPath(String startWord, String endWord, int startingWordPosition, int endingWordPosition){	
		Queue<Vertex> visitedVertices = new PriorityQueue<>();
		Vertex stepperVertex = null;
		
		//add Vertex with start word 
		addStartVertexToQueue(startingWordPosition,visitedVertices);

		while (!visitedVertices.isEmpty()){
			
			stepperVertex = visitedVertices.poll(); //get Vertex with smallest weight
			stepperVertex.setVisited(true);
			
			if (stepperVertex.getWord().equals(endWord)){ //exit condition - if the end Vertex is found
				return stepperVertex;}
			
			for (AdjListNode node: stepperVertex.getAdjList())
				processAdjacentNode(node, stepperVertex, visitedVertices);
		}
		
		return null;
	}
	
	
	private void addStartVertexToQueue(int startingWordPosition, Queue<Vertex> visitedVertices) {
		// TODO Auto-generated method stub
		Vertex startingVertex = this.getVertex(startingWordPosition);
		visitedVertices.add(startingVertex);
		
	}

	private void processAdjacentNode(AdjListNode node, Vertex stepperVertex,Queue<Vertex> visitedVertices){
		Vertex adjacentVertex = vertices[node.getVertexNumber()];
		relaxVertex(stepperVertex,adjacentVertex, visitedVertices);       			
	}
	
	private void relaxVertex(Vertex stepperVertex, Vertex adjacentVertex,Queue<Vertex> visitedVertices){
		if(!adjacentVertex.getVisited()){	
			
			if (adjacentVertex.getWeight() == Integer.MAX_VALUE){
				visitedVertices.add(adjacentVertex);}	
			
			updateVertexWeight(stepperVertex, adjacentVertex);
			adjacentVertex.setPredecessor(stepperVertex.getIndex());
				
		}
	}
	
	private void updateVertexWeight(Vertex stepperVertex, Vertex adjacentVertex){
		int newDistance  = stepperVertex.getWeight() + adjacentVertex.getDistance(stepperVertex);
		if (newDistance < adjacentVertex.getWeight())
			adjacentVertex.setWeight(newDistance);
	}
	
}
