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

import cn.sparrow.permission.model.group.Group;
import cn.sparrow.permission.model.group.Group_;

public interface GroupRepository extends JpaRepository<Group, String>, JpaSpecificationExecutor<Group> {

	@Transactional
	void deleteByIdIn(String[] ids);

	Page<Group> findByNameContaining(String name, Pageable pageable);

	default Page<Group> search(Group group, Pageable pageable) {
		Specification<Group> specification = new Specification<Group>() {
			private static final long serialVersionUID = 1L;
			List<Predicate> predicates = new ArrayList<Predicate>();

			@Override
			public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (group.getName() != null) {
					predicates.add(criteriaBuilder.like(root.get(Group_.name), "%" + group.getName() + "%"));
				}
				if (group.getCode() != null) {
					predicates.add(criteriaBuilder.like(root.get(Group_.code), "%" + group.getCode() + "%"));
				}

				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}

		};
		return findAll(specification, pageable);
	};
}
