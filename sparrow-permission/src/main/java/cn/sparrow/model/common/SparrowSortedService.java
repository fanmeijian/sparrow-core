package cn.sparrow.model.common;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SparrowSortedService<T, ID> implements ISparrowSortedService<T, ID> {

  JpaRepository<T, ID> repository;
  LinkedList<T> linkedList;
  private T newPreviosNode;
  private T newNextNoee;
  private T oldNode;
  private T newNode;
  private ID previousNodeId;
  private ID nextNodeId;
  private ID newNextNodeId;
  private ID newPreviousNodeId;

  @Override
  public void sort(List<T> list) {
    Map<ID, T> map = new HashMap<ID, T>();
    LinkedList<T> sortLinkedList = new LinkedList<T>();
    list.forEach(f -> {
      map.put(getNodeId(f), f);
//       sortLinkedList.add(f);
    });
    if(list.size()>0) {
      this.linkedList = sortLinkedList;
      this.linkedList.add(list.get(0));
      this.buildPrevious(list.get(0),map);
      this.buildNext(list.get(0), map);
      
      list.forEach(f->{
        if(!this.linkedList.contains(f))
          linkedList.addLast(f);
      });

      // sortLinkedList.addAll(list);
      // sortLinkedList.forEach(f->{
      // map.put(getNodeId(f), f);
      // });

//      list.forEach(f -> {
  //
////        sortLinkedList.add(f);
//        // check if previous exist
//        int previousIndex = getIndexByNodeId(sortLinkedList, getPreviousNodeId(f));
//        int nextIndex = getIndexByNodeId(sortLinkedList, getNextNodeId(f));
//        if (nextIndex >= 0) {
//          // if the node exist, then move it after previous node
//          sortLinkedList.remove(getIndexByNode(sortLinkedList, f));
//          sortLinkedList.add(nextIndex,f);
//        }
        // add self to the



//        int index = sortLinkedList.indexOf(map.get(this.getNodeId(f)));
//        if (this.getNextNodeId(f) != null) {
//          sortLinkedList.set(sortLinkedList.indexOf(map.get(this.getNextNodeId(f))), null);
//          sortLinkedList.add(index == sortLinkedList.size() - 1 ? index : index + 1,
//              map.get(this.getNextNodeId(f)));
//        }
//        if (this.getPreviousNodeId(f) != null) {
//          sortLinkedList.set(sortLinkedList.indexOf(map.get(this.getPreviousNodeId(f))), null);
//          sortLinkedList.add(index == 0 ? index : index - 1, map.get(this.getPreviousNodeId(f)));
//        }
//      });

      // list = sortLinkedList;
      list.clear();
      Iterator<T> iterator = sortLinkedList.iterator();
      while (iterator.hasNext()) {
        T next = iterator.next();
        if (next != null)
          list.add(next);
      }
    }
   


  }

  public int getIndexByNode(LinkedList<T> linkedList, T node) {

    for (T f : linkedList) {
      if (getNodeId(f).equals(getNodeId(node))) {
        return linkedList.indexOf(f);
      }
    }

    return -1;
  }

  public int getIndexByNodeId(LinkedList<T> linkedList, ID id) {

    for (T f : linkedList) {
      if (getNodeId(f).equals(id)) {
        return linkedList.indexOf(f);
      }
    }

    return -1;
  }

  @Override
  @Transactional
  public void saveSort(JpaRepository<T, ID> repository, T node) {
    this.repository = repository;
    this.oldNode = getOldNode(node);
    this.nextNodeId = getNextNodeId(oldNode);
    this.previousNodeId = getPreviousNodeId(oldNode);
    this.newNextNodeId = getNextNodeId(node);
    this.newPreviousNodeId = getPreviousNodeId(node);
    this.newNextNoee = repository.findById(newNextNodeId).get();
    this.newPreviosNode = repository.findById(newPreviousNodeId).get();

    this.buildLinkedList(repository, node);
    this.linkedList.remove(getOldNode(node));
    // insert node to position
    this.newNode = repository.getOne(getNodeId(node));
    setPreviousNodeId(newNode, getPreviousNodeId(node));
    setNextNodeId(newNode, getNextNodeId(node));

    // head node
    if (this.newPreviousNodeId == null) {
      this.linkedList.addFirst(newNode);
    }

    // last node
    if (this.newNextNodeId == null) {
      this.linkedList.addLast(newNode);
    }

    // insert to middle
    if (this.newPreviousNodeId != null && this.newNextNodeId != null) {
      this.linkedList.add(this.linkedList.indexOf(this.newPreviosNode) + 1, newNode);
      // this.linkedList.add(this.linkedList.indexOf(this.newNextNoee) - 1, newNode);
    }

    // resort it with new relation
    // this.linkedList.forEach(f -> {
    // if (this.linkedList.indexOf(f) == 0) {
    // setPreviousNodeId(f, null);
    // } else if (this.linkedList.indexOf(f) == this.linkedList.size() - 1) {
    // setNextNodeId(f, null);
    // } else {
    // setPreviousNodeId(f, getNodeId(this.linkedList.get(this.linkedList.indexOf(f)
    // - 1)));
    // setNextNodeId(f, getNodeId(this.linkedList.get(this.linkedList.indexOf(f) +
    // 1)));
    // }
    // });

    ListIterator<T> iterator = this.linkedList.listIterator(0);
    while (iterator.hasNext()) {
      if (!iterator.hasPrevious()) {
        // head node
        T curNode = iterator.next();
        setPreviousNodeId(curNode, null);
      } else {
        T previousNode = iterator.previous();
        iterator.next();
        T curNode = iterator.next();
        // set the previous node's next node to current node
        setNextNodeId(previousNode, getNodeId(curNode));

        // set the current node's previous node
        setPreviousNodeId(curNode, getNodeId(previousNode));
        if (!iterator.hasNext())
          setNextNodeId(curNode, null); // last node
      }
    }

    repository.saveAll(this.linkedList);

  }

  public ID getNodeId(T node) {
    ID id = null;
    try {
      id = (ID) node.getClass().getDeclaredMethod("getId").invoke(node, new Object[0]);
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException
        | IllegalArgumentException | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return id;
  }

  public ID getPreviousNodeId(T node) {
    if(node==null) return null;
    ID id = null;
    try {
      id = (ID) node.getClass().getDeclaredMethod("getPreviousNodeId").invoke(node, new Object[0]);
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException
        | IllegalArgumentException | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return id;
  }

  public ID getNextNodeId(T node) {
    if(node==null)
      return null;
    ID id = null;
    try {
      id = (ID) node.getClass().getDeclaredMethod("getNextNodeId").invoke(node, new Object[0]);
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException
        | IllegalArgumentException | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return id;
  }

  public void setNextNodeId(T node, ID nextNodeId) {
    try {
      node.getClass().getDeclaredMethod("setNextNodeId", getNodeId(node).getClass()).invoke(node,
          nextNodeId);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void setNextNode(T node, T nextNode) {
    try {
      setNextNodeId(node, getNodeId(nextNode));
    } catch (IllegalArgumentException | SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void setPreviousNodeId(T node, ID previousNodeId) {
    try {
      node.getClass().getDeclaredMethod("setPreviousNodeId", getNodeId(node).getClass())
          .invoke(node, previousNodeId);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void setPreviousNode(T node, T previousNode) {
    try {
      this.setPreviousNodeId(node, getNodeId(previousNode));
    } catch (IllegalArgumentException | SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public T getCurPreviousNode(T node) {
    T previosNode = null;
    try {
      previosNode = repository.findById(getPreviousNodeId(node)).get();
    } catch (IllegalArgumentException | SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return previosNode;
  }

  public T getPreviousNode(T node) {
    T previosNode = null;
    try {
      previosNode = repository.findById(getPreviousNodeId(getOldNode(node))).get();
    } catch (IllegalArgumentException | SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return previosNode;
  }

  public T getCurNextNode(T node) {
    T previosNode = null;
    try {
      previosNode = repository.findById(getNextNodeId(node)).get();
    } catch (IllegalArgumentException | SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return previosNode;
  }

  public T getNextNode(T node) {
    T previosNode = null;
    try {
      previosNode = repository.findById(getNextNodeId(getOldNode(node))).get();
    } catch (IllegalArgumentException | SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return previosNode;
  }

  public T getOldNode(T node) {
    return this.oldNode == null ? repository.findById(getNodeId(node)).get() : this.oldNode;
  }

  public LinkedList<T> buildLinkedList(JpaRepository<T, ID> repository, T node) {
    linkedList = new LinkedList<T>();
    linkedList.add(oldNode);

    if (this.previousNodeId == null && this.nextNodeId == null) {
      return linkedList;
    }

    if (this.previousNodeId != null && this.nextNodeId != null) {
      buildPrevious(oldNode);
      buildNext(oldNode);
      return linkedList;
    }

    if (this.previousNodeId == null && this.nextNodeId == null) {
      buildNext(oldNode);
    }

    if (this.nextNodeId == null && this.previousNodeId != null) {
      buildPrevious(oldNode);
    }
    return linkedList;
  }

  private void buildPrevious(T node) {
    T previousNode = repository.findById(getPreviousNodeId(node)).get();
    linkedList.addFirst(previousNode);
    if (getPreviousNodeId(previousNode) != null)
      buildPrevious(previousNode);
  }

  private void buildNext(T node) {
    T nextNode = repository.findById(getNextNodeId(node)).get();
    linkedList.addLast(nextNode);
    if (getNextNodeId(nextNode) != null)
      buildNext(nextNode);
  }

  private void buildPrevious(T node, Map<ID, T> map) {
    T previousNode = map.get(getPreviousNodeId(node));
    linkedList.addFirst(previousNode);
    if (getPreviousNodeId(previousNode) != null)
      buildPrevious(previousNode, map);
  }

  private void buildNext(T node, Map<ID, T> map) {
    T nextNode = map.get(getNextNodeId(node));
    linkedList.addLast(nextNode);
    if (getNextNodeId(nextNode) != null)
      buildNext(nextNode, map);
  }

}
