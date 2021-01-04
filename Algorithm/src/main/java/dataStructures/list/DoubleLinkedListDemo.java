package dataStructures.list;

/**
 * @author 69035
 * @date 2020/12/31
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        //测试
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "松江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "公孙胜", "入云龙");
        HeroNode2 myHero = new HeroNode2(1, "尼玛", "王尼玛");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();
        //修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公公", "改名后");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.del(4);
        System.out.println("删除后的链表情况");
        doubleLinkedList.list();

        //双向链表的第二种添加方式，按照编号顺序
        HeroNode2 newHeroNode2 = new HeroNode2(4, "添加", "添加结果");
        doubleLinkedList.addByNo(newHeroNode2, doubleLinkedList.getHead());
        System.out.println("根据序号添加");
        doubleLinkedList.list();

    }
}

class DoubleLinkedList {
    //初始化
    /**
     * 先定义一个头节点，头节点不要发生变化,不放任何具体数据
     */
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 遍历
     */
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            //判断是否到链表的最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp.toString());
            //将这个temp后移
            temp = temp.next;
        }
    }

    /**
     * 添加一个节点到双向链表的最后
     *
     * @param heroNode
     */
    public void add(HeroNode2 heroNode) {

        //因为head节点不能动，因此需要一个辅助遍历temp
        HeroNode2 temp = head;
        //遍历链表，找到最后一个
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后
            temp = temp.next;
        }
        //当退出while循环时，temp就指向链表的最后
        //构成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void addByNo(HeroNode2 heroNode2, HeroNode2 addNode) {
        if (heroNode2 == null) {
            return;
        }
        int no = heroNode2.no;
        while (addNode.next != null) {
            HeroNode2 next = addNode.next;
            int curNo = addNode.no;
            int nextNo = next.no;
            if (curNo == no || nextNo == no) {
                System.out.println("已经存在该编号的节点");
                break;
            }
            //如果curNo>no则将no拼接到curNo上
            if (curNo > no) {
                HeroNode2 pre = addNode.pre;
                addNode.pre = heroNode2;
                heroNode2.next = addNode;
                if (pre != null) {
                    pre.next = heroNode2;
                    heroNode2.pre = pre;
                }
                break;

            }
            //如果curNo<no<nextNo
            if (no < nextNo) {
                addNode.next = heroNode2;
                heroNode2.pre = addNode;
                heroNode2.next = next;
                next.pre = heroNode2;
                break;
            }

            if (addNode.next.next == null) {
                addNode.next.next = heroNode2;
                heroNode2.pre = addNode.next;
                break;
            }

            addNode = addNode.next;


        }
    }

    /**
     * 修改节点内容
     *
     * @param newHeroNode
     */
    public void update(HeroNode2 newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否正负号到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.println("没有找到编号为" + newHeroNode.no + "的节点");
        }
    }

    /**
     * 删除节点
     * 思路
     * 1.head不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
     * 2.在比较时，是temp.next.no和我们需要删除的节点的no比较
     * 3.对应双向链表，可以直接找到对应的节点，然后自我删除
     *
     * @param no
     */
    public void del(int no) {

        if (head.next == null) {
            System.out.println("链表为空无法删除");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                //找到的待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            //如果是最后一个节点，就不需要执行以下执行
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的%d节点不存在\n", no);
        }
    }

}


/**
 * 实体类
 */
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}