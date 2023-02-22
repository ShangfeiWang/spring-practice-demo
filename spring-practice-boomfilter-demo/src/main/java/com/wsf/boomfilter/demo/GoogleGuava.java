package com.wsf.boomfilter.demo;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.PrimitiveSink;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wsf
 * @since 20230223
 */
@SuppressWarnings("all")
public class GoogleGuava {

    public static void SimpleDemo() {
        // 创建布隆过滤器对象
        /*
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(), //数据格式
                100,//预计存入数据量
                0.01);//误判率
        */
        BloomFilter<CharSequence> filter = BloomFilter.create(
                Funnels.stringFunnel(Charsets.UTF_8),//Funnels.integerFunnel(), //数据格式
                1000000,//预计存入数据量
                0.01);//误判率
        // 判断指定元素是否存在
        System.out.println(filter.mightContain("1"));
        System.out.println(filter.mightContain("2"));
        // 将元素添加进布隆过滤器
        filter.put("1");
        filter.put("2");
        System.out.println(filter.mightContain("1"));
        System.out.println(filter.mightContain("2"));
    }

    public static void main(String[] args) {
        GoogleGuava.SimpleDemo();

    }

    @Test
    public void test1() {
        Random random = new Random();
        // 数据量
        int size = 1000000;
        // 用一个数据结构保存一下所有实际存在的值
        LinkedHashSet<String> existentNumbers = new LinkedHashSet<String>();

        BloomFilter<CharSequence> bloom = BloomFilter.create(
                Funnels.stringFunnel(Charsets.UTF_8),//Funnels.integerFunnel(), //数据格式
                1000000,//预计存入数据量
                0.01);//误判率
        AtomicInteger count_while = new AtomicInteger();
        while (true) {
            if (existentNumbers.size() >= size) {
                break;
            }
            count_while.incrementAndGet();
            String randomKey = random.nextInt() + "";
            existentNumbers.add(randomKey);
            bloom.put(randomKey);
        }
        System.out.printf("获取%d个数据量，循环了%d次", size, count_while.get());

        //verify.1 验证已存在的数是否都存在的
        AtomicInteger count = new AtomicInteger();
        existentNumbers.forEach(number -> {
            if (bloom.mightContain(number)) {
                count.incrementAndGet();
            }
        });
        System.out.printf("实际的数据量： %d, 判断存在的数据量: %d \n", existentNumbers.size(), count.get());

        //verify.2 找1000000个不存在的数，验证误识别率
        for (int i = 0; i < 10; i++) {
            LinkedHashSet<String> notExist = new LinkedHashSet<String>();
            int num = 0;
            while (num < 1000000) {
                String key = random.nextInt() + "";
                if (existentNumbers.contains(key)) {
                    continue;
                } else {
                    // 这里一定是不存在的数
                    notExist.add(key);
                    num++;
                }
            }
            count.set(0);
            notExist.forEach(number -> {
                if (bloom.mightContain(number)) {
                    count.incrementAndGet();
                }
            });
            System.out.printf("%d个不存在的数据, 判断存在的数据量: %d \n", 1000000, count.get());
        }
    }

    @Test
    public void testBloom2() {
        BloomFilter<Map> bloomFilter = BloomFilter.create(new Funnel<Map>() {
            @Override
            public void funnel(Map map, PrimitiveSink primitiveSink) {
                primitiveSink.putString(map.toString(), Charsets.UTF_8);
            }
        }, 1000, 0.001);

        Map<String, String> map = new HashMap<>();
        map.put("a", "a");
        bloomFilter.put(map);

        // 判断是否存在，false则表示一定不存在； true表示可能存在
        boolean ans = bloomFilter.mightContain(map);
        System.out.println(ans);

        map.put("b", "b");
        ans = bloomFilter.mightContain(map);
        System.out.println(ans);

        Map<String, String> map2 = new HashMap<>();
        map2.put("c", "c");
        ans = bloomFilter.mightContain(map2);
        System.out.println(ans);

        bloomFilter.put(map2);
        ans = bloomFilter.mightContain(map2);
        System.out.println(ans);

        map2.put("d", "d");
        ans = bloomFilter.mightContain(map2);
        System.out.println(ans);
    }
}

