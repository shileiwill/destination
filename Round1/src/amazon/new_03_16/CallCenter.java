package amazon.new_03_16;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CallCenter {
	List<List<Representative>> handlers = new ArrayList<List<Representative>>();
	List<LinkedList<Call>> calls = new ArrayList<LinkedList<Call>>();
	
	public void dispatchCall(Caller caller) {
		Call call = new Call(caller); // Add additional atttribute like call-in time
		dispatchCall(call);
	}
	
	void dispatchCall(Call call) {
		int level = call.getRank();
		
		List<Representative> rep = handlers.get(level);
		for (Representative r : rep) {
			if (r.currentCall == null) {
				r.currentCall = call;
				call.setHandler(r);
				break;
			}
		}
		
		if (call.handler == null) {
			call.setRank(+1);
			if (call.getRank() < 4) {
				dispatchCall(call);
			} else {
				//wait in queue
				calls.get(call.getRank()).offer(call);
			}
		}
	}
	
}

abstract class Representative {
	Rank rank;
	String name;
	Call currentCall = null; // This will also decide if this representative is available or not
	
	void escalateCallToNextLeval(Call call) {
		call.setRank(call.getRank().getRank() + 1);
		
		CallCenter.dispatchCall(call);
	}
	
	boolean pickUpCall(Call call) {
		this.currentCall = call;
		call.setHandler(this);
	}
	
	void finishCall(Call call) {
		this.currentCall = null;
		call.setResult(this, "Done OR Escalated");
	}
}

class Director extends Representative {
	Director() {
		this.rank = Rank.DIRECTOR;
	}
	
	void escalateCallToNextLeval(Call call) {
		call.setRank(this.rank.getRank() + 1);
	}
}

enum Rank {
	RESPONDENT(3), MANAGER(2), DIRECTOR(1);
	
	private int value;
	Rank(int value) {
		this.value = value;
	}
	
	int getRank() {
		return value;
	}
}

class Call {
	Rank rank;
	Caller caller = null;
	Representative handler = null;
	
	Call(Caller caller) {
		this.caller = caller;
		this.rank = Rank.RESPONDENT;
	}
	
	void setHandler(Representative handler) {
		this.handler = handler;
	}
	
	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}
}

class Caller {
	
}