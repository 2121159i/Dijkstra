import java.io.*;
import java.util.*;

/**
 program to find word ladder with shortest distance for two words in a dictionary
 distance between elements of the word ladder is the absolute difference in the
 positions of the alphabet of the non-matching letter
 */
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();

		String inputFileName = args[0]; // dictionary
		String startWord = args[1]; // first word
		String endWord = args[2]; // second word
  
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		
		// read in the data 
		ArrayList<String> words = new ArrayList<>();
		
		while (in.hasNext())
			words.add(in.nextLine());
		
        // create graph here
		Graph G = new Graph(words.size());
		int[] startEndWordPosition = populateGraph(G, words, startWord, endWord); //populate the graph & return the indexes of start & end word
		
		int startingWordPosition = startEndWordPosition[0];
		int endingWordPosition = startEndWordPosition[1];
		
		in.close();
        reader.close();
      
        // use Dijkstra's algorithm to find smallest path weight    
        Vertex result = G.findSmallestWeightPath(startWord, endWord, startingWordPosition, endingWordPosition);
        printShortestPath(G, result, startWord);
        
        // end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}
	
	
	//populate the graph with words from the List and return the indexes of the start and end word
	private static int[] populateGraph(Graph G, ArrayList<String> words, String startWord, String endWord) {
			String wordOne, wordTwo;
			int startingWordPosition = 0, endingWordPosition = 0;
			int[] positions = new int[2];
			for (int i=0; i < words.size(); i++){
				wordOne = words.get(i);
				G.getVertex(i).setWord(wordOne); //place all words in the already created vertices
				
				//getting the indexes of the start & end word to use in bfs
				if (wordOne.equals(startWord)){
					startingWordPosition = i;
					G.getVertex(i).setWeight(0); //set weight of start word to 0 (used when finding shortest path)
				}
				if (wordOne.equals(endWord))
					endingWordPosition = i;
				
				for (int j=0; j < words.size(); j++){
					wordTwo = words.get(j);
					if (OneLetterDifference(wordOne, wordTwo)){
						G.getVertex(i).addToAdjList(j);
						G.getVertex(j).addToAdjList(i);
					}
				}
			}
			positions[0] = startingWordPosition;
			positions[1] = endingWordPosition;
			return positions;
		}
	
	private static boolean OneLetterDifference(String wordOne, String wordTwo){
    	int letterDifference = 0;
    	for (int i=0; i<wordOne.length(); i++){
    		if (wordOne.charAt(i) != wordTwo.charAt(i))
    			letterDifference++;
    	}
    	if (letterDifference == 1)
    		return true;
    	return false;
    }
	
	private static void printShortestPath(Graph G,Vertex endVertex, String startWord){
		List<String> results = createResultList(G, endVertex, startWord);
//		Collections.reverse(results);
		
		printFormattedOutput(results,endVertex);
			
	}
	
	private static List<String> createResultList(Graph G, Vertex result, String startWord){
		List<String> resultList = new ArrayList<>();
		Vertex stepperVertex = result;
		if (result != null){	
			while (!stepperVertex.getWord().equals(startWord)){
				resultList.add(stepperVertex.getWord());
				stepperVertex = G.getVertex(stepperVertex.getPredecessor());
			}
			resultList.add(startWord);
			Collections.reverse(resultList);
			return resultList;
		}
		return resultList;
	}
	
	private static void printFormattedOutput(List<String> words, Vertex endVertex){
		if (!words.isEmpty()){
			System.out.println("Minimum distance between the two words is: " + endVertex.getWeight());	
			System.out.println("Length of the shortest path: " + (words.size()-1));
		
			for(String s: words){	
				System.out.print(s);	
				if (!s.equals(endVertex.getWord()))
					System.out.print(" -> ");
			}
		}
		else
			System.out.println("No ladder is possible.");
	}

}
