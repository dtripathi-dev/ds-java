package DS;

import java.security.InvalidParameterException;
import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T>{
    
    public Node<T> head;

    private static class Node<T> {

        private T data; 
        private Node<T> next = null;

        @Override
        public String toString () {

            return String.valueOf(data);
    
        }

        public Node(T data) {
            
            this.data = data;
            this.next = null;
        
        }

        public Node(T data, Node<T> next) {
            
            this.data = data;
            this.next = next;
        
        }

    }

    @Override
    public Iterator<T> iterator() {
        
        return new Iterator<T>() {
            
            private Node<T> n = head;

            @Override
            public boolean hasNext() {
                
                return n != null;
            }


            @Override
            public T next() {

                T data = n.data;
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

        //use iterator
        Iterator<T> itr = this.iterator();

        while(itr.hasNext()) {
            
            sb.append(itr.next());
            
            if (itr.hasNext()) {
                
                sb.append(",");

            }

        }

        sb.append("]");

        return sb.toString();

    }

    public Node<T> getHead() {

        return head;

    }

    private SinglyLinkedList<T> setHead(Node<T> n) {

        head = n;
        return this;

    }
    
    private Node<T> getNode(T data) {

        return new Node<T>(data);

    }

    public boolean isEmpty(SinglyLinkedList<T> list) {

        return list.getHead() == null;

    }

    public SinglyLinkedList<T> reset() {
        
        if (!isEmpty(this)) {

            Node<T> curNode = getHead();
            Node<T> temp = null;

            while (curNode != null) {

                temp = curNode;
                curNode = curNode.next;

                System.out.println("Deleting " + temp.data);

                //let the prev node be garbage collected
                temp.next = null;
                temp = null;

            }

            head = null;

        }

        return this;

    }
    
    public SinglyLinkedList<T> addToEmptyList(T data) {

        if (head != null) {

            throw new InvalidParameterException("List is not empty.");

        }
        
        Node<T> n = getNode(data);
        setHead(n);

        return this;

    }

    public SinglyLinkedList<T> add(T data) {
        
        Node<T> n = new Node<>(data);
        Node<T> currNode = getHead();

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

    public SinglyLinkedList<T> add(T data, int pos) {
        
        if (pos < 0) {

            throw new InvalidParameterException("Position cannot be -ve.");

        }

        if (head == null) {

            if (pos != 0) {

                throw new InvalidParameterException("Position doesn't yet exist for the target list.");

            }

            return addToEmptyList(data);

        }

        Node<T> currNode = getHead();
        Node<T> prevNode = null;

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

        Node<T> n = getNode(data);
        
        if(prevNode == null) {
            setHead(n);
        } else {
            prevNode.next = n;
        }

        n.next = currNode;
        
        return this;

    }

    public SinglyLinkedList<T> deleteAtPos(int pos) {

        if (pos < 0) {

            throw new InvalidParameterException("Position cannot be -ve.");

        }

        if (isEmpty(this)) {

            throw new IllegalStateException("List is already empty.");

        }

        int counter = 0;

        Node<T> curNode = getHead();
        Node<T> prevNode = null;

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

    public int search(T target) {

        if (isEmpty(this)) {
            
            return -1;

        }

        int pos = 0;

        Iterator<T> itr = this.iterator();

        while (itr.hasNext()) { 
            
            T item = itr.next();

            if (item == target) {

                return pos;

            }
            
            pos++;

        }
        
        return -1;

    }

    public boolean has (T target) {

        if (isEmpty(this)) {
            
            return false;

        }
        
        Iterator<T> itr = this.iterator();

        while(itr.hasNext()) {

            T data = itr.next();
            
            if (data == target) {

                return true;

            }

        }

        return false;

    }

}