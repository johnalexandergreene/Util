package org.fleen.util.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/*
 * we wrap one of these to implement all of the treenode methods
 */
public class TreeNodeServices{
  
//  DefaultMutableTreeNode a;//for debug
  
  private TreeNode parent=null;
  
  private TreeNode[] children=new TreeNode[0];
  
  public TreeNode getParent(){
    return parent;}
  
  public List<TreeNode> getChildren(){
    return new ArrayList<TreeNode>(Arrays.asList(children));}
  
  public TreeNode getChild(){
    if(!hasChildren())
      throw new IllegalArgumentException("this node has no children");
    return children[0];}
  
  public void setParent(TreeNode node){
    this.parent=node;}
  
  public void setChildren(List<? extends TreeNode> nodes){
    children=nodes.toArray(new TreeNode[nodes.size()]);}
  
  public void setChild(TreeNode node){
    children=new TreeNode[]{node};}
  
  public void addChild(TreeNode node){
    List<TreeNode> a=new ArrayList<TreeNode>(Arrays.asList(children));
    a.add(node);
    children=a.toArray(new TreeNode[a.size()]);}
  
  public int getChildCount(){
    return children.length;}
  
  public boolean hasChildren(){
    return children.length>0;}
  
  public void removeChild(TreeNode child){
    List<TreeNode> a=new ArrayList<TreeNode>(Arrays.asList(children));
    a.remove(child);
    children=a.toArray(new TreeNode[a.size()]);}
  
  public void removeChildren(Collection<? extends TreeNode> c){
    List<TreeNode> a=new ArrayList<TreeNode>(Arrays.asList(children));
    a.removeAll(c);
    children=a.toArray(new TreeNode[a.size()]);}
  
  public void clearChildren(){
    children=new TreeNode[0];}
  
  public int getChildIndex(TreeNode child){
    return Arrays.asList(children).indexOf(child);}
  
  public boolean isLeaf(){
    return getChildCount()==0;}
  
  public boolean isRoot(){
    return parent==null;}
  
  public int getDepth(TreeNode node){
    int c=0;
    TreeNode n=node;
    while(n.getParent()!=null){
      c++;
      n=n.getParent();}
    return c;}
  
  public TreeNode getRoot(TreeNode node){
    if(node.isRoot()){
      return node;
    }else{
      return node.getParent().getRoot();}}
  
  public TreeNode getAncestor(TreeNode node,int levels){
    for(int i=0;i<levels;i++){
      if(node==null)
        throw new IllegalArgumentException("levels > depth");
      node=node.getParent();}
    return node;}
  
  public List<TreeNode> getSiblings(TreeNode n){
    List<TreeNode> siblings=new ArrayList<TreeNode>();
    if(isRoot())return siblings;
    siblings.addAll(getParent().getChildren());
    siblings.remove(n);
    return siblings;}
  
}
