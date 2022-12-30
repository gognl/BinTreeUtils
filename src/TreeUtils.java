import java.util.InputMismatchException;
import java.util.Scanner;

public class TreeUtils {
	
	private static class Node<T> {
		private T value;
		private Node<T> next;
		
		Node(T value, Node<T> next){
			this.value = value;
			this.next = next;
		}
		
		Node(T value){
			this.value = value;
			this.next = null;
		}
		
		public T getValue() { return value; }
		public Node<T> getNext() { return next; }
		public void setValue(T value) { this.value = value; }
		public void setNext(Node<T> next) { this.next = next; }
		public boolean hasNext() { return next != null; }
		public String toString() { return value + "-->" + next; }
	}
	private static class Queue<T> {
		private Node<T> first, last;
		
		Queue(){
			this.first = null;
			this.last = null;
		}
		
		void insert(T x) {
			Node<T> temp = new Node<T>(x);
			if (first == null) first = temp;
			else last.setNext(temp);
			last = temp;
		}
		
		T remove() {
			T x = first.getValue();
			first = first.getNext();
			if (first == null) last = null;
			return x;
		}
		
		T head() {
			return first.getValue();
		}
		
		boolean isEmpty() {
			return first == null;
		}
		
		public String toString() {
			if (this.isEmpty()) return "[]";
			this.insert(null);
			String s = "[";
			T temp = this.remove();
			while (temp != null) {
				s += temp + ",";
				this.insert(temp);
				temp = this.remove();
			}
			return s.substring(0, s.length()-1) + "]";
					
		}
		
	}
	private static class LLUtils {
		public static <T> Node<T> getLast(Node<T> first){
			if (first == null) return null;
			while (first.hasNext()) first = first.getNext();
			return first;
		}
		
		public static <T> Node<T> changeToUntil(Node<T> first, T val){
			if (first == null || !first.hasNext()) return null;
			Node<T> temp = first;
			while(temp.hasNext() && temp.getNext().getValue() != val) temp = temp.getNext();
			Node<T> backup = temp.getNext().getNext();
			temp.setNext(null);
			return backup;
		}
		
		public static <T> boolean isInList(Node<T> first, T val) {
			while (first != null) {
				if (first.getValue() == val) return true;
				first = first.getNext();
			}
			return false;
		}
		
		public static <T> Node<T> addNode(Node<T> first, Node<T> node){
			if (first == null) return node;
			getLast(first).setNext(node);
			return first;
		}
	
		public static <T> Node<T> reverse(Node<T> first){
			Node<T> reversed=null;    
		    while(first != null){
		        Node<T> next=first.getNext();
		        first.setNext(reversed);
		        reversed=first;
		        first=next;
		    }
		    return reversed;
		}
		
		public static <T> int getLength(Node<T> first) {
			if (first == null) return 0;
			return 1 + getLength(first.getNext());
		}
		
	    public static <T> T extractRandomValue(Node<T> first){
			int idx = getRand(1, getLength(first));
			for(int i = 0; i<idx-1; i++) first = first.getNext();
			T output = first.getNext().getValue();
			first.setNext(first.getNext().getNext());
			return output;
	    }
	}
	
	public static <T> Node<T> getPreOrderTraversal(BinNode<T> root) {
		if (root == null) return null;
		Node<T> root_n = new Node<T>(root.getValue());
		Node<T> left_n = getPreOrderTraversal(root.getLeft());
		Node<T> right_n = getPreOrderTraversal(root.getRight());
		if (left_n != null) {
			root_n.setNext(left_n);
			LLUtils.getLast(left_n).setNext(right_n);
		} else root_n.setNext(right_n);
		return root_n;
	}
	public static <T> Node<T> getInOrderTraversal(BinNode<T> root) {
		if (root == null) return null;
		Node<T> root_n = new Node<T>(root.getValue());
		Node<T> left_n = getInOrderTraversal(root.getLeft());
		Node<T> right_n = getInOrderTraversal(root.getRight());
		root_n.setNext(right_n);
		if (left_n == null) return root_n;
		LLUtils.getLast(left_n).setNext(root_n);
		return left_n;
	}
	public static <T> Node<T> getPostOrderTraversal(BinNode<T> root){
		if (root == null) return null;
		Node<T> root_n = new Node<T>(root.getValue());
		Node<T> left_n = getPostOrderTraversal(root.getLeft());
		Node<T> right_n = getPostOrderTraversal(root.getRight());
		if (right_n == null) right_n = root_n;
		else LLUtils.getLast(right_n).setNext(root_n);
		if (left_n == null) left_n = right_n;
		else LLUtils.getLast(left_n).setNext(right_n);
		
		return left_n;
	}
	public static <T> Node<T> getLevelOrderTraversal(BinNode<T> root){
		if (root == null) return null;
		Node<T> output = new Node<T>(null), last = output;
		
		BinNode<T> head;
		Queue<BinNode<T>> q = new Queue<BinNode<T>>();
		q.insert(root);
		while (!q.isEmpty()) {
			head = q.remove();
			last.setNext(new Node<T>(head.getValue()));
			last = last.getNext();
			if (head.hasLeft()) q.insert(head.getLeft());
			if (head.hasRight()) q.insert(head.getRight());
		}
		return output.getNext();
	}

