package cn.sparrow.model.common;

import java.util.ArrayList;
import java.util.List;

public class SparrowTree<T> {
  private String id;
  private List<SparrowTree<T>> children = new ArrayList<SparrowTree<T>>();



  public String getId() {
    return id;
  }



  public void setId(String id) {
    this.id = id;
  }



  public SparrowTree() {
    super();
  }
  
  public SparrowTree(String id) {
    super();
    this.id = id;
  }
  

  public List<SparrowTree<T>> getChildren() {
    return children;
  }

  public void setChildren(List<SparrowTree<T>> children) {
    this.children = children;
  }
}
