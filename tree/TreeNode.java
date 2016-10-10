package org.fleen.util.tree;

import java.util.Collection;
import java.util.List;

/*
 * a treenode implements this interface, wraps a treenodeservices object, hooks all the methods up to the treenodeservices object. ez 
 */
public interface TreeNode{
  
  TreeNode getParent();
  
  void setParent(TreeNode node);
  
  List<? extends TreeNode> getChildren();
  
  /*
   * returns child #0
   * good for when we have just 1 child
   */
  TreeNode getChild();
  
  void setChildren(List<? extends TreeNode> nodes);
  
  void setChild(TreeNode node);
  
  void addChild(TreeNode node);
  
  int getChildCount();
  
  boolean hasChildren();
  
  void removeChild(TreeNode child);
  
  void removeChildren(Collection<? extends TreeNode> children);
  
  void clearChildren();
  
  boolean isRoot();
  
  boolean isLeaf();
  
  int getDepth();
  
  TreeNode getRoot();
  
  /*
   * 0 returns this treenode
   * 1 returns this treenode's parent
   * 2 returns this treenode's grandparent
   * etc
   * if levels is >depth then we throw an exception
   */
  TreeNode getAncestor(int levels);
  
  List<TreeNode> getSiblings();

}