    public static <T> BinNode<T> getTreeByPre(Node<T> pre, Node<T> in){
    	if (pre == null) return null;
    	T val = pre.getValue();
    	if (pre.getNext() == null) return new BinNode<T>(val);
    	
    	Node<T> left_in = in;
    	Node<T> right_in = LLUtils.changeToUntil(in, val); // Updates in
    	
    	Node<T> left_pre = null, right_pre = null;
    	Node<T> temp = pre;
    	while(temp != null) {
    		if(LLUtils.isInList(left_in, temp.getValue())) left_pre = LLUtils.addNode(left_pre, new Node<T>(temp.getValue()));
    		else if(LLUtils.isInList(right_in, temp.getValue())) right_pre = LLUtils.addNode(right_pre, new Node<T>(temp.getValue()));
    		temp = temp.getNext();
    	}
    	
    	return new BinNode<T>(getTreeByPre(left_pre, left_in), val, getTreeByPre(right_pre, right_in));
    }
    public static <T> BinNode<T> getTreeByPost(Node<T> post, Node<T> in){
    	return getTreeByPre(LLUtils.reverse(post), in);
    }
    
    private static <T> String getTreeDiagram(BinNode<T> root, String prefix, int dir) {
        if (root == null) {
        	return "";
        }

        String space = " ".repeat(("" + root.getValue()).length());
        return getTreeDiagram(root.getRight(), prefix + "│  ".charAt(dir) + space, 2)
            + prefix + "└ ┌".charAt(dir) + root.getValue()
            + " ┘┐┤".charAt((root.getLeft()  != null ? 2 : 0) 
                            + (root.getRight() != null ? 1 : 0)) + "\n"
            + getTreeDiagram(root.getLeft(), prefix + "  │".charAt(dir) + space, 0);
    }
    public static <T> String getTreeDiagram(BinNode<T> root) {
    	return getTreeDiagram(root, "", 1);
    }
    
    private static double roundTo(double n, int p) {
    	return Math.floor(n*Math.pow(10, p))/Math.pow(10, p);
    }
    private static int getRand(int min, int max) {
    	return (int)(Math.random()*(max-min)+min);
    }
    private static double getRand(double min, double max, int prec) {
    	return roundTo(Math.random()*(max-min)+min, prec);
    }
    private static <T> BinNode<T> getRandomTreeIndivP(int nodes, Node<T> values){
    	if (nodes == 0 || values == null) return null;
    	if (nodes == 1) return new BinNode<T>(LLUtils.extractRandomValue(values));
    	int left_nodes = getRand(0, nodes);
    	int right_nodes = nodes-1-left_nodes;
    	return new BinNode<T>(getRandomTreeIndivP(left_nodes, values), LLUtils.extractRandomValue(values), getRandomTreeIndivP(right_nodes, values));
    }
    private static <T> BinNode<T> getRandomTreeBalancedP(int nodes, Node<T> values){
    	if (nodes == 0 || values == null) return null;
    	if (nodes == 1) return new BinNode<T>(LLUtils.extractRandomValue(values));
    	int left_nodes = nodes/2;
    	int right_nodes = nodes-1-left_nodes;
    	return new BinNode<T>(getRandomTreeBalancedP(left_nodes, values), LLUtils.extractRandomValue(values), getRandomTreeBalancedP(right_nodes, values));
    }
    
    public static BinNode<Integer> getRandomTree(int nodes, int min, int max){
    	if (nodes == 0) return null;
    	if (nodes == 1) return new BinNode<Integer>(getRand(min, max));
    	int left_nodes = getRand(0, nodes-1);
    	int right_nodes = nodes-1-left_nodes;
    	return new BinNode<Integer>(getRandomTree(left_nodes, min, max), getRand(min, max), getRandomTree(right_nodes, min, max));
    }
    public static BinNode<Double> getRandomTree(int nodes, double min, double max, int prec){
    	if (nodes == 0) return null;
    	if (nodes == 1) return new BinNode<Double>(getRand(min, max, prec));
    	int left_nodes = getRand(0, nodes-1);
    	int right_nodes = nodes-1-left_nodes;
    	return new BinNode<Double>(getRandomTree(left_nodes, min, max, prec), getRand(min, max, prec), getRandomTree(right_nodes, min, max, prec));
    }
    public static <T> BinNode<T> getRandomTree(int nodes, T[] values){
    	if (nodes == 0 || values.length == 0) return null;
    	if (nodes == 1) return new BinNode<T>(values[getRand(0, values.length)]);
    	int left_nodes = getRand(0, nodes);
    	int right_nodes = nodes-1-left_nodes;
    	return new BinNode<T>(getRandomTree(left_nodes, values), values[getRand(0, values.length)], getRandomTree(right_nodes, values));
    }
    public static <T> BinNode<T> getRandomTreeIndiv(int nodes, Node<T> values){
    	if (nodes > LLUtils.getLength(values)) return null;
    	return getRandomTreeIndivP(nodes, new Node<T>(null, values));
    }
    public static BinNode<Integer> getRandomTreeIndiv(int nodes, int min, int max){
    	Node<Integer> first = new Node<Integer>(min), temp = first;
    	for(int i = min+1; i<max; i++) {
    		temp.setNext(new Node<Integer>(i));
    		temp = temp.getNext();
    	}
    	return getRandomTreeIndiv(nodes, first);
    }
    public static BinNode<Integer> getRandomTree(int nodes){
    	return getRandomTreeIndiv(nodes, 1, nodes+1);
    }
    public static BinNode<Integer> getRandomTreeBalanced(int nodes){
    	int min = 1, max = nodes+1;
    	Node<Integer> first = new Node<Integer>(min), temp = first;
    	for(int i = min; i<max; i++) {
    		temp.setNext(new Node<Integer>(i));
    		temp = temp.getNext();
    	}
    	return getRandomTreeBalancedP(nodes, first);
    }
    
