
public class P177Q27 {

	public static <T> int getHeight(BinNode<T> root) {
    	if (root == null) return 0;
    	if (!root.hasLeft() && !root.hasRight()) return 0;
    	return 1 + Math.max(getHeight(root.getLeft()), getHeight(root.getRight()));
    }

}
