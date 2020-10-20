package test;

import Data.TestData;
import sort.*;

import java.util.Arrays;

public class AlgorithmTest {

    public static void main(String args[]) {

        /**一般排序**/
        //冒泡排序
        // Bubble.sort(TestData.integerList);
        // 插入排序
        // Insertion.sort(TestData.integerList);
        // 选择排序
        // Selection.sort(TestData.integerList);
        //归并排序
        Merge.sort(TestData.integerList);

        /**高级排序**/
        Shell.sort(TestData.integerList);
        System.out.println(Arrays.toString(TestData.integerList));
    }
}
