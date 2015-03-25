/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.qcarver.redblacktree;

/**
 *
 * @author Quinn
 */
public class Sentinel extends Node {
    
    private static Sentinel singleton = new Sentinel();
    
    public static Sentinel getInstance(){
        return singleton;
    }
    
    private Sentinel(){
        super(Integer.MIN_VALUE,Color.BLACK);
    }

    @Override
    public int key() throws Exception{
        throw new Exception("attempted to get key from Sentinel");
    }

    @Override
    public void color(Color color) throws Exception{
        throw new Exception("attempted to set color of Sentinel");
    }

    @Override
    public void key(int key) throws Exception{
        throw new Exception("attempted to set key of Sentinel");
    }
    
    
}
