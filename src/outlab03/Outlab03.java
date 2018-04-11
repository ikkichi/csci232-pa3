package outlab03;

import java.util.Scanner;
import java.util.*;
import java.lang.*;

    /*
    Authors: Mariell Korringa, Elizabeth Hamaoka, Rachel Angelsberg
    Date: 10 April 2018
    Overview: This program implements three different algorithms to find the MST 
        of a graph.
    Notes: 
        - algorithm programmers:
            - Kruskal's -> Marielle
            - Prim's -> Lizzy
            - Floyd-Warshall -> Rachel
        - needs to use user input as we couldn't get the file input to work 
            properly
    */

public class Outlab03 {
    static Scanner scan;
    
    public static void floydWarshall() {
            /*
            steps: 
            1. get number of vertices -- make new array for table; set k = # vertices
            2. store weighted graph in 2d array; 0 = inf, - = [1][1], [2][2], etc.
            3. make 2 arrays: da for k-1 values, db for dk values
            4. loop through and fill d1/ to dk values in table
            5. print each completed table set for the current k value
            6. print the final version of the dk table, clearly labeled
            */    
            
            int k, j, i;            // k = # of vertices, i/j = row/column for table        
            int matrix[][];          // 2d array for matrix values in table

            // get # of vertices for k value
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter the number of vertices");
            k = scan.nextInt();

            // set up the # of rows and columns from k value
            i = k + 1;
            j = k + 1;

            // set up the 2d array to hold the initial graph values
            matrix = new int[i][j];

            // get and store initial graph values in array
            System.out.println("Enter the weighted matrix for the graph:");
            for (int s = 1; s <= k; s++) {
                for (int d = 1; d <= k; d++) {
                    // store each new int in the 2d array for each row
                    matrix[s][d] = scan.nextInt();

                    if (s == d) {
                        matrix[s][d] = 0;
                        continue;
                    }
                }
            }
            
            System.out.println("\n");
            
            // print out the initial matrix
            System.out.println("Initital graph matrix (k = 0): ");
            printFM(matrix, k);
            System.out.println("\n");
        
            /*
            psuedocode:

            Let d be a |V| by |V| array of minimum weights initialized to infinity
            BEGIN
                for each vertex v
                    d[v][v] ← 0
                        for each edge (u,v)
                            d[u][v] ← w(u,v)
                                for k from 1 to |V|
                                    for i from 1 to |V|
                                        for j from 1 to |V|
                                            if d[i][j] > d[i][k] + d[k][j]
                                                d[i][j] ← d[i][k] + d[k][j]
                                            end if
            END
            
            matrices:
                real table:
                0 6 0 4 0 0
                6 0 10 7 7 0
                0 10 0 8 5 6
                4 7 8 0 12 0
                0 7 5 12 0 7
                0 0 6 0 7 0

                ex table:
                0 50 0 80 0
                0 0 60 90 0
                0 0 0 0 40
                0 0 20 0 70
                0 50 0 0 0
            */

            // print out each subsequent matrix table from n = 1 to n = k
            for (int n = 1; n <= k; n++) {
                System.out.println("Updated weighted graph for k = " + n);
                // adjust the matrice table in temp matrix
                    for (int r = 1; r <= k; r++) {
                        // row loop
                        for (int c = 1; c <= k; c++) {
                            // col loop

                            // check to see which value is smaller: current [r][c] cell or
                            // the value sum for [r][k] + [k][c]
                            if (matrix[r][c] > ( matrix[r][n] + matrix[n][c] )) {
                                // current [r][c] cell is bigger, so assign smaller value
                                matrix[r][c] = matrix[r][n] + matrix[n][c];
                            }
                            /*else {
                                // current [r][c] cell is smaller, so assign that value
                                graph[r][c] = matrix[r][c];
                            }*/
                        }
                    }
                printFM(matrix, k);
                System.out.println();
            } 
            
            System.out.println("Final matrix for graph (k = " + k + "):");
            printFM(matrix, k);
            System.out.println();
            
            scan.close(); 
        
    }
    
    public static void printFM(int matrix[][], int k) {
            for (int a = 1; a <= k; a++) {
                System.out.print("\t"+ a);
            }
            System.out.println("\n");
            for (int r = 1; r <= k; r++) {
                // row loop
                System.out.print(r + "\t");
                
                for (int c = 1; c <= k; c++) {
                    // col loop
                    System.out.print(matrix[r][c] + "\t");
                }
                System.out.println();
            }
    }
    
     //Kruskal’s Algorithm to find the minimum spanning tree for a weighted graph
	 public static void KruskalMST(Graph G){
	        Edge T[] = new Edge[G.V];  //Contain edges of MST
	        for (int x=0; x<G.V; ++x){
	            T[x] = new Edge();
	        }
	        Arrays.sort(G.edges);
	        Cluster cluster[] = new Cluster[G.V];
	        for(int x=0; x<G.V; ++x){
	            cluster[x]=new Cluster();
	        }
	        //Define an elementary cluster C(v)={v} 
	        for (char v = 0; v < G.V; ++v){
	            cluster[v].parent = v;
	            cluster[v].height = 0;
	        }
	        int x = 0;
	        int edge = 0; 
	        while (edge < G.V - 1){
	            Edge next_edge = new Edge();
	            next_edge = G.edges[x++];
	            int y = Cluster.find(cluster, next_edge.u); //C(u)
	            int z = Cluster.find(cluster, next_edge.v); //C(v)
	            if (y != z){ //if C(u) != C(v)
	            	//add edge to Tree
	                T[edge++] = next_edge;
	                //Merge C(u) and C(v) into one cluster
	                Cluster.Merge(cluster, y, z);
	            }
	        }
	        //print out the results 
	        System.out.println("Kruskal Algorithm: ");
	        for (x = 0; x < edge; ++x){
	            System.out.print(T[x].u+""+T[x].v+" ");
	        }
	        System.out.println();
		}
         
         
        
