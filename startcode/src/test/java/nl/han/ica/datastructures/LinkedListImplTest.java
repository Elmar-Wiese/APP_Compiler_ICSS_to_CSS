package nl.han.ica.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListImplTest {
    LinkedListImpl<Integer> sut;

    @BeforeEach
    void setUp() {
        sut = new LinkedListImpl();
    }

    @Test
    void addFirstEmptyList() {
        ListNode<Integer> test = new ListNode<Integer>(22);

        sut.addFirst(22);

        assertEquals(new LinkedListImpl<Integer>(test, test, 1), sut);
    }

    @Test
    void clear() {
    }

    @Test
    void insert() {
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
    }

    @Test
    void removeFirst() {
    }

    @Test
    void getFirst() {
    }

    @Test
    void getSize() {
    }

    @Test
    void testToString() {
    }
}