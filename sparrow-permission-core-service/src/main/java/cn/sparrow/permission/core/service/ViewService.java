package cn.sparrow.permission.core.service;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;

public interface ViewService {
  public void filterNoReadObject(Iterable<AbstractSparrowEntity> iterable);
}
