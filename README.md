# ShortestPathAlgo

## Table of content
1. [Dijkstra's Algorithm](https://github.com/Sealpillow/ShortestPathAlgo#dijkstras-algorithm)
    - [Understanding](https://github.com/Sealpillow/ShortestPathAlgo#understanding)
    - [Code](https://github.com/Sealpillow/ShortestPathAlgo#code)
2. [Prim's Algorithm](https://github.com/Sealpillow/ShortestPathAlgo#prims-algorithm)
    - [Understanding](https://github.com/Sealpillow/ShortestPathAlgo#understanding-1)
    - [Code](https://github.com/Sealpillow/ShortestPathAlgo#code-1)
3. [Kruskal's Algorithm](https://github.com/Sealpillow/ShortestPathAlgo#kruskals-algorithm)
    - [Understanding](https://github.com/Sealpillow/ShortestPathAlgo#understanding-2)
    - [Code](https://github.com/Sealpillow/ShortestPathAlgo#code-2)
## Dijkstra's Algorithm
### Understanding

Dijkstra's algorithm allows us to find the shortest path between any two vertices of a graph.

<p float="left">
    <img src="https://user-images.githubusercontent.com/51332449/190891958-7fe2c03c-ed9f-4c45-8185-727aa7453594.png" width="500"> 
    <img src="https://user-images.githubusercontent.com/51332449/190891978-a3c1e604-de8a-4e9c-995b-ba4aa1db3edf.png" width="500"> 
</p>  
<p float="left">
    <img src="https://user-images.githubusercontent.com/51332449/190891986-bd201849-50d7-4be9-9c43-d77bed9e54e5.png" width="500"> 
    <img src="https://user-images.githubusercontent.com/51332449/190892274-124f8526-7c1c-401f-94fe-00d46c65b7cb.png" width="500"> 
</p>
<p float="left">
    <img src="https://user-images.githubusercontent.com/51332449/190892018-c7b221e9-8a39-45b9-b50f-869e4e784fa9.png" width="500"> 
    <img src="https://user-images.githubusercontent.com/51332449/190892032-3480d7aa-275b-464e-ac00-3b37f093f63c.png" width="500"> 
</p>
<p float="left">
    <img src="https://user-images.githubusercontent.com/51332449/190892040-cdbdc824-607f-4575-969c-62690f070662.png" width="500">
    <img src="https://user-images.githubusercontent.com/51332449/190892053-ce29f31a-40b4-417c-9693-81a6374ea13f.png" width="500"> 
</p>

### Array value showcase
```
// next shorted path->find the lowest d[i] that has not been visited
int u = findMinDistance(distance, visitedVertex); 

// node that has not been visited, and is connected to curr, and (currToStart + currToNext < nextToStart)
if (!visitedVertex[v] && graph[u][v] != 0 && (distance[u] + graph[u][v] < distance[v]))
    distance[v] = distance[u] + graph[u][v]; // update shorter path
```
<img src="https://user-images.githubusercontent.com/51332449/190895488-3a63dc18-3175-44ad-8238-069809439697.png" width="500">
<img src="https://user-images.githubusercontent.com/51332449/190895531-194553fc-eaca-44a5-8ed5-6a18c5624ecb.png" width="500"> 

<img src="https://user-images.githubusercontent.com/51332449/190895544-c34e0084-ddd8-4622-83fd-3c6b39491cbd.png" width="500">
<img src="https://user-images.githubusercontent.com/51332449/190895569-ed55e150-d07e-4196-9886-7769f351df60.png" width="500"> 

<img src="https://user-images.githubusercontent.com/51332449/190895587-778725d9-6f28-4a0a-8332-dff106c7be70.png" width="500">
<img src="https://user-images.githubusercontent.com/51332449/190895615-69d9d0dc-d8fe-4915-8a06-94f55367cdb2.png" width="500"> 

### Code
```
public class PracticeDijkstra
{
    public static void dijkstra(int[][] graph, int source)
    {
        int count = graph.length;
        boolean[] visitedVertex = new boolean[count];
        int[] distance = new int[count];
        for (int i = 0; i < count; i++)  // initialise array
        {
            visitedVertex[i] = false;    // set all not not visted 
            distance[i] = Integer.MAX_VALUE;
        }
        // Distance of self loop is zero
        distance[source] = 0;
        for (int i = 0; i < count; i++)
        {
            // Update the distance between neighbouring vertex and source vertex
            int u = findMinDistance(distance, visitedVertex);
            visitedVertex[u] = true;

            // Update all the neighbouring vertex distances
            for (int v = 0; v < count; v++)
            {
                // node that has not been visited, and is connected to curr, and (currToStart + currToNext < nextToStart)
                if (!visitedVertex[v] && graph[u][v] != 0 && (distance[u] + graph[u][v] < distance[v]))
                {
                    distance[v] = distance[u] + graph[u][v]; // update shorter path
                }
            }
        }
        for (int i = 0; i < distance.length; i++) {
            System.out.printf("Distance from %s to %s is %s%n", source, i, distance[i]);
        }

    }
    // Finding the minimum distance from graph row
    private static int findMinDistance(int[] distance, boolean[] visitedVertex)
    {
        int minDistance = Integer.MAX_VALUE;
        int minDistanceVertex = -1;
        for (int i = 0; i < distance.length; i++)
        {
            if (!visitedVertex[i] && distance[i] < minDistance)
            {
                minDistance = distance[i];
                minDistanceVertex = i;
            }
        }
        return minDistanceVertex;
    }

    public static void main(String[] args)
    {
        int graph[][] = new int[][]
                {{ 0, 4, 2, 6, 8},
                { 0, 0, 0, 4, 3},
                { 0, 0, 0, 1, 0},
                { 0, 1, 0, 0, 3},
                { 0, 0, 0, 0, 0}};
        dijkstra(graph, 0);

    }

}

```


## Prim's Algorithm
### Understanding

Prim's algorithm is a minimum spanning tree algorithm that takes a graph as input and finds the subset of the edges of that graph which

- form a tree that includes every vertex
- has the minimum sum of weights among all the trees that can be formed from the graph
```
Overview:
1. start from source, initialise selected[source] = true
2. find destination with connection with the source
3. find the lowest weighted edge from the source to the destination
4. if found -> min = g[v][e] -> selected[e] = true -> edge++
5. iterate through vertex that has selected[] = true
6. find the next lowest weighted edge from the vertex that has selected[] = true
7. if found -> min = g[v][e] -> selected[e] = true -> edge++
8. continue till edge = V-1;
```


<img src="https://user-images.githubusercontent.com/51332449/194746280-e26ddadf-70b9-4c3c-81ff-a91928895bf9.png" width="500"> 
<img src="https://user-images.githubusercontent.com/51332449/194746315-4d81232a-cc17-4145-a812-4e4f651ead96.png" width="500"> 
<img src="https://user-images.githubusercontent.com/51332449/194746335-43dcd1ec-c291-44c0-a04f-9c14b14ebaff.png" width="500"> 
<img src="https://user-images.githubusercontent.com/51332449/194746354-8642817f-008a-4bb2-a2c8-21259fc1d5ea.png" width="500"> 
<img src="https://user-images.githubusercontent.com/51332449/194746388-cb2b544e-1135-4359-af8b-68b09a0db374.png" width="500"> 
<img src="https://user-images.githubusercontent.com/51332449/194746399-3699d236-1ca3-4a8b-bccc-215e493ffc17.png" width="500"> 

### Code
```
import java.util.Arrays;
public class PracticePrim
{
    public static void Prim(int[][] G, int vertex)
    {

        int INF = Integer.MAX_VALUE;

        int edge; // number of edge

        // create an array to track selected vertex
        // selected will become true otherwise false
        boolean[] selected = new boolean[vertex];

        // set selected false initially
        Arrays.fill(selected, false);

        // set number of edge to 0
        edge = 0;

        // the number of edge in minimum spanning tree will be
        // always less than (V -1), where V is number of vertices in
        // graph

        // choose 0th vertex and make it true
        selected[0] = true;

        // print for edge and weight
        System.out.println("Edge : Weight");

        while (edge < vertex - 1)
        {
            // For every vertex in the set S, find the all adjacent vertices,
            // calculate the distance from the vertex selected at step 1.
            // if the vertex is already in the set S, discard it otherwise
            // choose another vertex nearest to selected vertex at step 1.

            int min = INF;
            int s = 0; // row number
            int d = 0; // col number

            for (int source = 0; source < vertex; source++)
            {
                if (selected[source])
                {
                    for (int destination = 0; destination < vertex; destination++)
                    {
                        // not in selected and there is an edge
                        if (!selected[destination] && G[source][destination] != 0)
                        {
                            //System.out.println(i+" "+j+" " +G[i][j]);
                            if (min > G[source][destination])
                            {
                                min = G[source][destination];
                                s = source;
                                d = destination;
                            }
                        }
                    }
                }
            }
            System.out.println(s + " - " + d + " :  " + G[s][d]);
            selected[d] = true;
            edge++;
        }
    }

    public static void main(String[] args)
    {

        // number of vertices in graph
        int vertex = 5;

        // create a 2d array of size 5x5
        // for adjacency matrix to represent graph
        int[][] G = { { 0, 9, 75, 0, 0 },
                      { 9, 0, 95, 19, 42 },
                      { 75, 95, 0, 51, 66 },
                      { 0, 19, 51, 0, 31 },
                      { 0, 42, 66, 31, 0 } };

        Prim(G, vertex);
    }

}

```
## Kruskal's Algorithm
### Understanding

We start from the edges with the lowest weight and keep adding edges until we reach our goal.
The steps for implementing Kruskal's algorithm are as follows:

- Sort all the edges from low weight to high
- Take the edge with the lowest weight and add it to the spanning tree. If adding the edge created a cycle, then reject this edge.
- Keep adding edges until we reach all vertices.
```
Overview

```

<img src="https://user-images.githubusercontent.com/51332449/194752160-eb3632cd-c761-4f2a-b459-0fad5ec47799.png" width="500"> 
<img src="https://user-images.githubusercontent.com/51332449/194752189-f7133be4-ec12-4552-91a1-daefc9416418.png" width="500"> 
<img src="https://user-images.githubusercontent.com/51332449/194752195-94343a74-244e-4de1-829f-9a58e06228c2.png" width="500"> 
<img src="https://user-images.githubusercontent.com/51332449/194752215-6247df66-b7ee-44c7-ace3-aa892aa798ba.png" width="500"> 
<img src="https://user-images.githubusercontent.com/51332449/194752222-88718739-921f-4590-b00a-a2028de98b55.png" width="500"> 
<img src="https://user-images.githubusercontent.com/51332449/194752231-2cab979f-aaec-46f0-b92e-104ecb61cd95.png" width="500"> 


### Code
```
package ShortestPath;// Kruskal's algorithm in Java

import java.util.*;
class PracticeKruskal {
    static class Edge implements Comparable<Edge>
    {
        int src, dest, weight;
        public int compareTo(Edge compareEdge)
        {
            return this.weight - compareEdge.weight;
        }
    };

    // Union
    static class subset
    {
        int parent, rank;
    };

    int vertices, edges;
    Edge[] edge;

    // graph creation
    PracticeKruskal(int v, int e)
    {
        vertices = v;
        edges = e;
        edge = new Edge[edges];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    int find(subset[] subsets, int i)
    {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    void Union(subset[] subsets, int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else
        {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    // Applying Krushkal Algorithm
    void KruskalAlgo()
    {
        Edge[] result = new Edge[vertices];
        int e = 0;
        int i = 0;
        for (i = 0; i < vertices; ++i)
            result[i] = new Edge();

        // Sorting the edges
        Arrays.sort(edge);
        subset[] subsets = new subset[vertices];
        for (i = 0; i < vertices; ++i)
            subsets[i] = new subset();

        for (int v = 0; v < vertices; ++v)
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }
        i = 0;
        while (e < vertices - 1)
        {
            Edge next_edge = new Edge();
            next_edge = edge[i++];
            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);
            if (x != y)
            {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
        }
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src + " - " + result[i].dest + ": " + result[i].weight);
    }

    public static void main(String[] args)
    {
        int vertices = 6; // Number of vertices
        int edges = 8; // Number of edges
        PracticeKruskal G = new PracticeKruskal(vertices, edges);

        G.edge[0].src = 0;
        G.edge[0].dest = 1;
        G.edge[0].weight = 4;

        G.edge[1].src = 0;
        G.edge[1].dest = 2;
        G.edge[1].weight = 4;

        G.edge[2].src = 1;
        G.edge[2].dest = 2;
        G.edge[2].weight = 2;

        G.edge[3].src = 2;
        G.edge[3].dest = 3;
        G.edge[3].weight = 3;

        G.edge[4].src = 2;
        G.edge[4].dest = 5;
        G.edge[4].weight = 2;

        G.edge[5].src = 2;
        G.edge[5].dest = 4;
        G.edge[5].weight = 4;

        G.edge[6].src = 3;
        G.edge[6].dest = 4;
        G.edge[6].weight = 3;

        G.edge[7].src = 5;
        G.edge[7].dest = 4;
        G.edge[7].weight = 3;
        G.KruskalAlgo();
    }
}
```
