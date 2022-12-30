
public class P177Q23 {
	
	// A
	public static Integer getMaxValue(BinNode<Integer> root) {
		if (root == null) return null;
		if (!root.hasLeft() && !root.hasRight()) return root.getValue();
		if (!root.hasRight()) return Math.max(root.getValue(), getMaxValue(root.getLeft()));
		if (!root.hasLeft()) return Math.max(root.getValue(), getMaxValue(root.getRight()));
		return Math.max(Math.max(root.getValue(), getMaxValue(root.getRight())), getMaxValue(root.getLeft()));
	}
	
	// B
	public static Integer getMinValue(BinNode<Integer> root) {
		if (root == null) return null;
		if (!root.hasLeft() && !root.hasRight()) return root.getValue();
		if (!root.hasRight()) return Math.min(root.getValue(), getMinValue(root.getLeft()));
		if (!root.hasLeft()) return Math.min(root.getValue(), getMinValue(root.getRight()));
		return Math.min(Math.min(root.getValue(), getMinValue(root.getRight())), getMinValue(root.getLeft()));
	}
	
	// C
	public static void main(String[] args) {
		BinNode<Integer> root = new BinNode<Integer>(1);
		BinNode<Integer> a = new BinNode<Integer>(2);
		BinNode<Integer> b = new BinNode<Integer>(3);
		BinNode<Integer> c = new BinNode<Integer>(4);
		root.setLeft(a);
		a.setRight(b);
		root.setRight(c);
		System.out.println(getMinValue(root)-getMaxValue(root)); // abs diff?
	}

}
