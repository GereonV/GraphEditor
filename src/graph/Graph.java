package graph;

import java.util.ArrayList;

public class Graph<T> {

    private static final int DEFAULT_CAPACITY = 10;
    public static final double NO_EDGE = Double.NEGATIVE_INFINITY;

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
        if(i != -1 && j != -1)
            directedEdges[i][j] = weight;
    }

    public void connectUndirected(T a, T b, double weight) {
        int i = find(a), j = find(b);
        if(i != -1 && j != -1)
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

}
