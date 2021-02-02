package dataStructures.list;

/**
 * @author 69035
 * @date 2021/1/18
 */
public class JosefuList {

    public static void main(String[] args) {
        //测试构建环形链表和遍历是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.list();
        circleSingleLinkedList.listJosefu(1,2,5);
    }
}

class CircleSingleLinkedList {
    /**
     * first节点，没有编号
     */
    private Boy first = new Boy(-1);

    /**
     * 添加小孩节点，构建一个环形链表
     *
     * @param nums
     */
    public void addBoy(int nums) {
        //nums 做一个数据校验
        if (nums < 1) {
            System.out.println("数据错误！");
            return;
        }
        /**
         * 辅助变量，帮助构建环形链表
         */
        Boy curBoy = null;
        //使用一个for循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    /**
     * 遍历链表
     */
    public void list() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //因为first不可以操作，因此需要辅助指针
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }


    public void listJosefu(int startNo, int countNum, int nums) {
        //创建两个节点，分别记录最后一个和第一个
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("传入参数有误，请重新输入");
            return;
        }
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                //说明helper指向最胡小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper移动k-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时移动m-1次，然后出圈
        //这里是一个循环操作，直到圈中只有一个界定啊
        while (true) {
            if (helper == first) {
                break;
            }
            //让first和helper指针同时移动countNum-1次
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这个时候first就是要出圈的小孩
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后的小孩是"+ first.getNo());
    }
}

class Boy {
    private int no;
    /**
     * 指向下一个节点
     */
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
