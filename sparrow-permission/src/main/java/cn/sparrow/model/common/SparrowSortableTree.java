package cn.sparrow.model.common;

import java.util.ArrayList;
import java.util.List;

import cn.sparrow.model.permission.Menu;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@EqualsAndHashCode(callSuper = true)
public class SparrowSortableTree<T, ID> extends SparrowTree<T, ID> {
	
	@Getter
	@Setter
	@EqualsAndHashCode.Exclude
	private ID previousNodeId;
	
	@Getter
	@Setter
	@EqualsAndHashCode.Exclude
	private ID nextNodeId;
	
	private List<SparrowSortableTree<T, ID>> children = new ArrayList<SparrowSortableTree<T,ID>>();

	public SparrowSortableTree(ID id, String name, ID previousNodeId, ID nextNodeId) {
		super(id, name);
		this.nextNodeId = nextNodeId;
		this.previousNodeId = previousNodeId;
	}
	
	public SparrowSortableTree(ID id, ID previousNodeId, ID nextNodeId) {
		this.setId(id);
		this.nextNodeId = nextNodeId;
		this.previousNodeId = previousNodeId;
	}
	
	public SparrowSortableTree(T t, ID id, ID previousNodeId, ID nextNodeId) {
		super(t,id);
		this.setMe(t);
		this.setId(id);
		this.nextNodeId = nextNodeId;
		this.previousNodeId = previousNodeId;
	}
	
	public SparrowSortableTree(T t) {
		super(t);
		this.setMe(t);
	}

	public List<SparrowSortableTree<T, ID>> getSortableChildren() {
		return this.children;
	}
	
	public void setSortableChildren(List<SparrowSortableTree<T, ID>> children) {
		this.children = children;
	}

	@Override
	public void setChildren(List<SparrowSortableTree<T, ID>> sortableChildren) {
		super.setChildren(sortableChildren);
	}
}
