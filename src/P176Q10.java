
public class P176Q10 {
	
	public static <T> void printLeaves(BinNode<T> root) {
		if(root == null) return;
		printLeaves(root.getLeft());
		if (!root.hasLeft() && !root.hasRight()) System.out.print(root.getValue() + " ");
		printLeaves(root.getRight());
	}

}
