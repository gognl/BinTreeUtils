
public class P177Q20 {
	
	public static <T> int getValCount(BinNode<T> root, Integer n) {
		if (root == null) return 0;
		if (root.getValue() == n) return 1 + getValCount(root.getLeft(), n) + getValCount(root.getRight(), n);
		return getValCount(root.getLeft(), n) + getValCount(root.getRight(), n);
	}
	
	public static <T> boolean isConsNTree(BinNode<T> root, Integer n) {
		if (root == null || !(root.getValue() instanceof Integer)) return false;
		if (n == 0) return true;
		if (getValCount(root, n) != 1) return false;
		return isConsNTree(root, n-1);
	}

}
