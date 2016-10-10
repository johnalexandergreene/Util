package org.fleen.util.tag;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * It handles tags. So we can tag stuff. Specifically, polygon nodes and grammar elements
 * We're looking at 1..4 tags max in the general case. Up to 10 or 20 in a weird extreme case maybe.
 * So we do a linear search. 
 */
public class TagManager implements Serializable{
  
  private static final long serialVersionUID=7475268738463881008L;
  
  /*
   * ################################
   * CONSTRUCTORS
   * ################################
   */
  
  public TagManager(){}
  
  public TagManager(String[] tags){
    setTags(tags);}
  
  /*
   * ################################
   * TAGS
   * ################################
   */
  
  //we use an array for compactness
  private String[] tags=new String[0];
  
  //SET
  
  public void setTags(String... tags){
    setTags(Arrays.asList(tags));}
  
  public void setTags(List<String> tags){
    this.tags=tags.toArray(new String[tags.size()]);}
  
  //GET

  public List<String> getTags(){
    return new ArrayList<String>(Arrays.asList(tags));}
  
  //HAS

  public boolean hasTags(List<String> tags){
    for(String tag:tags)
      if(!hasTag(tag))
        return false;
    return true;}
  
  public boolean hasTags(String... tags){
    for(String tag:tags)
      if(!hasTag(tag))
        return false;
    return true;}
  
  public boolean hasTag(String tag){
    for(String t:tags)
      if(t.equals(tag))return true;
    return false;}
  
  //ADD
  
  public void addTags(String... tags){
    addTags(Arrays.asList(tags));}
  
  //use a set to handle dupes
  public void addTags(List<String> tags){
    if(tags==null)return;
    Set<String> a=new HashSet<String>(Arrays.asList(this.tags));
    a.addAll(tags);
    this.tags=a.toArray(new String[a.size()]);}
  
  //REMOVE
  
  public void removeTags(String... tags){
    removeTags(Arrays.asList(tags));}
  
  public void removeTags(List<String> tags){
    Set<String> a=new HashSet<String>(Arrays.asList(this.tags));
    a.removeAll(tags);
    this.tags=a.toArray(new String[a.size()]);}
  
  /*
   * ################################
   * OBJECT
   * ################################
   */
  
  public String toString(){
    if(tags.length==0)
      return"[]";
    StringBuffer a=new StringBuffer();
    a.append("[");
    for(int i=0;i<tags.length-1;i++)
      a.append(tags[i]+" ");
    a.append(tags[tags.length-1]+"]");
    return a.toString();}

}
