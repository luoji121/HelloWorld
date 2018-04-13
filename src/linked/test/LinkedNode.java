package linked.test;

public class LinkedNode {
	public int val;
	public LinkedNode next;
	public LinkedNode(int val, LinkedNode next) {
		super();
		this.val = val;
		this.next = next;
	}
	public boolean hasNext() {
		return next!=null;
	}
}
