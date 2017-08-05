package company.linkedin;
'2. system design, 设计一个系统来监控各个应用以及服务器产生的异常。
'http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=218010&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

大概就是，首先各个机器先要log到自己的机器上，然后在自己的机器上做一次整合，整合之后，每隔一段时间集体发给一台中心节点存储起来。整合包括了，比如某个request在一秒钟之内发生了10次，
没有必要记录了成10个requests对吧。

乍一看有两种思路啊，各路大神不要喷我：
1，利用log，比如抓取服务器的exception的log做counter，设计一个异步log整合系统
。或直接导入到logstash进行aggregation也可以。
2，利用jvm底层字节流软件，比如像profiler或appdanamics之类的libagent在字节流
层面上抓取和监听异常事件

5.1 设计word里面提示错别字的功能。follow up是可以加什么advanced features。
5.2 top k visited URLs in last 24 hr/1 hr/5min。最后有个follow up是怎么保证对全球各地用户的响应速度。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=218646&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

design a system to get top k exceptions/errors within a system for the given period of time.
1. there are large number of servers. 
2. there are large number of exceptions
3. k is subject to change
4. time period is subject to change
5. granularity of time is minute level
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=218214&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

3 design web游戏hangman
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=212481&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
http://www.jiuzhang.com/qa/2655/

4. Sytem design, top 10 logs in last 60 min. Design the entire product, 
from how to get log data to how to show it in UI.
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=215128&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
首先各个机器先要log到自己的机器上，然后在自己的机器上做一次整合，整合之后，每隔一段时间集体发给一台中心节点存储起来。整合包括了，比如某个request在一秒钟之内发生了10次，没有必要记录了成10个requests

3.    
Design. 烙印 设计google/outlook calendar
create event
invite user
notify users at specific time or periodically
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=215640&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
http://www.jiuzhang.com/qa/3498/
http://www.jiuzhang.com/qa/2897/

design1: design 3层connections的functions和存储方式。. 
design2: design 一个monitering system。没什么要求，一直让我遐想。
design3: design 多线程的functions window(Msec msec)，可以返回这段时间内的平均值。add（int val）时可以调用一个给好的getTime function得到时间。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=216630&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

5. design，design api。给定一个get_friends_lists() 问怎么判断两个是1 degree friends，2 degree friends 还是 3 degree friends
6. design，design monitor system，比较麻烦，考虑了partition，replication，easy to use，还有一些其他乱七八糟的。面试的时候一定要小心shadow。。。
因为shadow没经验，肯定会问些乱七八糟的东西，这一轮的那个shadow不问死我我算完
7. design， 见http://www.1point3acres.com/bbs/thread-147555-1-1.html 
第三轮，这一轮的时候太累了，脑子抽了，直接导致面试官给提示都听不懂，搞了个比较麻烦的方法来解决，会用额外的空间。。。但是后来想一下可能会更快
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=208020&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

第四轮：中亚人+国人shadown。要求设计一个calendar system。要求1000用户，并可能拓展到100M+。这轮应该是挂了，作死的选了个cassandra
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=174614&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

第四轮system design: hangman
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=214713&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

34.
已知一个函数，输入用户ID，可以返回该用户的所有友好（degree 1 friends），按好友ID从小到大排序。
要求实现函数来输出返回一个用户的所有好友的好友(degree 2 friends), 以及 degree 3 friends。

这里感觉主要是聊天看思路，中间会临时加一些限制条件，来进行时间或者空间的优化。

'第三轮：系统设计
对于key，value pairs， 在给定的文件系统中实现 put，get，delete 的方法。其中key比较小，全部key可以放在内存中，value有的会比较大
已知一个文件系统，可以
create files, delete files, sequentially scan file content, read file content randomly, append file content. '
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=147555&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

