package cn.sparrow.common.service;

import java.util.LinkedList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SortService<T, ID> {

  /**
   * 对给予的可排序的对象进行排序
   * 
   * @param list
   */
  public void sort(List<T> list);

  /**
   * 将新的排序更新到数据库
   * 
   * @param repository
   * @param sortableNode
   */
  public void saveSort(JpaRepository<T, ID> repository, T sortableNode);

  /**
   * 从数据库里构建当前节点的排序列表；往前找到null，往后找到null;
   * 
   * @param repository
   * @param sortableNode
   * @return
   */
  public LinkedList<T> buildLinkedList(JpaRepository<T, ID> repository, T sortableNode);


  /**
   * 判断一个节点是否在另外一个节点的前面
   * 
   * @param node
   * @param targetNode
   * @return
   */
  public boolean isBefore(T node, T targetNode, List<T> sortableList);

  public boolean isAfter(T node, T targetNode, List<T> sortableList);

  public boolean isBefore(JpaRepository<T, ID> repository, T node, T targetNode);

  public boolean isAfter(JpaRepository<T, ID> repository, T node, T targetNode);

  public boolean isBetween(T node, T targetNode, List<T> sortableList);

  public boolean isBetween(JpaRepository<T, ID> repository, T node, T targetNode);
  
  public boolean isSameLevel(T node, T targetNode, List<T> sortableList);

  public boolean isSameLevel(JpaRepository<T, ID> repository, T node, T targetNode);

}
