package com.king.leetcode;

import java.util.*;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class LeetCode {

    /**
     * 1. 两数之和
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        for(int i = 0; i < length; i++){
            for(int j = i + 1; j < length; j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    /**
     * 2. 两数相加
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode listNode = new ListNode(0);
        addTwoNumbers(listNode,l1,l2,0);
        return listNode;
    }

    private void addTwoNumbers(ListNode listNode,ListNode l1, ListNode l2,int val) {

        if(l1 != null){
            val += l1.val;
        }
        if(l2 != null){
            val += l2.val;
        }

        listNode.val = val % 10;

        val /= 10;

        ListNode next1 = null;
        if (l1 != null) {
            next1 = l1.next;
        }

        ListNode next2 = null;
        if (l2 != null) {
            next2 = l2.next;
        }

        if (next1 != null || next2 != null) {
            listNode.next = new ListNode(val);
            addTwoNumbers(listNode.next, next1, next2, val);
        } else if (val > 0) {
            listNode.next = new ListNode(val);
        }

    }

    /**
     * 3. 无重复字符的最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();

        Map<Character,Integer> map = new HashMap<>();
        int max = 0;
        int j = 0;
        for(int i = 0; i < length; i++){
            char ch = s.charAt(i);
            if(map.containsKey(ch)){
                j = Math.max(j,map.get(ch));
            }
            max = Math.max(max, i - j + 1);
            map.put(ch,i + 1);
        }

        return max;
    }

    /**
     * 4. 寻找两个有序数组的中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0){
            return findMedianSortedArrays(nums2);
        }

        if(nums2 == null || nums2.length == 0){
            return findMedianSortedArrays(nums1);
        }

        int length1 = nums1.length;
        int length2 = nums2.length;
        int length = length1 + length2;
        int [] nums = new int[length];
        boolean bool = false;
        for(int i = 0, j = 0, k = 0; i < length; i++){
            if(bool){
                nums[i] = nums1[j];
                j++;
            }else{
                if(j < length1 && nums1[j] < nums2[k]){
                    nums[i] = nums1[j];
                    j++;
                }else{
                    nums[i] = nums2[k];
                    if(k >= length2 - 1){
                        bool = true;
                    }else{
                        k++;
                    }
                }
            }
        }

        return findMedianSortedArrays(nums);

    }

    private double findMedianSortedArrays(int[] nums){
        int length = nums.length;
        if(length == 1){
            return nums[0];
        }

        if(length % 2 == 0){
            int i = length / 2;
            return (nums[i] + nums[i - 1]) / 2.0f;
        }
        return nums[length / 2];
    }

    /**
     * 5. 最长回文子串
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int length = s.length();
        if(length <= 1){
            return s;
        }

        String str = "";
        for(int i = 0; i < length; i++){
            char chl = s.charAt(i);
            String sl = longestPalindrome(chl,s.substring(i));

            if(sl.length() > str.length()){
                str = sl;
            }
        }

        return str;
    }

    private String longestPalindrome(char ch, String s){

        int length = s.length();
        if(length <= 1){
            return s;
        }

        if(isPalindrome(s)){
            return s;
        }

        int index = s.lastIndexOf(ch);
        if(index == length - 1){
            s = s.substring(0,index);
        }else{
            s = s.substring(0,index + 1);
        }
        if(index > 0){
            return longestPalindrome(ch,s);
        }

        return String.valueOf(ch);
    }

    private boolean isPalindrome(String s){
        int length = s.length();
        if(length <= 1){
            return true;
        }
        int size = length / 2;
        for(int i = 0; i < size; i++){
            if(s.charAt(i) != s.charAt(length - i - 1)){
                return false;
            }
        }
        return true;
    }


    /**
     * 6. Z 字形变换
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if(numRows < 2){
            return s;
        }
        int length = s.length();
        int cols =  length - (length / numRows) + numRows;
        char[][] ss = new char[cols][numRows];
        boolean isBool = true;
        for(int i = 0, j = 0, k = 0; i < length; i++){
            char c = s.charAt(i);

            if(isBool){
                ss[j][k] = c;
                if(k == numRows - 1){
                    isBool = false;
                }else{
                    k++;
                }
            }else{
                k--;
                j++;
                ss[j][k] = c;
                if(k == 0){
                    isBool = true;
                    k++;
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < cols; j++){
                char ch = ss[j][i];
                if(ch != '\u0000'){
                    sb.append(ss[j][i]);
                }
            }
        }

        return sb.toString();
    }

    /**
     * 7. 整数反转
     * @param x
     * @return
     */
    public int reverse(int x) {
        long reverse = 0;
        while(x != 0){
            int temp = x % 10;
            x /= 10;
            reverse  = reverse * 10 + temp;

            if(reverse > Integer.MAX_VALUE || reverse < Integer.MIN_VALUE) {
                return 0;
            }
        }

        return (int)reverse;
    }

    /**
     * 8. 字符串转换整数 (atoi)
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        str = str.trim();
        int length = str.length();

        int n = 0;
        int j = length;
        boolean isMinus = false;
        for(int i = 0; i < length; i++){
            char ch = str.charAt(i);
            if(i == 0){
                if(ch == '0'){
                    n = 1;
                }else if(ch == '+'){
                    n = 1;
                }else if(ch == '-'){
                    n = 1;
                    isMinus = true;
                } else if(ch < 48 || ch > 57){
                    return 0;
                }
            }else if(n == i && ch == '0'){
                n++;
            }else if(ch < 48 || ch > 57){
                j = i;
                break;
            }
        }
        int size = j - n;

        if(size > 0){
            if(size > 10){
                if(isMinus){
                    return Integer.MIN_VALUE;
                }
                return Integer.MAX_VALUE;
            }

            str = str.substring(n, j);
            long l = Long.parseLong(str);

            if(isMinus){
                l = -l;
                if(l < Integer.MIN_VALUE){
                    return Integer.MIN_VALUE;
                }
                return (int)l;
            }

            if(l > Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }

            return Integer.parseInt(str);
        }

        return 0;
    }

    /**
     * 9. 回文数
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if(x < 0) {
            return false;
        }
        int value = x;
        long reverse = 0;
        while( x != 0){
            int temp =  x % 10;
            x /= 10;
            reverse = reverse * 10 + temp;
        }

        return value == reverse;
    }

    /**
     * 10. 正则表达式匹配
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        return false;
    }

    /**
     * 12. 整数转罗马数字
     * @param num
     * @return
     */
    public String intToRoman(int num) {

        StringBuilder sb = new StringBuilder();
        while (num > 0){
            if(num >= 1000){
                num -= 1000;
                sb.append('M');
            }else if(num >= 900){
                num -= 900;
                sb.append("CM");
            }else if(num >= 500){
                num -= 500;
                sb.append('D');
            }else if(num >= 400){
                num -= 400;
                sb.append("CD");
            }else if(num >= 100){
                num -= 100;
                sb.append('C');
            }else if(num >= 90){
                num -= 90;
                sb.append("XC");
            }else if(num >= 50){
                num -= 50;
                sb.append('L');
            }else if(num >= 40){
                num -= 40;
                sb.append("XL");
            }else if(num >= 10){
                num -= 10;
                sb.append('X');
            }else if(num >= 9){
                num -= 9;
                sb.append("IX");
            }else if(num >= 5){
                num -= 5;
                sb.append('V');
            }else if(num >= 4){
                num -= 4;
                sb.append("IV");
            }else if(num >= 1){
                num -= 1;
                sb.append('I');
            }
        }

        return sb.toString();
    }

    /**
     * 13. 罗马数字转整数
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        Map<Character,Integer> map = new HashMap<>(7);
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int sum = 0;
        int preValue = map.get(s.charAt(0));
        for(int i = 1; i < s.length(); i++){
            int value = map.get(s.charAt(i));
            if(preValue >= value){
                sum += preValue;
            }else{
                sum -= preValue;
            }
            preValue = value;
        }

        sum += preValue;

        return sum;
    }

    /**
     * 14. 最长公前缀
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        String prefix = "";
        if(strs != null && strs.length > 0){
            int minLength = strs[0].length();
            int size = strs.length;
            for(int i = 1;i < size; i++){
                int len = strs[i].length();
                if(len < minLength){
                    minLength = len;
                }
            }

            for(int i = 1; i <= minLength; i++){
                String temp = strs[0].substring(0,i);
                for(int j = 1; j < size; j++){
                    if(!strs[j].startsWith(temp)){
                        return prefix;
                    }
                }
                prefix = temp;

            }
        }

        return prefix;
    }


    /**
     * 15. 三数之和
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        int length = nums.length;
        if(length >= 3){
            Arrays.sort(nums);
            if(nums[length - 1] >= 0){
                int i = 0;
                while (i < length - 2){
                    int n = nums[i];
                    if(n > 0){
                        break;
                    }

                    int left = i + 1;
                    int right = length - 1;
                    while (left < right){
                        if( n > 0 && nums[right] > 0){
                            break;
                        }

                        if(n < 0 && nums[right] < 0){
                            break;
                        }

                        int sum = n + nums[left] + nums[right];
                        if(sum == 0){
                            List<Integer> item = new ArrayList<>();
                            item.add(n);
                            item.add(nums[left]);
                            item.add(nums[right]);
                            list.add(item);
                        }

                        if(sum < 0){
                            //左边指针向右移
                            if(left < right - 1){
                                while (nums[left] == nums[++left]){
                                    //跳过重复值
                                    if(left == right){
                                        break;
                                    }
                                }
                            }else{
                                break;
                            }
                        }else{
                            if(right > left + 1){
                                //右边指针向左移
                                while (nums[right] == nums[--right]){
                                    //跳过重复值
                                    if(right == left){
                                        break;
                                    }
                                }
                            }else{
                                break;
                            }

                        }
                    }

                    if(i < length - 2){
                        while (n == nums[++i]){
                            //跳过重复值
                            if(i == length - 2){
                                break;
                            }
                        }
                    }else{
                        break;
                    }

                }
            }
        }

        return list;
    }


    /**
     * 16. 最接近的三数之和
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int length = nums.length;
        int oldAbs = 0;
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < length - 2; i++) {
            int n = nums[i];
            int left = i + 1;
            int right = length - 1;
            while (left < right) {
                int sum = n + nums[left] + nums[right];
                int diff = target - sum;
                int abs = Math.abs(diff);

                if (oldAbs == 0 || oldAbs > abs) {
                    oldAbs = abs;
                    ans = sum;
                }

                if (diff < 0) {//比对值小于0时，表示太大了，需要小一点的数来匹配
                    right--;
                } else if(diff > 0) {//比对值大于0时，表示太小了，需要大一点的数来匹配
                    left++;
                }else{
                    return ans;
                }
            }

        }

        return ans;
    }

    /**
     * 18. 四数之和
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        int length = nums.length;
        if(length >= 4){
            Arrays.sort(nums);

            int n1 = 0;

            while (n1 < length - 3){

                int n4 = length - 1;
                while (n4 > n1 + 2){

                    int n2 =  n1 + 1;
                    int n3 = n4 - 1;
                    while (n2 < n3){
                        int sum = nums[n1] + nums[n2] + nums[n3] + nums[n4];
                        if(sum == target){
                            List<Integer> item = new ArrayList<>();
                            item.add(nums[n1]);
                            item.add(nums[n2]);
                            item.add(nums[n3]);
                            item.add(nums[n4]);
                            list.add(item);
                        }

                        if(sum <= target){
                            if(n2 < n3 - 1){
                                while (nums[n2] == nums[++n2]){
                                    if(n2 == n3){
                                        break;
                                    }
                                }
                            }else{
                                break;
                            }

                        }else if(n3 > n2 + 1){
                            while (nums[n3] == nums[--n3]){
                                if(n3 == n2){
                                    break;
                                }
                            }
                        }else{
                            break;
                        }

                    }

                    if(n4 > n1 + 2){
                        while (nums[n4] == nums[--n4]){
                            if(n4 == n1 + 2){
                                break;
                            }
                        }
                    }else{
                        break;
                    }
                }

                if(n1 < length - 4){
                    while (nums[n1] == nums[++n1]){
                        if(n1 == length - 4){
                            break;
                        }
                    }
                }else{
                    break;
                }
            }
        }

        return list;
    }


    /**
     * 20. 有效的括号
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if(s != null && s.length() > 0){
            Stack<Character> stack = new Stack<>();

            char[] chars = s.toCharArray();
            for(int i = 0; i< chars.length; i++){
                char c = chars[i];
                switch (c){
                    case '(':
                    case '[':
                    case '{':
                        stack.push(c);
                        break;
                    case ')':
                        if(stack.size() > 0 && stack.peek() == '('){
                            stack.pop();
                        }else{
                            return false;
                        }
                        break;
                    case ']':
                        if(stack.size() > 0 && stack.peek() == '['){
                            stack.pop();
                        }else{
                            return false;
                        }
                        break;
                    case '}':
                        if(stack.size() > 0 && stack.peek() == '{'){
                            stack.pop();
                        }else{
                            return false;
                        }
                        break;
                }
            }
            return stack.isEmpty();
        }
        return true;
    }

    /**
     * 21. 合并两个有序链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        List<Integer> list = new ArrayList<>();

        if(l1 != null){
            getListByListNode(list,l1);
        }

        if(l2 != null){
            getListByListNode(list,l2);
        }

        int size = list.size();
        for(int i = 0; i< size - 1; i++){
            for(int j = 0; j < size - i - 1; j++){
                int val1 = list.get(j);
                int val2 = list.get(j + 1);
                if(val1 > val2){
                    int temp = val1;
                    list.set(j,val2);
                    list.set(j + 1,temp);
                }

            }
        }
        ListNode listNode = null;
        if(!list.isEmpty()){
            listNode = new ListNode(list.get(0));
            getListNodeByList(list,listNode,1);
        }

        return listNode;
    }

    private void getListByListNode(List<Integer> list,ListNode listNode){
        if(listNode != null){
            list.add(listNode.val);
            getListByListNode(list,listNode.next);
        }
    }

    private void getListNodeByList(List<Integer> list,ListNode listNode,int pos){
        if(pos < list.size()){
            listNode.next = new ListNode(list.get(pos));
            pos++;
            getListNodeByList(list,listNode.next,pos);
        }

    }

    /**
     * 26. 删除排序数组中的重复项
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if(length == 0){
            return 0;
        }
        int n = 0;
        for(int i = 1; i < length; i++){
            if(nums[i] != nums[n]){
                n++;
                nums[n] = nums[i];
            }
        }

        return n + 1;

    }

    /**
     * 27. 移除元素
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        if(length == 0){
            return 0;
        }
        int n = 0;
        for(int i = 0;i< length; i++){
            if(nums[i] != val){
                nums[n] = nums[i];
                n++;
            }
        }
        return n;
    }

    /**
     * 28. 实现strStr
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if(needle.isEmpty()){
            return 0;
        }

        int length = needle.length();
        for(int i = 0; i <= haystack.length() - length; i++){
            if(needle.equals(haystack.substring(i, i +  length))){
                return i;
            }
        }

        return -1;
    }

    /**
     * 35. 搜索插入位置
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {

        int length = nums.length;
        if(length == 0){
            return 0;
        }

        for(int i = 0; i < length; i++){
            if(target <= nums[i]){
                return i;
            }
        }

        return length;
    }

    /**
     * 38. 外观数列
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 6.     312211
     * 7.     13112221
     * 8.     1113213211
     * 9.     31131211131221
     * 10.    13211311123113112211
     * @param n
     */
    public String countAndSay(int n) {
        String str = "1";
        for(int i = 2; i <= n; i++){
            str = nextAndSay(str);
        }
        return str;
    }

    public String nextAndSay(String str) {
        int n = 0;
        StringBuilder sb = new StringBuilder();
        char ch = str.charAt(0);
        for(int i = 0; i< str.length(); i++){
            char c = str.charAt(i);
            if(ch == c){
                n++;
            }else{
                sb.append(n).append(ch);
                ch = c;
                n = 1;
            }
        }
        sb.append(n).append(ch);

        return sb.toString();
    }

    /**
     * 53. 最大子序和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int length = nums.length;
        for(int i = 1; i < length; i++){
            if(nums[i - 1] > 0){
                nums[i] += nums[i -1];
            }
            if(nums[i] > max){
                max = nums[i];
            }
        }
        return max;
    }

    /**
     * 58. 最后一个单词的长度
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        String[] ss = s.split(" ");
        int length = ss.length;
        if(length > 0){
            return ss[length - 1].length();
        }

        return 0;
    }

    /**
     * 66. 加一
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int length = digits.length;
        if(digits[length - 1] == 9){

            for(int i = length -1; i >= 0; i--){
                if(digits[i] != 9){
                    digits[i] += 1;
                    return digits;
                }else{
                    digits[i] = 0;
                }
            }
            int[] res = new int[length + 1];
            res[0] = 1;
            return res;
        }

        digits[length -1] += 1;
        return digits;
    }

    /**
     * 67. 二进制求和
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {

        int length1 = a.length();
        int length2 = b.length();
        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();

        if(length1 > length2){
            return addBinary(as,bs,length1,length2);
        }
        return addBinary(bs,as,length2,length1);
    }

    private String addBinary(char[] ch1, char[] ch2,int length,int minLength){

        StringBuilder sb = new StringBuilder();
        int n = 0;
        int size = length - minLength;
        for(int i = length -1; i >= 0; i--){
            if(ch1[i] == '1'){
                n++;
            }
            if(i >= size){
                if(ch2[i - size] == '1'){
                    n++;
                }
            }
            sb.insert(0,n % 2);
            n /= 2;
        }

        addBinary(sb,n);

        return sb.toString();
    }

    private void addBinary(StringBuilder builder,int n){
        if(n > 0){
            builder.insert(0,n % 2);
            addBinary(builder, n / 2);
        }
    }


    /**
     * 70. 爬楼梯
     * @param n
     *      2.  2
     *      3.  3
     *      4.  5
     *      5.  8
     *      6.  13
     * @return
     */
    public int climbStairs(int n) {
        if(n > 3){
            return climbStairs(3,2,3,n);
        }
        return n;
    }

    private int climbStairs(int i,int a,int b,int n) {
        if(i < n){
            return climbStairs(++i, b, a + b, n);
        }
        return b;
    }

    /**
     * 206. 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if(head != null){
            return reverse(null,head);
        }
        return head;
    }

    public ListNode reverse(ListNode list,ListNode head) {
        if(head != null){
            ListNode listNode = new ListNode(head.val);
            listNode.next = list;
            return reverse(listNode,head.next);
        }

        return list;
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
     * 994. 腐烂的橘子
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        return orangesRotting(0,grid);
    }

    private int orangesRotting(int n,int[][] grid) {

        boolean isOne = false;
        boolean bool = false;

        int row = grid.length;
        int col = grid[0].length;
        int[][] nums = new int[row][col];
        int i = 0;
        while (i < row){
            int j = 0;
            while (j < col){
                int num = grid[i][j];
                if(num == 1){
                    isOne = true;
                    if(i > 0){
                        int top = grid[i - 1][j];
                        if(top == 2){
                            nums[i][j] = 2;
                            bool = true;
                            j++;
                            continue;
                        }
                    }

                    if(i < row - 1){
                        int bottom = grid[i + 1][j];
                        if(bottom == 2){
                            nums[i][j] = 2;
                            bool = true;
                            j++;
                            continue;
                        }
                    }

                    if(j > 0){
                        int left = grid[i][j - 1];
                        if(left == 2){
                            nums[i][j] = 2;
                            bool = true;
                            j++;
                            continue;
                        }
                    }

                    if(j < col - 1){
                        int right = grid[i][j + 1];
                        if(right == 2){
                            nums[i][j] = 2;
                            bool = true;
                            j++;
                            continue;
                        }
                    }

                }

                nums[i][j] = num;
                j++;

            }

            i++;
        }

        if(bool){
            return orangesRotting(++n, nums);
        }else if(isOne){
            n = -1;
        }

        return n;
    }

    /**
     * 1103. 分糖果 II
     * @param candies
     * @param num_people
     * @return
     */
    public int[] distributeCandies(int candies, int num_people) {
        int n = 1;
        int[] nums = new int[num_people];
        while (candies > 0){
            for(int i = 0; i < num_people; i++){
                if(candies >= n){
                    nums[i] += n;
                    candies -= n;
                    n++;
                }else{
                    nums[i] += candies;
                    candies = 0;
                    break;
                }
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();

        System.out.println(leetCode.isPalindrome("abcdeffedcba"));
        System.out.println(leetCode.myAtoi("0000000000012345678"));
        int[] a = new int[]{4,5,6,0,0,0};
        leetCode.merge(a,3,new int[]{1,2,3},3);


    }
}
