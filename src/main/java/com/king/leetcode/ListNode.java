package com.king.leetcode;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        if (next != null){
            return " -> " + val + " -> " + next;
        }
        return val + "" ;
    }
}
