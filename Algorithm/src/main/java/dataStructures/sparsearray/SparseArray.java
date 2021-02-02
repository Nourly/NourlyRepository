package dataStructures.sparsearray;

import Const.BasicConst;

import java.io.*;

/**
 * @author 69035
 * @date 2020/12/1
 *
 */
public class SparseArray {

    public static void main(String[] args) throws IOException {

        String path = "./src";
        String childPath = "main/resources/dataStructures/sparsearray";
        File file = new File(path, childPath);

        if (file.mkdirs()) {
            System.out.println(BasicConst.SUCCESS.toString());
        }
        file = new File(path + "/" + childPath + "/test.txt");
        System.out.println(file.createNewFile() + "");
        OutputStream out = new FileOutputStream(file);
        //创建一个原始的二维数组11*11
        //0表示没有棋子，1表示黑子 2 表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][4] = 2;
        chessArr1[3][5] = 2;
        //输出原始二维数组
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
        }
        System.out.println();
        //将二维数组转化为稀疏数组的思路
        //1.先遍历二维数组
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //2.创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将数据放入
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                    String value = new String(i + "," + j + "," + chessArr1[i][j] + "\n");
                    byte b[] = value.getBytes();
                    out.write(b);
                }
            }
        }
        //输出稀疏数组
        // System.out.println();
        // System.out.println("稀疏数组结果是");
        // for (int[] ints : sparseArr) {
        //     System.out.printf("%d\t%d\t%d\t\n", ints[0], ints[1], ints[2]);
        // }
        // System.out.print("");
        //
        //读取文件
        InputStream in = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader buffer = new BufferedReader(reader);
        String readLine;
        while ((readLine = buffer.readLine()) != null) {
            System.out.println(readLine);
        }

        //1.先读取稀疏数组的第一行，根据第一行的数据创建原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        //输出原始二维数组
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
        }
    }
}
