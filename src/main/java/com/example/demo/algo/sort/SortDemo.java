package com.example.demo.algo.sort;

import java.util.Random;

/**
 * @author cong.pi
 * @date 2022/4/25 14:23
 */
public class SortDemo {

  /**
   * 思路：每两个元素之间进行比较，没一轮冒泡都会有一个元素冒泡为有序 冒泡排序, 平均时间复杂度O(n^2),空间复杂度O(1)原地排序
   *
   * @param arr 需要排序的数组
   * @param n   数组的大小
   */
  public void bubbleSort(int[] arr, int n) {
    if (n <= 1) {
      return;
    }
    // 没有数据交换证明已经排序完成
    boolean swapFlag = false;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
          swapFlag = true;
        }
      }
      // 如果没有进行数据交换，说明已经排序完成
      if (!swapFlag) {
        break;
      }
    }
  }

  /**
   * 思路：将数组分为两部分，前面一部分是有序的，后面一部分是需要排序的， 每次从后面一部分元素中取出一个在前面一部分元素中找到对应的位置进行插入
   * 插入排序，平均时间复杂度O(n^2)，空间复杂度O(1)
   *
   * @param arr
   * @param n
   */
  public void insertSort(int[] arr, int n) {
    if (n <= 1) {
      return;
    }
    for (int i = 1; i < n; i++) {
      int value = arr[i]; // 当前元素
      int j = i - 1;
      // 获取插入的位置，相等的情况不改变原来两个元素的相对位置，以保证排序的稳定性
      for (; j >= 0; j--) {
        if (arr[j] > value) {
          // 移动元素
          arr[j + 1] = arr[j];
        } else {
          break;
        }
      }
      // 插入元素
      arr[j + 1] = value;
    }
  }

  /**
   * 选择排序，最好最坏平均时间复杂度都为O(n^2),空间复杂度为O(1) 稳定性不如冒泡排序和插入排序
   *
   * @param arr
   * @param n
   */
  public void selectionSort(int[] arr, int n) {
    if (n <= 0) {
      return;
    }
    for (int i = 0; i < n; i++) {
      int min = arr[i]; // 未排序的开头元素,默认最小值
      int minIndex = i;
      // 获取最小值，和最小值位置
      for (int j = i; j < n; j++) {
        if (arr[j] < min) {
          min = arr[j];
          minIndex = j;
        }
      }
      // 交换
      arr[minIndex] = arr[i];
      arr[i] = min;
    }
  }

  // 归并排序算法，n表示数组大小
  public void mergeSort(int[] arr, int n) {
    mergeSortC(arr, 0, n - 1);
  }

  /**
   * p~r的中间位置为(p + r)/2 递推公式：merge_sort(p...r) = merge(merge_sort(p...q), merge_sort(q+1...r))
   * 终止条件：p >= r 不用再继续分解 归并排序arr中从p~r的元素
   *
   * @param arr
   * @param p
   * @param r
   */
  private void mergeSortC(int[] arr, int p, int r) {
    // 终止条件
    if (p >= r) {
      return;
    }
    // 取p到r之间的中间位置q
//    int q = (p + r) / 2;
    int q = p + (r - p) / 2; // 为了避免 p + r溢出int的范围
    // 分治递归
    mergeSortC(arr, p, q);
    mergeSortC(arr, q + 1, r);
//    merge(arr, p, q, r);
    mergeBySentry(arr, p, q, r);
  }

  /**
   * 合并
   *
   * @param arr
   * @param p
   * @param q
   * @param r
   */
  private void merge(int[] arr, int p, int q, int r) {
    // 申请两个指针i,j，分别执行p~q和q+1~r
    int i = p, j = q + 1, k = 0;
    int[] tmp = new int[r - p + 1]; // 申请一个大小跟a[p...r]一样的临时数组
    while (i <= q && j <= r) {
      if (arr[i] <= arr[j]) {
        tmp[k++] = arr[i++];
      } else {
        tmp[k++] = arr[j++];
      }
    }

    // 判断哪个子数组中有剩余的数据
    int start = i, end = q;
    if (j <= r) {
      start = j;
      end = r;
    }
    // 将剩余的元素拷贝到tmp中
    while (start <= end) {
      tmp[k++] = arr[start++];
    }
    // 将tmp数组中的元素拷贝会arr
    for (i = 0; i <= r - p; i++) {
      arr[p + i] = tmp[i];
    }
  }

  /**
   * 合并（哨兵）
   * @param arr
   * @param p
   * @param q
   * @param r
   */
  private void mergeBySentry(int[] arr, int p, int q, int r) {
    int[] leftArr = new int[q - p + 2];
    int[] rightArr = new int[r - q + 1];

    for(int i = 0; i <= q - p; i++) {
      leftArr[i] = arr[p + i];
    }
    // 第一个数组添加哨兵
    leftArr[q - p + 1] = Integer.MAX_VALUE;

    for (int i = 0; i < r - q; i++) {
      rightArr[i] = arr[q + 1 + i];
    }
    // 第二个数组添加哨兵（最大值）
    rightArr[r - q] = Integer.MAX_VALUE;

    int i = 0;
    int j = 0;
    int k = p;
    while (k <= r) {
      // 当左边数组到达哨兵值时，i不再增加，直到右边数组读取完剩余值，同理右边数组也一样
      if (leftArr[i] <= rightArr[j]) {
        arr[k++] = leftArr[i++];
      } else {
        arr[k++] = rightArr[j++];
      }
    }
  }

  public static void main(String[] args) {
    SortDemo sortDemo = new SortDemo();
    int[] arr = {3, 2, 5, 6, 1};
//    sortDemo.bubbleSort(arr, arr.length);
//    sortDemo.insertSort(arr, arr.length);
//    sortDemo.selectionSort(arr, arr.length);
    sortDemo.mergeSort(arr, arr.length);
    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
    }

    Random random = new Random();
    System.out.println(random.nextInt(10));

  }

//  private static boolean testException() {
//    Executors.newSingleThreadExecutor().execute(() -> {
//      try {
//        TimeUnit.SECONDS.sleep(5);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//      int i = 1 / 0;
//    });
//    return true;
//  }

}
