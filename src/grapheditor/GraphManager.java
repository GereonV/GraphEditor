package grapheditor;

import abiturklassen.graphklassen.*;
import abiturklassen.listenklassen.List;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.*;

/**
 * @author Pardella
 */
public class GraphManager implements MouseListener {
    
    //Eine solche Aufzählung gibt den gazzahligen Werten 0 (=Knoten),1 (=Kanten), usw. einen Namen.
    //Genausogut könnte man Fälle mit 0,1,2,usw. unterscheiden. Dann könnte man den Code aber nicht so gut lesen.
    public static enum DrawMode { Knoten, Kanten, Edit };
    
    private Graph g;//DS Graph in der gezeichnete Knoten und Kanten gespeichert werden sollen - ggf. zur List erweitern, dann können mehreren Graphen gespeichert werden.
    private GraphFrame gf;//Zeiger auf dne Frame für Zugriff auf GUI-Objekte
    private DrawMode mode;//Aktueller Modus: Sollen Knoten oder Kanten gezeichnet werden?
    private int num_vert;//Anzahl bisheriger Knoten im aktuellen Graphen
    private EmbeddedVertex startVert, editVert;//Zwei Knoten, die zur Kantengenerierung markiert sein können
    
    /**
     * Konstruktor - selbsterklärend
     * @param pGF Referenz zur GUI
     */
    public GraphManager(GraphFrame pGF) {
        g = new Graph();
        gf = pGF;
        num_vert = 0;
        mode = DrawMode.Knoten;
        startVert = editVert = null;
    }

    /**
     * @return aktueller Graph
     */
    public Graph getG() {
        return g;
    }
    
    /**
     * "Clear" für den aktuellen Graphen und die Zeichenfläche?
     */
    public void newG() {
        g = new Graph();
        num_vert = 0;
        startVert = editVert = null;
        JPanel canvas = gf.getjTabbedPane1();
        Graphics cg = canvas.getGraphics();
        cg.clearRect(canvas.getX(), canvas.getY(), canvas.getWidth(), canvas.getHeight());
    }
    
    public EmbeddedVertex getMarkedEV() {
        return startVert;
    }
    
    /**
     * Ausgabe der Knoten des aktuellen Graphen.
     * @return String-Repräsentation aller Knoten des aktuellen Graphen
     */
    public String outVertices() {
        String s = "Knoten: \n";
        List<Vertex> l = g.getVertices();
        for(l.toFirst(); l.hasAccess(); l.next())
            s += l.getContent().toString() + "\n";
        return s;
    }

    /**
     * Ausgabe der Kanten des aktuellen Graphen.
     * @return String-Repräsentation aller Kanten des aktuellen Graphen - Achtung: g,getEdges ist nicht Doku-konform
     */
    public String outEdges() {
        String s = "Kanten: \n";
        List<Edge> l = g.getEdges();
        for(l.toFirst(); l.hasAccess(); l.next())
            s += l.getContent().toString() + "\n";
        return s;
    }
    
    public DrawMode getMode() {
        return mode;
    }

    public void setMode(DrawMode mode) {
        this.mode = mode;
    } 
    
