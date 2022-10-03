package nl.han.ica.datastructures;

public class QueueImpl<T> implements IHANQueue<T>{
    IHANLinkedList<T> queue;

    public QueueImpl() {
        queue = new LinkedListImpl();
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public boolean isEmpty() {
        return queue.getSize() == 0;
    }

    @Override
    public void enqueue(T value) {
        queue.insert(queue.getSize(), value);
    }

    @Override
    public T dequeue() {
        T returnValue = queue.getFirst();
        queue.removeFirst();
        return returnValue;
    }

    @Override
    public T peek() {
        return queue.getFirst();
    }

    @Override
    public int getSize() {
        return 0;
    }


    @Override
    public String toString() {
        return "QueueImpl{" +
                "queue=" + queue.toString() +
                '}';
    }
}
