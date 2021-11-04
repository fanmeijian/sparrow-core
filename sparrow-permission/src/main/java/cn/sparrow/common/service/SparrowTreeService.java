package cn.sparrow.common.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import cn.sparrow.common.repository.SparrowMultiRelationRepository;
import cn.sparrow.common.repository.SparrowSingleRelationRepository;
import cn.sparrow.model.common.SparrowTree;

@Service
public class SparrowTreeService<T, ID> implements ISparrowTreeService<T, ID> {

	@Autowired
	ISparrowSortService<T, ID> sortService;
	JpaRepository<T, ID> repository;
	LinkedList<T> originLinkedList;
	LinkedList<T> newLinkedList = new LinkedList<T>();

	public void sortSparrowSortableTree(List<SparrowTree<T, ID>> sparrowSortableTrees) {
		List<T> list = new ArrayList<T>();
		Map<ID, SparrowTree<T, ID>> map = new HashMap<ID, SparrowTree<T, ID>>();
		sparrowSortableTrees.forEach(f -> {
			list.add(f.getMe());
			map.put(f.getId(), f);
		});
		sortService.sort(list);
		sparrowSortableTrees.clear();
		list.forEach(f -> {
			sparrowSortableTrees.add(map.get(getNodeId(f)));
		});
	}

	@Override
	public void sort(SparrowTree<T, ID> sparrowSortableTree) {
		// sorted the children;
		sortSparrowSortableTree(sparrowSortableTree.getChildren());
		sparrowSortableTree.setChildren(sparrowSortableTree.getChildren());
		sparrowSortableTree.getChildren().forEach(f -> {
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

	@Override
	public SparrowTree<T, ID> buildTree(SparrowSingleRelationRepository<T, ID> repository, ID parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SparrowTree<T, ID> buildTree(SparrowMultiRelationRepository<T, ID> repository, ID parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SparrowTree<T, ID> buildTreeWithParent(SparrowSingleRelationRepository<T, ID> repository, ID parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SparrowTree<T, ID> buildTreeWithParent(SparrowMultiRelationRepository<T, ID> repository, ID parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SparrowTree<T, ID>> getChildren(SparrowSingleRelationRepository<T, ID> repository, ID parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SparrowTree<T, ID>> getChildren(SparrowMultiRelationRepository<T, ID> repository, ID parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveSort(JpaRepository<T, ID> repository, T node) {
		sortService.saveSort(repository, node);
	}

	public ID getNodeId(T node) {
		try {
			return (ID) BeanUtils.getPropertyDescriptor(node.getClass(), "id").getReadMethod().invoke(node);
		} catch (BeansException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isChild(SparrowMultiRelationRepository<T, ID> repository, ID id, ID parentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChild(SparrowSingleRelationRepository<T, ID> repository, ID id, ID parentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isParent(SparrowMultiRelationRepository<T, ID> repository, ID parentId, ID id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isParent(SparrowSingleRelationRepository<T, ID> repository, ID parentId, ID id) {
		// TODO Auto-generated method stub
		return false;
	}

}