    /* Ab hier: Implementierung der Mouse-Listener-Methoden
        Nicht gefüllte Methoden können noch für viele erweiterte Funktionalitäten genutzt werden.*/
    
    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel canvas = gf.getjTabbedPane1();
        Graphics cg = canvas.getGraphics();
        EmbeddedVertex vert = findNode(e.getX(), e.getY());
        switch(mode) {
            case Knoten: 
                if(vert != null)
                    break;
                vert = new EmbeddedVertex("V_" + num_vert++, e.getX(), e.getY());
                g.addVertex(vert);
                drawVertex(vert, Color.black, cg);
                break;
            case Kanten:
                if(vert == null)
                    break;
                if(startVert == null) {
                    drawVertex(vert, Color.red, cg);
                    startVert = vert;
                    break;
                }
                g.addEdge(new Edge(startVert, vert, Math.random() * 10));
                drawVertex(startVert, Color.black, cg);
                drawEdge(vert, cg);
                startVert = null;
                break;
            case Edit:
                if(e.getButton() == MouseEvent.BUTTON3) {
                    if(editVert == null)
                        g.removeVertex(vert);
                    else if(vert != null)
                        g.removeEdge(g.getEdge(editVert, vert));
                    editVert = null;
                    redraw();
                    break;
                }
                if(!(editVert == null ^ vert == null))
                    break;
                if(editVert == null) {
                    drawVertex(vert, Color.red, cg);
                    editVert = vert;
                    break;
                }
                editVert.setX(e.getX());
                editVert.setY(e.getY());
                redraw();
                editVert = null;
        }
    }
    
    private void drawVertex(EmbeddedVertex vertex, Color c, Graphics cg) {
        int offset = 15, size = offset * 2;
        cg.setColor(c);
        cg.drawOval(vertex.getX() - offset, vertex.getY() - offset, size, size);
        cg.drawString(vertex.getID(), vertex.getX(), vertex.getY());
    }
    
    private void drawEdge(EmbeddedVertex endVert, Graphics cg) {
        int dirX = endVert.getX() - startVert.getX(), dirY = endVert.getY() - startVert.getY();
        double length = 15 / Math.sqrt(dirX * dirX + dirY * dirY);
        int offsetX = (int) (dirX * length), offsetY = (int) (dirY * length);
        cg.setColor(Color.black);
        cg.drawLine(startVert.getX() + offsetX, startVert.getY() + offsetY, endVert.getX() - offsetX, endVert.getY() - offsetY);
    }
    
    private EmbeddedVertex findNode(int pX, int pY) {
        List<Vertex> nodes = g.getVertices();
        for(nodes.toFirst(); nodes.hasAccess(); nodes.next())
            if(((EmbeddedVertex) nodes.getContent()).isInNode(pX, pY))
                return (EmbeddedVertex) nodes.getContent();
        return null;
    }
    
    public boolean complete() {
        List<Vertex> help_l = g.getVertices(), help_l2 = deepCopyList(help_l);
        for(help_l.toFirst(); help_l.hasAccess(); help_l.next()) {
            for(help_l2.toFirst(); help_l2.hasAccess(); help_l2.next()) {
                if(help_l.getContent().equals(help_l2.getContent()) || g.getEdge(help_l.getContent(), help_l2.getContent()) != null)
                    continue;
                System.out.println("Kante existiert nicht:" + help_l.getContent().toString() + " -> " + help_l2.getContent().toString());
                return false;
            }
        }
        return true;
    }

    //Alex, kopier hier rein!
    //das alte einfach löschen
    public boolean eulerianPath() {
        int odds = 0;
        List<Vertex> vertices = g.getVertices();
        for(vertices.toFirst(); vertices.hasAccess(); vertices.next()) {
            int size = 0;
            List<Vertex> neighbours = g.getNeighbours(vertices.getContent());
            for(neighbours.toFirst(); neighbours.hasAccess(); neighbours.next())
                size++;
            if(size % 2 != 0)
                odds++;
        }
        return odds == 0 || odds == 2;
    }

    public void completeGraph() {
        Graphics cg = gf.getjTabbedPane1().getGraphics();
        List<Vertex> l1 = g.getVertices(), l2 = deepCopyList(l1);
        for(l1.toFirst(); l1.hasAccess(); l1.next()) {
            startVert = (EmbeddedVertex) l1.getContent();
            for(l2.toFirst(); l2.hasAccess(); l2.next()) {
                g.addEdge(new Edge(l1.getContent(), l2.getContent(), (int) (Math.random() * 10)));
                drawEdge((EmbeddedVertex) l2.getContent(), cg);
            }
        }
    }
    
    //Alex, kopier hier rein!
    //1. Zeile: newG();
    //letzte Zeile: redraw();
    public void generateGraph(int count) {
        newG();
        redraw();
    }
    
    private <T> List<T> deepCopyList(List<T> l) {
        List<T> c = new List<>();
        for(l.toFirst(); l.hasAccess(); l.next())
            c.append(l.getContent());
        return c;
    }
    
    public int exportGraph(File fileName) {
        try {
            FileOutputStream os = new FileOutputStream(fileName);
            os.write(serialize().getBytes());
            os.close();
        } catch(FileNotFoundException e) {
            return 1;
        } catch(IOException e) {
            return 2;
        }
        return 0;
    }

    public int importGraph(File fileName) {
        try {
            FileInputStream is = new FileInputStream(fileName);
            parse(new String(is.readAllBytes()));
            is.close();
            redraw();
        } catch(FileNotFoundException e) {
            return 1;
        } catch(IOException e) {
            return 2;
        }
        return 0;
    }
    
    private String serialize() {
        StringBuilder sb = new StringBuilder();
        List<Vertex> vertices = g.getVertices();
        List<Edge> edges = g.getEdges();
        for(vertices.toFirst(); vertices.hasAccess(); vertices.next()) {
            EmbeddedVertex vert = (EmbeddedVertex) vertices.getContent();
            sb.append(vert.getID());
            sb.append("\n");
            sb.append(vert.getX());
            sb.append("\n");
            sb.append(vert.getY());
            sb.append("\n");
        }
        sb.append("\n");
        for(edges.toFirst(); edges.hasAccess(); edges.next()) {
            Vertex[] verts = edges.getContent().getVertices();
            sb.append(verts[0].getID());
            sb.append("\n");
            sb.append(verts[1].getID());
            sb.append("\n");
            sb.append(edges.getContent().getWeight());
            sb.append("\n");
        }
        sb.append("|");
        return sb.toString();
    }
    
    private void parse(String s) {
        g = new Graph();
        String[] lines = s.split("\n");
        int i = 0;
        while(!lines[i].equals(""))
            g.addVertex(new EmbeddedVertex(lines[i++], Integer.parseInt(lines[i++]), Integer.parseInt(lines[i++])));
        num_vert = i++ / 3;
        while(i < lines.length - 1)
            g.addEdge(new Edge(g.getVertex(lines[i++]), g.getVertex(lines[i++]), Double.parseDouble(lines[i++])));
    }

    private void redraw() {
        Graph graph = g;
        int vert_count = num_vert;
        newG();
        g = graph;
        num_vert = vert_count;
        Graphics cg = gf.getjTabbedPane1().getGraphics();
        List<Vertex> vertices = g.getVertices();
        for(vertices.toFirst(); vertices.hasAccess(); vertices.next())
            drawVertex((EmbeddedVertex) vertices.getContent(), Color.black, cg);
        List<Edge> edges = g.getEdges();
        for(edges.toFirst(); edges.hasAccess(); edges.next()) {
            Vertex[] verts = edges.getContent().getVertices();
            startVert = (EmbeddedVertex) verts[0];
            drawEdge((EmbeddedVertex) verts[1], cg);
        }
        startVert = null;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
