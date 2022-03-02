package cn.sparrow.permission.service;

import cn.sparrow.permission.model.AbstractSparrowEntity;

public interface ViewService {
  public void filterNoReadObject(Iterable<AbstractSparrowEntity> iterable);
}
