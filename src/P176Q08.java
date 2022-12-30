
public class P176Q08 {
	
	// A
	public static void printPostOrderNegative(BinNode<Integer> root) {
		if (root == null) return;
		printPostOrderNegative(root.getLeft());
		printPostOrderNegative(root.getRight());
		if (root.getValue() < 0) System.out.print(root.getValue() + " ");
	}
	
	// B
	public static <T> void printAllLeft(BinNode<T> root) {
		if (root == null) return;
		if (root.hasLeft()) System.out.print(root.getLeft().getValue() + " ");
		printAllLeft(root.getLeft());
		printAllLeft(root.getRight());
	}
	
	// C
	public static void main(String[] args) {
		// ?????????????
	}

}
