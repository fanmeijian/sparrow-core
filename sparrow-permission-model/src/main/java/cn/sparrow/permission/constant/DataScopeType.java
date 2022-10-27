package cn.sparrow.permission.constant;

/*
 * 数据范围权限类型，用于基于模型权限后的可操作的数据范围。
 * 数据范围的控制，是基于模型的属性值来进行判断。例如某人或某种角色，只可产生某种数据或读取属性值为XX的数据
 */
public enum DataScopeType {
	ReadAll, // 可以读取所有数据，忽略所有的读权限设置
	CreateAll, // 可以创建任意类型的数据，忽略所有创建权限设置
	UpdateAll, // 可以修改任意类型的数据，忽略所有更新权限设置
	DeleteAll, // 可以删除任意类型的数据，忽略所有删除权限的设置
	SearchAll, // 可以查询任何数据，忽略所有查询条件的设置
}
