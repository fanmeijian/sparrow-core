package cn.sparrow.model.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ISparrowSortedService<T,ID> {
	public void sort(List<T> list);
	public void saveSort(JpaRepository<T, ID> repository ,T node);
}
