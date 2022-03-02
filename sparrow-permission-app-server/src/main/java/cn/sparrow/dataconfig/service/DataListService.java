package cn.sparrow.dataconfig.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sparrow.dataconfig.model.DataList;
import cn.sparrow.dataconfig.repository.DataListRepository;
import cn.sparrow.permission.model.SparrowTree;

@Service
public class DataListService {
  private static Logger logger = LoggerFactory.getLogger(DataListService.class);

  @Autowired
  DataListRepository dataListRepository;

  public List<DataList> getDataListByCode(String code) {
    return dataListRepository.findByParentId(dataListRepository.findByCode("YES_OR_NO").getId());
  }

  public SparrowTree<DataList, String> getDataListTreeByCode(String code) {
    return getDataListTreeByParentId(dataListRepository.findByCode(code).getId());
  }

  public SparrowTree<DataList, String> getDataListTreeByParentId(String parentId) {
    DataList rootDataList = new DataList();
    rootDataList.setId(parentId);
    SparrowTree<DataList, String> dataListTree =
        new SparrowTree<DataList, String>(dataListRepository.findById(parentId).orElse(rootDataList));
    buildDataListTree(dataListTree);
    return dataListTree;

  }

  public void buildDataListTree(SparrowTree<DataList, String> dataListTree) {
    List<DataList> dataLists = dataListRepository.findByParentId(dataListTree.getMe().getId());
    for (DataList dataLst : dataLists) {
    	SparrowTree<DataList, String> leaf = new SparrowTree<DataList, String>(dataLst);
      dataListTree.getChildren().add(leaf);
      buildDataListTree(leaf);
    }
  }
}
