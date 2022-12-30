
public class P176Q15 {
	
	public static int getBetweenCount(BinNode<Integer> root, int low, int high) {
		if (root == null) return 0;
		if (root.getValue() > low && root.getValue() < high) 
			return 1 + getBetweenCount(root.getLeft(), low, high) + getBetweenCount(root.getRight(), low, high);
		return getBetweenCount(root.getLeft(), low, high) + getBetweenCount(root.getRight(), low, high);
	}

}
