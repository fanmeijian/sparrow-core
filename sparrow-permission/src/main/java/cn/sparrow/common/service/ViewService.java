package cn.sparrow.common.service;

import cn.sparrow.model.common.AbstractSparrowEntity;

public interface ViewService {
  public void filterNoReadObject(Iterable<AbstractSparrowEntity> iterable);
}
