package cn.sparrow.common.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.model.common.SparrowSortableTree;
import cn.sparrow.model.permission.Menu;

public interface ISparrowSortedService<T,ID> {
	public void sort(List<T> list);
	public void saveSort(JpaRepository<T, ID> repository ,T node);
	public void sort(SparrowSortableTree<T, ID> sparrowSortableTree);
}
