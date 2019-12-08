import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

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

public class Deque<Item> implements Iterable<Item> {
    private int dequesize;
    private Node<Item> head;
    private Node<Item> last;

    // construct an empty deque
    public Deque() {
        dequesize = 0;
        head = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return dequesize == 0;
    }

    // return the number of items on the deque
    public int size() {
        return dequesize;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (head == null) {
            head = new Node<Item>(item);
            last = head;
        }
        else {
            Node<Item> newHead = new Node<Item>(item);
            newHead.next = head;
            head.pre = newHead;
            head = newHead;
        }
        dequesize++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (dequesize == 0) {
            head = new Node<Item>(item);
            last = head;
        }
        else {
            Node<Item> newLast = new Node<Item>(item);
            last.next = newLast;
            newLast.pre = last;
            last = newLast;
        }
        dequesize++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (dequesize == 0) {
            throw new java.util.NoSuchElementException();
        }
        if (dequesize == 1) {
            dequesize--;
            last = null;
            Item i = head.i;
            head = null;
            return i;
        }
        dequesize--;
        Item i = head.i;
        head = head.next;
        head.pre = null;
        return i;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (dequesize == 0) {
            throw new java.util.NoSuchElementException();
        }
        if (dequesize == 1) {
            dequesize--;
            head = null;
            Item i = last.i;
            last = null;
            return i;
        }
        dequesize--;
        Item i = last.i;
        last = last.pre;
        last.next = null;
        return i;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>(head);
    }

    // unit testing (required)
    public static void main(String[] args) {
        // int num = Integer.parseInt(args[0]);
        String s = StdIn.readString();
        StdOut.println(s);
    }
}

class DequeIterator<Item> implements Iterator<Item> {
    Node<Item> current;

    public DequeIterator(Node<Item> head) {
        current = head;
    }

    public boolean hasNext() {
        return current != null;
    }

    public Item next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Item i = current.i;
        current = current.next;
        return i;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}



