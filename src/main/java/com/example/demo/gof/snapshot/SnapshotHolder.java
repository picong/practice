package com.example.demo.gof.snapshot;

import java.util.Stack;

/**
 * @author cong.pi
 * @date 2022/3/29 11:12
 */
public class SnapshotHolder {

  private Stack<Snapshot> snapshots = new Stack<>();

  public Snapshot popSnapshot() {
    return snapshots.pop();
  }

  public void pushSnapshot(Snapshot snapshot) {
    snapshots.push(snapshot);
  }

}
