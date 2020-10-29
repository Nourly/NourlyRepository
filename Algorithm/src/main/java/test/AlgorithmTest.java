package test;

import Data.TestData;
import sort.*;

import java.util.Arrays;

public class AlgorithmTest {

    public static void main(String args[]) {

        //时间复杂度 开始计时
        long start = System.currentTimeMillis();
        /**一般排序**/
        //冒泡排序
        // Bubble.sort(TestData.integerList);
        // 插入排序
        // Insertion.sort(TestData.integerList);
        // 选择排序
        // Selection.sort(TestData.integerList);

        /**高级排序**/
        // 希尔排序
        // Shell.sort(TestData.integerList);
        // 归并排序
        // Merge.sort(TestData.integerList);
        // 快速排序
        Quick.sort(TestData.integerList);
        //时间复杂度  结束计时
        long end = System.currentTimeMillis();

        System.out.println("输出结果为：" + Arrays.toString(TestData.integerList));

        System.out.println("时间为：" + (end - start));
    }
}
