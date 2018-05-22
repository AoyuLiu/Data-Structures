import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left , right;

        public Node(E e) {
            this.e =e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e){
        root = add(root,e);
    }

    private  Node add(Node node , E e){
        if(node == null){
            size ++;
            return new Node(e);
        }

        if(e.compareTo(node.e) <0){
           node.left = add(node.left,e);
        }
        else if(e.compareTo(node.e)>0)
          node.right = add(node.right,e);

        return node;
    }

    public boolean contains(E e){
        return contains(root,e);
    }

    private boolean contains(Node node, E e){
        if(node == null)
            return false;


        if(e.compareTo(node.e) == 0)
            return true;
        else if(e.compareTo(node.e) < 0)
            return contains(node.left,e);
        else
            return contains(node.right,e);

    }

    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node == null)
            return;
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if (node == null)
            return;
        inOrder(node.left);
        inOrder(node.right);
        System.out.println(node.e);
    }

    public void preOrderNR(){
        if (root == null)
            return;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            if(cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }
    //改过，从右视角看二叉树
    public void levelOrder(){
        Node cur = null;
        int dept = 0;
        if (root == null)
            return;

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int n = q.size();
            for(int i=0; i< n; i++) {
                //System.out.println("size-----" + q.size());
                cur = q.remove();
               // System.out.println("son------"+cur.e );
                if (cur.left != null)
                    q.add(cur.left);
                if (cur.right != null)
                    q.add(cur.right);
                System.out.println("change_size-----" + q.size());
            }

            System.out.println("final-----"+ cur.e);
            System.out.println(dept);
            dept++;
        }
    }

    public E minimum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");

        Node minNode = minimum(root);
        return minNode.e;
    }

    private Node minimum(Node node){
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    public E maxmum(){
        if (size == 0)
            throw  new IllegalArgumentException("BST is empty");
        return maxmum(root).e;
    }

    private Node maxmum(Node node){
        if (node.right == null )
            return node;

        return maxmum(node.right);
    }

    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node){
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);

        return node;
    }

    public E removeMax(){
        E ret = maxmum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node){
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e){
        root = remove(root, e);
    }

    private Node remove(Node node , E e){

        if (node == null )
            return null;

        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left,e);
            return node;
        }
        if (e.compareTo(node.e) > 0){
            node.right = remove(node.right,e);
            return node;
        }
        else{
            //找到节点，分类讨论
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            // 要删除节点左右子树都不为空
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = null;
            node.right = null;

            return successor;
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }

    private  void generateBSTString(Node node, int depth, StringBuilder res){
        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e+ "\n");
        generateBSTString(node.left,depth+1,res);
        generateBSTString(node.right,depth+1,res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for (int i =0; i< depth ; i++)
            res.append("--");
        return res.toString();
    }
}
