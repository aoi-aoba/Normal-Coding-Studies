package BST;

public class AVLTree implements IndexInterface<AVLNode> {
    private AVLNode root;
    static final AVLNode NIL = new AVLNode(null, null, null, 0);

    public AVLTree() {
        root = NIL;
    }

    public AVLNode search(Comparable x) {
        return searchItem(root, x);
    }

    private AVLNode searchItem(AVLNode tNode, Comparable x) {
        if (tNode == null) return NIL;
        else if (x.compareTo(tNode.item) == 0)
            return tNode;
        else if (x.compareTo(tNode.item) < 0)
            return searchItem(tNode.left, x);
        else return searchItem(tNode.right, x);
    }

    public void insert(Comparable x) {
        root = insertItem(root, x);
    }

    private AVLNode insertItem(AVLNode tNode, Comparable x) {
        if (tNode == NIL) // 빈 트리이거나 리프 노드 아래에 삽입
            tNode = new AVLNode(x);
        else if (x.compareTo(tNode.item) < 0) { // 왼쪽으로 삽입
            tNode.left = insertItem(tNode.left, x);
            tNode.height = 1 + Math.max(tNode.right.height, tNode.left.height);
            int type = needBalance(tNode); // 균형을 맞춰야 하는지 여부를 판단
            if (type != NO_NEED) tNode = balanceAVL(tNode, type);
        } else  {
            tNode.right = insertItem(tNode.right, x); // 오른쪽으로 삽입
            tNode.height = 1 + Math.max(tNode.right.height, tNode.left.height);
            int type = needBalance(tNode); // 균형을 맞춰야 하는지 여부를 판단
            if (type != NO_NEED) tNode = balanceAVL(tNode, type);
        }
        return tNode;
    }

    public void delete(Comparable x) {
        root = deleteItem(root, x);
    }

    private AVLNode deleteItem(AVLNode tNode, Comparable x) {
        if (tNode == NIL) return NIL;
        else {
            if (x.compareTo(tNode.item) == 0) tNode = deleteNode(tNode);
            else if (x.compareTo(tNode.item) < 0) {
                tNode.left = deleteItem(tNode.left, x);
                tNode.height = 1 + Math.max(tNode.right.height, tNode.left.height);
                int type = needBalance(tNode); // 균형을 맞춰야 하는지 여부를 판단
                if (type != NO_NEED) tNode = balanceAVL(tNode, type);
            } else {
                tNode.right = deleteItem(tNode.right, x);
                tNode.height = 1 + Math.max(tNode.right.height, tNode.left.height);
                int type = needBalance(tNode); // 균형을 맞춰야 하는지 여부를 판단
                if (type != NO_NEED) tNode = balanceAVL(tNode, type);
            }
            return tNode;
        }
    }

    private AVLNode deleteNode(AVLNode tNode) {
        if (tNode.left == NIL && tNode.right == NIL) // 자식이 없으면 그냥 지워버리자
            return NIL;
        else if (tNode.left == NIL) // 왼쪽 자식만 없는 경우
            return tNode.right; // 오른쪽 자식을 받아와서 그대로 위치해주면 사라짐
        else if (tNode.right == NIL) // 오른쪽 자식만 없는 경우
            return tNode.left; // 왼쪽 자식을 받아와서 그대로 위치해주면 사라짐
        else { // 양쪽의 자식이 모두 있는 경우
            returnPair rPair = deleteMinItem(tNode.right);
            // 우서브트리의 최솟값을 찾기 위해 바로 오른쪽 한 칸으로만 이동해주기, 최소 노드를 받아오기
            tNode.item = rPair.item;
            tNode.right = rPair.node;
            // 원래 지우려고 했던 노드를 대체할 노드로 바꿔주는 과정
            tNode.height = 1 + Math.max(tNode.right.height, tNode.left.height);
            int type = needBalance(tNode); // 균형을 맞춰야 하는지 여부를 판단
            if (type != NO_NEED) tNode = balanceAVL(tNode, type);
            return tNode;
        }
    }

    private returnPair deleteMinItem(AVLNode tNode) {
        int type;
        if (tNode.left == NIL) return new returnPair(tNode.item, tNode.right);
        else {
            returnPair rPair = deleteMinItem(tNode.left);
            tNode.left = rPair.node;
            tNode.height = 1 + Math.max(tNode.right.height, tNode.left.height);
            type = needBalance(tNode); // 균형을 맞춰야 하는지 여부를 판단
            if (type != NO_NEED) tNode = balanceAVL(tNode, type);
            rPair.node = tNode;
            return rPair;
        }
    }

    private class returnPair {
        private Comparable item;
        private AVLNode node;
        private returnPair(Comparable item, AVLNode node) {
            this.item = item;
            this.node = node;
        }
    }

    private AVLNode balanceAVL(AVLNode tNode, int type) {
        AVLNode returnNode = NIL;
        switch (type) {
            case LL:
                returnNode = rightRotate(tNode);
                break;
            case LR:
                tNode.left = leftRotate(tNode.left);
                returnNode = rightRotate(tNode);
                break;
            case RR:
                returnNode = leftRotate(tNode);
                break;
            case RL:
                tNode.right = rightRotate((tNode.right));
                returnNode = leftRotate(tNode);
                break;
            default:
                System.out.println("Impossible type!");
                break;
        }
        return returnNode;
    }

    private AVLNode leftRotate(AVLNode t) {
        AVLNode RChild = t.right;
        if (RChild == NIL)
            System.out.println(t.item + "'s RChild shouldn't be NIL!");
        AVLNode RLChild = RChild.left;
        RChild.left = t;
        t.right = RLChild;
        t.height = 1 + Math.max(t.left.height, t.right.height);
        RChild.height = 1 + Math.max(RChild.left.height, RChild.right.height);
        return RChild;
    }

    private AVLNode rightRotate(AVLNode t) {
        AVLNode LChild = t.left;
        if (LChild == NIL)
            System.out.println(t.item + "'s LChild shouldn't be NIL!");
        AVLNode LRChild = LChild.right;
        LChild.right = t;
        t.left = LRChild;
        t.height = 1 + Math.max(t.left.height, t.right.height);
        LChild.height = 1 + Math.max(LChild.left.height, LChild.right.height);
        return LChild;
    }

    private final int LL = 1, LR = 2, RR = 3, RL = 4, NO_NEED = 0, ILLEGAL = -1;

    private int needBalance(AVLNode t) {
        int type = ILLEGAL;
        if (t.left.height + 2 <= t.right.height) { // R 유형
            if (t.right.left.height <= t.right.right.height) type = RR;
            else type = RL;
        } else if (t.left.height >= t.right.height + 2) { // L 유형
            if (t.left.left.height >= t.left.right.height) type = LL;
            else type = LR;
        } else type = NO_NEED;
        return type;
    }

    public boolean isEmpty() {
        return root == NIL;
    }

    public void clear() {
        root = NIL;
    }
}
