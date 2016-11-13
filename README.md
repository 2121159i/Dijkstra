# Dijkstra
Implemented Dijkstra's Algorithm with running time O(n log n) implemented in Java.

My implementation uses a priority queue which initially contains only the starting vertex and the only elements that get
added to the queue are the relevant vertices which actually get traversed. Similarly to the backtracking algorithm, I use predecessors to get the shortest path & print it. I have tried to keep the program simple and without any additional overcomplications in order to avoid using memory or CPU speed.

Create a graph using the dictionary file words5.txt and then using that graph and Dijkstra's algorithm find the path with the lowest weight from the start to end word.
Print the path between the two words.

If there is no path between the two words, the appropriate message gets printed.

Use the dictionary file words5.txt to test the program.
