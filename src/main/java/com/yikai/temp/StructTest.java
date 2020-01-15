package com.yikai.temp;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/27 17:51
 */
public class StructTest {
    public static void main(String[] args) {
        Person person = new Person("10","wangyikai");
        int i = person.hashCode();
        String s = Integer.toHexString(i);
        System.out.println("16进制的数为: " + s);
        System.out.println();
        System.gc();
        System.gc();
        System.gc();
        System.out.println(ClassLayout.parseInstance(person).toPrintable());
    }
}
