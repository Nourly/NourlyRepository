package test;

import Data.Student;

public class test {

    public static void main(String[] args) {
        Student student1 = new Student();
        student1.setUsername("一");
        student1.setAge(15);
        Student student2 = new Student();
        student2.setUsername("二");
        student2.setAge(14);
        Comparable max = getMax(student1,student2);
        System.out.println(max);
    }

    public static Comparable getMax(Comparable c1,Comparable c2){
        int result = c1.compareTo(c2);
        //如果result小于0则表示c1比c2小，反之则大于。
        if(result>=0){
            return c1;
        }else{
            return c2;
        }
    }


}