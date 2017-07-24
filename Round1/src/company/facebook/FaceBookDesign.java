package company.facebook;

public class FaceBookDesign {
	1 Design a web crawler with fixed set of resources.

	2 Design a real-time type-ahead search-phrase predictor which presents the top-10 ranked search strings that begin with a given prefix. 
	http://www.jiuzhang.com/qa/2860/

	3 Design timeline的group权限，比如说user发一条status可以选择对某个group的好
	友可见。题目很简单，但是会讨论到facebook用户规模的估算，服务器估算，social  graph的存储。感觉system design只要讲个大概思路就行，面试官不会去纠结太细节的东西。
	4 design偏向设计存储结构
	5 system design: 设计key-value store，直接列了一大堆从client到server的要求，基本处处陷阱，经验这里比较重要，光按面试准备. 基本没效果。
	6 搜索栏的自动完成功能
	7 那个给你一个点， 然后有几个million的POI， 找出最近的20个。。。我说那个Z distance。。 two dimension变成一个dimension， 那个面试官说， 没听说过Zdistance， 不行。。。

		http://massivetechinterview.blogspot.com/2015/06/poi-geohash.html

	8 find close coordinates
	9 上来讨论了20分钟的如何设计data structure表示fb的friend和follower两种关系
	，各种结构的tradeoff。我边讨论边猜是不是要我clone graph，然后默念怎么还不让
	我写code。果然，deep copy。不过最后讨论的data structure 和lc上有点不同，dfs
	思路是一样的。
	10 deadlock设计-google
	11 问为什么Facebook，对Facebook的哪个feature最喜欢，为什么喜欢，然后这个feature还有什么不足。
	12 之后让给他一个非常specific的例子说当你和同事出现技术上的冲突的时候，应该怎么解决，问的特别细，
	特别深入。
	13 Design news feed API, 这题我准备过，但是按pull/push model准
	备的，还准备了pub/sub model，就是给每一个friend都建一个queue,推送一份news，
	算准备过的题。但他不考这些，根本不让我说关于aggregator tier或者database tier
	的东西，主要focus API怎么写，input/output, feed里图片怎么存，想mention 
	friends怎么存，怎么做multi device sync。我觉得他的考点似乎在data 
	serialization/deserialization这边？
	https://www.jiuzhang.com/qa/4414/

