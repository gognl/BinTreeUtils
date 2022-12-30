
public class P176Q09 {
	
	public static void update(BinNode<Character> root) {
		if (root == null) return;
		if (root.getValue() == 'z') root.setValue('a');
		else root.setValue((char)(root.getValue() + 1));
		update(root.getLeft());
		update(root.getRight());
	}

}
