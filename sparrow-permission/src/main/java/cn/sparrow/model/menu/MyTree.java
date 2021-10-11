package cn.sparrow.model.menu;

import java.util.ArrayList;
import java.util.List;

public class MyTree<T> {
  private T me;
  private List<MyTree<T>> children = new ArrayList<MyTree<T>>();

  public T getMe() {
    return me;
  }



  public MyTree(T me) {
    super();
    this.me = me;
  }


  public void setMe(T me) {
    this.me = me;
  }

  public List<MyTree<T>> getChildren() {
    return children;
  }

  public void setChildren(List<MyTree<T>> children) {
    this.children = children;
  }


}
