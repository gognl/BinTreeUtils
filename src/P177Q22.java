
public class P177Q22 {

	public static <T> boolean isBalanced(BinNode<T> root) {
		if (root == null) return true;
		if (root.hasLeft() ^ root.hasRight()) return false;
		return isBalanced(root.getLeft()) && isBalanced(root.getRight());
	}

}
