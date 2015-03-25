/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.qcarver.redblacktree;

import junit.framework.TestCase;
/**
 *
 * @author Quinn
 */
public class TreeTest extends TestCase {

    Tree t = null;

    public TreeTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        t = new Tree();
        //NIL = Sentinel.getInstance();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of leftRotate method, of class Tree.
     */
    public void testLeftRotate() {
        Node x = new Node();
        Node y = new Node();
        t.root = x;
        t.root.right = y;
        assertEquals(t.root, x);
        assertEquals(t.root.right, y);
        assertEquals(t.root.left, t.nil);

        t.leftRotate(x);

        assertEquals(t.root, y);
        assertEquals(t.root.left, x);
        assertEquals(t.root.right, t.nil);
    }

    /**
     * Test of leftRotate method, of class Tree.
     */
    public void testRightRotate() {
        Node y = new Node();
        Node x = new Node();
        t.root = y;
        t.root.left = x;
        assertEquals(t.root, y);
        assertEquals(t.root.left, x);
        assertEquals(t.root.right, t.nil);

        t.rightRotate(y);

        assertEquals(t.root, x);
        assertEquals(t.root.right, y);
        assertEquals(t.root.left, t.nil);
    }

    public void testInsert(Tree t) {
        this.t = t;
        testInsert();
    }

    public void testInsert() {
        Node nodes[] = {new Node(0), new Node(1), new Node(2), new Node(3),
            new Node(4), new Node(5), new Node(6), new Node(7), new Node(8),
            new Node(9), new Node(10), new Node(11),
            new Node(12), new Node(13), new Node(14), new Node(15)};

        t.root = t.nil;

        for (Node node : nodes) {
            try {
                t.rbInsert(node);
            } catch (Exception e) {
                System.out.println("inserting node " + node.color()
                        + " test insert threw " + e);
            }
        }

        //TEST TREE

        //ROOT Must Be Black
        assertEquals(Color.BLACK, t.root.color());

        //IF A NODE IS READ THEN BOTH ITS CHILDREN ARE BLACK
        for (Node node : nodes) {
            if ((node.color()) == Color.RED) {
                assert (node.left.color() == Color.BLACK);
                assert (node.right.color() == Color.BLACK);
            }
        }
        //SAME BLACK HEIGHT FOR ALL LEAVES BACK TO ROOT
        int globalBlackCount = 1;
        for (Node node : nodes) {
            final int UNINITIALIZED = 1;
            //root is always black anyway (tested above)
            int pathBlackCount = 1;
            //find a node which is a leaf
            if ((node.left == t.nil) && (node.right == t.nil)) {
                //work backward to root, while tallying the black nodes
                for (pathBlackCount = 1; node.parent != t.nil; node = node.parent) {
                    if (node.color() == Color.BLACK) {
                        pathBlackCount++;
                    }
                }
                //first time is free
                if (globalBlackCount == UNINITIALIZED) {
                    globalBlackCount = pathBlackCount;
                } else {
                    //after that.. these two had better match!
                    assertEquals(pathBlackCount, globalBlackCount);
                }
            }
        }
        //trivia
        System.out.println("Black height of tree is " + globalBlackCount);
    }

    public void testTransplant() {
        Tree t = new Tree();
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        t.root = node0;
        node0.right = node1;
        node1.parent = node0;
        node1.right = node3;
        node3.parent = node1;

        //transplant the middle node of 0,1,3  with 2
        t.rbTransplant(node1, node2);

        //assert that zeros child is 2
        assertEquals(node2, node0.right);
        //assert that two2 parent is zero
        assertEquals(node0, node2.parent);
    }

    public void testDelete(Tree t) {
        this.t = t;
        testDelete();
    }

    public void testDelete() {
        Node nodes[] = {new Node(0), new Node(1), new Node(2), new Node(3),
            new Node(4), new Node(5), new Node(6), new Node(7), new Node(8),
            new Node(9), new Node(10), new Node(11),
            new Node(12), new Node(13), new Node(14), new Node(15)};

        t.root = t.nil;

        for (Node node : nodes) {
            try {
                t.rbInsert(node);
            } catch (Exception e) {
                System.out.println("inserting node " + node.color()
                        + " test insert threw " + e);
            }
        }

        //TEST DELETE
        try {
            t.rbDelete(nodes[7]);
            nodes[7] = null;
            t.rbDelete(nodes[2]);
            nodes[2] = null;
            t.rbDelete(nodes[11]);
            nodes[11] = null;
            t.rbDelete(nodes[1]);
            nodes[1] = null;
            t.rbDelete(nodes[15]);
            nodes[15] = null;
            t.rbDelete(nodes[5]);
            nodes[5] = null;
            t.rbDelete(nodes[6]);
            nodes[6] = null;
            t.rbDelete(nodes[9]);
            nodes[9] = null;
        } catch (Exception e) {
            System.out.println("deleting node threw exception " + e);
        }
        testTreeRules(t, nodes);
    }

    /**
     * This test is only called by other tests
     * @param t
     * @param nodes 
     */
    void testTreeRules(Tree t, Node nodes[]) {
        //TEST TREE

        //ROOT Must Be Black
        assertEquals(Color.BLACK, t.root.color());

        //IF A NODE IS READ THEN BOTH ITS CHILDREN ARE BLACK
        for (Node node : nodes) {
            //skip over ones we deleted
            if (node == null) {
                continue;
            }

            if ((node.color()) == Color.RED) {
                assert (node.left.color() == Color.BLACK);
                assert (node.right.color() == Color.BLACK);
            }
        }
        //SAME BLACK HEIGHT FOR ALL LEAVES BACK TO ROOT
        int globalBlackCount = 1;
        for (Node node : nodes) {
            //skip over ones we deleted
            if (node == null) {
                continue;
            }
            final int UNINITIALIZED = 1;
            //root is always black anyway (tested above)
            int pathBlackCount = 1;
            //find a node which is a leaf
            if ((node.left == t.nil) && (node.right == t.nil)) {
                //work backward to root, while tallying the black nodes
                for (pathBlackCount = 1; node.parent != t.nil; node = node.parent) {
                    if (node.color() == Color.BLACK) {
                        pathBlackCount++;
                    }
                }
                //first time is free
                if (globalBlackCount == UNINITIALIZED) {
                    globalBlackCount = pathBlackCount;
                } else {
                    //after that.. these two had better match!
                    assertEquals(pathBlackCount, globalBlackCount);
                }
            }
        }
        //trivia
        System.out.println("After delete black height of tree is " + globalBlackCount);

    }
}
