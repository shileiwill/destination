stripe_questions.java

https://www.1point3acres.com/bbs/thread-804154-1-1.html
老题Wishlist
第一问找top mutual rank user
第二问给定rank，找rank上的mutual rank user
第三问changePair，我感觉面试官把自己和我都绕进去了。比如说找a的mutual rank user，我一开始理解的是先找原来rank上的mutual rank user，然后把rank-1上的user比如说b和rank上的user比如说c互换顺序，然后看b在rank上的对应的user是不是a，是的话也加到结果集里。结果test case有一个没跑过，然后面试官跟我说是换完是看c在rank-1上对应的user是不是a... 然后我就改了，test case是跑过了。但是我自己figure out其它test cases的‍‍‍‍‌‍‍‍‍‍‌‍‍‍‌‌‍时候本来想找个换前和换后都有mutual rank user的case，但发现这样的话这种case不可能存在，我还跟面试官说，然后面试官也说是的然后还说这个是挺绕的。。面完回来越想越不对，，估计gg了


https://www.1point3acres.com/bbs/thread-753783-1-1.html
Coding : Design an API that allows users to execute_endpoint(customer, tm). The system should not allow user to enter too many data points in a short time, say no more than 5 in 2 seconds.
System design : end-2-end设计一个metrics system，从application side的agent到背后的backend storage都要想清楚。比如app发metrics的方法就是Metrics.increment("event-name")。然后有一个UI能帮忙检测某个时间段内某个event的count的plot。
cnt
^
|    #
|  ##
|  ##  ‍‍‍‍‌‍‍‍‍‍‌‍‍‍‌‌‍ #
|######
---------------> t

https://www.1point3acres.com/bbs/thread-792266-1-1.html
每一问都要写详细的assert equals test cases
我开始赶时间没写headers 和set为空 edge case, 面官在我run第一个case时提出来，让我补上，还补上3个 header, set分别为空的case测试

/*
        *   "en-US, fr-CA, fr-FR",  # the client's Accept-Language header, a string
  ["fr-FR", "en-US"]      # the server's supported languages, a set of strings
)
returns: ["en-US", "fr-FR"]

parse_accept_language("fr-CA, fr-FR", ["en-US", "fr-FR"])
returns: ["fr-FR"]

parse_accept_language("en-US", ["en-US", "fr-CA"])
returns: ["en-US"]*/
        HTTP_request test=new HTTP_request();
        String[] list1={"fr-FR", "en-US"};
        String head1="en-US, fr-CA, fr-FR";
        Set<String> set1=new HashSet<>(Arrays.asList(list1));
        //   assertEquals(Arrays.asList(new String[]{"en-US", "fr-FR"}),test.parseLang(head1,set1));

        String[] list2={"en-US", "fr-FR"};
        String head2="fr-CA, fr-FR";
        Set<String> set2=new HashSet<>(Arrays.asList(list2));
      //  assertEquals(Arrays.asList(new String[]{"fr-FR"}),test.parseLang(head2,set2));

        String[] list3={"en-US", "fr-CA"};
        String head3="en-US";
        Set<String> set3=new HashSet<>(Arrays.asList(list3));
       // assertEquals(Arrays.asList(new String[]{"en-US"}),test.parseLang(head3,set3));

        String[] list4={"en-US", "fr-CA"};
        String head4="";
        Set<String> set4=new HashSet<>(Arrays.asList(list4));
        //  assertEquals(Arrays.asList(new String[]{}),test.parseLang(head4,set4));


        /*
        *
        parse_accept_language("en", ["en-US", "fr-CA", "fr-FR"])
returns: ["en-US"]

parse_accept_language("fr", ["en-US", "fr-CA", "fr-FR"])
returns: ["fr-CA", "fr-FR"]

parse_accept_language("fr-FR, fr", ["en-US", "fr-CA", "fr-FR"])
returns: ["fr-FR", "fr-CA"]
        * */

        String[] list1b={"en-US", "fr-CA", "fr-FR"};
        String head1b="en";
        Set<String> set1b=new HashSet<>(Arrays.asList(list1b));
        assertEquals(Arrays.asList(new String[]{"en-US"}),test.parseLang2(head1b,set1b));

        Stri‍‍‍‍‌‍‍‍‍‍‌‍‍‍‌‌‍ng[] list1c={"en-US", "fr-CA", "fr-FR"};
        String head1c="fr";
        Set<String> set1c=new HashSet<>(Arrays.asList(list1c));
        assertEquals(Arrays.asList(new String[]{"fr-FR","fr-CA"}),test.parseLang2(head1c,set1c));

        String[] list1d={"en-US", "fr-CA", "fr-FR"};
        String head1d="fr-FR, fr";
        Set<String> set1d=new HashSet<>(Arrays.asList(list1d));
        assertEquals(Arrays.asList(new String[]{"fr-FR", "fr-CA"}),test.parseLang2(head1d,set1d));


        https://www.1point3acres.com/bbs/thread-793699-1-1.html
        地里老题，不过问了四个follow-up，反馈地里，求大米，谢谢！！
Part 1
给一个user map，比如 {a: [c,d], c: [a]}，key是不同的user，value是其他user的ranking。然后实现hasMutualRank(a)，如果a的第一rank user恰好也把a作为第一rank，那么return true

Part 2
在第一题的基础上改动，rank作为变量, hasMutualRank(a, rank)。第一题就相当于hasMutualRank(a, 0)

Part 3
如果rank有浮动，rank和rank - 1都可以算作hasMutualRank，输出所有有mutual rank的user pair

Part 4
Anti-ranking. ‍‍‍‍‌‍‍‍‍‍‌‍‍‍‌‌‍比如 {a: [b,c,d], b: [d,c,a]}，b是a的第一rank，同时a是b的倒数第一rank，这个才能算作hasAntiMutualRank。Follow-up是如果这个anti-rank也是像part 3是浮动的怎么办

https://www.1point3acres.com/bbs/thread-908575-1-1.html

很快收到拒信，说面的很好但是没有find strong fit。还说了“everyone really enjoyed their conversations with you”。浪费感情浪费时间啊，很生气，虽然只想拿他家compete。发一下他们的面经。他家怎么样才能过啊？是需要简历也非常match吗？
电面就是Server Remove Penalty，看最强总结帖就够 https://www.1point3acres.com/bbs/thread-795392-1-1.html

VO （我用的java）
第一轮常规HM BQ，着重说了leadership，感觉很好。

第二轮Coding，设计load balancer，不难，地里面经写的不是很细。第一问就是每个request有一个weight，我用PQ存，每次找最小的serve。第二问是每个request有TTL，商量后不考虑distributed和多线程，我propose lazy delete TTL，每次有request时候直接brute force PQ里的所有request。follow up了一个我提到的test问题，我觉得用一个clock最好，test的时候可以把fake clock穿进去，就不用thread.sleep来test了 which is flaky。这轮能感觉面试官挺满意。

第三轮integration，bike map，过程也不错，直接附了我写的答案。之前没练过，基本写出来了。handle_json是例子没用。第一问写的readJson，第二问sen‍‍‍‍‌‍‍‍‍‍‌‍‍‍‌‌‍dRequest，第三问sendRequest里面加一点第一问的到的field继续send。

第四轮moshi debug表演，感觉演的也可以。附件中错误的两行代码在ParserStack里，我注释掉了，可以直接run自己debug试试。

第五轮design ledger，我觉得说的也不错，国人面试官，帮我把控时间，所有问题都接住了，感觉说的不错。但是时间不够，有些我想到的点没说到。但是看他反应感觉也不错啊。


