package cn.sparrow.common.service;

public class ExpressionMvel {

  public boolean IS(Object arg1,Object arg2) {
    return arg1.equals(arg2);
  }
  
  public boolean IN(Object arg1, Iterable<?> list) {
    for(Object object : list) {
      if(arg1.equals(object))
        return true;
    }
    return false;
  }
  
}
