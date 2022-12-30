
public class P176Q12 {
	
	public static int getCount10till100(BinNode<Integer> root) {
		if (root == null) return 0;
		if (root.getValue() >= 10 && root.getValue() < 100) return 1 + getCount10till100(root.getLeft()) + getCount10till100(root.getRight());
		return getCount10till100(root.getLeft()) + getCount10till100(root.getRight());
	}

}
