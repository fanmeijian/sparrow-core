package cn.sparrow.permission.mgt.api;

import java.util.List;

import cn.sparrow.permission.model.resource.SparrowTree;

public interface TreeService<T, ID>{
  /**
   * 对整颗树进行排序，即对同一层级的叶排序
   * 
   * @param sparrowSortableTree
   */
  public void sort(SparrowTree<T, ID> sparrowTree);

  /**
   * 构建1对多的关系树
   * 
   * @param repository
   * @param parentId
   * @return
   */
  public SparrowTree<T, ID> buildTree(ID parentId);

  /**
   * 构建含直接上级的树，但只有直接上级，没有上级的同级节点
   * 
   * @param repository
   * @param id
   * @return
   */
  public SparrowTree<T, ID> buildTreeWithParent(ID id);

  /**
   * 获取一对多关系的子节点
   * 
   * @param repository
   * @param parentId
   * @return
   */
  public List<SparrowTree<T, ID>> getChildren(ID parentId);

  /**
   * 是否它的孩子
   * 
   * @param repository
   * @param id
   * @param parentId
   * @return
   */
  public boolean isChild(ID childId, ID parentId);

  /**
   * 是否它的孩子
   * 
   * @param repository
   * @param id
   * @param parentId
   * @return
   */
  public boolean isAndChild(ID childId, ID parentId);


  /**
   * 是否他的祖先
   * 
   * @param repository
   * @param parentId
   * @param id
   * @return
   */
  public boolean isParent(ID childId, ID parentId);

  /**
   * 是否她的祖先
   * 
   * @param repository
   * @param parentId
   * @param id
   * @return
   */
  public boolean isAndParent(ID childId, ID parentId);

  public boolean isChildToParent(ID id, ID childId, ID parentId);

  public boolean isAndChildToParent(ID id, ID childId, ID parentId);

  public boolean isChildToAndParent(ID id, ID childId, ID parentId);

  public boolean isAndChildToAndParent(ID id, ID childId, ID parentId);

}