35.
１.ｄｅｓｉｇｎ，给一个ｍａｐ函数输入一个值输出一个值，一个ｒｅｄｕｃｅ函数输入２个参数返回一个值，
ｎ个ｔｈｒｅａｄ，设计一个函数先ｍａｐ再ｒｅｄｕｃｅ，最大化利用所有ｔｈｒｅａｄｓ
２．ｄｅｉｓｇｎ，地里有，求１ｓｔ，２ｎｄ，３ｒｄ，ｃｏｎｎｅｃｔｉｏｎｓ
３．ｄｅｓｉｇｎ，推荐过去５ｍｉｎ，１ｈｏｕｒ，２４ｈｏｕｒｓ　的ｔｏｐ１００　ｓｈａｒｅｄ文章
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=209589&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

36.
Design 
设计一个找系统里面所有符合要求文件的function, 要求能不停的添加新的条件
type linkedin.com url 后发生什么
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=204503&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

37.
r3: tiny URL
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=171681&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

38.
'design
important'
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=175538&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311


39.
Design a system to report top N 500 server exceptions.
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=198297&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

40.
5.设计短网址服务器构架。讨论流量，服务器架构和数据库架构。纯设计，半点代码都不碰。各种挑战。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=203816&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

41.
设计日历（不用考虑重复事件，用户少）
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=204207&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

42.
5: 设计题:  
a restful server with 4GB,  
given a request such as: http://seq=4?len=60?xxxxdata
the system will store the binary data with that sequence number.
given a request: http://startseq=3?maxLen=100, the system returns all data objects with sequence >= 3 with total data length less equal than 100.

multiple clients calling simultaneously
what data structure, concurrency, locking, etc.
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=99469&extra=page%3D3%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

43.
system design: design a system to monitors the top exceptions during last hour, last 24 hours.
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=198457&extra=page%3D3%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

desig设计过去5min 1hr 24hr各类系统的exception Top K
http://www.mitbbs.com/article_t/JobHunting/33226795.html

5min: Queue<Map<Exception, Integer>> queue.size() == 300s
1hr: Queue<Map<Exception, Integer>> queue.size() == 60min
24hr: Queue<Map<Exception, Integer>> queue.size() == 24hr
这个设计好吗？ 时间是固定的一个小时吧

思路是用queue+heap来解。这里假设window size = 5min，queue用来存过去5min的request及timestamp，每当过去1s就移除掉queue头部过期的数据，并且在尾部加入这1s进入的新数据。
用heap来maintain requests, 其中value是次数，每当新进来数据，则频次递增1，每当需要删除老数据，则频次-1。（频次的加减在heap中用增删节点来做）。最后所需的top k就是heap最顶k个。 
5min, 1hr, 24hr还涉及到一个内存上限的问题，简单思路如下：
5分钟 纯内存 
1小时 内存+外存 
24小时 内存+外村+颗粒（将每5min视作1个颗粒）

Design tiny URL 问了很多细节，最后居然问到了怎么配置memcache
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=145037&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

design a notebook application like evernote or onenote, it should support search, collabration. inverted index + google doc?
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=148877&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

2. 中国大哥（斯坦福毕业的大牛）+加拿大白人（步步追问，不问出最优设计不甘心） system design http://www.1point3acres.com/bbs/thread-147555-1-1.html 第三题 
对于key，value pairs， 在给定的文件系统中实现 put，get，delete 的方法。其中key比较小，全部key可以放在内存中，value有的会比较大
已知一个文件系统，可以
create files, delete files, sequentially scan file content, read file content randomly, append file content

3. 俩白人小哥，很幽默。。。气氛轻松活跃 system design http://www.1point3acres.com/bbs/thread-147555-1-1.html 第一题 
已知一个函数，输入用户ID，可以返回该用户的所有友好（degree 1 friends），按好友ID从小到大排序。
要求实现函数来输出返回一个用户的所有好友的好友(degree 2 friends), 以及 degree 3 friends。

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=159920&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

