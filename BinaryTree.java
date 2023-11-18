package cs250.hw4;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class BinaryTree implements TreeStructure {

  class TreeNode {
    Integer key;
    Long timeInserted;
    TreeNode left, right;

    public TreeNode(Integer key, Long timeInserted) {
      this.key = key;
      this.timeInserted = timeInserted;
      left = right = null;
    }
  }

  TreeNode root;

  public BinaryTree() {
    root = null;
  }

  public BinaryTree(Integer value) {
    root = new TreeNode(value, System.nanoTime());
  }

  public static void main(String[] args) throws FileNotFoundException, IOException {
    File file = new File(args[0]);
    FileReader fReader = new FileReader(file);
    BufferedReader bufferedReader = new BufferedReader(fReader);
    TreeStructure tree = new BinaryTree();
    Random rng = new Random(42);
    ArrayList<Integer> nodesToRemove = new ArrayList<>();
    ArrayList<Integer> nodesToGet = new ArrayList<>();
    String line = bufferedReader.readLine();

    while (line != null) {
      Integer lineInt = Integer.parseInt(line);
      tree.insert(lineInt);
      Integer rand = rng.nextInt(10);
      if (rand < 5)
        nodesToRemove.add(lineInt);
      else if (rand >= 5)
        nodesToGet.add(lineInt);
      line = bufferedReader.readLine();
    }

    bufferedReader.close();
    for (int i = 0; i < 10; i++) {
      System.out.println(nodesToGet.get(i) + " inserted at " + tree.get(nodesToGet.get(i)));
    }
    System.out.println("Max depth: " + tree.findMaxDepth());
    System.out.println("Min depth: " + tree.findMinDepth());
  }

  public void insert(Integer num) {
    root = insertHelper(root, num);
  }

  private TreeNode insertHelper(TreeNode root, Integer key) {
    if (root == null) {
      root = new TreeNode(key, System.nanoTime());
      return root;
    } else if (root.key > key) {
      root.left = insertHelper(root.left, key);
    } else if (root.key < key) {
      root.right = insertHelper(root.right, key);
    }
    return root;
  }

  public Boolean remove(Integer key) {
    if (removeHelper(root, key) != null)
      return true;
    return false;
  }

  private TreeNode removeHelper(TreeNode root, Integer key) {
    if (root == null)
      return root;
    if (root.key > key) {
      root.left = removeHelper(root.left, key);
      return root;
    } else if (root.key < key) {
      root.right = removeHelper(root.right, key);
      return root;
    }

    if (root.left == null) {
      TreeNode temp = root.right;
      return temp;
    } else if (root.right == null) {
      TreeNode temp = root.left;
      return temp;
    } else {
      TreeNode succParent = root;
      TreeNode succ = root.right;
      while (succ.left != null) {
        succParent = succ;
        succ = succ.left;
      }
      if (succParent != root)
        succParent.left = succ.right;
      else
        succParent.right = succ.right;
      root.key = succ.key;
      return root;
    }
  }

  public Long get(Integer num) {
    return getHelper(root, num);
  }

  public Long getHelper(TreeNode root, Integer num) {
    if (root.key != num) {
      if (root.key > num) {
        return getHelper(root.left, num);
      }
      if (root.key < num) {
        return getHelper(root.right, num);
      }
    } else {
      return root.timeInserted;
    }
    return Long.MIN_VALUE;
  }

  public Integer findMaxDepth() {
    return maxDepthHelper(root);
  }

  public Integer maxDepthHelper(TreeNode root) {
    if (root != null) {
      int leftDepth = maxDepthHelper(root.left);
      int rightDepth = maxDepthHelper(root.right);
      if (leftDepth > rightDepth)
        return leftDepth + 1;
      else
        return rightDepth + 1;
    }
    return 0;
  }

  public Integer findMinDepth() {
    return minDepthHelper(root);
  }

  public Integer minDepthHelper(TreeNode root) {
    if (root != null) {
      int leftDepth = minDepthHelper(root.left);
      int rightDepth = minDepthHelper(root.right);
      if (leftDepth < rightDepth)
        return leftDepth + 1;
      else
        return rightDepth + 1;
    }
    return 0;
  }

}
