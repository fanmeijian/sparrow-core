package cn.sparrow.common.service;

import java.util.Set;
import cn.sparrow.model.common.SparrowTree;

public interface ITree<T> {

  public SparrowTree<T> tree(String parentId);
  
  public void buildTree(SparrowTree<T> tree);
//  
//  public void buildParents(ID parentId, Set<T> target);
//  
//  public void buildChildren(ID id, Set<T> target);
//  
//  public void getParentAndChildren(ID id, Set<T> target);
}
