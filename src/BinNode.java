

public class BinNode<T> {
	private BinNode<T> left, right;
	private T value;
	
	BinNode(T value){
		this.value = value;
	}
	
	BinNode(BinNode<T> left, T value, BinNode<T> right){
		this.left = left;
		this.value = value;
		this.right = right;
	}
	
	T getValue() { return value; }
	BinNode<T> getLeft(){ return left; }
	BinNode<T> getRight() { return right; }
	boolean hasLeft() { return left != null; }
	boolean hasRight() { return right != null; }
	void setValue(T value) { this.value = value; }
	void setLeft(BinNode<T> left) { this.left = left; }
	void setRight(BinNode<T> right) { this.right = right; }
	
    private String getTreeDiagram(BinNode<T> root, String prefix, int dir) {
        if (root == null) return "";
        String space = " ".repeat(("" + root.getValue()).length());
        return getTreeDiagram(root.getRight(), prefix + "│  ".charAt(dir) + space, 2)
            + prefix + "└ ┌".charAt(dir) + root.getValue()
            + " ┘┐┤".charAt((root.getLeft()  != null ? 2 : 0) 
                            + (root.getRight() != null ? 1 : 0)) + "\n"
            + getTreeDiagram(root.getLeft(), prefix + "  │".charAt(dir) + space, 0);
    }
    
	public String toString() { return getTreeDiagram(this, "", 1); }
	
}
