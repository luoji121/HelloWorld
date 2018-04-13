package linked.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LinkedHandler {
	/*public LinkedNode intersect(LinkedNode ln1, LinkedNode ln2) {
//		List<Integer> intList = new ArrayList<>();
		LinkedNode returnLn = null;
		LinkedNode curLn = null;
		if(ln1.val<ln2.val) {
			returnLn = ln1;
			curLn = ln1;
			if(ln1.hasNext()) {
				ln1 = ln1.next;
			}
			else {
				returnLn.next=ln2;
				return returnLn;
			}
		}else {
			returnLn = ln2;
			curLn = ln2;
			if(ln2.hasNext()) {
				ln2 = ln2.next;
			}
			else {
				returnLn.next=ln1;
				return returnLn;
			}
		}//if(ln1.val<ln2.val) else
		while(ln1.hasNext()) {
			if(ln1.val>ln2.val) {
				ln
			}
			
			ln1 = ln1.next;
		}
		return returnLn;
		
	}*/
	
	public LinkedNode intersect(LinkedNode ln1, LinkedNode ln2) {
		List<Integer> li = new ArrayList<>();
		while(ln1.hasNext() && ln2.hasNext()) {
			if(ln1.val==ln2.val) {
				li.add(ln1.val);
				ln1 = ln1.next;
				ln2 = ln2.next;
			}else if(ln1.val>ln2.val) {
				ln2 = ln2.next;
			}else{//if(ln1.val<ln2.val)
				ln1 = ln1.next;
			}
		}
		
		LinkedNode returnLn= null;
		Collections.reverse(li);
//		li.forEach(e -> {
//			returnLn = new LinkedNode(e, returnLn);
//		});
		for(Integer i:li) {
			returnLn = new LinkedNode(i, returnLn);
		}
		return returnLn;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
