package cn.sparrow.permission.model.resource;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
public class SparrowTree<T, ID> {

  @EqualsAndHashCode.Include
  private ID id;

  private String name;
  private T me;
  private int level;
  private int childCount;
  private ID previousNodeId;
  private ID nextNodeId;
  private List<SparrowTree<T, ID>> children = new ArrayList<SparrowTree<T, ID>>();

  public SparrowTree(T me) {
    this.me = me;
  }

  public SparrowTree(String name, int level) {
    this.name = name;
    this.level = level;
  }

  public SparrowTree(T me, String name, int level) {
    this.me = me;
    this.name = name;
    this.level = level;
  }

  public SparrowTree(ID id, String name) {
    this.id = id;
    this.name = name;
  }

  public SparrowTree(T object, ID id) {
    this.me = object;
    this.id = id;
  }

  public SparrowTree(ID id, String name, ID previousNodeId, ID nextNodeId) {
    this.id = id;
    this.nextNodeId = nextNodeId;
    this.previousNodeId = previousNodeId;
  }

  public SparrowTree(ID id, ID previousNodeId, ID nextNodeId) {
    this.id = id;
    this.nextNodeId = nextNodeId;
    this.previousNodeId = previousNodeId;
  }

  public SparrowTree(T object, ID id, ID previousNodeId, ID nextNodeId) {
    this.id = id;
    this.me = object;
    this.nextNodeId = nextNodeId;
    this.previousNodeId = previousNodeId;
  }

}
