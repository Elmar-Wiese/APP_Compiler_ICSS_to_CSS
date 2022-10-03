package nl.han.ica.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        assertEquals(new LinkedListImpl<Integer>(test, test, 1).toString(), sut.toString());
        assertEquals(1, sut.length);
    }

    @Test
    void addFirstNonEmptyList() {
        ListNode<Integer> test = new ListNode<>(33);
        ListNode<Integer> test2 = new ListNode<>(44, test);

        sut.addFirst(33);

        sut.addFirst(44);

        assertEquals(new LinkedListImpl<Integer>(test2, test, 2).toString(), sut.toString());
        assertEquals(2, sut.length);
    }

    @Test
    void insertPos0() {
        ListNode<Integer> test = new ListNode<Integer>(22);

        sut.insert(0, 22);

        assertEquals(new LinkedListImpl<Integer>(test, test, 1).toString(), sut.toString());
        assertEquals(1, sut.length);
    }

    @Test
    void insertPos1() {
        ListNode<Integer> test = new ListNode<>(33);
        ListNode<Integer> test2 = new ListNode<>(22, test);
        ListNode<Integer> test3 = new ListNode<>(44, test2);

        sut.addFirst(33);
        sut.addFirst(44);

        sut.insert(1, 22);

        assertEquals(new LinkedListImpl<Integer>(test3, test, 3).toString(), sut.toString());
        assertEquals(3, sut.length);
    }

    @Test
    void insertPos1Last() {
        ListNode<Integer> test = new ListNode<>(33);
        ListNode<Integer> test2 = new ListNode<>(44, test);
        sut.addFirst(44);

        sut.insert(1, 33);

        assertEquals(new LinkedListImpl<Integer>(test2, test, 2).toString(), sut.toString());
        assertEquals(2, sut.length);
    }

    @Test
    void checkOutOfBoundsUnder0() {
        sut.addFirst(44);

        assertThrows(IndexOutOfBoundsException.class, () -> sut.insert(-1, 44));
    }

    @Test
    void checkOutOfBoundsBiggerThanSizeInsert() {
        sut.addFirst(22);
        sut.addFirst(23);

        assertThrows(IndexOutOfBoundsException.class, () -> sut.insert(3, 44));
    }

    @Test
    void checkOutOfBoundsSizeDelete() {
        sut.addFirst(22);
        sut.addFirst(23);

        assertThrows(IndexOutOfBoundsException.class, () -> sut.delete(2));
    }

    @Test
    void delete0() {
        ListNode<Integer> test = new ListNode<>(33);
        sut.addFirst(33);

        sut.delete(0);

        assertEquals(new LinkedListImpl<Integer>().toString(), sut.toString());
        assertEquals(0, sut.length);
    }

    @Test
    void delete1() {
        ListNode<Integer> test = new ListNode<>(33);
        ListNode<Integer> test2 = new ListNode<>(55, test);

        sut.addFirst(33);
        sut.addFirst(44);
        sut.addFirst(55);

        sut.delete(1);

        assertEquals(new LinkedListImpl<Integer>(test2, test, 2).toString(), sut.toString());
        assertEquals(2, sut.length);
    }

    @Test
    void delete1last() {
        ListNode<Integer> test = new ListNode<>(44);
        ListNode<Integer> test2 = new ListNode<>(55, test);

        sut.addFirst(33);
        sut.addFirst(44);
        sut.addFirst(55);

        sut.delete(2);

        assertEquals(new LinkedListImpl<Integer>(test2, test, 2).toString(), sut.toString());
        assertEquals(2, sut.length);
    }

    @Test
    void get() {
        Integer testvalue = 10;

        sut.addFirst(10);
        sut.addFirst(1337);

        assertEquals(testvalue, sut.get(1));
    }

//    @Test
//    void removeFirst() {
//    }

    @Test
    void getFirst() {
        Integer testvalue = 1337;

        sut.addFirst(10);
        sut.addFirst(1337);

        assertEquals(testvalue, sut.getFirst());
    }


}