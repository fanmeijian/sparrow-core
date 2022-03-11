package cn.sparrow.permission.mgt.service.impl;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;

public interface ViewService {
  public void filterNoReadObject(Iterable<AbstractSparrowEntity> iterable);
}
