# ShortestPathAlgo

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
</p>
<p float="left">
    <img src="https://user-images.githubusercontent.com/51332449/190894377-87688c5c-1c72-4329-af74-8ef93a0c4ce5.png" width="500">
    <img src="https://user-images.githubusercontent.com/51332449/190894452-d21a9abf-64bb-42c8-9114-3527a374b95b.png" width="500"> 
</p>

<p float="left">
    <img src="https://user-images.githubusercontent.com/51332449/190894483-59be1941-5274-4968-b1ef-d0521bdbfdcf.png" width="500">
    <img src="https://user-images.githubusercontent.com/51332449/190894528-154162e1-f3ab-48b4-a021-f61d0e5f568f.png" width="500"> 
</p>

<p float="left">
    <img src="https://user-images.githubusercontent.com/51332449/190894626-e1bb7315-6808-429f-a9f2-b46e108057f8.png" width="500">
    <img src="https://user-images.githubusercontent.com/51332449/190894661-57f8c7d0-eb76-40cd-b8f9-8edf01b76899.png" width="500"> 
</p>



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
