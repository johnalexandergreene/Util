package org.fleen.util.tag;

import java.util.List;

/*
 * a simple tagging interface
 * we have just the one test : has tags
 * we can always filter the tags in a more complex way by grabbing them and doing some other method elsewhere
 */
public interface Tagged{
  
  /*
   * set tags to the specified
   * any existing tags get obliviated
   */
  void setTags(String... tags);
  
  void setTags(List<String> tags);
  
  /*
   * return all tags in a list
   */
  List<String> getTags();
  
  /*
   * return true if all specified tags are present
   */
  boolean hasTag(String tag);
  
  boolean hasTags(String... tags);
  
  boolean hasTags(List<String> tags);
  
  /*
   * add specified tags
   * no dupes
   */
  void addTag(String tag);
  
  void addTags(String... tags);
  
  void addTags(List<String> tags);
  
  /*
   * remove specified tags
   * if nonexistent then nothing happens
   */
  void removeTag(String tag);
  
  void removeTags(String... tags);
  
  void removeTags(List<String> tags);

}
