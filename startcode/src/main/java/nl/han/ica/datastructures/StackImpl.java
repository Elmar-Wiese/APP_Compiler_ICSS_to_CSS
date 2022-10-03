package nl.han.ica.datastructures;

public class StackImpl<T> implements IHANStack<T>{

    IHANLinkedList<T> stack;

    public StackImpl() {
        stack = new LinkedListImpl();
    }

    @Override
    public void push(T value) {
        stack.addFirst(value);
    }

    @Override
    public T pop() {
        T returnValue = stack.getFirst();
        stack.removeFirst();
        return returnValue;
    }

    @Override
    public T peek() {
        return stack.getFirst();
    }
}
