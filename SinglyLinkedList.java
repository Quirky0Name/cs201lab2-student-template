import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){

        // get ArrayList of all elements
        List<Node<E>> original = new ArrayList<>();
        Map elementNode = new TreeMap<>();
        Node current = head;
        while (current != null) {
            original.add(current);
            elementNode.put(current.getElement(), current);
            current = current.getNext();
        }

        List<Map.Entry<E, Node<E>>> entries = new ArrayList<>(elementNode.entrySet());
// System.out.println(entries);
        
        int total = entries.size();

        // array to sort new node order
        Node[] sortedNodes = new Node[total];
        int left = 0;
        int right = total - 1;
        while (left < right) {
            Node small = entries.get(left).getValue();
            Node big = entries.get(right).getValue();
            int smallIndex = original.indexOf(small);
            int bigIndex = original.indexOf(big);

            sortedNodes[smallIndex] = big;
            sortedNodes[bigIndex] = small;
            left++;
            right--;
        }
        if (left == right) { // handle middle
            Node small = entries.get(left).getValue();
            int smallIndex = original.indexOf(small);
            sortedNodes[smallIndex] = small;
        }
        
        // build new links from order
        head = sortedNodes[0];
        current = head;
        for (int i = 1; i < total; i++) {
            current.setNext(sortedNodes[i]);
            current = current.getNext();
        }
        current.setNext(null);

    }
   
}

