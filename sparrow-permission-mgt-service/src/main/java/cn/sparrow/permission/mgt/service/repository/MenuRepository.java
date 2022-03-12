package cn.sparrow.permission.mgt.service.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.resource.Menu;
import cn.sparrow.permission.model.resource.Menu_;

public interface MenuRepository extends JpaRepository<Menu, String> ,JpaSpecificationExecutor<Menu>{

  List<Menu> findByParentId(String parentId);
  Menu findByCode(String code);
  
  default Page<Menu> search(Menu menu, Pageable pageable){
	  Specification<Menu> specification = new Specification<Menu>() {
			private static final long serialVersionUID = 1L;
			List<Predicate> predicates = new ArrayList<Predicate>();
			@Override
			public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if(menu.getName()!=null) {
					predicates.add(criteriaBuilder.like(root.get(Menu_.name), "%"+ menu.getName()+"%"));
				}
				if(menu.getCode()!=null) {
					predicates.add(criteriaBuilder.like(root.get(Menu_.code), "%"+ menu.getCode()+"%"));
				}
				
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
			
		};
		return findAll(specification,pageable);
  };

  @Transactional
  void deleteByIdIn(String[] ids);
}
