/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] s;

    public RandomizedQueue() {
        s = (Item[]) new Object[1];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == s.length) {
            resize(2 * s.length);
        }
        s[size++] = item;

    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int random = StdRandom.uniformInt(size);
        return s[random];
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randint = StdRandom.uniformInt(size);
        Item temp = s[randint];
        s[randint] = s[size - 1];
        s[size - 1] = null;
        size--;
        if (size > 0 && size <= s.length / 4) {
            resize(s.length / 2);
        }
        return temp;
    }

    public Iterator<Item> iterator() {
        return new IndependentIterator();
    }

    private class IndependentIterator implements Iterator<Item> {
        int[] array = StdRandom.permutation(size);
        int i = 0;

        public boolean hasNext() {
            return i < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (i < size) {
                return s[array[i++]];
            }
            else {
                throw new NoSuchElementException();
            }
        }

    }


    public static void main(String[] args) {
        try {
            RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
            rq.enqueue(1);
            rq.enqueue(2);
            rq.enqueue(3);
            rq.enqueue(4);
            rq.enqueue(5);
            rq.enqueue(6);
            StdOut.println(rq.size());
            StdOut.println(rq.isEmpty());
            Integer spitem = rq.dequeue();
            StdOut.println(spitem);
            Integer regitem = rq.sample();
            StdOut.println(regitem);
            for (Integer inty : rq) {
                StdOut.println(inty);
            }
            for (Integer intx : rq) {
                StdOut.println(intx);
            }
        }
        catch (IllegalArgumentException | NoSuchElementException |
               UnsupportedOperationException e) {
            e.printStackTrace();
        }
    }
}
