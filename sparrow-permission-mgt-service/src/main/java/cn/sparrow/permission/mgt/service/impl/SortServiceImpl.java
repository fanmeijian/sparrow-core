package cn.sparrow.permission.mgt.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.mgt.api.SortService;

@Service
public class SortServiceImpl<T, ID> implements SortService<T, ID> {

  @Override
  public void sort(List<T> list) {
    Map<ID, T> map = new HashMap<ID, T>();
    List<LinkedList<T>> sortLinkedLists = new ArrayList<LinkedList<T>>();
    list.forEach(f -> {
      map.put(getNodeId(f), f);
    });
    list.forEach(f -> {
      if (!isInLinkedList(f, sortLinkedLists)) {
        // build new sorted linkedlist
        LinkedList<T> tempSortedLinkedList = new LinkedList<T>();
        tempSortedLinkedList.add(f);
        buildPrevious(f, tempSortedLinkedList, map);
        buildNext(f, tempSortedLinkedList, map);
        sortLinkedLists.add(tempSortedLinkedList);
      }
    });
    list.clear();
    sortLinkedLists.forEach(f -> {
      f.forEach(node -> {
        list.add(node);
      });
    });
  }

  @Override
  public void saveSort(JpaRepository<T, ID> repository, T sortableNode) {

    ID newPreviousNodeId = getPreviousNodeId(sortableNode);
    ID newNextNodeId = getNextNodeId(sortableNode);
    ID nodeId = getNodeId(sortableNode);
    T orginSortableNode = repository.findById(nodeId).get();
    ID originNextNodeId = getNextNodeId(orginSortableNode);
    ID originPreviousNodeId = getPreviousNodeId(orginSortableNode);

    // remove from origin position
    if (originPreviousNodeId != null) {
      T originPreviousNode = repository.getOne(originPreviousNodeId);
      setNextNodeId(originPreviousNode, originNextNodeId);
      repository.save((T) originPreviousNode);
    }

    if (originNextNodeId != null) {
      T originNextNode = repository.getOne(originNextNodeId);
      setPreviousNodeId(originNextNode, originPreviousNodeId);
      repository.save((T) originNextNode);
    }

    // save the new Node
    T newNode = repository.getOne(nodeId);
    setPreviousNodeId(newNode, newPreviousNodeId);
    setNextNodeId(newNode, newNextNodeId);
    repository.save((T) newNode);

    // insert the new node to target position
    if (newPreviousNodeId != null) {
      T newPreviousNode = repository.getOne(newPreviousNodeId);
      setNextNodeId(newPreviousNode, nodeId);
      repository.save((T) newPreviousNode);
    }

    if (newNextNodeId != null) {
      T newNextNode = repository.getOne(newNextNodeId);
      setPreviousNodeId(newNextNode, nodeId);
      repository.save((T) newNextNode);
    }
  }

  @Override
  public LinkedList<T> buildLinkedList(JpaRepository<T, ID> repository, T originNode) {
    LinkedList<T> originLinkedList = new LinkedList<T>();
    originLinkedList.add(originNode);
    ID originNextNodeId = getNextNodeId(originNode);
    ID originPreviousNodeId = getPreviousNodeId(originNode);

    if (originPreviousNodeId == null && originNextNodeId == null) {
      return originLinkedList;
    }

    if (originPreviousNodeId != null && originNextNodeId != null) {
      buildPrevious(repository, originNode, originLinkedList);
      buildNext(repository, originNode, originLinkedList);
      return originLinkedList;
    }

    if (originPreviousNodeId == null && originNextNodeId == null) {
      buildNext(repository, originNode, originLinkedList);
    }

    if (originNextNodeId == null && originPreviousNodeId != null) {
      buildPrevious(repository, originNode, originLinkedList);
    }
    return originLinkedList;
  }

  private boolean isInLinkedList(T node, List<LinkedList<T>> linkedLists) {
    for (LinkedList<T> linkedList : linkedLists) {
      for (T t : linkedList) {
        if (getNodeId(t).equals(getNodeId(node)))
          return true;
      }
    }

    return false;
  }

  private void buildPrevious(JpaRepository<T, ID> repository, T node,
      LinkedList<T> originLinkedList) {
    T previousNode = repository.findById(getPreviousNodeId(node)).get();
    originLinkedList.addFirst(previousNode);
    if (getPreviousNodeId(previousNode) != null)
      buildPrevious(repository, previousNode, originLinkedList);
  }

  private void buildNext(JpaRepository<T, ID> repository, T node, LinkedList<T> originLinkedList) {
    T nextNode = repository.findById(getNextNodeId(node)).get();
    originLinkedList.addLast(nextNode);
    if (getNextNodeId(nextNode) != null)
      buildNext(repository, nextNode, originLinkedList);
  }

  private void buildPrevious(T node, LinkedList<T> sorLinkedList, Map<ID, T> map) {
    T previousNode = map.get(getPreviousNodeId(node));
    if (previousNode != null)
      sorLinkedList.addFirst(previousNode);
    if (getPreviousNodeId(previousNode) != null)
      buildPrevious(previousNode, sorLinkedList, map);
  }

  private void buildNext(T node, LinkedList<T> sorLinkedList, Map<ID, T> map) {
    T nextNode = map.get(getNextNodeId(node));
    if (nextNode != null)
      sorLinkedList.addLast(nextNode);
    if (getNextNodeId(nextNode) != null)
      buildNext(nextNode, sorLinkedList, map);
  }

  @SuppressWarnings("unchecked")
public ID getNodeId(T node) {
    try {
      return (ID) BeanUtils.getPropertyDescriptor(node.getClass(), "id").getReadMethod()
          .invoke(node);
    } catch (BeansException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  @SuppressWarnings("unchecked")
public ID getPreviousNodeId(T node) {
    if (node == null)
      return null;
    try {
      return (ID) BeanUtils.getPropertyDescriptor(node.getClass(), "previousNodeId").getReadMethod()
          .invoke(node);
    } catch (BeansException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  @SuppressWarnings("unchecked")
public ID getNextNodeId(T node) {
    if (node == null)
      return null;

    try {
      return (ID) BeanUtils.getPropertyDescriptor(node.getClass(), "nextNodeId").getReadMethod()
          .invoke(node);
    } catch (BeansException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  public void setNextNodeId(T node, ID nextNodeId) {
    try {
      BeanUtils.getPropertyDescriptor(node.getClass(), "nextNodeId").getWriteMethod().invoke(node,
          nextNodeId);

      // node.getClass().getMethod("setNextNodeId", Object.class).invoke(node,
      // nextNodeId);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | SecurityException e) {
      e.printStackTrace();
    }
  }

  public void setPreviousNodeId(T node, ID previousNodeId) {
    try {
      BeanUtils.getPropertyDescriptor(node.getClass(), "previousNodeId").getWriteMethod()
          .invoke(node, previousNodeId);
      // node.getClass().getMethod("setPreviousNodeId", Object.class).invoke(node,
      // previousNodeId);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | SecurityException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean isBefore(JpaRepository<T, ID> repository, T node, T targetNode) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isAfter(JpaRepository<T, ID> repository, T node, T targetNode) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isBefore(T node, T targetNode, List<T> sortableList) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isAfter(T node, T targetNode, List<T> sortableList) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isBetween(T node, T targetNode, List<T> sortableList) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isBetween(JpaRepository<T, ID> repository, T node, T targetNode) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isSameLevel(T node, T targetNode, List<T> sortableList) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isSameLevel(JpaRepository<T, ID> repository, T node, T targetNode) {
    // TODO Auto-generated method stub
    return false;
  }

}
