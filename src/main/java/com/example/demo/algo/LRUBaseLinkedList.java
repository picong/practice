package com.example.demo.algo;

import java.util.Scanner;

/**
 * 基于单链表LRU算法
 * @author cong.pi
 * @date 2022/5/5 10:50
 */
public class LRUBaseLinkedList<T> {

  /**
   * 链表容量
   */
  private final static int DEFAULT_CAPACITY = 10;

  /**
   * 头结点
   */
  private SNode<T> headNode;

  /**
   * 链表长度
   */
  private int length;

  /**
   * 链表容量
   */
  private int capacity;

  public LRUBaseLinkedList() {
    this.headNode = new SNode<>();
    this.capacity = DEFAULT_CAPACITY;
    this.length = 0;
  }

  public LRUBaseLinkedList(int capacity) {
    this.headNode = new SNode<>();
    this.capacity = capacity;
    this.length = 0;
  }

  public void add(T data) {
    SNode preNode = findPreNode(data);

    // 链表中存在，删除原数据，再插入到链表头部
    if (preNode != null) {
      deleteByPreNode(preNode);
      insertToHead(data);
    } else {
      if (length >= capacity) {
        // 删除尾结点
        deleteTail();
      }
      insertToHead(data);
    }
  }

  private void deleteTail() {
    SNode node = headNode;
    if (node.getNext() == null) {
      // 空链表直接返回
      return;
    }

    // 获取倒数第二个元素
    while (node.getNext().getNext() != null) {
      node = node.getNext();
    }

    // 将倒数第二个元素的next指针指向null，即删除最后一个元素
    node.setNext(null);
    length--;
  }

  private void insertToHead(T data) {
    SNode tureHead = new SNode(data);
    tureHead.setNext(headNode.getNext());
    headNode.setNext(tureHead);
    length++;
  }

  private void deleteByPreNode(SNode preNode) {
    preNode.next = preNode.next.next;
    length--;
  }

  private SNode findPreNode(T data) {
    SNode cur = headNode;
    while (cur.getNext() != null) {
      if (data.equals(cur.getNext().getElement())) {
        return cur;
      }
      cur = cur.getNext();
    }
    return null;
  }

  private void printAll() {
    SNode node = headNode.getNext();
    while (node != null) {
      System.out.print(node.getElement() + ",");
      node = node.getNext();
    }
    System.out.println();
  }

  public static class SNode<T> {
    private T element;

    private SNode next;

    public SNode(T element) {
      this.element = element;
    }

    public SNode(T element, SNode next) {
      this.element = element;
      this.next = next;
    }

    public SNode() {
      this.next = null;
    }

    public T getElement() {
      return element;
    }

    public void setElement(T element) {
      this.element = element;
    }

    public SNode getNext() {
      return next;
    }

    public void setNext(SNode next) {
      this.next = next;
    }
  }

  public static void main(String[] args) {
    LRUBaseLinkedList<Integer> list = new LRUBaseLinkedList<>();
    Scanner sc = new Scanner(System.in);
    while (true) {
      list.add(sc.nextInt());
      list.printAll();
    }
  }

}
