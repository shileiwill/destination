#############################
#
# Most Important
#
#############################

Why Uber


#############################
#
# Algorithm
#
#############################

Coding: 给一颗二叉树，每一个节点有一个value，找出一堆不相邻的节点，使得他们value的和最大。节点之间有link就算相邻，比如parent和children
Coding: Rate limiter (http://blog.gssxgss.me/not-a-simple-problem-rate-limiting/) token bucket, leaky buckt   
Coding: LRU Cache， follow-up 为什么用double linked list, Complexity, 修改为thread-safe的code                   
Coding: Sudoku validate + solver                                                                              
Coding: 找出string里所有是palindrome的subsequence                                                                
Coding: Number of Islands I/II                                                                                
Coding: Sorting algorithms
Coding: Course schedule II                                                                                    
Coding: Nested Iterator
Coding: Recover Binary Search Tree                                                                            
Coding: House Robber                                                                                          
Coding: merge two sorted linked list，lc原题。只是又问了两个follow up，一个是去除重复元素merge怎么做，另一个是merge的时候不算重复的数字怎么做
Coding: BST怎么找到kth smallest element
Coding: reverse words of string                                                                   
Coding: Implement TimeTravelingHashTable的get和insert方法.                                          
* TimeTravelingHashTable
* insert(key, value, timestamp)
* get(key, timestamp)
* get(key) // returns value associated with key at latest time
Coding: LeetCode304, LeetCode308 (know quad tree solution)                                                                              
Coding:  http://www.fgdsb.com/2015/01/25/group-contacts/ (union find)
Coding: LeetCode Word Search II                                                                         
Coding: 3Sum/4Sum                                                                                 
Coding: Weighted Random Gen
Coding: word pattern I 和word pattern II                                                          
Coding: LCA                                                                                       
Coding: Reverse nodes in pairs                                                                    
Coding: Clone Graph                                                                                 
Coding: CSV parser                                                                                
Coding: find element in rotated array                                                             
Coding: Word Ladder
Coding: TreeMap/RedBlack Tree
Coding: Spiral Matrix                                                                             
Coding: Happy Number                                                                              
Coding: Count and Say                                                                             
Coding: Serialize/Deserialize N-ary Tree                                                          

##############################
#
# Sys Design
#
###############################

Design Uber/Uber eat
Design Uber fare split                 
Design Netflix
Design Youtube                          
Design auto suggest/auto complete       
Design Excel/Google Spread Sheet/find circle in cell dependency(topological sort)
Design Tiny URL                         
Design Facebook Messenger
Design Spotify
Design Bus tracking app
Design Online multiplayer blackjack


设计个类似coursera的网站，主要是设计数据库&存储数据
设计surge pricing
design elevator system
design battleship : 看一下那个游戏的功能，他会问需要什么class， 什么数据结构，什么变量来存，然后就是执行游戏的某一部分操作的时候需要怎么update，完全照着游戏来就差不多感觉！  
印度小哥，ood， 设计一个battleship game 
design airline ticket systems
给定时间段和地点，判断给定的条件是否满足？问的是数据怎么存？
OOD, car sale website
扫QR code借自行车的问题。也是geo location相关的。例如找车啊，开始用自行车啊，怎么锁车解锁之类的。api design？
好像是设计url。只是太不了解这个过程而且背景差太远完全没有还手之力。例如被问怎么个socket通信，我只知道在java建一个socket，
然后塞个ip什么地就通信。还问你说用sql db，用什么sql db啊，sql有好多种的？我说，呃，我不知道postgress跟mysql到底有什么不一样...

design 一个手机 app, 每天早上6点，给你发notification, 告诉你有那些popular 的apps, 或者根据 你现在手机上装的apps, 给你一个list of recommending apps. 从前端扯到后端.

第三轮 System design 设计一个类似message center的black box。 从producer定时拿一批数据，然后进入到message center，然后存入中间的类似备份的数据库，然后返回前端。
中间涉及一些format的转换。包括收到任何消息，都要给producer发回执。

design pokemonGo. pokemonGo有三个要求，显示pokemonStop, spinpokeMonstop, catchPokemon. 在提到要把pokemonStop信息存储的时候，卡住了。
因为pokemonstop的数目很多要多台DB, 如何sharding成了问题。如果按照id sharding，用坐标查询附近的stop就很麻烦。如果按城市存储，查询的坐标正好落在城市边缘就要去另外一个数据库查询。
如果DB存一些周围城市stop 的duplication, consistence 就成了问题。请高手指点如何存储stop 的信息并且方便按cutomer的位置进行快速的查询。

假设有5billion个分布在全球的停车场sensor，可以不断汇报各个车位是空的还是满的，设计一个服务能够支持查询离用户最近的5个空车位。答得一般，最后谈存储的时候认为我的schema太浪费空间，
卡了，最后提示下才想出来可以用array的index做sensor id直接存储代表sensor状态的boolean变量，这样就可以直接用单机内存存储所有数据了

设计一个next stop bus 的app，能够支持查找离用户最近的5个车站各趟线路的ETA。
先讨论了如何获得各个ETA的方案，然后在此基础上增加限制条件减少数据的写入，最后要求设计出一套完整的data path


Post trip pipeline. A lot of processing must happen after a trip has completed.
Collect ratings.
Send emails.
Update databases.
Schedule payments.

Design uber eat (system design) : Uber?
Design messaging platform (system design) : Chat?
Design ads platform (system design) ： Recommendation?

Kafka                                   
Thrift                                  
Riak
Redis                                   
Spark Streaming
BigTable                                
Ringpop
Uber schemaless 

#############################
#
# Userful links
#
#############################                               

http://highscalability.com/blog/2015/9/14/how-uber-scales-their-real-time-market-platform.html
https://github.com/checkcheckzz/system-design-interview
https://eng.uber.com/
http://basho.com/posts/technical/ubers-ringpop-and-riak/
https://www.youtube.com/watch?v=-W9F__D3oY4



Partner team:

Phil Rabin  Rider App,  Bar raiser, What i am working on, how deep i can go, previous job experience, logging flow, end to end. Good communicator, huge impact, driving best practice, how work collaboar working with others, OOD, Design, Coding as well 

# All those folks are from Dev Platform team
Jay Bobzin  Developer Platform
Adam Rogal  Sr. Director is hiring manager  45 min  Learn more about myself
Goutham Nath  New manager will shadow Adam
Riaz Majid  Rider experience GEO location, figure out best way. Uber Pool
Patrick Slattery

Integration with FB API, Airport, Millitary

call Uber from FB

Caching layer

Ask clarifying questions before writing, try to figure out best solution

# 08032017
https://eng.uber.com/developer-platform/

Interview with Partnership team
1. Phil Rabin,  Rider Team,  Engineer manager, Android engineer. Bar raiser. It will be project deep dive.

# Following are from developer platform, what does developer platform do?
2. Jay Bobzin, Senior Android, iOS, Data engineer. coding?

3. Adam Rogal Hiring Manager, Culture fit?
   Goutham Nath shadow, Tech Lead
   		Since interviewing with Developer Platform, show case the Turing SDK
   		Developer platform VS. Tools team(C2D, Git, Gerrit), How to gather requirements?

4. Riaz Majid coding? Rider Team
	Rider experience GEO location

5. Patrick Slattery coding?

What does developer platform do, what kind of product?
How many rounds of design?
Coding will be using my personal laptop? So, similar with the phone screen.
Is there lunch time?
What is the level or position I am interviewing with?

coding,
problem solving, API integration, design uber backend, ETA, Price. Uber eats, Uber pool. Dispatch.
System design. Docker, Redis. 

It is a Senior position.