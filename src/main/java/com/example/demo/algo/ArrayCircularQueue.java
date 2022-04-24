package com.example.demo.algo;

/**
 * @author cong.pi
 * @date 2022/4/24 16:50
 */
public class ArrayCircularQueue<Item> {

  private Object[] items;
  private int n = 0; // 队列容量

  private int head = 0;
  private int tail = 0;

  public ArrayCircularQueue(int capacity) {
    items = new Object[capacity];
    this.n = capacity;
  }

  public boolean enqueue(Item item) {
    // 如果队满，返回false
    if ((tail + 1) % n == head) {
      return false;
    }
    items[tail] = item;
    tail = (tail + 1) % n;
    return true;
  }

  public Item dequeue() {
    if (head == tail) return null;
    Item ret = (Item) items[tail];
    head = (head + 1) % n;
    return ret;
  }
}
