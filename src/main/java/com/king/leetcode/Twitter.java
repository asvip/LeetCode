package com.king.leetcode;

import java.util.*;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
class Twitter {

    Map<Integer,Integer> tweets;
    Map<Integer,Set<Integer>>  personals;

    /** Initialize your data structure here. */
    public Twitter() {
        tweets = new LinkedHashMap<>(16,0.75f,true);
        personals = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        tweets.put(tweetId,userId);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {

        Set<Integer> follows = personals.get(userId);
        if(follows == null){
            follows = new HashSet<>();
        }
        List<Integer> list = new ArrayList<>();
        int count = 0;
        ListIterator<Map.Entry<Integer,Integer>> iterator = new ArrayList<>(tweets.entrySet()).listIterator(tweets.size());
        while (iterator.hasPrevious()){
            Map.Entry<Integer,Integer> entry = iterator.previous();
            if(entry != null){
                int v = entry.getValue();
                if(v == userId || follows.contains(v)){
                    list.add(entry.getKey());
                    count++;
                    if(count >= 10){
                        break;
                    }
                }
            }
        }
        return list;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(personals.containsKey(followerId)){
            personals.get(followerId).add(followeeId);
        }else{
            Set<Integer> follows = new HashSet<>();
            follows.add(followeeId);
            personals.put(followerId,follows);
        }
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> follows = personals.get(followerId);
        if(follows != null){
            follows.remove(followeeId);
        }
    }

}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
