package DS;

public class Main {

    public static void main(String[] args) {
        
        SinglyLinkedList l = new SinglyLinkedList();

        System.out.println("Running...");
        
        //System.out.println(l.isEmpty(l));
        
        l.add(3).add(5).add(7);

        //System.out.println(l.isEmpty(l));

        //l.add(2, 2).add(1, 1).add(0, 0);

        //l.deleteAtPos(0).deleteAtPos(0).deleteAtPos(0);
        //l.deleteAtPos(2);
        //l.deleteAtPos(1);
        //l.deleteAtPos(999);

        System.out.println(l);

        System.out.println(l.reset());

        //System.out.println(l.search(7));
        //System.out.println(l.has(3));
        //System.out.println(l.has(31));
        
        

    }

}