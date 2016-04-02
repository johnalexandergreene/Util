package org.fleen.util.tree;

import java.util.Iterator;
import java.util.LinkedList;


/*
 * we don't guarantee node-traversal-order
 * we can skip arbitrary nodes
 * we can block arbitrary nodes, excluding the tree rooted at that node from traversal
 */
public class TreeNodeIterator implements Iterator<TreeNode>{
  
  /*
   * ################################
   * CONSTRUCTOR
   * ################################
   */
  
  public TreeNodeIterator(TreeNode node){
    nodes.add(node);
    setNextNode();}
  
  /*
   * ################################
   * ITERATOR INTERFACE
   * ################################
   */
  
  public boolean hasNext(){
    return nextnode!=null;}

  public TreeNode next(){
    TreeNode n=nextnode;
    setNextNode();
    return n;}

  public void remove(){
    throw new IllegalArgumentException("not implemented");}
    
  /*
   * ################################
   * NODES
   * ################################
   */
  
  private LinkedList<TreeNode> nodes=new LinkedList<TreeNode>();
  private TreeNode nextnode=null;
  
  /*
   * get the node off the front of the list
   * 
   * Here we handle node skipping and terminating
   * by default we address every node in the tree and 
   */
  private void setNextNode(){
    TreeNode n;
    nextnode=null;
    while(!nodes.isEmpty()&&nextnode==null){
      n=nodes.removeFirst();
      if(terminate(n)){
        //n is nextnode but do not address the children
        nextnode=n;
      }else if(skip(n)){
        //n is not nextnode but address the children
        nodes.addAll(n.getChildren());
      }else{
        //n is nextnode and address the children
        nextnode=n;
        nodes.addAll(n.getChildren());}}}
  
  /*
   * ################################
   * TRAVERSAL CONTROL
   * By default these do nothing, no node is skipped and traversal terminates at the leaf. 
   * ################################
   */
  
  /*
   * return true to skip the specified node in the course of our traversal
   * A skipped node is not returned by next() but we do continue traversal to its children
   */
  protected boolean skip(TreeNode node){
    return false;}
  
  /*
   * return true to terminate traversal at the specified node. 
   * A terminated node is returned by next() but we do not traverse to its children
   */
  protected boolean terminate(TreeNode node){
    return false;}
  
}
