package leetcode18.greedy;
/**
 * 134. There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
 */
public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumGas = 0;
        int sumCost = 0;
        int tank = 0;
        int start = 0;
        
        // If car starts at A and can not reach B. Any station between A and B can not reach B.
        // B is the first station that A can not reach.
        for (int i = 0; i < gas.length; i++) {
            sumGas += gas[i];
            sumCost += cost[i];
            tank += gas[i] - cost[i];
            
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        
        // If the total number of gas is bigger than the total number of cost. There must be a solution.
        if (sumGas >= sumCost) {
            return start;
        } else {
            return -1;
        }
    }

}
