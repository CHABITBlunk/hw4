package cs250.hw4;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class BinaryTree implements TreeStructure {

  class TreeNode {
    Integer key;
    TreeNode left, right;

    public TreeNode(Integer item) {
      key = item;
      left = right = null;
    }
  }

  TreeNode root;

  public BinaryTree() {
    root = null;
  }

  public BinaryTree(Integer value) {
    root = new TreeNode(value);
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

  private TreeNode insertHelper(Node root, Integer key) {
    if (root == null) {
      root = new TreeNode(num);
      return root;
    } else if (root.key > num) {
      root.left = insertHelper(root.left, key);
    } else if (root.key < num) {
      root.right = insertHelper(root.right, key);
    }
    return root;
  }

  public Boolean remove() {
    return false;
  }

  public Long get(Integer num) {
    return 0;
  }

  public Integer findMaxDepth() {
    return 0;
  }

  public Integer findMinDepth() {
    return 0;
  }

}
