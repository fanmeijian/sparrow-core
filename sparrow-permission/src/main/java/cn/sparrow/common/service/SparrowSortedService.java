package cn.sparrow.common.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.common.SparrowSortableTree;

@Service
public class SparrowSortedService<T, ID> implements ISparrowSortedService<T, ID> {

	JpaRepository<T, ID> repository;
	LinkedList<T> originLinkedList;
	LinkedList<T> newLinkedList = new LinkedList<T>();
	private T originNode;
	private T newNode;
	private ID originPreviousNodeId;
	private ID originNextNodeId;
	private ID newNextNodeId;
	private ID newPreviousNodeId;

	@Override
	@Transactional
	public void saveSort(JpaRepository<T, ID> repository, T node) {

		this.repository = repository;
		this.originNode = getOriginNode(node);
		this.originNextNodeId = getNextNodeId(originNode);
		this.originPreviousNodeId = getPreviousNodeId(originNode);
		this.newNextNodeId = getNextNodeId(node);
		this.newPreviousNodeId = getPreviousNodeId(node);

		// remove from origin position
		if (this.originPreviousNodeId != null) {
			T originPreviousNode = repository.getOne(originPreviousNodeId);
			setNextNodeId(originPreviousNode, originNextNodeId);
			repository.save(originPreviousNode);
		}

		if (originNextNodeId != null) {
			T originNextNode = repository.getOne(originNextNodeId);
			setPreviousNodeId(originNextNode, originPreviousNodeId);
			repository.save(originNextNode);
		}

		// save the new Node
		this.newNode = repository.getOne(getNodeId(node));
		setPreviousNodeId(newNode, getPreviousNodeId(node));
		setNextNodeId(newNode, getNextNodeId(node));
		repository.save(newNode);

		// insert the new node to target position
		if (newPreviousNodeId != null) {
			T newPreviousNode = repository.getOne(newPreviousNodeId);
			setNextNodeId(newPreviousNode, getNodeId(node));
			repository.save(newPreviousNode);
		}

		if (newNextNodeId != null) {
			T newNextNode = repository.getOne(newNextNodeId);
			setPreviousNodeId(newNextNode, getNodeId(node));
			repository.save(newNextNode);
		}
	}

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

	public void sortSparrowSortableTree(List<SparrowSortableTree<T, ID>> sparrowSortableTrees) {
		List<T> list = new ArrayList<T>();
		Map<ID, SparrowSortableTree<T, ID>> map = new HashMap<ID, SparrowSortableTree<T, ID>>();
		sparrowSortableTrees.forEach(f -> {
			list.add(f.getMe());
			map.put(f.getId(), f);
		});
		sort(list);
		sparrowSortableTrees.clear();
		list.forEach(f -> {
			sparrowSortableTrees.add(map.get(getNodeId(f)));
		});
	}

	@Override
	public void sort(SparrowSortableTree<T, ID> sparrowSortableTree) {
		// sorted the children;
		sortSparrowSortableTree(sparrowSortableTree.getSortableChildren());
		sparrowSortableTree.setChildren(sparrowSortableTree.getSortableChildren());
		sparrowSortableTree.getSortableChildren().forEach(f -> {
			sort(f);
		});

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

	public ID getNodeId(T node) {
		ID id = null;
		try {
			id = (ID) node.getClass().getMethod("getId").invoke(node, new Object[0]);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public ID getPreviousNodeId(T node) {
		if (node == null)
			return null;
		ID id = null;
		try {
			id = (ID) node.getClass().getMethod("getPreviousNodeId").invoke(node, new Object[0]);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public ID getNextNodeId(T node) {
		if (node == null)
			return null;
		ID id = null;
		try {
			id = (ID) node.getClass().getMethod("getNextNodeId").invoke(node, new Object[0]);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public void setNextNodeId(T node, ID nextNodeId) {
		try {
			node.getClass().getMethod("setNextNodeId", Object.class).invoke(node, nextNodeId);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
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
			node.getClass().getMethod("setPreviousNodeId", Object.class).invoke(node, previousNodeId);
//      node.getClass().getField("previousNodeId").set(node, previousNodeId);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
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
			previosNode = repository.findById(getPreviousNodeId(getOriginNode(node))).get();
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
			previosNode = repository.findById(getNextNodeId(getOriginNode(node))).get();
		} catch (IllegalArgumentException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return previosNode;
	}

	public T getOriginNode(T node) {
		return this.originNode == null ? repository.findById(getNodeId(node)).get() : this.originNode;
	}

	public LinkedList<T> buildLinkedList(JpaRepository<T, ID> repository, T node) {
		originLinkedList = new LinkedList<T>();
		originLinkedList.add(originNode);

		if (this.originPreviousNodeId == null && this.originNextNodeId == null) {
			return originLinkedList;
		}

		if (this.originPreviousNodeId != null && this.originNextNodeId != null) {
			buildPrevious(originNode);
			buildNext(originNode);
			return originLinkedList;
		}

		if (this.originPreviousNodeId == null && this.originNextNodeId == null) {
			buildNext(originNode);
		}

		if (this.originNextNodeId == null && this.originPreviousNodeId != null) {
			buildPrevious(originNode);
		}
		return originLinkedList;
	}

	private void buildPrevious(T node) {
		T previousNode = repository.findById(getPreviousNodeId(node)).get();
		originLinkedList.addFirst(previousNode);
		if (getPreviousNodeId(previousNode) != null)
			buildPrevious(previousNode);
	}

	private void buildNext(T node) {
		T nextNode = repository.findById(getNextNodeId(node)).get();
		originLinkedList.addLast(nextNode);
		if (getNextNodeId(nextNode) != null)
			buildNext(nextNode);
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

	private boolean isInLinkedList(T node, List<LinkedList<T>> linkedLists) {
		for (LinkedList<T> linkedList : linkedLists) {
			for (T t : linkedList) {
				if (getNodeId(t).equals(getNodeId(node)))
					return true;
			}
		}

		return false;
	}

}
