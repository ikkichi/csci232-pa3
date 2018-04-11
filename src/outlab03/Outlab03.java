/* * Author: Marielle Korringa
 * Date: 10 April 2018 
 * Overview: This program implement different algorithms to find the MST of a graph. 
 *  There are no special instructions for running this program. */

package outlab3;

import java.util.*;
import java.lang.*;
import java.io.*;

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
	//get cluster
	static int find(Cluster clusters[], int x){
	    if (clusters[x].parent != x){
	        clusters[x].parent = find(clusters, clusters[x].parent);
	    }
	    return clusters[x].parent;
	}
	
	//Merge two clusters together
	 static void Merge(Cluster cluster[], int x, int y){
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

public class Outlab03 {
	public static void main(String args[]){ 
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
	            int y = G.find(cluster, next_edge.u); //C(u)
	            int z = G.find(cluster, next_edge.v); //C(v)
	            if (y != z){ //if C(u) != C(v)
	            	//add edge to Tree
	                T[edge++] = next_edge;
	                //Merge C(u) and C(v) into one cluster
	                G.Merge(cluster, y, z);
	            }
	        }
	        //print out the results 
	        System.out.println("Kruskal Algorithm: ");
	        for (x = 0; x < edge; ++x){
	            System.out.print(T[x].u+""+T[x].v+" ");
	        }
	        System.out.println();
		}
}
