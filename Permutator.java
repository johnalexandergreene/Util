package org.fleen.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Permutator{
  
  //test permutator
  //tested. works
  
//  private static final List<String> A0=Arrays.asList(new String[]{"A","B","C","D"});
//  private static final List<String> A1=Arrays.asList(new String[]{"1","2","3","4","5"});
//  private static final List<String> A2=Arrays.asList(new String[]{"X","Y","Z"});
//  private static final List<String> A3=Arrays.asList(new String[]{"7","8","9"});
//  
//  public static void main(String[] a){
//    List<List<String>> b=new ArrayList<List<String>>();
//    b.add(A0);
//    b.add(A1);
//    b.add(A2);
//    b.add(A3);
//    List<List<String>> p=getPermutations(b);
//    System.out.println("TOTAL PERMUTATIONS :"+p.size());
//    for(List<String> s:p)
//      System.out.println(s);}
  
  /**
   * Combines several collections of elements and create permutations of all of them, taking one element from each
   * collection, and keeping the same order in resultant lists as the one in original list of collections.
   * 
   * <ul>Example
   * <li>Input  = { {a,b,c} , {1,2,3,4} }</li>
   * <li>Output = { {a,1} , {a,2} , {a,3} , {a,4} , {b,1} , {b,2} , {b,3} , {b,4} , {c,1} , {c,2} , {c,3} , {c,4} }</li>
   * </ul>
   * 
   * @param collections Original list of collections which elements have to be combined.
   * @return Resultant collection of lists with all permutations of original list.
   */
  public static <T> List<List<T>> getPermutations(List<List<T>> collections){
    if(collections==null||collections.isEmpty()){
      return new LinkedList<List<T>>();
    }else{
      List<List<T>> res = new LinkedList<List<T>>();
      permutationsImpl(collections,res,0,new LinkedList<T>());
      return res;}}

  private static <T> void permutationsImpl(List<List<T>> ori, List<List<T>> res, int d, List<T> current) {
    //if depth equals number of original collections, final reached, add and return
    if(d==ori.size()){
      res.add(current);
      return;}
    //iterate from current collection and copy 'current' element N times, one for each element
    Collection<T> currentCollection = ori.get(d);
    for (T element:currentCollection) {
      List<T> copy=new LinkedList<T>(current);
      copy.add(element);
      permutationsImpl(ori,res,d+1,copy);}}
  
}