	14 设计题，传输10G的data到5个data center，每个data center 有1000的
	节点。三哥从问背景就开始找茬，面试过程中要求解gossip protocol的微分方程
	15 设计iPhone Find Friends 的后端。Geohashing + DHT解之
	16 设计题问得很细，比如DHT如何实现，单机的Hash table如何实现能节省内存， 如何做
	concurrency control，如何实现mutex之类的。
	17 system design: 每个record有个很大field，比如年龄，性别，爱好等。给一个field的组合，比如小于25岁，爱好体育，query满足这些组合条件的用户个数
	18 设计一个facebook的搜索引擎，这个引擎能搜索出包含关键字的facebook动态。没有讨
	论太多前端的，主要在讨论架构和存储。
	给出了倒排索引来存储index，以及讨论了下如何存储facebook的动态(key-value 存储
	）如何handle hot keyword。面试官人很好，引导我的思路。
	19 system design白人大叔， 有个function是List<id> getNearest(int x, int y
	){}, 假设从mobile上在地图上点一下，然后返回改点附近的所有建筑location。怎么
	设计data structure以及data schema
	20 System design设计手机上读取photo feeds的app。
	    功能： 读取好友的最近图片
	               阅览好友的相册
	    要求： 满足功能的同时减少对手机的能耗。
	21 design：tiny url
	22 System design： instgram  https://www.educative.io/collection/page/5668639101419520/5649050225344512/5673385510043648
	23 Culture fit： 有200M个用户，现在让你进行分组，将他们分成大概20个组，每个
	组里大概有10M的用户，尽量让用户interaction多的在一起
	24 design看了下几篇文章，知道个大意，google的mapreduce, file system, big table,
	fb的memcache, unicorn。其他看到过的觉得还不错的design资料，最后一个常见题目
	汇总可以过过看，很有帮助:
	http://blog.csdn.net/v_july_v/article/details/7382693
	https://www.youtube.com/watch?v=-W9F__D3oY4 
	http://www.mitbbs.com/article_t/JobHunting/32741713.html
	另外建议稍微准备下常见数据类的写法(包括generic programming), 我倒是没碰到其
	他一些concurrency, database, NP-hard之类的题目.
	25 设计一个facebook功能：在一个post下面，如果有了新的comment，可以自动显示，
	不需要刷新后再显示。
	27 design facebook chat
	28 写一个sequential 多线程pool。实现f（Runable r）要求caller不可以block，但是
	在pool里面要一个跟这一个的运行。
	29 设计类似gogle地图系统，从A点到B点的算法已经有了。整个地图大概有好几亿条线
	段组成，这个系统的市场占有大概30%。要求在小于1妙的时间里算出结果。估算需要多
	少台机？要怎么样保存地图，怎么cache？
	30 然后面试中有个印度人问了个问题，就是如果系统出问题了，有个size很大的log如何
	从里面找出相关的信息，同学说直接search关键字，但是面试官不满意也没给提示，所
	以不知道怎么回答。
	31 国人面试官面出的 design：Shorten Url
	34 System Design：设计一个K recent contact 的service，就是当用户把鼠标点到
	chat对话框的时候，自动弹出K个最近的联系人。follow-up是如果要弹出K个最熟悉的
	人怎么设计，以及资源估计（需要多少台机器来做数据存储，多少个处理request等等
	）。
	35 design准备：板上有几个design总结贴，非常管用。我就是照着 flamingos和beidapig
	的两个总结贴，大概看了看，学习了不少知识。
	http://www.mitbbs.com/article_t/JobHunting/32777529.html
	http://www.mitbbs.com/article_t/JobHunting/32984309.html

	如何设计一个fb的privacy framework/system
	http://www.1point3acres.com/bbs/thread-283571-1-1.html
	privacy setting, 就是被everyone可见，只被朋友的朋友可见，group可见，问底层SQL怎么存（每一column是什么，怎么Join)，问存friend mapping怎么存，
	用SQL存和用noSQ存friend mapping的trade off. 然后问friend of friend怎么找，之前看过面经，直接说出最基本的n^2和两种O(n)方法，
	结果没一种是面试官想要，只能跟着面试官的提示走，一路看面试官的语气、表情、以及对我提出方案的反应，揣测面试官想要的解法。之后就是判断需要多少server存等，
	group是每个user可以自己设些group，把一些user加进每个Group，其他用户不知道他设的group是什么，然后sql怎么存group，其实就是听面试官的提示和意图。
	仔细体会面试官的反馈和意图很重要，每个面试官想要的可能不一样

	Desgin an Advertisement (AD) statistic system. 每次用户登录的时候，系统都会show几个广告给他。广告总共有K种类别，可以认为K<=10。用户看到广告可能会点击，Click Through Ratio (CTR) = 用户点的广告数量/给用户看的广告数量。注意若同一个广告被用户点击了多次，只算一个click。设计一个系统，answer the following two types of queries:
	1. Given a user, return his CTR for all types of ADs.
	2. Given an AD type, return its average CTR ovre all the users.
	Follow ups: 
	a. What if K becomes very large? for example, we consider each product as one type, thus K can be as large as 10000.
	b. New query type: Given an AD type, return the top-X users with highest CTR. 1< X < 100.

	设计是 news API design, 也是面经了。   注意 pagination. 看看twitter 的 API developer's guide 会有帮助。 


	通常面试官会要求你在45分钟内设计Netflix（或支持数亿用户的某种弹性服务）。这个问题看似无法完成，因为 45分钟太短，根本无法详细讨论其中任何一个模块。这些服务是由成百上千名工程师耗时多年开发完成，你是不可能把所有这些工作简明扼要的写在5x5的白板上的。
	既然完成这个问题基本是不可能的，那么面试官到底想知道些什么？其实他希望你给他一个的概述，定义高级模块，并尽可能简洁地描述组件之间的交互。大概分三个阶段：
	1.画一个大框架来表示系统
	2.放大框架并把它打散变成5-6个模块
	3.简要讨论每个模块的功能。计算，存储，前端，后端，缓存，队列，网络，负载均衡等

	design fb inbox search    —> just focus the post
	Design autocomplete in a search engine
	Design Youtube

	设计脸书search
	TinyUrl
}


细节讲清楚，project要够high-level
