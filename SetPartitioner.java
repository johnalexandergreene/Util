package org.fleen.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SetPartitioner{
  
  /*
   * given a set, get all possible partitionings of its elements
   * our param is the number of elements in the set
   * we return the list of all possible partitioning schemes
   * we describe our partitioning schemes in terms of group-integers. A list of int[].
   * ex:
   * a set of 3 elements. Our param is "3"
   * return
   * {0,0,0}
   * {0,0,1}
   * {0,1,0}
   * {1,0,0}
   * {0,1,2}
   * 
   * We cache it of course.
   * 
   * This may be a little awkward or verbose but it gets the job done snappily 
   * 
   * 6 works
   * 
   * at 9+ we get
   * java.lang.OutOfMemoryError: Java heap space
   * maybe just add more heap
   * 
   * ------
   * put this in config
   * on first run it initializes for set sizes 2..12
   * so before we distribute it, run it once.
   * or something. maybe put serialized initialized form in assets
   * "SET PARTITION SCHEMES TABLE" serialized object
   * 
   */
  
  Map<Integer,List<int[]>> schemelistbyelementcount=new Hashtable<Integer,List<int[]>>();
  
  public List<int[]> getPartitioningSchemes(Integer ec){
    List<int[]> sl=schemelistbyelementcount.get(ec);
    if(sl==null){
      sl=initPartitioningSchemes(ec);
      schemelistbyelementcount.put(ec,sl);}
    return sl;}
  
  private List<int[]> initPartitioningSchemes(int ec){
    //the init scheme is a group of 1 for every element
    Scheme initscheme=new Scheme();
    for(int i=0;i<ec;i++)
      initscheme.add(new Group(i));
    //generate schemes 
    Set<Scheme> 
      newschemes=new HashSet<Scheme>(),
      schemes=new HashSet<Scheme>(),
      gschemes;
    newschemes.add(initscheme);
    Scheme s;
    while(!newschemes.isEmpty()){
      s=newschemes.iterator().next();
      newschemes.remove(s);
      schemes.add(s);
      gschemes=generateSchemes(s);
      if(gschemes!=null)
        newschemes.addAll(generateSchemes(s));}
    
    System.out.println("schemescount="+schemes.size());
    for(Scheme a:schemes)
      System.out.println(a);
    
    System.out.println("foo");
    
    //convert set of schemes to list of int[]
//    List<int[]> intarrays=getIntArrays(schemes,ec);
//    return intarrays;
    return null;
    
  
  }
  
  private Set<Scheme> generateSchemes(Scheme scheme){
    if(scheme.size()==1)return null;
    Set<Scheme> gschemes=new HashSet<Scheme>();
    Scheme snew;
    for(Group g0:scheme){
      for(Group g1:scheme){
        if(g0.equals(g1))continue;
        snew=new Scheme(scheme);
        snew.remove(g0);
        snew.remove(g1);
        snew.add(new Group(g0,g1));
        gschemes.add(snew);}}
    return gschemes;}
  
//  private List<int[]> getIntArrays(List<Scheme> schemes,int ec){
//    NoDupe intarrays=new NoDupe();
//    for(Scheme scheme:schemes)
//      intarrays.addArray(getIntArray(scheme,ec));
//    return intarrays;}
  
  @SuppressWarnings("serial")
  class NoDupe extends ArrayList<int[]>{
    
    NoDupe(){
      super();}
    
    void addArray(int[] a){
      if(!containsArray(a))
        add(a);}
    
    boolean containsArray(int[] a){
      for(int[] b:this)
        if(arraysAreEqual(a,b))return true;
      return false;}
    
    boolean arraysAreEqual(int[] a,int[] b){
      for(int i=0;i<a.length;i++)
        if(a[i]!=b[i])return false;
      return true;}
    
  }
  
  
//  private int[] getIntArray(Scheme scheme,int arraylength){
//    int[] a=new int[arraylength];
//    for(int i=0;i<arraylength;i++)
//      a[i]=scheme.getGroupIndex(i);
//    a=normalize(a);
//    return a;}
  
//  private int[] normalize(int[] a){
//    int[] b=new int[a.length];
//    int groupindex=0;
//    Integer replacement;
//    Map<Integer,Integer> replace=new Hashtable<Integer,Integer>();
//    for(int i=0;i<b.length;i++){
//      replacement=replace.get(a[i]);
//      if(replacement==null){
//        replacement=groupindex;
//        groupindex++;
//        replace.put(a[i],replacement);}
//      b[i]=replacement;}
//    return b;}
  
  @SuppressWarnings("serial")
  private class Group extends HashSet<Integer>{
    
    //init
    Group(int initelement){
      super();
      add(initelement);}
    
    //merge
    Group(Group g0,Group g1){
      super();
      addAll(g0);
      addAll(g1);}

    public int hashCode(){
      return size();}
    
    public boolean equals(Object a){
      return size()==((Group)a).size()&&containsAll((Group)a);}
    
  }
  
  @SuppressWarnings("serial")
  class Scheme extends HashSet<Group>{
    
    Scheme(){
      super();}
    
    Scheme(Scheme scheme){
      super(scheme);}
    
    int getGroupIndex(Integer element){
//      Group g;
//      for(int i=0;i<size();i++){
////        g=get(i);
//        if(g.contains(element))return i;}
      throw new IllegalArgumentException("element not in scheme");
    }
    
    public int hashCode(){
      return size();}
    
    public boolean equals(Object a){
      return size()==((Scheme)a).size()&&containsAll((Scheme)a);}
    
    public String toString(){
      StringBuffer a=new StringBuffer();
      for(Group g:this){
        a.append("(");
        for(Integer i:g)
          a.append(i+",");
        a.append(")");}
      return a.toString();
    }
    
  }
  
  /*
   * ################################
   * TEST
   * ################################
   */
  
  public static final void main(String[] a){
    SetPartitioner p=new SetPartitioner();
    List<int[]> b=p.getPartitioningSchemes(8);
    System.out.println("schemecount="+b.size());
    StringBuffer sb;
    for(int[] ia:b){
      sb=new StringBuffer();
      sb.append("[");
      for(int i:ia)
        sb.append(i+",");
      sb.append("]");
      System.out.println(sb.toString());}}
  
}
