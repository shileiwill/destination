package amazon;

public class PrisonerAndPoison {

}

/*
A bad king has a cellar of 1000 bottles of delightful and very expensive wine.

A neighboring queen plots to kill the bad king and sends a servant to poison the wine. 
Fortunately (or say unfortunately) the bad king’s guards catch the servant after he has only poisoned one bottle. 
Alas, the guards don’t know which bottle but know that the poison is so strong that even if diluted 100,000 times it would still kill the king.

Furthermore, it takes one month to have an effect. The bad king decides he will get some of the prisoners in his vast dungeons to drink the wine. 
Being a clever bad king he knows he needs to murder as less prisoners as possible – believing he can fob off such a low death rate – 
and will still be able to drink the rest of the wine (999 bottles) at his anniversary party in 5 weeks time.

In the worst case, what is the minimum number of prisoner he would have to kill in order to find out the poisoned bottle? 
Do note that the king wants to minimize the number of prisoners involved in the experiment. 
He might decide to kill every prisoner involved in the experiment if he feels that they may tell the world about his evil plans.


Number the bottles 1 to 1000 and write the number in binary format.

bottle 1 = 0000000001 (10 digit binary)
bottle 2 = 0000000010
bottle 500 = 0111110100
bottle 1000 = 1111101000
Now take 10 prisoners and number them 1 to 10, now let prisoner 1 take a sip from every bottle that has a 1 in its least significant bit. Let prisoner 10 take a sip from every bottle with a 1 in its most significant bit. etc.

prisoner =  10 9 8 7 6 5 4 3 2 1
bottle 924 = 1 1 1 0 0 1 1 1 0 0
For instance, bottle no. 924 would be sipped by 10, 9, 8, 5, 4 and 3. 
That way if bottle no. 924 was the poisoned one, only those prisoners would die.
After four weeks, line the prisoners up in their bit order and read each living prisoner as a 0 bit and each dead prisoner as a 1 bit. 
The number that you get is the bottle of wine that was poisoned.

1000 is less than 1024 (2^10). If there were 1024 or more bottles of wine it would take more than 10 prisoners to do the experiment.

Now, lets look at the number of prisoners that will die in the worst case. Thats equal to the maximum number of 1 bits in numbers from 0 - 999. 
Number 511 has 9 1 bits. The first number with 10 set bits is 2^10 - 1 = 1023. Since, thats outside our range, 
the maximum number of prisoners that will die in the experiment is 9.
*/