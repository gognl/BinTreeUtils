
public class P176Q14 {
	
	public static <T> int getLeafCount(BinNode<T> root) {
		if (root == null) return 0;
		if (!root.hasLeft() && !root.hasRight()) return 1;
		return getLeafCount(root.getLeft()) + getLeafCount(root.getRight());
	}

}
