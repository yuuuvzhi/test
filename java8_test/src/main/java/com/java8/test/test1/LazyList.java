package com.java8.test.test1;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @description:
 * @author: zhiyun.yu
 * @create: 2019-06-26 14:40
 **/
public class LazyList<T> implements MyList<T> {

    final T head;

    final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail.get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        return isEmpty() ? this : p.test(head()) ? new LazyList<>(head(), () -> tail().filter(p)) : tail().filter(p);
    }

}
