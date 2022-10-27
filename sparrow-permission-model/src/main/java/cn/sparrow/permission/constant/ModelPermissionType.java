package cn.sparrow.permission.constant;

/*
 * 模型权限类型
 */
public enum ModelPermissionType {
	Read, //读权限，可以读取此模型产生的数据
	Create, // 创建权限，可以创建此模型的数据
	Update, // 更新权限，可以更新此模型产生的数据
	Delete, // 删除权限，可以删除此模型产生的数据
	Search, // 查询权限，可以搜索此模型产生的权限
}
