package test;

import linear.LinkList;
import linear.SequenceList;
import linear.TwoWayLinkList;

/**
 * @author Nour
 */
public class LinearTest {

    public static void main(String[] args) {
        //创建顺序表
        // SequenceList<String> s1 = new SequenceList<>(2);
        // LinkList<String> s1 = new LinkList<>();
        TwoWayLinkList<String> s1 = new TwoWayLinkList<>();
        //测试插入
        s1.insert("1");
        s1.insert("2");
        s1.insert("3");
        s1.insert("4");
        s1.insert("5");
        System.out.println(s1.get(1));
        for (String s : s1) {
            System.out.println(s);
        }
        //双向链表的分界线
        System.out.printf("第一个元素是" + s1.getFirst());
        System.out.printf("最后一个元素是" + s1.getLast());
        //双向链表的分界线


        //测试获取
        String getResult = s1.get(1);
        System.out.println("索引1处的结果为：" + getResult);
        //测试删除
        String removeResult = s1.remove(0);
        System.out.println("删除的元素是：" + removeResult);

        //测试清空
        s1.clear();
        System.out.println("清空后的元素个数为" + s1.length());
    }
}