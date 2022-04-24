package com.example.demo.algo;

/**
 * 1) 单链表的插入、删除、查找操作；
 * 2) 链表中存储的是int类型的数据；
 * @author cong.pi
 * @date 2022/4/19 16:24
 */
public class SinglyLinkedList {

  private Node head = null;

  public Node findByByValue(int value) {
    Node p = head;
    while (p != null && p.data != value) {
      p = p.next;
    }
    return p;
  }

  public Node findByIndex(int index) {
    Node p = head;
    int pos = 0;
    while (p != null && pos != index) {
      p = p.next;
      ++pos;
    }
    return p;
  }

  /**
   * 无头结点
   * 表头插入
   * 这种操作将于输入的顺序相反，逆序
   * @param value
   */
  public void insertToHead(int value) {
    Node newNode = new Node(value, null);
    insertToHead(newNode);
  }


  public void insertToHead(Node newNode) {
    if (head == null) {
      head = newNode;
    } else {
      newNode.next = head;
      head = newNode;
    }
  }

  public void insertTail(int value) {
    Node newNode = new Node(value, null);
    //空链表，可以插入新结点作为head，也可以不操作
    if (head == null) {
      head = newNode;
    } else {
      Node q = head;
      while(q.next != null) {
        q = q.next;
      }
      newNode.next = q.next;
      q.next = newNode;
    }
  }

  public void insertAfter(Node p, int value) {
    Node newNode = new Node(value, null);
    insertAfter(p, newNode);
  }

  public void insertAfter(Node p, Node newNode) {
    if (p == null) return;
    newNode.next = p.next;
    p.next = newNode;
  }

  public void insertBefore(Node p, int value) {
    Node newNode = new Node(value, null);
    insertBefore(p, newNode);
  }

  public void insertBefore(Node p, Node newNode) {
    if (p == null) return;
    if (head == p) {
      insertToHead(newNode);
      return;
    }
    Node q = head;
    while (q != null && q.next != p) {
      q = q.next;
    }
    if (q == null) {
      return;
    }
    newNode.next = p;
    q.next = newNode;
  }

  public void deleteByNode(Node p) {
    if (p == null || head == null) return;
    if (p == head) {
      head = head.next;
      return;
    }
    Node q = head;
    while (q != null && q.next != p) {
      q = q.next;
    }
    if (q == null) {
      return;
    }
    q.next = q.next.next;
  }

  public void deleteByValue(int value) {
    if (head == null) return;

    Node p = head;
    Node q = null;
    while (p != null && p.data != value) {
      q = p;
      p = p.next;
    }
    if (p == null) return;
    if (q == null) {
      head = head.next;
    } else {
      q.next = q.next.next;
    }
    // 可重复删除指定value的代码
    /*if (head != null && head.data == value) {
      head = head.next;
    }
    Node pNode = head;
    while (pNode != null) {
      if (pNode.next.data == value) {
        pNode.next = pNode.next.next;
        continue;
      }
      pNode = pNode.next;
    }*/
  }

  public void printAll() {
    Node p = head;
    while (p != null) {
      System.out.print(p.data + " ");
      p = p.next;
    }
    System.out.println();
  }

  // 判断true or false
  public boolean TFResult(Node left, Node right) {
    Node l = left;
    Node r = right;

    boolean flag = true;
    System.out.println("left_:" + l.data);
    System.out.println("right_:" + r.data);
    while (l != null && r != null) {
      if (l.data == r.data) {
        l = l.next;
        r = r.next;
      } else {
        flag = false;
        break;
      }
    }
    System.out.println("什么结果");
    return flag;
  }

