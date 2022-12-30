
public class P177Q21 {
	
	public static <T> int getHeight(BinNode<T> root) {
    	if (root == null) return 0;
    	if (!root.hasLeft() && !root.hasRight()) return 0;
    	return 1 + Math.max(getHeight(root.getLeft()), getHeight(root.getRight()));
    }
	
	public static boolean isSymmetrical(BinNode<Integer> root) {
		if (root == null) return true;
		int cl = root.hasLeft() ? 1 : 0,
			cr = root.hasRight() ? 1 : 0;
		if (cl+getHeight(root.getLeft()) - (cr+getHeight(root.getRight())) > 1) return false;
		//if (Math.abs(cl+getHeight(root.getLeft()) - (cr+getHeight(root.getRight())) > 1) return false;
		return isSymmetrical(root.getLeft()) && isSymmetrical(root.getRight());
	}

}
