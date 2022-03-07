package cn.sparrow.permission.mgt.common.listener;
//package cn.sparrow.permission.listener;
//
//import org.springframework.validation.BeanPropertyBindingResult;
//import org.springframework.validation.Errors;
//
//public final class RepositoryErrorFactory {
//
//  public static Errors getErros(Object object) {
//    return new BeanPropertyBindingResult(object, object.getClass().getName());
//  }
//
//  public static Errors getErros(Object object, String rejectCode, String rejectMessage) {
//    Errors errors = new BeanPropertyBindingResult(object, object.getClass().getName());
//    errors.reject(rejectCode, rejectMessage);
//    return errors;
//  }
//}
