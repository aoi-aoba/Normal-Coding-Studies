package BST;

public class BinarySearchTree implements IndexInterface<TreeNode> {
    private TreeNode root;
    public BinarySearchTree() {
        root = null;
    }

    public TreeNode search(Comparable searchKey) {
        return searchItem(root, searchKey);
    }

    public TreeNode searchItem(TreeNode tNode, Comparable searchKey) {
        if (tNode == null) return null; // 검색에 실패함
        else if (searchKey.compareTo(tNode.key) == 0)
            return tNode;
        else if (searchKey.compareTo(tNode.key) < 0)
            return searchItem(tNode.left, searchKey);
        else return searchItem(tNode.right, searchKey);
    }

    public void insert(Comparable newKey) {
        root = insertItem(root, newKey);
    }

    public TreeNode insertItem(TreeNode tNode, Comparable newItem) {
        if (tNode == null) // 빈 트리이거나 리프 노드 아래에 삽입
            tNode = new TreeNode(newItem, null, null);
        else if (newItem.compareTo(tNode.key) < 0) // 왼쪽으로 삽입
            tNode.left = insertItem(tNode.left, newItem);
        else tNode.right = insertItem(tNode.right, newItem); // 오른쪽으로 삽입
        return tNode;
    }

    public void delete(Comparable searchKey) {
        root = deleteItem(root, searchKey);
    }

    public TreeNode deleteItem(TreeNode tNode, Comparable searchKey) {
        if (tNode == null) return null; // 찾을 수 없는 = 삭제할 수 없는 키 값
        else {
            // 운 좋게 지울 값을 바로 찾았다면 바로 노드 삭제로 넘어가기
            if (searchKey == tNode.key)
                tNode = deleteNode(tNode);
            // 못 찾았다면 작은 값, 큰 값 비교해가면서 쭉쭉 찾아가기
            else if (searchKey.compareTo(tNode.key) < 0)
                tNode.left = deleteItem(tNode.left, searchKey);
            else tNode.right = deleteItem(tNode.right, searchKey);
            return tNode; // 지운 노드를 반환
        }
    }

    public TreeNode deleteNode(TreeNode tNode) { // 지울 노드를 받아왔으니
        if (tNode.left == null && tNode.right == null) // 자식이 없으면 그냥 지워버리자
            return null;
        else if (tNode.left == null) // 왼쪽 자식만 없는 경우
            return tNode.right; // 오른쪽 자식을 받아와서 그대로 위치해주면 사라짐
        else if (tNode.right == null) // 오른쪽 자식만 없는 경우
            return tNode.left; // 왼쪽 자식을 받아와서 그대로 위치해주면 사라짐
        else { // 양쪽의 자식이 모두 있는 경우
            returnPair rPair = deleteMinItem(tNode.right);
            // 우서브트리의 최솟값을 찾기 위해 바로 오른쪽 한 칸으로만 이동해주기, 최소 노드를 받아오기
            tNode.key = rPair.key;
            tNode.right = rPair.node;
            // 원래 지우려고 했던 노드를 대체할 노드로 바꿔주는 과정
            return tNode;
        }
    }

    private returnPair deleteMinItem(TreeNode tNode) {
        if (tNode.left == null) // 찾은 노드가 우서브트리의 최솟값이어서 더 이상 왼쪽 자식이 없다면
            return new returnPair(tNode.key, tNode.right); // 해당 노드를 받아와서 그대로 올려주기
        else { // 아니라면
            returnPair rPair = deleteMinItem(tNode.left); // 최소 노드 찾을때까지 계속 왼쪽으로 내려가고
            tNode.left = rPair.node;
            rPair.node = tNode;
            return rPair;
        }
    }

    // 순서쌍 형태로 반환하기 위한 임의의 클래스 설정
    private class returnPair {
        private Comparable key;
        private TreeNode node;
        private returnPair(Comparable item, TreeNode node) {
            key = item;
            this.node = node;
        }
    }

    // 비어 있으면 루트 노드가 null
    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }
}
