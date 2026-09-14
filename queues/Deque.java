/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private Node first;
    private Node last;
    private int size;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        size++;
        if (first == null) {
            first = new Node();
            last = first;
            first.item = item;
            first.next = null;
            first.prev = null;
        }
        else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            first.prev = null;
            oldfirst.prev = first;
        }
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        size++;
        if (first == null) {
            first = new Node();
            last = first;
            first.item = item;
            first.prev = null;
            first.next = null;
        }
        else {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            last.prev = oldlast;
            oldlast.next = last;
        }
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        size--;
        Item toreturn;
        if (first == last) {
            toreturn = first.item;
            first = last = null;
        }
        else if (first.next == last) {
            toreturn = first.item;
            first = last;
            last.prev = null;
        }
        else {
            toreturn = first.item;
            first = first.next;
            first.prev = null;
        }
        return toreturn;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        size--;
        Item togiveaway;
        if (first == last) {
            togiveaway = last.item;
            first = last = null;
        }
        else if (first.next == last) {
            togiveaway = last.item;
            last = first;
            first.next = null;
        }
        else {
            togiveaway = last.item;
            last = last.prev;
            last.next = null;
        }
        return togiveaway;
    }

    public Iterator<Item> iterator() {
        return new RandomistIterator();
    }

    private class RandomistIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {

        Deque<Integer> dq = new Deque<Integer>();
        try {
            dq.addLast(4);
            dq.addFirst(5);
            dq.addLast(6);
            int valuea = dq.removeFirst();
            int valueb = dq.removeLast();
            int size = dq.size();
            StdOut.println(valuea);
            StdOut.println(valueb);
            StdOut.println(size);
            for (Integer i : dq) {
                StdOut.println(i);
            }
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }

    }
}
