
public class P177Q24 {
	
	// A
	public static <T> boolean hasOneChild(BinNode<T> node) {
		if (node == null) return false;
		return node.hasLeft() ^ node.hasRight();
	}
	
	// B
	public static <T> int getOnlyChildCount(BinNode<T> root) {
		if (root == null) return 0;
		int s = hasOneChild(root) ? 1 : 0;
		return s + getOnlyChildCount(root.getLeft()) + getOnlyChildCount(root.getRight());
	}
	
	// C
	public static int getOnlyGrandsonCount(BinNode<Integer> root) {
		if (root == null) return 0;
		int c = hasOneChild(root) && (hasOneChild(root.getLeft()) || hasOneChild(root.getRight())) ? 1 : 0;  
		return c + getOnlyGrandsonCount(root.getLeft()) + getOnlyGrandsonCount(root.getRight());
		
	}

}
