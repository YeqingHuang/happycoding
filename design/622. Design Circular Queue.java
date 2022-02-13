class MyCircularQueue {
    // we can implement a linked list by our self
    // by comparing the size with capacity k, we know isEmpty() and isFull()
    // by checking the head and the tail, we know Front() and Rear()
    class Node {
        Node next;
        int value;
        
        public Node(int value) {
            this.value = value;
        }
    }
    
    private int size, capacity;
    private Node head, tail; // initially, they are all null
    
    public MyCircularQueue(int k) {
        this.size = 0;
        this.capacity = k;
    }
    
    public boolean enQueue(int value) {
        if (isFull()) return false;
        
        Node newNode = new Node(value);
        if (size == 0) {
            head = newNode;
            tail = newNode;
            head.next = tail;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }
    
    public boolean deQueue() {
        if (isEmpty()) return false;
        
        head = head.next;
        size--;
        return true;
    }
    
    public int Front() {
        return isEmpty() ? -1: head.value;
    }
    
    public int Rear() {
        return isEmpty() ? -1: tail.value;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == capacity;
    }
}