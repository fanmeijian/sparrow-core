package cn.sparrow.common.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.sparrow.common.service.UrlService;
import cn.sparrow.model.url.SparrowUrl;

@RestController
public class UrlController {
  
  @Autowired UrlService urlService;
  
  @PostMapping("/sparrowUrls/batch")
  public int saveUrls(List<SparrowUrl> sparrowUrls) {
    return urlService.saveUrls(sparrowUrls);
  }
  
  @DeleteMapping("/sparrowUrls/batch")
  public void delUrls(List<String> sparrowUrls) {
    urlService.delUrls(sparrowUrls);
  }
  
  @PatchMapping("/sparrowUrls/{id}")
  public SparrowUrl udpateSparrowUrl(SparrowUrl sparrowUrl) {
    return urlService.updateUrl(sparrowUrl);
  }
  
//  @GetMapping("/sparrowUrls/{id}")
//  public SparrowUrl getSparrowUrl(String id) {
//    return urlService.updateUrl(sparrowUrl);
//  }
}
