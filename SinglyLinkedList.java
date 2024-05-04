package DS;

import java.security.InvalidParameterException;
import java.util.Iterator;

public class SinglyLinkedList implements Iterable<Integer>{
    
    public Node head;

    private static class Node {

        private int data; 
        private Node next = null;

        @Override
        public String toString () {

            return String.valueOf(data);
    
        }

        public Node(int data) {
            
            this.data = data;
            this.next = null;
        
        }

        public Node(int data, Node next) {
            
            this.data = data;
            this.next = next;
        
        }

    }

    @Override
    public Iterator<Integer> iterator() {
        
        return new Iterator<Integer>() {
            
            private Node n = head; 

            @Override
            public boolean hasNext() {
                
                return n != null;
            }


            @Override
            public Integer next() {

                Integer data = n.data;
                n = n.next;
                
                return data;

            }

            @Override
            public void remove() {

                throw new UnsupportedOperationException("Not allowed");

            }
        };

    }

    @Override
    public String toString () {

        //@TODO 
        //implement iterator to iterate over data and output it

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        // Node n = head;

        // while(n != null) {

        //     sb.append(n.data);

        //     if (n.next != null) {

        //         sb.append(", ");

        //     }

        //     n = n.next;

        // }

        //use iterator
        Iterator<Integer> itr = this.iterator();

        while(itr.hasNext()) {
            
            sb.append(itr.next());
            
            if (itr.hasNext()) {
                
                sb.append(",");

            }

        }

        sb.append("]");

        return sb.toString();

    }

    public Node getHead() {

        return head;

    }

    private SinglyLinkedList setHead(Node n) {

        head = n;
        return this;

    }
    
    private Node getNode(int data) {

        return new Node(data);

    }

    public static boolean isEmpty(SinglyLinkedList list) {

        return list.getHead() == null;

    }

    public SinglyLinkedList reset() {
        
        head = null;

        return this;

    }
    
    public SinglyLinkedList addToEmptyList(int data) {

        if (head != null) {

            throw new InvalidParameterException("List is not empty.");

        }
        
        Node n = getNode(data);
        setHead(n);

        return this;

    }

    public SinglyLinkedList add(int data) {
        
        Node n = new Node(data);
        Node currNode = getHead();

        if (head == null) {
            
            setHead(n);
            return this;
        }

        while(currNode.next != null) {

            currNode = currNode.next;

        }

        currNode.next = n;
        
        return this;
    }

    public SinglyLinkedList add(int data, int pos) {
        
        if (pos < 0) {

            throw new InvalidParameterException("Position cannot be -ve.");

        }

        if (head == null) {

            if (pos != 0) {

                throw new InvalidParameterException("Position doesn't yet exist for the target list.");

            }

            return addToEmptyList(data);

        }

        Node currNode = getHead();
        Node prevNode = null;

        int counter = 0;

        while(counter != pos && currNode.next != null) {

            prevNode = currNode;
            currNode = currNode.next;
            
            counter++;

        }

        //pos given doesn't exist on the list yet
        if (counter != pos) {
            throw new InvalidParameterException("Position doesn't yet exist for the target list.");
        }

        Node n = getNode(data);
        
        if(prevNode == null) {
            setHead(n);
        } else {
            prevNode.next = n;
        }

        n.next = currNode;
        
        return this;

    }

    public SinglyLinkedList deleteAtPos(int pos) {

        if (pos < 0) {

            throw new InvalidParameterException("Position cannot be -ve.");

        }

        if (isEmpty(this)) {

            throw new IllegalStateException("List is already empty.");

        }

        int counter = 0;

        Node curNode = getHead();
        Node prevNode = null;

        while (counter != pos && curNode.next != null) {

            prevNode = curNode;
            curNode = curNode.next;

            counter++;

        }

        //we have reached to the end of the list
        if (counter != pos) {
            
            throw new InvalidParameterException("Position doesn't yet exist for the target list.");

        }

        if (prevNode == null) {

            setHead(curNode.next);

        } else {

            //skip the node to delete
            prevNode.next = curNode.next;

        }

        //garbage collect
        curNode = null;

        return this;

    }

    public int search(int data) {

        if (isEmpty(this)) {
            
            return -1;

        }

        int pos = 0;

        Iterator<Integer> itr = this.iterator();

        while (itr.hasNext()) { 
            
            int item = itr.next();

            if (item == data) {

                return pos;

            }
            
            pos++;

        }
        
        return -1;

    }

    public boolean has (int target) {

        if (isEmpty(this)) {
            
            return false;

        }
        
        Iterator<Integer> itr = this.iterator();

        while(itr.hasNext()) {

            int data = itr.next();
            
            if (data == target) {

                return true;

            }

        }

        return false;

    }

}