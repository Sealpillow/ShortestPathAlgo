import sys


def PracticeDijkstra(g, source):
    count = len(g)
    visited = list(False for i in range(count))
    distance = list(sys.maxsize for i in range(count))
    distance[source] = 0
    for i in range(0, count):
        index = minDistance(distance, visited)
        visited[index] = True
        for j in range(0, count):
            if not visited[j] and g[index][j] != 0 and (distance[index]+g[index][j] < distance[j]):
                distance[j] = distance[index]+g[index][j]
    print(distance)


def minDistance(distance, visited):
    min = sys.maxsize
    minindex = -1
    for x in range(len(distance)):
        if not visited[x] and (distance[x] <=min):
            min = distance[x]
            minindex = x
    return minindex


g = [[0, 4, 2, 6, 8],
     [0, 0, 0, 4, 3],
     [0, 0, 0, 1, 0],
     [0, 1, 0, 0, 3],
     [0, 0, 0, 0, 0]]
PracticeDijkstra(g, 0)


