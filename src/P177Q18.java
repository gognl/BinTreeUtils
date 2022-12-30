
public class P177Q18 {

    public static <T> boolean isInTree(BinNode<T> root, T val) {
    	if (root == null) return false;
    	if (root.getValue() == val) return true;
    	return isInTree(root.getLeft(), val) || isInTree(root.getRight(), val);
    }
	
	public static boolean isIncluded(BinNode<Integer> t1, BinNode<Integer> t2) {
		if (t2 == null) return true;
		return isInTree(t1, t2.getValue()) && isIncluded(t1, t2.getLeft()) && isIncluded(t1, t2.getRight());
	}

}
