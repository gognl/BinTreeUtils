
public class P177Q25 {
	
	private static boolean isDescendantPriv(BinNode<Character> root, char c) {
		if (root == null) return false;
		if (root.getValue() == c) return true;
		return isDescendantPriv(root.getLeft(), c) || isDescendantPriv(root.getRight(), c);
	}
	
	public static boolean isDescendant(BinNode<Character> t, char c1, char c2) {
		if (t == null) return false;
		if (t.getValue() == c1) return isDescendantPriv(t, c2);
		if (t.getValue() == c2) return isDescendantPriv(t, c1);
		return isDescendant(t.getLeft(), c1, c2) || isDescendant(t.getRight(), c1, c2);
	}

}
