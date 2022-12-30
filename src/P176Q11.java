
public class P176Q11 {
	
	public static void printSpecificNodes(BinNode<Integer> root) {
		if (root == null) return;
		if (root.getValue()%2 == 0) {
			if(!root.hasLeft() || root.getLeft().getValue()%2 == 0) {
				if (!root.hasRight() || root.getRight().getValue()%2 == 0)
					System.out.print(root.getValue() + " ");
			}
		}
		printSpecificNodes(root.getLeft());
		printSpecificNodes(root.getRight());
	}

}
