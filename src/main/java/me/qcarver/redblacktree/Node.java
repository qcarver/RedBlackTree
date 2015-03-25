/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.qcarver.redblacktree;

import java.util.Random;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Quinn
 */
class Node {   
    public Node right = null;
    public Node left = null;
    @XmlTransient
    public Node parent = null;
    @XmlAttribute
    private Color color = Color.RED;  
    @XmlAttribute
    private int key = 0;

    public Color color() {
        return color;
    }

    public void color(Color color) throws Exception {
        this.color = color;
    }

    public int key() throws Exception {
        return key;
    }

    public void key(int key)throws Exception {
        this.key = key;
    }

    Node(){
        Random random = new Random();
        init(random.nextInt(256), Color.RED);
    }
    
    Node(int key){
       init(key, Color.RED);
    }
    
    //ONLY USED BY THE SENTINEL
    protected Node(int key, Color color){
      init(key, color);
    }
    
    void init(int key, Color color){
        this.key = key;
        this.color = color;
        this.parent = Sentinel.getInstance();
        this.left = Sentinel.getInstance();
        this.right = Sentinel.getInstance();       
    }
}
