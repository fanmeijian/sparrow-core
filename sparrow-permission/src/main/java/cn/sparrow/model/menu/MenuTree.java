package cn.sparrow.model.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuTree extends Menu {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1520162770424658116L;
	private List<MenuTree> children;
	private String parentString;
	private String parentIdString;
	
	
	public MenuTree(Menu menu) {
		this.setId(menu.getId());
		this.setName(menu.getName());
		this.setParentId(menu.getParentId());
		this.setSort(menu.getSort());
		this.setUrl(menu.getUrl());
		this.children=new ArrayList<MenuTree>();
	}

	public List<MenuTree> getChildren() {
		return children;
	}

	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}

	public String getParentString() {
		return parentString;
	}

	public void setParentString(String parentString) {
		this.parentString = parentString;
	}

	public String getParentIdString() {
		return parentIdString;
	}

	public void setParentIdString(String parentIdString) {
		this.parentIdString = parentIdString;
	}
	
	
}
