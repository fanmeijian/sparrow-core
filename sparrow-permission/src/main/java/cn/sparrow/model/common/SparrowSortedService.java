package cn.sparrow.model.common;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.apache.ignite.internal.processors.platform.client.cluster.ClientClusterGroupGetNodeIdsRequest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.repository.JpaRepository;

import net.bytebuddy.asm.Advice.This;

public class SparrowSortedService<T, ID> implements ISparrowSortedService<T, ID> {

	@Override
	public void sort(List<T> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveSort(JpaRepository<T, ID> repository, T node) {

		if (getPreviousNodeId(node) == null) {
			T headNode = this.findHead(repository, node);
			this.setPreviousNode(headNode, node);
			repository.save(headNode);
		}

		if (getNextNode(node) == null) {
			// if the current node is last node
			// set the current last node's next to node
			T lastNode = this.findLast(node);
			this.setNextNode(lastNode, node);
			repository.save(lastNode);
		}

		// remove the node from old position
		T previosNode = getPreviousNode(node);
		T nextNode = getNextNode(node);
		this.setNextNode(previosNode, nextNode);
		this.setPreviousNode(nextNode, previosNode);
		repository.save(previosNode);
		repository.save(nextNode);
		repository.save(node);
	}

	public T findHead(JpaRepository<T, ID> repository,T node) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("previousNodeId", GenericPropertyMatchers.exact());
		Example<T> ex = Example.of(obj, matcher); 
		T head = repository.findOne(null);
		if(!=null)
			findHead(repository, repository.findOne(null).get());
	}

	public T findLast(T node) {

	}

	public ID getNodeId(T node) {
		ID id = null;
		try {
			id = (ID) node.getClass().getDeclaredMethod("getId").invoke(node, new Object[0]);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public ID getPreviousNodeId(T node) {
		ID id = null;
		try {
			id = (ID) node.getClass().getDeclaredMethod("getPreviousNodeId").invoke(node, new Object[0]);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public ID getNextNodeId(T node) {
		ID id = null;
		try {
			id = (ID) node.getClass().getDeclaredMethod("getNextNodeId").invoke(node, new Object[0]);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public void setNextNodeId(T node, ID nextNodeId) {
		try {
			node.getClass().getDeclaredMethod("setNextNodeId").invoke(node, nextNodeId);
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
			node.getClass().getDeclaredMethod("setPreviosNodeId").invoke(node, previousNodeId);
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
			previosNode = (T) node.getClass().getDeclaredMethod("getPreviousNode").invoke(node, new Object[0]);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return previosNode;
	}

	public T getPreviousNode(T node) {
		T previosNode = null;
		try {
			previosNode = (T) node.getClass().getDeclaredMethod("getPreviousNode").invoke(node, new Object[0]);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return previosNode;
	}

	public T getCurNextNode(T node) {
		T previosNode = null;
		try {
			previosNode = (T) node.getClass().getDeclaredMethod("getPreviousNode").invoke(node, new Object[0]);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return previosNode;
	}

	public T getNextNode(T node) {
		T previosNode = null;
		try {
			previosNode = (T) node.getClass().getDeclaredMethod("getPreviousNode").invoke(node, new Object[0]);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return previosNode;
	}

	public T getOldNode(T node) {
		return node;

	}

	public LinkedList<T> buildLinkedList(JpaRepository<T, ID> repository, T node) {
		LinkedList<T> linkedList = new LinkedList<T>();
		if (getPreviousNodeId(node) == null && getNextNode(node) == null) {
			// only one node
			repository.save(node);
		}

		if (getPreviousNodeId(node) == null) {
			// if the current node set to head node, then add the old head node
			this.setNextNode(getPreviousNode(node), getNextNode(node));
			this.setPreviousNode(getNextNode(node), getPreviousNode(node));
			this.setPreviousNode(this.findHead(repository, node), node);
			repository.save(getPreviousNode(node));
			repository.save(getNextNode(node));
			return linkedList;
		}

		if (getNextNode(node) == null) {
			// if the current node is last node
			linkedList.add(getCurPreviousNode(node));
			linkedList.addLast(node);
		}

		// insert the node to the target position
		linkedList.add(getCurPreviousNode(node));
		linkedList.add(node);
		linkedList.add(getCurNextNode(node));

		return linkedList;
	}

}
