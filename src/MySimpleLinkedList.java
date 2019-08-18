import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * My simple linked list variant for a training
 */
public class MySimpleLinkedList<E> {

    // =================================================================================================================
    // Fields
    // =================================================================================================================

    private int size;
    private Entry<E> firstEntry;
    private Entry<E> lastEntry;


    // =================================================================================================================
    // Constructor
    // =================================================================================================================

    MySimpleLinkedList() {
        this.size = 0;
        this.firstEntry = null;
        this.lastEntry = null;
    }


    // =================================================================================================================
    // Public methods
    // =================================================================================================================

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Nullable
    public E getFirst() {
        return firstEntry == null ? null : firstEntry.value;
    }

    @Nullable
    public E getLast() {
        return lastEntry == null ? null : lastEntry.value;
    }

    public void add(@Nullable final E e) {
        if (lastEntry == null) {
            lastEntry = new Entry(e);
            firstEntry = lastEntry;

        } else {
            Entry newEntry = new Entry(e, lastEntry, firstEntry);
            lastEntry.next = newEntry;
            firstEntry.prev = newEntry;
            lastEntry = newEntry;
        }

        size++;
    }

    public void removeEntry(@Nullable final E entryToDelete) {
        if (lastEntry == null || firstEntry == null) {
            return;
        }

        for (Entry e = firstEntry; e != lastEntry ; e = e.next) {
            if (entryToDelete == null) {
                if (e.value == null) {
                    removeEntry(e);
                    return;
                }

                continue;
            }

            if (entryToDelete.equals(e.value)) {
                removeEntry(e);
                return;
            }
        }

        System.out.println("No such element: " + entryToDelete.toString());
    }

    public void removeEntry(final int index) throws IndexOutOfBoundsException {
        if (lastEntry == null || firstEntry == null) {
            return;
        }

        if (size == 0 || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (size == 1) {
            lastEntry = null;
            firstEntry = null;
            size = 0;
            return;
        }

        if (index == 0) {
            firstEntry = firstEntry.next;
            lastEntry.next = firstEntry;
            size--;
            return;
        }

        if (index == size - 1) {
            lastEntry = lastEntry.prev;
            firstEntry.prev = lastEntry;
            size--;
            return;
        }

        Entry entryAtPosition = firstEntry;
        for (int i = 0; i < index; i++) {
            entryAtPosition = entryAtPosition.next;
            removeEntry(entryAtPosition);
        }
    }

    @Override
    public String toString() {
        if (size == 0 || lastEntry == null || firstEntry == null) {
            return "Empty list";
        }

        Entry entryAtPosition = firstEntry;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(entryAtPosition.value.toString());
            sb.append(",");
            entryAtPosition = entryAtPosition.next;
        }

        return sb.toString();
    }


    // =================================================================================================================
    // Private methods
    // =================================================================================================================

    private void removeEntry(@NotNull Entry e) {
        e.next.prev = e.prev;
        e.prev.next = e.next;
        e = null;
        size--;
    }


    // =================================================================================================================
    // Inner classes
    // =================================================================================================================

    private static class Entry<E> {
        E value;
        Entry<E> next;
        Entry<E> prev;

        Entry (@Nullable final E value) {
            this.value = value;
            this.prev = this;
            this.next = this;
        }

        Entry (@Nullable final E value, @NotNull final Entry prev, @NotNull final Entry next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
