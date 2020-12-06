package dataStructures.queue;

import java.util.Scanner;

/**
 * @author 69035
 * @date 2020/12/6
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形案例");
        //创建一个队列
        CircleArray arrayQueue = new CircleArray(4);
        //接收用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("(s(show):显示队列");
            System.out.println(("e(exit):退出程序"));
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            //接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    try {
                        arrayQueue.addQueue(value);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 'g':
                    try {
                        int result = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.println(res);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }

}


class CircleArray {
    /**
     * 数组最大的容量
     */
    private int maxSize;
    /**
     * 队列头
     */
    private int front;

    /**
     * 队列尾
     */
    private int rear;

    /**
     * 用于存放数据，模拟队列
     */
    private int[] arr;

    /**
     * 创建队列构造器
     *
     * @param arrMaxSize 最大值
     */
    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[arrMaxSize];
    }

    /**
     * 判断队列是否满
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 入队
     *
     * @param n
     */
    public void addQueue(int n) throws Exception {
        //判断队列是否满
        if (isFull()) {
            throw new Exception("队列已满");
        }
        arr[rear] = n;
        //rear后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    /**
     * 出队
     *
     * @return
     * @throws Exception
     */
    public int getQueue() throws Exception {
        // 判断队列是否是空的
        if (isEmpty()) {
            throw new Exception("队列是空的");
        }
        //这里需要分析出front是指向队列的一个元素
        //1.先把front对应的值保存到临时变量
        //2.将front后移
        //3.将临时保存的变量保存
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**
     * 显示数据
     */
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列空");
        }
        //思路:从front开始遍历,遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 显示队列的头数据，不是取数据
     *
     * @return
     */
    public int headQueue() throws Exception {
        if (isEmpty()) {
            throw new Exception("队列为空");
        }
        return arr[front];
    }
}

