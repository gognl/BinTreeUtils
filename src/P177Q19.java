
public class P177Q19 {
	
	public static int getTreeSum(BinNode<Integer> root) {
		if(root == null) return 0;
		return root.getValue() + getTreeSum(root.getLeft()) + getTreeSum(root.getRight());
	}

}
