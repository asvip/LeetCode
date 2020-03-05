package com.king.leetcode;

import java.util.LinkedList;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class MyStack {

    LinkedList<Integer> ll;

    /** Initialize your data structure here. */
    public MyStack() {
        ll = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        ll.add(x);
        int size = ll.size();
        while (size > 1){
            ll.add(ll.pop());
            size--;
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return ll.pop();
    }

    /** Get the top element. */
    public int top() {
        return ll.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return ll.isEmpty();
    }
}
