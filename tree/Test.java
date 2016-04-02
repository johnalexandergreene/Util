package org.fleen.util.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Test{
  
  static class NTest implements TreeNode{

    TreeNodeServices tns=new TreeNodeServices();
    int index;
    
    NTest(int i){
      index=i;}
    
    public TreeNode getParent(){
      return tns.getParent();}

    public List<TreeNode> getChildren(){
      return tns.getChildren();}

    public void setParent(TreeNode node){
      tns.setParent(node);}

    public void setChildren(List<? extends TreeNode> nodes){
      tns.setChildren(nodes);}

    @Override
    public TreeNode getChild(){
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public void setChild(TreeNode node){
      // TODO Auto-generated method stub
      
    }

    @Override
    public int getChildCount(){
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    public boolean hasChildren(){
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public void clearChildren(){
      // TODO Auto-generated method stub
      
    }

    @Override
    public boolean isRoot(){
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public boolean isLeaf(){
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public int getDepth(){
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    public TreeNode getRoot(){
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public TreeNode getAncestor(int levels){
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public void addChild(TreeNode node){
      // TODO Auto-generated method stub
      
    }

    @Override
    public void removeChildren(Collection<? extends TreeNode> children){
      // TODO Auto-generated method stub
      
    }

    @Override
    public List<TreeNode> getSiblings(){
      // TODO Auto-generated method stub
      return null;
    }}
  
  static class TNI extends TreeNodeIterator{

    public TNI(TreeNode node){
      super(node);}
    
    public TreeNode next(){
      TreeNode n=super.next();
      System.out.println("node "+((NTest)n).index);
      return n;}
    
  }
  
  public static final void main(String[] a){
    
    NTest[] n=new NTest[7];
    
    for(int i=0;i<7;i++)
      n[i]=new NTest(i);
    
    link(n[0],n[1],n[2]);
    link(n[1],n[3],n[4]);
    link(n[2],n[5],n[6]);
    
    TNI i=new TNI(n[0]);
    while(i.hasNext())
      i.next();
    
  }
  
  private static void link(NTest p,NTest... c){
    p.setChildren(new ArrayList<NTest>(Arrays.asList(c)));
    for(NTest n:c)
      n.setParent(p);}

}
