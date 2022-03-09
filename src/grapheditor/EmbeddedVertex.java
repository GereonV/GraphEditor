/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapheditor;

/**
 * Diese Klasse erbt von GraphNode und ihre Objekte können so in einem Graphen gespeichert werden.
 * Zusätzlich werden hier aber Koordinaten verwaltet. Durch TypeCast können aus den in der DS Graph
 gespeicherten Instanzen der Klassen GraphNode wieder EmbeddedVertex-Objekte gemacht werden, sodass
 Zeichenoperationen gemacht werden können.
 * @author Pardella
 */
public class EmbeddedVertex extends abiturklassen.graphklassen.Vertex {
    
    private int x, y;
    
    public EmbeddedVertex(String name) {
        super(name);
        x = y = 0;
    }

    public EmbeddedVertex(String name, int x, int y) {
        super(name);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Stellt fest, ob die übergebenen Pixel-Koordinaten innerhalb von 30 Pixeln im
     * Radius der Knoten-Koordinaten liegen.
     * @return true, falls (pX|pY) näher als 30 Pixel an (x|y) liegt
     **/
    public boolean isInNode(int pX, int pY) {
        return Math.sqrt((pX-x)*(pX-x)+(pY-y)*(pY-y)) < 30;
    }
    
    @Override
    public String toString() {
        return super.toString() + " (" + x + "|" + y + ")";
    }
}
