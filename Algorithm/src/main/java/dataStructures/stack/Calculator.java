package dataStructures.stack;

/**
 * @author 69035
 * @date 2021/1/25
 */
public class Calculator {

    public static void main(String[] args) {
        String expression = "70+3*6-9";
        //创建两个栈，数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        //将每次扫描得到的char保存到ch
        char ch = ' ';
        //开始while循环扫描expression
        while (true) {
            //依次得到expression中的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch)) {
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                } else {
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算结果入数栈
                        numStack.push(res);
                        //把当前操作符入栈
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                }
            } else {
                // numStack.push(ch - 48);
                //1.当处理多位数的时候，不能发现是一个数就立即入栈
                //2.在入数栈时，需要向后诺一位，判断是不是数字
                int num = 0;
                for( ;index<expression.length();index++){
                    char now = expression.substring(index,index+1).charAt(0);
                    if(operStack.isOper(now)){
                        index--;
                        break;
                    }else{
                        num = num * 10 + now-48;
                    }
                }

                numStack.push(num);

            }
            //让index +1 ,并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num2, num1 ,oper);
            numStack.push(res);
        }
        System.out.println(numStack.pop());

    }

}

class ArrayStack2 {
    /**
     * 栈的大小
     */
    private int maxSize;
    /**
     * 栈的数据
     */
    private int[] stack;
    /**
     * 栈顶
     */
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 栈满
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 栈空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     *
     * @param val
     */
    public void push(int val) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = val;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据");
        }
        int val = stack[top];
        top--;
        return val;
    }

    /**
     * 遍历栈
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    /**
     * 返回运算符的优先级
     *
     * @param oper
     * @return
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 判断是不是运算符
     *
     * @param val
     * @return
     */
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算方法
     *
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            default:
                break;
        }
        return res;
    }

    public int peek() {
        return stack[top];
    }


}
