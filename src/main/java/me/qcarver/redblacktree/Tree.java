/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.qcarver.redblacktree;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Quinn
 */
@XmlRootElement
public class Tree {

    public Node root = Sentinel.getInstance();
    @XmlTransient
    public Node nil = Sentinel.getInstance();

    void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != nil) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == nil) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (x.right != nil) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == nil) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }
        x.right = y;
        y.parent = x;
    }

    void rbInsert(Node z) throws Exception {
        Node y = nil;
        Node x = root;
        for (;x != nil;) {
            y = x;
            if (z.key() < x.key()) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y == nil) {
            root = z;
        } else if (z.key() < y.key()) {
            y.left = z;
        } else {
            y.right = z;
        }
        z.left = nil;
        z.right = nil;
        z.color(Color.RED);
        rbInsertFixup(z);
    }

    void rbInsertFixup(Node z) throws Exception {
        Node y = new Node();
        for (; z.parent.color() == Color.RED;) {
            if (z.parent == z.parent.parent.left) {
                y = z.parent.parent.right;
                if (y.color() == Color.RED) {
                    z.parent.color(Color.BLACK);
                    y.color(Color.BLACK);
                    z.parent.parent.color(Color.RED);
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.color(Color.BLACK);
                    z.parent.parent.color(Color.RED);
                    rightRotate(z.parent.parent);
                }
            } else //z's parent was a right child of it's parent{
            {
                y = z.parent.parent.left;
            }
            if (y.color() == Color.RED) {
                z.parent.color(Color.BLACK);
                y.color(Color.BLACK);
                z.parent.parent.color(Color.RED);
                z = z.parent.parent;
            } else {
                if (z == z.parent.left) {
                    z = z.parent;
                    rightRotate(z);
                }
                z.parent.color(Color.BLACK);
                z.parent.parent.color(Color.RED);
                leftRotate(z.parent.parent);
            }
        }
        root.color(Color.BLACK);
    }
    
    public void rbTransplant(Node u, Node v) {
        if (u.parent == nil) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }
    
    public Node treeMinimum(Node x){
        for(;x.left != nil;){
            x = x.left;
        }
        return x;
    }
    
    public void rbDelete(Node z) throws Exception{
        Node y = z;
        Node x = nil;
        Color y_orig_color = y.color();
        if (z.left == nil){
            x = z.right;
            rbTransplant(z,z.right);
        }
        else if (z.right == nil){
            x = z.left;
            rbTransplant(z,z.left);
        }
        else{
            y = treeMinimum(z.right);
            y_orig_color = y.color();
            x = y.right;
            if (y.parent == z)
                x.parent = y;
            else {
                rbTransplant(y,y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            rbTransplant(z,y);
            y.left = z.left;
            y.left.parent = y;
            y.color(z.color());
        }
        if (y_orig_color == Color.BLACK)
            rbDeleteFixup(x);
        }
    
    private void rbDeleteFixup(Node x) throws Exception {
        Node w;
        for (; ((x != root) && (x.color() == Color.BLACK));) {
            if (x == x.parent.left) {
                w = x.parent.right;
                if (w.color() == Color.RED) {
                    w.color(Color.BLACK);
                    x.parent.color(Color.RED);
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if ((w.left.color() == Color.BLACK) && (w.right.color() == Color.BLACK)) {
                    w.color(Color.RED);
                    x = x.parent;
                } else {
                    if (w.right.color() == Color.BLACK) {
                        w.left.color(Color.BLACK);
                        w.color(Color.RED);
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    w.color(x.parent.color());
                    x.parent.color(Color.BLACK);
                    w.right.color(Color.BLACK);
                    leftRotate(x.parent);
                    x = root;
                }
            }
            else{//x is a right child of its parent
                w = x.parent.left;
                if (w.color() == Color.RED) {
                    w.color(Color.BLACK);
                    x.parent.color(Color.RED);                    
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if ((w.right.color() == Color.BLACK) && (w.left.color() == Color.BLACK)) {
                    w.color(Color.RED);
                    x = x.parent;
                } else {
                    if (w.left.color() == Color.BLACK) {
                        w.right.color(Color.BLACK);
                        w.color(Color.RED);
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.color(x.parent.color());
                    x.parent.color(Color.BLACK);
                    w.left.color(Color.BLACK);
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color(Color.BLACK);
    }
}
