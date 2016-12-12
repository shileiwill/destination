package leetcode4.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 355. Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
Example:

Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);
 */
public class DesignTwitter {

    // This is not very good
    // Map<Integer, List<Integer>> fans = new HashMap<Integer, List<Integer>>();
    // Map<Integer, List<Integer>> tweets = new HashMap<Integer, List<Integer>>();
    int tweetTime = 0;
    Map<Integer, Set<Integer>> fans = new HashMap<Integer, Set<Integer>>();
    Map<Integer, LinkedList<Tweet>> tweets = new HashMap<Integer, LinkedList<Tweet>>();
    
    /** Initialize your data structure here. */
    public DesignTwitter() {
        
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!fans.containsKey(userId)) { // First time to tweet
            fans.put(userId, new HashSet<Integer>());
        }
        // Why this has to be outside of the above loop. Because userA may have another fan, but not himself yet.
        fans.get(userId).add(userId); // Add himself
        
        if (!tweets.containsKey(userId)) {
            tweets.put(userId, new LinkedList<Tweet>());
        }
        tweets.get(userId).addFirst(new Tweet(tweetId, tweetTime++));
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<Integer>();
        PriorityQueue<Tweet> heap = new PriorityQueue<Tweet>();
        
        if (!fans.containsKey(userId)) {
            return res;
        }   
        
        for (int user : fans.get(userId)) {
            if (tweets.containsKey(user)) {
                for (Tweet t : tweets.get(user)) {
                    heap.offer(t);
                }
            }
        }
        
        // while (feed.size() > 0 && res.size() < 10) res.add(feed.poll().id);
        
        while (!heap.isEmpty() && res.size() < 10) {
            res.add(heap.poll().tweetId);
        }
        
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!fans.containsKey(followerId)) {
            fans.put(followerId, new HashSet<Integer>());
        }
        fans.get(followerId).add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (!fans.containsKey(followerId) || followerId == followeeId) {
            return;
        }
        fans.get(followerId).remove(followeeId);
    }
    
}

class Tweet implements Comparable<Tweet> {
    int tweetTime;
    int tweetId;
    
    Tweet(int id, int time) {
        this.tweetId = id;
        this.tweetTime = time;
    }
    
    public int compareTo(Tweet t2) {
        return t2.tweetTime - this.tweetTime;
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