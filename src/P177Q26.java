
public class P177Q26 {
	
	public static <T> int getHeight(BinNode<T> root) {
    	if (root == null) return 0;
    	if (!root.hasLeft() && !root.hasRight()) return 0;
    	return 1 + Math.max(getHeight(root.getLeft()), getHeight(root.getRight()));
    }
	
    public static <T> int getLeafCount(BinNode<T> root){
    	if (root == null) return 0;
    	if(!root.hasLeft() && !root.hasRight()) return 1;
    	return getLeafCount(root.getLeft()) + getLeafCount(root.getRight());
    }
	
	public static <T> boolean isFull(BinNode<T> root) {
		return Math.pow(2, getHeight(root)) == getLeafCount(root);
	}

}
