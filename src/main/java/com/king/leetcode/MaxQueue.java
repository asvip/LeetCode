package com.king.leetcode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 面试题59 - II. 队列的最大值
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class MaxQueue {

    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MaxQueue() {
        queue1 = new LinkedList<>();
        queue2 = new PriorityQueue<>(comparator);
    }

    public int max_value() {
        if(!queue2.isEmpty()){
            return queue2.peek();
        }
        return -1;
    }

    public void push_back(int value) {
        queue1.add(value);
        queue2.add(value);
    }

    public int pop_front() {
        if(!queue1.isEmpty()){
            int n = queue1.poll();
            queue2.remove(n);
            return n;
        }
        return -1;
    }

    static Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer e1, Integer e2) {
            return e2 - e1;
        }
    };
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */