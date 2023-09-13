package com.aldogg.lists;

import java.util.*;

public class DoubleList<T> extends AbstractList<T> implements List<T> , Queue<T>, Deque<T>, RandomAccess  {
    ArrayList<T> low = new ArrayList<>();
    int startLow = 0;
    int endLow = 0;

    ArrayList<T> high = new ArrayList<>();
    int startHigh = 0;
    int endHigh = 0;


    //empty

    //filled 1
    //startHigh = 0, endHigh = 1

    //filled high
    //startLow = 0; endLow <= end.size();

    //filled low
    //startHigh = 0; endHigh <= high.size();

    //filled both

    //half filled high

    //half filled low

    private boolean lowEmpty() {
        return startLow == 0 && endLow == 0;
    }

    private boolean highEmpty() {
        return startHigh == 0 && endHigh == 0;
    }

    private int getHighSize() {
        return endHigh - startHigh;
    }

    private int getLowSize() {
        return endLow - startLow;
    }


    //done
    @Override
    public void addFirst(T t) {
        if (!lowEmpty() || startHigh == 0) {
            low.add(t);
            endLow++;
        } else {
            high.set(startHigh - 1, t);
            startHigh--;
        }
    }

    //done
    @Override
    public void addLast(T t) {
        if (!highEmpty() || startLow == 0) {
            high.add(t);
            endHigh++;
        } else {
            low.set(startLow - 1, t);
            startLow--;
        }
    }

    //done
    @Override
    public boolean offerFirst(T t) {
        addFirst(t);
        return true;
    }

    //done
    @Override
    public boolean offerLast(T t) {
        addLast(t);
        return true;
    }

    //done
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return pollFirst();
    }

    //done
    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return pollLast();
    }

    //done
    @Override
    public T pollFirst() {
        if (isEmpty()) {
            return null;
        }
        T first;
        if (!lowEmpty() || startHigh == 0) {
            first = low.get(endLow - 1);
            endLow--;
        } else {
            first = high.get(startHigh);
            startHigh++;
        }
        return first;
    }

    //done
    @Override
    public T pollLast() {
        if (isEmpty()) {
            return null;
        }
        T last;
        if (!highEmpty() || startLow == 0) {
            last = high.get(endHigh - 1);
            endHigh--;
        } else {
            last = low.get(startLow);
            startLow++;
        }
        return last;
    }

    //done
    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return peekFirst();
    }

    //done
    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return peekLast();
    }

    //done
    @Override
    public T peekFirst() {
        if (isEmpty()) {
            return null;
        }
        T first;
        if (!lowEmpty() || startHigh == 0) {
            first = low.get(endLow - 1);
        } else {
            first = high.get(startHigh);
        }
        return first;
    }

    //done
    @Override
    public T peekLast() {
        if (isEmpty()) {
            return null;
        }
        T last;
        if (!highEmpty() || startLow == 0) {
            last = high.get(endHigh - 1);
        } else {
            last = low.get(startLow);
        }
        return last;
    }

    //done
    @Override
    public boolean removeFirstOccurrence(Object o) {
        int index = indexOf(o);
        if (index > 0) {
            remove(index);
            return true;
        }
        return false;
    }

    //done
    @Override
    public boolean removeLastOccurrence(Object o) {
        int index = lastIndexOf(o);
        if (index > 0) {
            remove(index);
            return true;
        }
        return false;
    }

    //done
    @Override
    public void push(T t) {
        addFirst(t);
    }

    //done
    @Override
    public T pop() {
        return removeFirst();
    }

    //done
    @Override
    public Iterator<T> descendingIterator() {
        ListIterator<? extends T> li = listIterator(size());
        return new Iterator<T>() {
            public boolean hasNext() {
                return li.hasPrevious();
            }

            public T next() {
                return li.previous();
            }
        };
    }

    //done
    @Override
    public int size() {
        return getHighSize() + getLowSize();
    }

    @Override
    //done
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    //done
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    //done
    @Override
    public boolean add(T t) {
        addLast(t);
        return true;
    }

    //done
    @Override
    public boolean offer(T t) {
        addLast(t);
        return true;
    }

    //done
    @Override
    public T remove() {
        return removeFirst();
    }

    //done
    @Override
    public T poll() {
        return pollFirst();
    }

    //done
    @Override
    public T element() {
        return getFirst();
    }

    //done
    @Override
    public T peek() {
        return peekFirst();
    }

    //done
    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not Implemented yet");
    }

    //done
    @Override
    public void clear() {
        high.clear();
        low.clear();
        startHigh = 0;
        endHigh = 0;
        startLow = 0;
        endLow = 0;
    }

    //done
    @Override
    public T get(int index) {
        int lowSize = getLowSize();
        int highSize = getHighSize();
        int size = lowSize + highSize;
        if (index < 0 || index  >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index < lowSize) {
            return low.get(endLow - 1 - index);
        } else {
            index = index - lowSize;
            return high.get(startHigh + index);
        }
    }

    //done
    @Override
    public T set(int index, T element) {
        int lowSize = getLowSize();
        int highSize = getHighSize();
        int size = lowSize + highSize;
        if (index < 0 || index  >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index < lowSize) {
            return low.set(endLow - 1 - index, element);
        } else {
            index = index - lowSize;
            return high.set(startHigh + index, element);
        }
    }

    @Override
    public void add(int index, T element) {
        int lowSize = getLowSize();
        int highSize = getHighSize();
        int size = lowSize + highSize;
        if (index < 0 || index  > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(element);
            return;
        }
        if (index == size) {
            addLast(element);
            return;
        }
        if (index < lowSize) {
            //TODO OPTIMIZE
            int mapIndex = endLow - 1 - index;
            low.add(mapIndex, element);
        } else {
            //TODO OPTIMIZE
            index = index - lowSize;
            int mapIndex = startHigh + index;
            high.add(mapIndex, element);
        }
    }

    @Override
    public T remove(int index) {
        int lowSize = getLowSize();
        int highSize = getHighSize();
        int size = lowSize + highSize;
        if (index < 0 || index  >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        if (index < lowSize) {
            //TODO OPTIMIZE
            return low.remove(endLow - 1 - index);
        } else {
            //TODO OPTIMIZE
            index = index - lowSize;
            return high.remove(startHigh + index);
        }
    }

    @Override
    //done
    public int indexOf(Object o) {
        int end = size();
        int start = 0;
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (get(i) == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(get(i))) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    //done
    public int lastIndexOf(Object o) {
        int end = size();
        int start = 0;
        if (o == null) {
            for (int i = end - 1; i >= start; i--) {
                if (get(i) == null) {
                    return i;
                }
            }
        } else {
            for (int i = end - 1; i >= start; i--) {
                if (o.equals(get(i))) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List l = new DoubleList();
        l.add(0, 1);
        l.add(0, 0);
        l.remove(0);
        System.out.println(l);
    }

}
