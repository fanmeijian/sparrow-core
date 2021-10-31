package cn.sparrow.model.common;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MyTree<T>{
  private T me;
  private List<MyTree<T>> children = new ArrayList<MyTree<T>>();
  private int level;
  
  public MyTree(T me) {
    super();
    this.me = me;
  }

}
