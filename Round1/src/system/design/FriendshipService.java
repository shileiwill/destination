package system.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*
Friendship Service
Support follow & unfollow, getFollowers, getFollowings.
follow(1, 3)
getFollowers(1) // return [3]
getFollowings(3) // return [1]
follow(2, 3)
getFollowings(3) // return [1,2]
unfollow(1, 3)
getFollowings(3) // return [2]

单向好友关系。
使用两个HashMap分别保存follower和following，以提高查找效率。
在每个HashMap内，使用TreeSet来保存内容，利用TreeSet自排序特性，保证查找的结果有序。
同时，可以避免在插入时查重。

Friendship中，如果好友关系是双向的，也就是A是B的好友，B也是A的好友
这时在数据库中存一条记录就行了，省空间， 查询的时候查两次
如果你存为一条信息的话，比如 user_id=100 和 user_id=200 的成为好友，你的数据库表里有两个column一个是smaller_user_id, 一个是bigger_user_id。
为什么这么存呢，因为(100,200)和 (200,100)是同一条数据。所以存成一条数据要排序过再存。

假如user_id=200和user_id=300也是好友。那么这一次200会存在 smaller_user_id里。
好了，下面要检索 200的所有好友，你需要：
select bigger_user_id  from friendship where smaller_user_id=200;  => 得到 300
select smaller_user_id from friendship where bigger_user_id=200; => 得到 100
这样才能得到完整的100和300是200的好友。

两种方式， 两条record或者only 1 条record
https://explainextended.com/2009/03/07/selecting-friends/
*/

public class FriendshipService { 

    private Map<Integer, Set<Integer>> followers, followings; // 这个答案正好反了

    public FriendshipService() {
        // initialize your data structure here.
        this.followers = new HashMap<Integer, Set<Integer>>(); // follower == 追随者
        this.followings = new HashMap<Integer, Set<Integer>>();
    }

    // @param user_id an integer
    // return all followers and sort by user_id 谁follow了我
    public List<Integer> getFollowers(int user_id) {
        if (!followers.containsKey(user_id))
            return new ArrayList<Integer>();

        return new ArrayList<Integer>(followers.get(user_id));
    }
        
    // @param user_id an integer
    // return all followings and sort by user_id 我follow了谁
    public List<Integer>  getFollowings(int user_id) {
        if (!followings.containsKey(user_id)) {
        	return new ArrayList<Integer>();
        }

        return new ArrayList<Integer>(followings.get(user_id));
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id follows to_user_id
    public void follow(int from_user_id, int to_user_id) { // from follows to
        if (!followers.containsKey(from_user_id)) {
        	followers.put(from_user_id, new TreeSet<Integer>());
        }
        followers.get(from_user_id).add(to_user_id);

        if (!followings.containsKey(to_user_id)) {
        	followings.put(to_user_id, new TreeSet<Integer>());
        }
        followings.get(to_user_id).add(from_user_id);
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
        if (followers.containsKey(from_user_id))
            followers.get(from_user_id).remove(to_user_id);

        if (followings.containsKey(to_user_id))
            followings.get(to_user_id).remove(from_user_id);
    }
}