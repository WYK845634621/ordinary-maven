package com.yikai.redis;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/3/3 9:42
 */
public class BloomFilterTest {
    public static void main(String[] args) {
        Integer total = 1000000;
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(),total);
        for (int i = 0; i < total; i++) {
            bloomFilter.put(i);
        }
        for (int i = 0; i < total; i++) {
            if (!bloomFilter.mightContain(i)){
                System.out.println("出现了不匹配");
            }
        }
        int count = 0;
        for (int i = total; i < total + 10000; i++) {
            if (bloomFilter.mightContain(i)){
                count++;
            }
        }
        System.out.println("误判个数为: " + count);

    }
}
