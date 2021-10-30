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
		if(getPreviousNodeId(node)==null) {
			//head node
			T head = findHead(repository,node);
			// change current head node previousNode to the current node's next node
			this.setPreviousNode(head, getNextNodeId(node));
			repository.save(head);
		}else {
			// change the previousNode's next node to current node
			T previousNode = repository.findById(getPreviousNodeId(node)).get();
			this.setNextNode(previousNode, getNodeId(node));
			repository.save(previousNode);
		}
	}
	
	public T findHead(JpaRepository<T, ID> repository,T node) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("previousNodeId", GenericPropertyMatchers.exact());
		Example<T> ex = Example.of(obj, matcher); 
		T head = repository.findOne(null);
		if(!=null)
			findHead(repository, repository.findOne(null).get());
	}
	
	public ID getNodeId(T node) {
		ID id=null;
		try {
			id = (ID) node.getClass().getDeclaredMethod("getId").invoke(node, new Object[0]);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public ID getPreviousNodeId(T node) {
		ID id=null;
		try {
			id = (ID) node.getClass().getDeclaredMethod("getPreviousNodeId").invoke(node, new Object[0]);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public ID getNextNodeId(T node) {
		ID id=null;
		try {
			id = (ID) node.getClass().getDeclaredMethod("getNextNodeId").invoke(node, new Object[0]);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public void setNextNode(T node, ID nextNodeId) {
		try {
			node.getClass().getDeclaredMethod("setNextNodeId").invoke(node, nextNodeId);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPreviousNode(T node, ID previousNodeId) {
		try {
			node.getClass().getDeclaredMethod("setPreviosNodeId").invoke(node, previousNodeId);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
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
	
	public LinkedList<T> buildLinkedList(JpaRepository<T, ID> repository, T node){
		LinkedList<T> linkedList = new LinkedList<T>();
		if(getPreviousNodeId(node)==null) {
			linkedList.add(this.findHead(repository, node));
		}else {
			linkedList.add(getPreviousNode(node));
			linkedList.add(node);
		}
		return linkedList;
	}


}
