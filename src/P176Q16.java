
public class P176Q16 {
	
	public static int getParentsSum(BinNode<Integer> root) {
		if (root == null) return 0;
		if (root.hasLeft() && root.hasRight()) 
			return root.getValue() + getParentsSum(root.getLeft()) + getParentsSum(root.getRight());
		return getParentsSum(root.getLeft()) + getParentsSum(root.getRight());
	}

}
