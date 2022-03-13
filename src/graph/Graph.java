package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph<T> {

    private static final int DEFAULT_CAPACITY = 10;
    public static final double NO_EDGE = Double.NEGATIVE_INFINITY;
    public static final int NOT_EULERIAN = 0, EULERIAN_PATH = 1, EULERIAN_CIRCUIT = 2;

    private Object[] vertices;
    private double[][] directedEdges, undirectedEdges;

    public Graph() {
        this(DEFAULT_CAPACITY);
    }

    public Graph(int capacity) {
        empty(capacity);
    }

    public Graph(Graph<T> original) {
        vertices = new Object[original.vertices.length];
        directedEdges = new double[vertices.length][vertices.length];
        undirectedEdges = new double[vertices.length][vertices.length];
        copy(original.vertices, original.directedEdges, original.undirectedEdges);
    }

    public boolean add(T vertex) {
        if(vertex == null)
            return false;
        int emptySpot = -1;
        for(int i = 0; i < vertices.length; i++) {
            if(vertex.equals(vertices[i]))
                return false;
            if(emptySpot == -1 && vertices[i] == null)
                emptySpot = i;
        }
        if(emptySpot == -1) {
            emptySpot = vertices.length;
            grow();
        }
        vertices[emptySpot] = vertex;
        return true;
    }

    public boolean remove(T vertex) {
        int i = find(vertex);
        if(i == -1)
            return false;
        vertices[i] = null;
        for(int j = 0; j < vertices.length; j++)
            directedEdges[i][j] = directedEdges[j][i] = undirectedEdges[i][j] = undirectedEdges[j][i] = NO_EDGE;
        return true;
    }

    public void connectDirected(T from, T to, double weight) {
        int i = find(from), j = find(to);
        if(i != -1 && j != -1 && i != j && undirectedEdges[i][j] == NO_EDGE)
            directedEdges[i][j] = weight;
    }

    public void connectUndirected(T a, T b, double weight) {
        int i = find(a), j = find(b);
        if(i != -1 && j != -1 && i != j && directedEdges[i][j] == NO_EDGE && directedEdges[j][i] == NO_EDGE)
            undirectedEdges[i][j] = undirectedEdges[j][i] = weight;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<T> getVertices() {
        ArrayList<T> l = new ArrayList<>();
        for(Object vertex : vertices)
            if(vertex != null)
                l.add((T) vertex);
        return l;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<T> getInwardEdges(T to) {
        int j = find(to);
        if(j == -1)
            return null;
        ArrayList<T> l = new ArrayList<>();
        for(int i = 0; i < vertices.length; i++)
            if(directedEdges[i][j] != NO_EDGE)
                l.add((T) vertices[i]);
        return l;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<T> getOutwardEdges(T from) {
        int i = find(from);
        if(i == -1)
            return null;
        ArrayList<T> l = new ArrayList<>();
        for(int j = 0; j < vertices.length; j++)
            if(directedEdges[i][j] != NO_EDGE)
                l.add((T) vertices[j]);
        return l;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<T> getEdges(T vertex) {
        int i = find(vertex);
        if(i == -1)
            return null;
        ArrayList<T> l = new ArrayList<>();
        for(int j = 0; j < vertices.length; j++)
            if(undirectedEdges[i][j] != NO_EDGE)
                l.add((T) vertices);
        return l;
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> merge(ArrayList<T>... lists) {
        ArrayList<T> l = new ArrayList<>();
        for(ArrayList<T> list : lists) {
            if(list == null)
                continue;
            for(T e : list)
                if(!l.contains(e))
                    l.add(e);
        }
        return l;
    }

    private LinkedList<Integer> bfs(int from, int to) {
        ArrayList<Integer> visited = new ArrayList<>();
        Queue<Stack<Integer>> queue = new LinkedList<>();
        visited.add(from);
        queue.offer(new Stack<>());
        queue.peek().push(from);
        while(!queue.isEmpty()) {
            Stack<Integer> stack = queue.poll();
            int i = stack.peek();
            if(i == to)
                return stackToPath(stack);
            for(int j = 0; j < vertices.length; j++) {
                if((directedEdges[i][j] == NO_EDGE && undirectedEdges[i][j] == NO_EDGE) || visited.contains(j))
                    continue;
                Stack<Integer> newStack = new Stack<>();
                for(int e : stack)
                    newStack.push(e);
                newStack.push(j);
                visited.add(j);
                queue.offer(newStack);
            }
        }
        return null;
    }

    public LinkedList<T> breadthFirstSearch(T from, T to) {
        int i = find(from), j = find(to);
        if(i == -1 || j == -1)
            return null;
        LinkedList<Integer> indices = bfs(i, j);
        return indices == null ? null : path(indices);
    }

    private LinkedList<Integer> dfs(int from, int to) {
        ArrayList<Integer> visited = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        visited.add(from);
        stack.push(from);
        while(!stack.isEmpty()) {
            int i = stack.peek();
            if(i == to)
                return stackToPath(stack);
            boolean found = false;
            for(int j = 0; j < vertices.length; j++) {
                if((directedEdges[i][j] == NO_EDGE && undirectedEdges[i][j] == NO_EDGE) || visited.contains(j))
                    continue;
                visited.add(j);
                stack.push(j);
                found = true;
                break;
            }
            if(!found)
                stack.pop();
        }
        return null;
    }

    public LinkedList<T> depthFirstSearch(T from, T to) {
        int i = find(from), j = find(to);
        if(i == -1 || j == -1)
            return null;
        LinkedList<Integer> indices = dfs(i, j);
        return indices == null ? null : path(indices);
    }

    private LinkedList<Integer> dijkstra(int from, int to) {
        ArrayList<Integer> visited = new ArrayList<>();
        double[] dist = new double[vertices.length];
        int[] prev = new int[vertices.length];
        for(int i = 0; i < vertices.length; i++) {
            if(vertices[i] == null)
                visited.add(i);
            dist[i] = Double.POSITIVE_INFINITY;
        }
        dist[from] = 0;
        prev[from] = -1;
        while(visited.size() < vertices.length) {
            int curr = -1;
            double min = Double.POSITIVE_INFINITY;
            for(int i = 0; i < vertices.length; i++) {
                if(dist[i] > min || visited.contains(i))
                    continue;
                curr = i;
                min = dist[i];
            }
            for(int j = 0; j < vertices.length; j++) {
                if((directedEdges[curr][j] == NO_EDGE && undirectedEdges[curr][j] == NO_EDGE) || visited.contains(j))
                    continue;
                double distance = dist[curr] + (directedEdges[curr][j] == NO_EDGE ? undirectedEdges[curr][j] : directedEdges[curr][j]);
                if(distance < dist[j]) {
                    dist[j] = distance;
                    prev[j] = curr;
                }
            }
            visited.add(curr);
            if(curr == to)
                break;
        }
        if(dist[to] == Double.POSITIVE_INFINITY)
            return null;
        LinkedList<Integer> indices = new LinkedList<>();
        for(int curr = to; curr != -1; curr = prev[curr])
            indices.addFirst(curr);
        return indices;
    }

    public LinkedList<T> shortestPath(T from, T to) {
        int i = find(from), j = find(to);
        if(i == -1 || j == -1)
            return null;
        LinkedList<Integer> indices = dijkstra(i, j);
        return indices == null ? null : path(indices);
    }

    private int eulerDir() {
        ArrayList<Integer> indices = new ArrayList<>();
        int start = -1, end = -1;
        for(int i = 0; i < vertices.length; i++) {
            int ins = 0, outs = 0;
            for(int j = 0; j < vertices.length; j++) {
                undirectedEdges[i][j] = NO_EDGE;
                if(directedEdges[j][i] != NO_EDGE)
                    ins++;
                if(directedEdges[i][j] != NO_EDGE)
                    outs++;
            }
            if(ins == 0 && outs == 0)
                continue;
            else if(ins - outs == 1 && end == -1)
                end = i;
            else if(outs - ins == 1 && start == -1)
                start = i;
            else if(outs != ins)
                return NOT_EULERIAN;
            indices.add(i);
        }
        return connectedTo(indices, end == -1 ? indices.get(0) : end) ? start == -1 ? EULERIAN_CIRCUIT : EULERIAN_PATH : NOT_EULERIAN;
    }

    public int eulerianDirected() {
        double[][] oldUndirectedEdges = undirectedEdges;
        undirectedEdges = new double[vertices.length][vertices.length];
        int euler = eulerDir();
        undirectedEdges = oldUndirectedEdges;
        return euler;
    }

    private int eulerUndir() {
        ArrayList<Integer> indices = new ArrayList<>();
        int odds = 0;
        for(int i = 0; i < vertices.length; i++) {
            int degree = 0;
            for(int j = 0; j < vertices.length; j++) {
                directedEdges[i][j] = NO_EDGE;
                if(undirectedEdges[i][j] != NO_EDGE)
                    degree++;
            }
            if(degree == 0)
                continue;
            odds += degree % 2;
            indices.add(i);
        }
        return !(odds == 0 || odds == 2) || !connectedTo(indices, indices.get(0)) ? NOT_EULERIAN : odds == 0 ? EULERIAN_CIRCUIT : EULERIAN_PATH;
    }

    public int eulerianUndirected() {
        double[][] oldDirectedEdges = directedEdges;
        directedEdges = new double[vertices.length][vertices.length];
        int euler = eulerUndir();
        directedEdges = oldDirectedEdges;
        return euler;
    }

    public boolean contains(Object vertex) {
        return find(vertex) != -1;
    }

    public int size() {
        int size = 0;
        for(Object vertex : vertices)
            if(vertex != null)
                size++;
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sbVerts = new StringBuilder("Vertices:\n"), sbEdges = new StringBuilder("\nEdges:\n");
        for(int i = 0; i < vertices.length; i++) {
            if(vertices[i] == null)
                continue;
            sbVerts.append(vertices[i]).append("\n");
            for(int j = 0; j < vertices.length; j++) {
                if(directedEdges[i][j] != NO_EDGE)
                    sbEdges.append(vertices[i]).append(" -> ").append(vertices[j]).append(" (").append(directedEdges[i][j]).append(")\n");
                if(j >= i && undirectedEdges[i][j] != NO_EDGE)
                    sbEdges.append(vertices[i]).append(" -- ").append(vertices[j]).append(" (").append(undirectedEdges[i][j]).append(")\n");
            }
        }
        return sbVerts.toString() + sbEdges.toString();
    }

    private void empty(int capacity) {
        vertices = new Object[capacity];
        directedEdges = new double[capacity][capacity];
        undirectedEdges = new double[capacity][capacity];
        for(int i = 0; i < capacity; i++)
            for(int j = 0; j < capacity; j++)
                directedEdges[i][j] = undirectedEdges[i][j] = NO_EDGE;
    }

    private void grow() {
        Object[] oldVertices = vertices;
        double[][] oldDirectedEdges = directedEdges;
        double[][] oldUndirectedEdges = undirectedEdges;
        empty(vertices.length * 2);
        copy(oldVertices, oldDirectedEdges, oldUndirectedEdges);
    }

    private void copy(Object[] oldVertices, double[][] oldDirectedEdges, double[][] oldUndirectedEdges) {
        for(int i = 0; i < oldVertices.length; i++) {
            vertices[i] = oldVertices[i];
            for(int j = 0; j < oldVertices.length; j++) {
                directedEdges[i][j] = oldDirectedEdges[i][j];
                undirectedEdges[i][j] = oldUndirectedEdges[i][j];
            }
        }
    }

    private int find(Object vertex) {
        if(vertex == null)
            return -1;
        for(int i = 0; i < vertices.length; i++)
            if(vertex.equals(vertices[i]))
                return i;
        return -1;
    }

    private boolean connectedTo(ArrayList<Integer> indices, int to) {
        for(int from : indices)
            if(dfs(from, to) == null)
                return false;
        return true;
    }

    private static LinkedList<Integer> stackToPath(Stack<Integer> stack) {
        LinkedList<Integer> l = new LinkedList<>();
        for(int e : stack)
            l.addLast(e);
        return l;
    }

    @SuppressWarnings("unchecked")
    private LinkedList<T> path(LinkedList<Integer> indices) {
        LinkedList<T> l = new LinkedList<>();
        for(int index : indices)
            l.addLast((T) vertices[index]);
        return l;
    }

}
