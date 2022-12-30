
public class P176Q13 {
	
	public static void printClosestSon(BinNode<Integer> root) {
		if (root == null || !root.hasLeft() && !root.hasRight()) return;
		if (!root.hasRight()) System.out.print(root.getLeft().getValue() + " ");
		else if (!root.hasLeft()) System.out.print(root.getRight().getValue() + " ");
		else if  (Math.abs(root.getValue()-root.getRight().getValue()) < Math.abs(root.getValue()-root.getLeft().getValue()))
			System.out.print(root.getRight().getValue() + " ");
		else System.out.print(root.getLeft().getValue() + " ");
		
		printClosestSon(root.getLeft());
		printClosestSon(root.getRight());
	}

}
