package org.fleen.util.tag;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
  
  private String[] tags=new String[0];
  
  public String[] getTags(){
    return tags;}
  
  //rather than just setting the tags array we copy it, to break the reference
  public void setTags(String[] tags){
    this.tags=new String[tags.length];
    System.arraycopy(tags,0,this.tags,0,tags.length);}
  
  public boolean hasTag(String tag){
    for(String t:tags)
      if(t.equals(tag))return true;
    return false;}
  
  public boolean hasTags(String[] tags){
    for(String tag:tags)
      if(!hasTag(tag))
        return false;
    return true;}
  
  public void addTag(String tag){
    List<String> a=new ArrayList<String>(Arrays.asList(tags));
    a.add(tag);
    tags=a.toArray(new String[a.size()]);}
  
  public void addTags(String[] tags){
    List<String> a=new ArrayList<String>(Arrays.asList(this.tags));
    a.addAll(Arrays.asList(tags));
    this.tags=a.toArray(new String[a.size()]);}
  
  public void removeTag(String tag){
    List<String> a=new ArrayList<String>(Arrays.asList(tags));
    a.remove(tag);
    tags=a.toArray(new String[a.size()]);}
  
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
