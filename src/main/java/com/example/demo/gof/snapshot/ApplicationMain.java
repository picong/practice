package com.example.demo.gof.snapshot;

import java.util.Scanner;

/**
 * @author cong.pi
 * @date 2022/3/29 11:14
 */
public class ApplicationMain {

  public static void main(String[] args) {
    InputText inputText = new InputText();
    SnapshotHolder snapshotHolder = new SnapshotHolder();
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      String input = scanner.next();
      if (input.equals(":list")) {
        System.out.println(inputText.getText());
      } else if (input.equals(":undo")) {
        Snapshot snapshot = snapshotHolder.popSnapshot();
        inputText.restoreSnapshot(snapshot);
      } else {
        snapshotHolder.pushSnapshot(inputText.createSnapshot());
        inputText.append(input);
      }
    }
  }

}
