package com.example.demo.gof;

import java.util.ArrayList;
import java.util.List;

/**
 * 表示目录结构 - 组合模式
 * @author cong.pi
 * @date 2022/3/3 18:38
 */
public class FileSystemNode {

  private String path;
  private boolean isFile;
  private List<FileSystemNode> subNodes = new ArrayList<>();

  public FileSystemNode(String path, boolean isFile) {
    this.path = path;
    this.isFile = isFile;
  }

  public int countNumOfFiles() {
    // TODO
    return 0;
  }
  public long countSizeIfFies() {
    // TODO
    return 0;
  }

  public String getPath() {
    return path;
  }

  public void addSubNode(FileSystemNode fileOrDir) {
    subNodes.add(fileOrDir);
  }

  public void removeSubNode(FileSystemNode fileDir) {
    int size = subNodes.size();
    int i = 0;
    for (; i < size; i++) {
      if (subNodes.get(i).getPath().equalsIgnoreCase(fileDir.getPath())) {
        break;
      }
      if (i < size) {
        subNodes.remove(i);
      }
    }
  }
}