面试官进来就跟我唠嗑唠了很…… 然后叫我介绍一下我自己，我就随便说了暑假在Amazon实习，她就说：噢……我本来想叫你设计Amazon Product Page的但必须换个题目了（
我：。。。）所以最后就是TinyURL... 之前看了挺多资料的所以并没有遇到很大困难。说完了之后还有挺多时间，于是还是把Amazon product page的设计题过了一遍。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=145426&extra=page%3D7%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=169441&highlight=%C9%E8%BC%C6Amazon%2BProduct%2BPage
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=143335&highlight=%C9%E8%BC%C6Amazon%2BProduct%2BPage

top N exceptions in recent K time, 基本是frequency estimation of streaming data，然后再讨论下如果要处理N台机器的log怎么做分布式。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=222643&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

第四轮 system design 楼主以为这个是要high level的设计一个系统或者service，包括分析QPS， concurrent user那些，到后台用哪种database存，push or pull，
这些，结果完全又不按套路出牌，让设计一个日历
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=223667&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

设计亚麻购物页面 从数据库开始 妹子让我用sql 我就一顿扯
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=224947&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26sortid%3D311

Design Youtube.
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=225081&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

Design LinkedIn
search功能里inverted index 和data of user , data of company 怎么存，分别用Nosql还是sql？然后设计timeline，问我push/pull模型在哪儿看的
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=175538&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

canlendar design
几个主要功能，添加修改删除event，邀请别人，看别人的availability。 
然后跟server怎么交互，数据怎么load，数据库schema设计等等。说完功能考量scalability，都常规的design题步骤走一下. 
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=175497&page=1#pid2272026

最后让设计search similar people的API，大致说一下前端，后端，不用太细。

Design Uber
http://www.jiuzhang.com/qa/410/

Blacklist IP。这个list会增加删减条目，每一个request都会来lookup这个list

Design an E-Commerce platform - features include products in different categories, order processing, multiple users be able to operate at the same time, 
have reviews for the products and when a user enters the website daily, he has to be shown top 5 products sold in the last week 
( consider the amount of data while querying - and make the system efficient )


Design POI: https://www.slideshare.net/mmalone/scaling-gis-data-in-nonrelational-data-stores

感觉这个面试很有代表性： http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=288520&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26sortid%3D311
LinkedIn上的用户会用到很多shared links，然后要求设计个service来统计过去五分钟， 一个小时， 一天或着一周里， 出现次数最多的Top k个shared links

设计界面上那个你的技能被endorse的那个系统+tiny url + DB schema设计

交流就是尽量往他们的文化上靠，什么be bold, act as owner. 讲项目的时候，就说我过去怎么怎么了，就是be bold, act as owner.
问你为什么来linkedin，因为你们这个文化好啊，我就是这么一个人，鼓吹自己一番，然后说你看我多么合适。
在家多练习练习。

Login Recommendation: Proactively do something, rather than passively do.

Details for each round? How many coding, design, and behavior
	5 rounds.
		1 host lead
		2 coding 
			edge cases
			runtime
		1 tech communication
			pretend onboard the new teammate. big picture. Previous experience
		1. Design
			Come up with a high level solution. Problem solving. Ask a lot of questions.
	Lunch

Is there a specific team I am interviewing with, or it is generial, then team match?
	A big team, generial for Application team. Hiring community. Will do team match. Tech, projects we are working on.

The position I applied is a senior role, any other difference with junior role except difficulty?
	Very similiar. Differece is interviewers will be more senior. They would judge you as a senior. Tech leadership. How mentor people. Career movement.
	Get the list of interviewers. The day before onsite, will get the list from coordination team.

Any other pitfalls I need to pay attention before the onsite?
	Coding, keep talking. so they understand.
	It is tough, someone is coding, but not talking.
	Imagine you are coaching someone.

	Design, make sure ask clarifying questions.
	Make sure go to correct direction. Am I in the correct direction?
	How you work within a team environment. How you make decisions? Interactive.
	Have you thought about this?