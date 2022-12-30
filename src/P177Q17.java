
public class P177Q17 {

	public static int getParentsNoLeaves(BinNode<Integer> root) {
		if (root == null) return 0;
		if (root.hasLeft() && root.hasRight()) {
			if (root.getLeft().hasLeft() || root.getLeft().hasRight()) {
				if (root.getRight().hasLeft() || root.getRight().hasRight()) {
					return 1 + getParentsNoLeaves(root.getLeft()) + getParentsNoLeaves(root.getRight());
				}
			}
		}
		return getParentsNoLeaves(root.getLeft()) + getParentsNoLeaves(root.getRight());
	}

}
