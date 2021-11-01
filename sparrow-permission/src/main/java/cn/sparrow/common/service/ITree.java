package cn.sparrow.common.service;

import java.util.Set;
import cn.sparrow.model.common.MyTree;

public interface ITree<T> {

  public MyTree<T> tree(String parentId);
  
  public void buildTree(MyTree<T> tree);
//  
//  public void buildParents(ID parentId, Set<T> target);
//  
//  public void buildChildren(ID id, Set<T> target);
//  
//  public void getParentAndChildren(ID id, Set<T> target);
}
