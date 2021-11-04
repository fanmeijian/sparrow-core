package cn.sparrow.common.service;

import java.util.List;
import cn.sparrow.common.repository.SparrowMultiRelationRepository;
import cn.sparrow.common.repository.SparrowSingleRelationRepository;
import cn.sparrow.model.common.SparrowTree;

public interface ISparrowTreeService<T, ID> {
	/**
	 * 对整颗树进行排序，即对同一层级的叶排序
	 * 
	 * @param sparrowSortableTree
	 */
	public void sort(SparrowTree<T, ID> sparrowTree);

	/**
	 * 构建1对多的关系树
	 * 
	 * @param repository
	 * @param parentId
	 * @return
	 */
	public SparrowTree<T, ID> buildTree(SparrowSingleRelationRepository<T, ID> repository, ID parentId);

	/**
	 * 构建多对多的关系树
	 * 
	 * @param repository
	 * @param parentId
	 * @return
	 */
	public SparrowTree<T, ID> buildTree(SparrowMultiRelationRepository<T, ID> repository, ID parentId);

	/**
	 * 构建含直接上级的树，但只有直接上级，没有上级的同级节点
	 * 
	 * @param repository
	 * @param id
	 * @return
	 */
	public SparrowTree<T, ID> buildTreeWithParent(SparrowSingleRelationRepository<T, ID> repository, ID id);

	/**
	 * 构建含直接上级的树，但只有直接上级，没有上级的同级节点
	 * 
	 * @param repository
	 * @param id
	 * @return
	 */
	public SparrowTree<T, ID> buildTreeWithParent(SparrowMultiRelationRepository<T, ID> repository, ID id);

	/**
	 * 获取一对多关系的子节点
	 * 
	 * @param repository
	 * @param parentId
	 * @return
	 */
	public List<SparrowTree<T, ID>> getChildren(SparrowSingleRelationRepository<T, ID> repository, ID parentId);

	/**
	 * 获取多对多关系的子节点
	 * 
	 * @param repository
	 * @param parentId
	 * @return
	 */
	public List<SparrowTree<T, ID>> getChildren(SparrowMultiRelationRepository<T, ID> repository, ID parentId);

	/**
	 * 是否它的孩子
	 * @param repository
	 * @param id
	 * @param parentId
	 * @return
	 */
	public boolean isChild(SparrowMultiRelationRepository<T, ID> repository, ID id, ID parentId);
	
	/**
	 * 是否它的孩子
	 * @param repository
	 * @param id
	 * @param parentId
	 * @return
	 */
	public boolean isChild(SparrowSingleRelationRepository<T, ID> repository, ID id, ID parentId);

	
	/**
	 * 是否他的祖先
	 * @param repository
	 * @param parentId
	 * @param id
	 * @return
	 */
	public boolean isParent(SparrowMultiRelationRepository<T, ID> repository, ID parentId, ID id);
	
	/**
	 * 是否她的祖先
	 * @param repository
	 * @param parentId
	 * @param id
	 * @return
	 */
	public boolean isParent(SparrowSingleRelationRepository<T, ID> repository, ID parentId, ID id);


}