        public static void main(String args[]) {  
            // CODE FOR KRUSKAL ALGORITHM
		/*
		help from:
			Barnwal , Aashish. “Greedy Algorithms | Set 2 (Kruskal's Minimum Spanning Tree Algorithm).”
			GeeksforGeeks, GreeksforGeeks, 17 Oct. 2017, www.geeksforgeeks.org/greedy-algorithms-set-2-kruskals-minimum-spanning-tree-mst/. 
		*/
            int V = 6;  // Number of vertices in graph
	     int E = 8;  // Number of edges in graph
	     Graph graph = new Graph(V, E);
	 
	        // add edge 01
	        graph.edges[0].u = 0;
	        graph.edges[0].v = 1;
	        graph.edges[0].weight = 5;
	 
	        // add edge 03
	        graph.edges[1].u = 0;
	        graph.edges[1].v = 3;
	        graph.edges[1].weight = 2;
	 
	        // add edge 04
	        graph.edges[2].u = 0;
	        graph.edges[2].v = 4;
	        graph.edges[2].weight = 7;
	 
	        // add edge 12
	        graph.edges[3].u = 1;
	        graph.edges[3].v = 2;
	        graph.edges[3].weight = 6;
	 
	        // add edge 24
	        graph.edges[4].u = 2;
	        graph.edges[4].v = 4;
	        graph.edges[4].weight = 8;
	        
	        //add edge 25
	        graph.edges[5].u = 2;
	        graph.edges[5].v = 5;
	        graph.edges[5].weight = 3;
	        
	        //add edge 34
	        graph.edges[6].u = 3;
	        graph.edges[6].v = 4;
	        graph.edges[6].weight = 4;
	        
	        //add edge 45
	        graph.edges[7].u = 4;
	        graph.edges[7].v = 5;
	        graph.edges[7].weight = 5;
	        
		KruskalMST(graph);
                
            // CODE FOR PRIM'S ALGORITHM
            scan = new Scanner(System.in);

            int [][] matrix = new int [6][6];
            int [] visited = new int[6];
            int min;
            int u = 0;
            int v = 0;
            int total = 0;

            for (int i = 0; i < 6; i++) {
                visited[i] = 0;

                for (int j = 0; j < 6; j++) {
                    matrix[i][j] = scan.nextInt();

                    if (matrix[i][j] == 0) {
                        matrix[i][j] = 999;
                    }
                }
            }
            visited[0] = 1;

            for(int counter = 0; counter < 5; counter++)
            {
                min = 999;

                for(int i = 0; i < 6; i++)
                {
                    if (visited[i] == 1)
                    {
                        for (int j = 0; j < 6; j++)
                        {
                            if (visited[j] != 1)
                            {
                                if(min > matrix[i][j])
                                {
                                    min = matrix[i][j];
                                    u = i;
                                    v = j;
                                }
                            }
                        }
                    }
                }
                visited[v] = 1;
                total += min;
                System.out.println("Edge found: " + u + "->" + v);
            }
            System.out.println("the weight of the mst is: " + total);
            
            // METHOD THAT CALLS THE FLOYD-WARHSALL ALGORITHM
            floydWarshall();
        }
}



    class Edge implements Comparable<Edge> { //queue for edges 
        int u; //start point
        int v; //end point
        int weight; //weight of edge
        public int compareTo(Edge compareEdge) {
            return this.weight-compareEdge.weight;
        }
    }

    class Cluster{ 
        int parent;
        int height;

            //get cluster
            public static int find(Cluster clusters[], int x){
                if (clusters[x].parent != x){
                    clusters[x].parent = find(clusters, clusters[x].parent);
                }
                return clusters[x].parent;
            }

            //Merge two clusters together
             public static void Merge(Cluster cluster[], int x, int y){
                int u = find(cluster, x); //C(u)
                int v = find(cluster, y); //C(v)

                if (cluster[u].height > cluster[v].height){ //if C(u) > C(v)
                    cluster[v].parent = u;
                }
                else if (cluster[u].height < cluster[v].height){ //if C(u) < C(v)
                    cluster[u].parent = v;
                }
                else{ //if C(u) = C(v)
                    cluster[v].parent = u;
                    cluster[u].height++;
                }
            }
        }

        class Graph{
                static int V; //number of vertices
                int E; //number of edges 
                static Edge edges[]; 

            public Graph(int n, int m) {
                V=n; //number of vertices 
                E=m; //number of edges
                edges = new Edge[E]; 
                for(int x = 0; x < E; ++x){
                        edges[x] = new Edge(); 
                }
            }
            //get value of V
                public int V(){
                        return V;
                }
                //get value of E
                public int E(){
                        return E;
                }
        }
