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
    public void swap() {
        if (head == null || head.getNext() == null) {
            return;
        }

        int total = size;

        // original and sorted Node order
        Node<E>[] originalNodes = (Node<E>[]) new Node[total];
        Node<E>[] sortedNodes = (Node<E>[]) new Node[total];

        // Store nodes in arrays for indexing and sorting
        Node<E> current = head;
        for (int i = 0; i < total; i++) {
            originalNodes[i] = current;
            sortedNodes[i] = current;
            current = current.getNext();
        }

        Arrays.sort(sortedNodes, (a, b) -> a.getElement().compareTo(b.getElement()));

        // map node pairs
        Map<Node<E>, Node<E>> replacementMap = new HashMap<>();
        int left = 0;
        int right = total - 1;

        while (left < right) {
            Node<E> smallestNode = sortedNodes[left];
            Node<E> largestNode = sortedNodes[right];

            replacementMap.put(smallestNode, largestNode);
            replacementMap.put(largestNode, smallestNode);

            left++;
            right--;
        }

        // array for new order
        Node<E>[] resultNodes = (Node<E>[]) new Node[total];
        for (int i = 0; i < total; i++) {
            Node<E> orig = originalNodes[i];
            resultNodes[i] = replacementMap.getOrDefault(orig, orig);
        }

        // relink nodes
        head = resultNodes[0];
        current = head;
        for (int i = 1; i < total; i++) {
            current.setNext(resultNodes[i]);
            current = current.getNext();
        }
        current.setNext(null);
        tail = current; 
    }
   
}

