package cn.sparrow.model.common;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
public class SparrowTree<T, ID> {

	@EqualsAndHashCode.Include
	private ID id;

	private String name;
	private T me;
	private int level;
	private List<SparrowTree<T, ID>> children = new ArrayList<SparrowTree<T, ID>>();

	public SparrowTree(T me) {
		this.me = me;
	}

	public SparrowTree(String name, int level) {
		this.name = name;
		this.level = level;
	}

	public SparrowTree(T me, String name, int level) {
		super();
		this.me = me;
		this.name = name;
		this.level = level;
	}

	public SparrowTree(ID id, String name) {
		this.id = id;
		this.name = name;
	}

	public SparrowTree(T t, ID id) {
		this.me = t;
		this.id = id;
	}

	public void setChildren(List<SparrowSortableTree<T, ID>> sortableChildren) {
		this.children.clear();
		this.children.addAll(sortableChildren);
		
	}

}
