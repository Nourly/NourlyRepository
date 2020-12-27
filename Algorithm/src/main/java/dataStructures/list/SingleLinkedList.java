package dataStructures.list;

import java.util.Stack;

/**
 * @author 69035
 * @date 2020/12/13
 */
public class SingleLinkedList {
    public static void main(String[] args) {
        //测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "公孙胜", "入云龙");
        HeroNode myHero = new HeroNode(1, "尼玛", "王尼玛");
        SingledList singledList = new SingledList();
        singledList.add(hero1);
        singledList.add(hero2);
        singledList.add(hero3);
        singledList.add(hero4);
        singledList.addByNo(myHero);
        hero2.name = "卢本伟";
        hero2.nickname = "不开挂";
        singledList.update(hero2);
        singledList.del(4);
        singledList.list();

        System.out.println("测试逆序打印单链表");
        reversePrint(singledList.getHead());

        System.out.println("测试链表反转");
        HeroNode heroNode = reverseNode(singledList.getHead());
        while (heroNode != null) {
            System.out.println(heroNode.toString());
            heroNode = heroNode.next;
        }


    }


    /**
     * 查找单链表中的倒数第k个节点【新浪】
     * 思路
     * 1.编写一个方法，接收head节点，同时接收一个index
     * 2.index 表示倒数第index个节点
     * 3.先把链表从头到尾编辑一下,得到链表的总长度
     * 4.得到size后，我们从链表的第一个开始遍历(size-index)个就可以了
     * 5.如果找到了就返回该节点，否则返回空
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断链表为空返货null
        if (head.next == null) {
            return null;
        }
        //第一次遍历得到链表的长度
        int size = getLength(head);
        //第二次遍历到size-index个
        if (index <= 0) {
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 翻转链表【腾讯】
     * 思路：建立一个辅助翻转链表，遍历原有的链表，辅助变量每次都临时保存下翻转变量头节点的下一个节点，然后让翻转变量头节点的下一个节点变成比当前节点，然后将辅助变量的节点拼接到当前节点即可。
     *
     * @param head
     * @return
     */
    public static HeroNode reverseNode(HeroNode head) {
        //没有元素或者只有一个元素则不需要移动
        if (head.next == null || head.next.next == null) {
            return head;
        }
        HeroNode temp = head.next;
        HeroNode next = null;
        HeroNode reverseNode = new HeroNode(0, "", "");
        while (temp != null) {
            //先保存当前节点的下一个节点，后面需要使用
            next = temp.next;
            //将temp的下一个节点指向新链表的最前端
            temp.next = reverseNode.next;
            reverseNode.next = temp;
            temp = next;
        }
        //将head.next指向reverseNode.next
        head.next = reverseNode.next;
        return reverseNode;
    }

    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            System.out.println("空链表无法打印");
            return;
        }
        //创建一个栈，将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        //将链表的所有节点压入栈中
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        //将栈中的节点进行打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * @param head 链表的头节点
     * @return
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        //定义一个辅助变量
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }
}


class SingledList {
    /**
     * 先定义一个头节点，头节点不要发生变化,不放任何具体数据
     */
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    /**
     * 添加节点到单向链表
     * 思路，当不考虑编号顺序的时候
     * 1.找到当前链表的最后节点
     * 2.将最后这个节点的next指向新节点
     *
     * @param heroNode
     */
    public void add(HeroNode heroNode) {

        //因为head节点不能动，因此需要一个辅助遍历temp
        HeroNode temp = head;
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
        temp.next = heroNode;
    }

    /**
     * 显示链表
     */
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此需要一个辅助变量来遍历
        HeroNode temp = head.next;
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
     * 按照编号来进行添加
     *
     * @param heroNode
     */
    public void addByNo(HeroNode heroNode) {
        int heroNo = heroNode.no;
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("编号已存在！");
        } else {
            HeroNode next = temp.next;
            temp.next = heroNode;
            heroNode.next = next;
        }
    }

    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个一个辅助变量
        HeroNode temp = head.next;
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
     *
     * @param no
     */
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("要删除的节点不存在");
        }
    }
}

/**
 * 实体类
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
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
