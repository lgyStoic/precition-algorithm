/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Node<Item> {
    public Item i;
    public Node<Item> next;
    public Node<Item> pre;

    public Node(Item item) {
        next = null;
        pre = null;
        i = item;
    }
}

class RandomizedQueueIterator<Item> implements Iterator<Item> {
    RandomizedQueue<Item> current;

    RandomizedQueueIterator(RandomizedQueue<Item> rq) {
        current = rq;
    }

    public Item next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return current.dequeue();

    }

    public boolean hasNext() {
        if (current.size() != 0) {
            return true;
        }
        return false;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node<Item> head;
    private Node<Item> last;
    private int rqsize;

    // construct an empty randomized queue
    public RandomizedQueue() {
        head = null;
        rqsize = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return rqsize == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return rqsize;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (rqsize == 0) {
            head = new Node<Item>(item);
            last = head;
        }
        else {
            Node<Item> newlast = new Node<Item>(item);
            last.next = newlast;
            last = newlast;
        }
        rqsize++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (rqsize == 0) {
            throw new java.util.NoSuchElementException();
        }
        if (rqsize == 1) {
            rqsize--;
            Item i = head.i;
            head = null;
            last = null;
            return i;
        }
        rqsize--;
        int deindex = StdRandom.uniform(rqsize);
        if (deindex == 0) {
            Item i = head.i;
            head = head.next;
            return i;
        }
        Node<Item> iterHead = head;
        for (int i = 0; i < deindex - 1; i++) {
            iterHead = iterHead.next;
        }
        Item i = iterHead.next.i;
        iterHead.next = iterHead.next.next;
        return i;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (rqsize == 0) {
            throw new java.util.NoSuchElementException();
        }
        if (rqsize == 1) {
            return head.i;
        }
        int sampeimdex = StdRandom.uniform(rqsize);
        Node<Item> fhead = head;
        for (int i = 0; i < sampeimdex; i++) {
            fhead = fhead.next;
        }
        return fhead.i;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<Item>(this);
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
