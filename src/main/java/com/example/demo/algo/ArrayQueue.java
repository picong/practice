package com.example.demo.algo;

/**
 * @author cong.pi
 * @date 2022/4/24 15:54
 */
public class ArrayQueue<Item> {

  private Object[] items;
  // 对头位置
  private int head = 0;
  // 队尾
  private int tail = 0;
  // 对量容量
  int n = 0;

  public ArrayQueue(int capacity) {
    this.items = new Object[capacity];
    this.n = capacity;
  }

  public boolean enqueue(Item item) {
    // tail已经移动到数据末尾
    if (tail == n) {
      if (head == 0) {
        // 如果头位置没变化表示数组容量不够了，入队失败
        return false;
      }
      // 整体往数组前面搬移
      for (int i = head; i < tail; i++) {
        items[i - head] = items[i];
      }
      // 修正tail和head
      tail -= head;
      head = 0;
    }
    //修正head和
    // 正常入队操作
    items[tail++] = item;
    return true;
  }

  public Item dequeue() {
    if (head == tail) {
      return null;
    }
    return (Item) items[++head];
  }


}
