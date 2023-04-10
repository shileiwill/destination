stripe_design.java


https://www.1point3acres.com/bbs/thread-939996-1-1.html
metric 系统，侧重在client push metrics to serv‍‍‍‍‌‍‍‍‍‍‌‍‍‍‌er的情况。很多细节问题。E.g client如何存储metrics，如果app使用这个系统，如何减少耗电量，如何handle app crash的情况等等。
有点像weather monitoring system.

 Mobile integration: 完成一个app， 像一个点单系统，可以把item加入/移除订单。订单完成后需要call backend service来获取discount信息，
 然后利用discount+银行卡信息来进行结账，感觉考察的点跟https://www.1point3acres.com/bbs/thread-908575-1-1.html的bike map很像



第二轮Coding，设计load balancer，不难，地里面经写的不是很细。第一问就是每个request有一个weight，我用PQ存，每次找最小的serve。
第二问是每个request有TTL，商量后不考虑distributed和多线程，我propose lazy delete TTL，
每次有request时候直接brute force PQ里的所有request。follow up了一个我提到的test问题，
我觉得用一个clock最好，test的时候可以把fake clock穿进去，就不用thread.sleep来test了 which is flaky。这轮能感觉面试官挺满意。

第三轮integration，bike map，过程也不错，直接附了我写的答案。之前没练过，基本写出来了。handle_json是例子没用。
第一问写的readJson，第二问sendRequest，第三问sendRequest里面加一点第一问的‍‍‍‍‌‍‍‍‍‍‌‍‍‍‌‌‍到的field继续send。

第四轮moshi debug表演，感觉演的也可以。附件中错误的两行代码在ParserStack里，我注释掉了，可以直接run自己debug试试。

第五轮design ledger，我觉得说的也不错，国人面试官，帮我把控时间，所有问题都接住了，感觉说的不错。但是时间不够，
有些我想到的点没说到。但是看他反应感觉也不错啊。

