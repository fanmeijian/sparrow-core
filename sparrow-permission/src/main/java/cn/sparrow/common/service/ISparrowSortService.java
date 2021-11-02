package cn.sparrow.common.service;

import java.util.LinkedList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISparrowSortService<T, ID> {

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

}
