package cn.sparrow.permission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sparrow.model.permission.Model;
import cn.sparrow.permission.repository.ModelRepository;

@Service
public class ModelService {

  @Autowired
  ModelRepository modelRepository;
  
  public Model getModel(Object object) {
    return modelRepository.findById(object.getClass().getName()).orElse(null);
  }
  
}