  //判断是否为回文
  public boolean palindrome() {
    if (head == null) {
      return false;
    } else {
      System.out.println("开始执行找到中间结点");
      Node p = head; // 慢指针
      Node q = head; // 快指针
      if (p.next == null) {
        System.out.println("只有一个元素");
        return true;
      }
      while (q.next != null && q.next.next != null) {
        p = p.next;
        q = q.next.next;
      }
      System.out.println("中间结点" + p.data);
      System.out.println("开始执行奇数结点的回文判断");
      Node leftLink = null;
      Node rightLink = null;
      if (q.next == null) {
        // p 一定为整个链表的中点，且结点数目为奇数
        rightLink = p.next;
        leftLink = inverseLinkList(p).next;
        System.out.println("左边第一个结点" + leftLink.data);
        System.out.println("右边第一个结点" + rightLink.data);
      } else {
        // p p.next均为中点
        rightLink = p.next;
        leftLink = inverseLinkList(p);
      }
      return TFResult(leftLink, rightLink);
    }
  }

  /**
   * 新建头结点，始终是头，后面的节点进行翻转
   * @param p 原来链表的头结点
   * @return
   */
  public Node inverseLinkListHead(Node p) {
    Node head = new Node(9999, null);
    // 头结点的next指向原来链表的头
    head.next = p;

    Node cur = p.next;
    p.next = null;
    Node next = null;
    while (cur != null) {
      next = cur.next;
      cur.next = head.next;
      head.next = cur;
      cur = next;
    }
    return head.next;
  }



  // 无头结点的链表翻转
  public Node inverseLinkList(Node p) {
    Node r = head;
    Node pre = null;
    Node next = null;
    while (r != p) {
      next = r.next;
      r.next = pre;
      pre = r;
      r = next;
    }
    r.next = pre;

    return r;
  }

  public Node reverseList1() {
    if (head == null || head.next == null) {
      return head;
    }
    Node prev = null;
    Node cur = head;//临时结点
    while (cur != null) {
      Node nextTemp = cur.next;
      cur.next = prev;
      prev = cur;
      cur = nextTemp;
    }
    return prev;
  }

  /**
   * 时间复杂度O(n),空间复杂度O(n)
   * 递归翻转
   * @return
   */
  public Node reverseRecur(Node head) {
    if (head == null || head.next == null) {
      return head;
    }
    Node p = reverseRecur(head.next);
    head.next.next = head;
    head.next = null;
    return p;
  }



  public static class Node {
    private int data;
    private Node next;

    public Node(int data, Node next) {
      this.data = data;
      this.next = next;
    }

    public int getData() {
      return data;
    }
  }

//  public static void main(String[] args) {
//    SinglyLinkedList link = new SinglyLinkedList();
//    //int data[] = {1};
//    //int data[] = {1,2};
//    //int data[] = {1,2,3,1};
//    //int data[] = {1,2,5};
//    //int data[] = {1,2,2,1};
////     int data[] = {1,2,5,2,1};
////    int data[] = {1,2,5,3,1};
//
//    int data[] = {1, 2, 3, 4, 5, 6};
//
//    for(int i =0; i < data.length; i++){
//      //link.insertToHead(data[i]);
//      link.insertTail(data[i]);
//    }
//
////    Node sixNode = link.findByByValue(6);
////    Node threeNode = link.findByByValue(3);
////    sixNode.next = threeNode;
////    System.out.println("是否存在环:" + link.hasCycle());
//
////    Node p = link.reverseList();
////
////    while (p != null) {
////      System.out.print(p.data + " ");
////      p = p.next;
////    }
//
//
////     link.printAll();
////     Node p = link.inverseLinkListHead(link.head);
////    Node p = link.reverseList1();
////    while(p != null){
////         System.out.println(p.data);
////         p = p.next;
////     }
//
//
//
//    link.printAll();
////     Node p = link.inverseLinkListHead(link.head);
//    Node p = link.reverseRecur(link.head);
//    while(p != null){
//      System.out.println(p.data);
//      p = p.next;
//    }
//
////    System.out.println("打印原始:");
////    link.printAll();
////    if (link.palindrome()){
////      System.out.println("回文");
////    }else{
////      System.out.println("不是回文");
////    }
//  }

  public Node reverseList() {
    Node r = head;
    Node pre = null;
    Node next = null;
    if (r == null || head.next == null) {
      return head;
    }
    while(r.next != null) {
      next = r.next;
      r.next = pre;
      pre = r;
      r = next;
    }
    r.next = pre;
    return r;
  }