    public static <T> BinNode<T> clone(BinNode<T> root){
    	if (root == null) return null;
    	return new BinNode<T>(clone(root.getLeft()), root.getValue(), clone(root.getRight()));
    }
    public static <T> boolean isInTree(BinNode<T> root, T val) {
    	if (root == null) return false;
    	if (root.getValue() == val) return true;
    	return isInTree(root.getLeft(), val) || isInTree(root.getRight(), val);
    }
    public static <T> int getHeight(BinNode<T> root) {
    	if (root == null) return 0;
    	if (!root.hasLeft() && !root.hasRight()) return 0;
    	return 1 + Math.max(getHeight(root.getLeft()), getHeight(root.getRight()));
    }
    public static boolean isAllPositive(BinNode<Integer> root) {
    	if (root == null) return true;
    	return root.getValue() > 0 && isAllPositive(root.getLeft()) && isAllPositive(root.getRight());
    }
    public static <T> boolean isFull(BinNode<T> root) {
    	return Math.pow(2, getHeight(root)) == getLeafCount(root);
    }
    public static <T> boolean isLeaf(BinNode<T> root) {
    	return !(root.hasLeft() || root.hasRight());
    }
    public static Integer getMax(BinNode<Integer> root) {
    	if (root == null) return null;
    	int max = root.getValue();
    	if (root.hasLeft()) max = Math.max(max, getMax(root.getLeft()));
    	if (root.hasRight()) max = Math.max(max, getMax(root.getRight()));
    	return max;
    }
    public static <T> int getLeafCount(BinNode<T> root){
    	if (root == null) return 0;
    	if(!root.hasLeft() && !root.hasRight()) return 1;
    	return getLeafCount(root.getLeft()) + getLeafCount(root.getRight());
    }
    public static <T> int getNodeCount(BinNode<T> root) {
    	if (root == null) return 0;
    	return 1 + getNodeCount(root.getLeft()) + getNodeCount(root.getRight());
    }
    public static <T> BinNode<T> getParent(BinNode<T> root, BinNode<T> child){
    	if (root == child || root == null) return null;
    	if (root.getLeft() == child || root.getRight() == child) return root;
    	BinNode<T> p = getParent(root.getLeft(), child);
    	if (p != null) return p;
    	return getParent(root.getRight(), child);
    }
    public static Integer sum(BinNode<Integer> root) {
    	if (root == null) return null;
    	int s = root.getValue();
    	if (root.hasLeft()) s += sum(root.getLeft());
    	if (root.hasRight()) s += sum(root.getRight());
    	return s;
    }
    public static Integer sumInLevel(BinNode<Integer> root, int level) {
    	if(root == null) return 0;
    	if(level == 0) return root.getValue();
    	return sumInLevel(root.getLeft(), level-1) + sumInLevel(root.getRight(), level-1);
    }
    public static <T> boolean isPerfect(BinNode<T> root) {
    	if (root == null) return true;
    	return isPerfect(root.getLeft()) && isPerfect(root.getRight()) && getHeight(root.getLeft()) == getHeight(root.getRight());
    }
    
    static Scanner in = new Scanner(System.in);
    public static BinNode<Integer> getTreeInput() {
    	Integer val;
    	try {
    		val = in.nextInt();
    	} catch(InputMismatchException e) {
    		in.nextLine();
    		return null;
    	}
    	System.out.println("Enter left for node " + val + ":");
    	BinNode<Integer> left = getTreeInput();
    	System.out.println("Enter right for node " + val + ":");
    	BinNode<Integer> right = getTreeInput();
    	return new BinNode<Integer>(left, val, right);
    }
    
    public static void main(String[] args) {
    	System.out.println(getRandomTreeBalanced(6));
    }
    
}
