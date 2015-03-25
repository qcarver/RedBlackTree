package me.qcarver.redblacktree;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Hello world!
 *
 */
public class App {
    

    public static void main(String[] args) {
        
        Tree t = new Tree();
        
        test(t);
    }
    
    public static void test(Tree t) {
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
        
        System.out.println("Printing tree after insertions...");
        printTree(t);

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
        
        System.out.println("Printing tree after deletions...");
        printTree(t);
        
        testTreeRules(t, nodes);
        
    }

    /**
     * This test is only called by other tests
     * @param t
     * @param nodes 
     */
    public static void testTreeRules(Tree t, Node nodes[]) {
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
    
    public static void assertEquals(int a, int b){
        if (a != b){
            System.err.print("assert failed");
            System.exit(1);
        }
    }
    
    public static void assertEquals(Color a, Color b){
        if (a != b){
            System.err.print("assert failed");
            System.exit(1);
        }
    }

    public static void printTree(Tree tree) {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Tree.class,Node.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(tree, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
