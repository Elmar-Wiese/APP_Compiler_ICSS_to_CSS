package nl.han.ica.datastructures;

public class LinkedListImpl<T> implements IHANLinkedList<T> {
    int length = 0;
    ListNode<T> first;
    ListNode<T> last;

    LinkedListImpl () {
    }

    LinkedListImpl (ListNode<T> first, ListNode<T> last, int length) {
        this.length = length;
        this.first = first;
        this.last = last;
    }

//    public void add (T value) {
//        length++;
//        if (next == null) {
//            next = new ListNode<T>(value);
//        } else {
//            next.add(value);
//        }
//    }

//    public void add(int index, T value) {
//        length++;
//        if(index == 0) {
//            ListNode<T> replacement = new ListNode<T>(value, next);
//            next = replacement;
//        } else {
//            checkOutOfBounds(index);
//            next.add(index, value);
//        }
//
//    }

    @Override
    public void addFirst(T value) {
        if (first == null) {
            first = new ListNode<>(value);
            last = first;
        } else {
            first = new ListNode<>(value, first);
        }
    }



    @Override
    public void clear() {
        length = 0;
        first = null;
        last = null;
    }

    @Override
    public void insert(int index, T value) {
        checkOutOfBounds(index);
        if (index == 0 || first == null) {
            addFirst(value);
        }

        for (ListNode<T> current = first; current != null; current = current.getNext()) {
            index--;
            if (index == 0) {
                insertNode(current, current.getNext(), value);
            }
        }
    }

    private void insertNode(ListNode<T> prev ,ListNode<T> next, T value) {
        if (next == null) {
            ListNode<T> inserted = new ListNode<>(value);
            last = inserted;
            prev.setNext(inserted);
        } else {
            ListNode<T> inserted = new ListNode<>(value, next);
            prev.setNext(inserted);
        }
    }

    //        if (first == null) {
    //            first = new LLItem<>(value, null);
    //        } else {
    //            for(LLItem<T> current = first; current != null; current = current.next) {
    //                if (current.next == null) {
    //                    current.next = new LLItem<>(value, null);
    //                    return;
    //                }
    //            }
    //        }

//    public void add(int index, T value) {
//        index--;
//        if(index == 0) {
//            ListNode<T> replacement = new ListNode<T>(value, next);
//            next = replacement;
//        } else {
//            next.add(index, value);
//        }
//    }

    @Override
    public void delete(int pos) {
        checkOutOfBounds(pos);
        if (pos == 0) {
            removeFirst();
        } else {
            for (ListNode<T> current = first; current != null; current = current.getNext()) {
                pos--;
                if (pos == 0) {
                    deleteNode(current, current.getNext());
                }
            }
        }

    }

    private void deleteNode(ListNode<T> prev ,ListNode<T> next) {
            prev.setNext(next.getNext());
    }

    //
//    public void remove(int index) {
//        checkOutOfBounds(index);
//        if (index == 0) {
//            next = next.getLink();
//        } else {
//            next.remove(index);
//        }
//    }

    @Override
    public T get(int pos) {
        checkOutOfBounds(pos);
        T returnValue = null;

        for (ListNode<T> current = first; current != null; current.getNext()) {
            if(pos == 0) {
                returnValue = current.getValue();
            }
            pos--;
        }

        return returnValue;
    }

//    public T get(int index) {
//        checkOutOfBounds(index);
//        return next.get(index);
//    }

    @Override
    public void removeFirst() {
        if (first != null) {
            first = first.getNext();
        }
    }

    @Override
    public T getFirst() {
        return first.getValue();
    }

    @Override
    public int getSize() {
        return 0;
    }

    private void checkOutOfBounds(int index) {
        if (index < 0 || index > length - 1) {
            throw new IndexOutOfBoundsException();
        }
    }



    @Override
    public String toString() {
        String linker = first != null ? first.toString() : "";
        return "LinkedList{" +
                linker +
                '}';
    }
}