  /**
   * 时间复杂度O(n)，空间复杂度O(1)
   * 检查链表中是否存在环
   * @return
   */
  public boolean hasCycle() {
    if (head == null || head.next == null) {
      return false;
    }
    // 快慢指针，如果存在环，两个指针始终会相遇，因为快指针每次跟slow指针的距离拉开1
    Node fast = head;
    Node slow = head;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        return true;
      }
    }
    return false;
  }

  /**
   * 时间复杂度为O(m+n)
   * 空间复杂度为O(1) 虽然引入了l3指针，但是返回的node还是在原来的内存上面只是修改了next的指向
   * 合并两个有序的链表，合并之后还保持有序
   * 做法：两个指针分别指向l1,l2和一个最终结果的指针，为了减少判断可以将要返回的指针加上哨兵头结点，返回的时候返回l3.next即可
   * 从第一个结点开始比较两个指针指向的值的大小，如果l1 <= l2 l3.next 指向l1反之指向l2
   * 结束的条件：l1为null或者l2为null，最后再把l3的指针不为null的那个指针即可
   * @param l1
   * @param l2
   * @return
   */
  public static Node mergeTwoLinkedList(Node l1, Node l2) {
    // 哨兵结点 为了减少判断
    Node dum = new Node(0, null);
    Node cur = dum;

    while (l1 != null && l2 != null) {
      if (l1.data <= l2.data) {
        cur.next = l1;
        l1 = l1.next;
      } else {
        cur.next = l2;
        l2 = l2.next;
      }
      cur = cur.next;
    }
    cur.next = l1 == null ? l2 : l1;

    return dum.next;
  }

  /**
   * 终止条件，l1 == null || l2 == null，即最后肯定有一个链表先遍历到null，遍历到null另外一个链表的后边部分直接返回到链尾
   * 递归合并，比较结点大小，执行递归，如果满足条件就将满足条件的结点的next指针代入递归方法，队规完成返回满足条件的结点即可
   * @param l1
   * @param l2
   * @return
   */
  public static Node mergeTwoLinkedListWithRecur(Node l1, Node l2) {
    if (l1 == null || l2 == null) {
      return l1 == null ? l2 : l1;
    }
    if (l1.data <= l2.data) {
      l1.next = mergeTwoLinkedListWithRecur(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoLinkedListWithRecur(l1, l2.next);
      return l2;
    }
  }

  public static void main(String[] args) {
    SinglyLinkedList singlyLinkedList1 = new SinglyLinkedList();
    int data[] = {1, 2, 4};

    for(int i =0; i < data.length; i++){
      singlyLinkedList1.insertTail(data[i]);
    }


    SinglyLinkedList singlyLinkedList2 = new SinglyLinkedList();
    int[] data2 = {1, 3, 4};
    for (int i : data2) {
      singlyLinkedList2.insertTail(i);
    }

//    Node twoListHead = mergeTwoLinkedList(singlyLinkedList1.head, singlyLinkedList2.head);
    Node twoListHead = mergeTwoLinkedListWithRecur(singlyLinkedList1.head, singlyLinkedList2.head);
    while (twoListHead != null) {
      System.out.print(twoListHead.data + " ");
      twoListHead = twoListHead.next;
    }
  }

  /**
   * 删除链表倒数第n个结点
   * @return
   */
  public Node removeNthFromEnd(int n) {
    int length = getLength();

    // 哨兵结点，为了避免删除第一个结点需要做额外的判断
    Node sentinelNode = new Node(0, null);
    sentinelNode.next = head;
    Node tempNext = sentinelNode;
    for (int i = 0; i < length - n; i++) {
      sentinelNode = sentinelNode.next;
    }
    sentinelNode.next = sentinelNode.next.next;
    return tempNext.next;
  }

  /**
   * 获取链表长度
   * @return
   */
  public int getLength() {
    int n = 0;
    while (head != null) {
      n++;
      head = head.next;
    }
    return n;
  }
}
