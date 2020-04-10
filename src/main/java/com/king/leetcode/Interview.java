package com.king.leetcode;


import java.util.*;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class Interview {

    /**
     * 面试题 01.06. 字符串压缩
     * @param S
     * @return
     */
    public String compressString(String S) {
       int length = S.length();
       if(length > 2){
           StringBuilder sb = new StringBuilder();
           char ch = S.charAt(0);
           int n = 1;
           for(int i = 1;i < length; i++){
               if(ch == S.charAt(i)){
                    n++;
               }else{
                   sb.append(ch).append(n);
                   ch = S.charAt(i);
                   n = 1;
               }
           }
           sb.append(ch).append(n);
           if(sb.length() < length){
               return sb.toString();
           }
       }

       return S;
    }


    /**
     * 面试题 10.01. 合并排序的数组
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int[] A, int m, int[] B, int n) {
        int size = m + n;

        for(int i = m; i < size; i++){
            A[i] = B[i - m];
        }

        for(int i = 0; i < size - 1; i++){
            for(int j = 0; j < size - i - 1; j++){
                if(A[j] > A[j + 1]){
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
        }

    }


    /**
     * 面试题 17.16. 按摩师
     * @param nums
     * @return
     */
    public int massage(int[] nums) {
        int length = nums.length;
        if(length > 1){
            int[] dp = new int[length];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0],nums[1]);
            for(int i = 2; i < length; i++){
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
            return dp[length - 1];
        }

        if(length == 1){
            return nums[0];
        }

        return 0;
    }

    /**
     * 面试题40. 最小的k个数
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        int[] mins = new int[k];
        for(int i = 0; i < k; i++){
            mins[i] = arr[i];
        }
        return mins;
    }

    /**
     * 面试题57 - II. 和为s的连续正数序列
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        if(target > 2){
            int sn = target * 2;
            int n = target;
            int m = target / 2;
            List<int[]> list = new ArrayList<>();

            while (n > 1){
                if (sn % n == 0){
                    int k = sn / n;
                    if(k > 1 && k <= n){
                        int l = 1;
                        while (l <= m){
                            if(sn == k * (l + l + k - 1)){//等差数列变形公式
                                int[] nums = new int[k];
                                for(int i = 0; i < k; i++){
                                    nums[i] = l + i;
                                }
                                list.add(nums);
                                break;
                            }
                            l++;
                        }

                    }
                }
                n--;
            }

            int size = list.size();
            int [][] ns = new int[size][];
            for(int i = 0; i < size; i++){
                ns[i] = list.get(size - 1 - i);
            }

            return ns;
        }

        return new int[0][];
    }


    /**
     * 面试题62. 圆圈中最后剩下的数字
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        int s = 0;
        for(int i = 2; i <= n; i++){
            s = (m + s) % i;
        }
        return s;
    }

}
