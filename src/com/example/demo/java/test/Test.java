package com.example.demo.java.test;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zhangtao
 * @since 2021/12/14 17:13
 */
public class Test {
    private static List<InputStream> iss = new ArrayList<>();
    private static int count = 0;

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException, ExecutionException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        users.add(new User("1"));
        users.add(new User("2"));
        users.add(new User("3"));
        users.add(new User("3"));

        List<List<User>> users2 = new ArrayList();
        users2.add(users);

        List<User> collect = users2.stream().flatMap(l -> l.stream()).collect(Collectors.toList());
//        System.out.println(collect);

//        Map<String, User> userMap = users.stream().collect(Collectors.toMap(User::getName, Function.identity()));

//        users = users.stream().map(u -> {
//            u.setName(u.getName() + "_xxx");
//            return u;
//        }).collect(Collectors.toList());

        IntStream intStream = users.stream().flatMapToInt(u -> IntStream.of(u.getName().length()));
//        System.out.println(intStream.max().getAsInt());
        List<User> collect1 = users.stream().limit(2).collect(Collectors.toList());
//        System.out.println(collect1);
        boolean anyMatch = users.stream().anyMatch(u -> u.getName().contains("1"));
        boolean allMatch = users.stream().allMatch(u -> u.getName().contains("1"));
        User first = users.stream().findFirst().orElse(null);

        User any = users.stream().findAny().orElse(null);
//        System.out.println(any);
//        System.out.println(first);
//
//        System.out.println(anyMatch);
//        System.out.println(allMatch);
//        System.out.println(users);

        String maxLenName = users.stream().reduce((a, b) -> {
            return a.getName().length() > b.getName().length() ? a : b;
        }).get().getName();
//        System.out.println(maxLenName);

        int sum = Arrays.asList(1, 2, 3, 4).stream().reduce(0, (a, b) -> a + b).intValue();
//        System.out.println(sum);

        Map<String, User> userMap = users.stream().collect(Collectors.toMap(User::getName, u -> u, (existing, replacement) -> existing));

        System.out.println(userMap);
    }

    static class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    private static void async(Callback callback) {
        new Thread(() -> {
            try {
                System.out.println("开始异步");
                callback.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static String encode(String input) {
        String keyStr = "ABCDEFGHIJKLMNOP" + "QRSTUVWXYZabcdef" + "ghijklmnopqrstuv"
                + "wxyz0123456789+/" + "=";

        String output = "";
        boolean not2 = false, not3 = false;
        char chr1, chr2, chr3;
        int enc1, enc2, enc3, enc4;
        int i = 0;
        do {
            chr1 = input.charAt(i++);
            if (i >= input.length()) {
                chr2 = 0;
                not2 = true;
                i++;
            } else {
                chr2 = input.charAt(i++);
            }

            if (i >= input.length()) {
                chr3 = 0;
                not3 = true;
                i++;
            } else {
                chr3 = input.charAt(i++);
            }

            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (not2) {
                enc3 = enc4 = 64;
            } else if (not3) {
                enc4 = 64;
            }
            output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2)
                    + keyStr.charAt(enc3) + keyStr.charAt(enc4);
        } while (i < input.length());
        return output;
    }

    public static List<String> split(String regEx, String string) {
        //string = formatXmlTextValue(string);
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(string);
        /*按照句子结束符分割句子*/
        String[] words = p.split(string);
        List<String> resultList = new LinkedList<>();
        /*将句子结束符连接到相应的句子后*/
        if (words.length > 0) {
            int count = 0;
            while (count < words.length) {
                String word = words[count];
                if (StringUtils.isNotBlank(word)) {
                    resultList.add(word);
                }
                if (m.find()) {
                    String value = m.group();
                    if (StringUtils.isNotBlank(value)) {
                        resultList.add(value);
                    }
                }
                count++;
            }
            while (m.find()) {
                String value = m.group();
                if (StringUtils.isNotBlank(value)) {
                    resultList.add(value);
                }
            }
        }
        return resultList;
    }

    public static List<String> getMsg(String msg) {
        List<String> list = new ArrayList<String>();
        Pattern p = Pattern.compile("(\\()([0-9a-zA-Z\\.\\/\\=])*(\\))");
        Matcher m = p.matcher(msg);
        while (m.find()) {
            list.add(m.group(0).substring(1, m.group().length() - 1));
        }
        return list;
    }

    static class T {
        private Integer a;
        private String b;

        public Integer getA() {
            return a;
        }

        public void setA(Integer a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }

    static abstract class A {
        int a = 1;
    }

    static class B extends A implements Serializable {
        private int b;
    }

    static class C extends B {
        private int c;
    }

    static class TrailingZerosBigDecimal extends BigDecimal {

        public TrailingZerosBigDecimal(String val) {
            super(val);
        }

        @Override
        public BigDecimal stripTrailingZeros() {
            BigDecimal tmp = super.stripTrailingZeros();
            return new TrailingZerosBigDecimal(tmp.toString());
        }

        @Override
        public String toString() {
            return super.toPlainString();
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        public String getResult() {
            return this.toString();
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("run");
        }
    }

    private static void future() throws ExecutionException, InterruptedException {
        long begin = System.currentTimeMillis();

        List<CompletableFuture> futures = new ArrayList<>();
        ExecutorService executorService = ThreadPoolSupport.getExecutor("IndexPage");

        CompletableFuture future1 = CompletableFuture.runAsync(() -> {
            long start = System.currentTimeMillis();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("step1 took " + (System.currentTimeMillis() - start) + "ms");
        }, executorService);

        futures.add(future1);

        CompletableFuture future2 = CompletableFuture.runAsync(() -> {
            long start = System.currentTimeMillis();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("step2 took " + (System.currentTimeMillis() - start) + "ms");
        }, executorService);

        futures.add(future2);

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();

        for (CompletableFuture future : futures) {
            future.get();
        }

        System.out.println("total took " + (System.currentTimeMillis() - begin) + "ms");
    }

    static class ThreadPoolSupport {

        private static ExecutorService executorService = null;

        private static Map<String, ExecutorService> executors = new ConcurrentHashMap<>();

        private static Lock lock = new ReentrantLock();

        public static ExecutorService getExecutor(String prefix) {
            ExecutorService executor = null;
            try {
                lock.lock();
                executor = executors.get(prefix);
                if (executor == null) {
                    executor = Executors.newFixedThreadPool(200, new CustomizableThreadFactory(prefix));
                    executors.put(prefix, executor);
                }
            } finally {
                lock.unlock();
                return executor;
            }
        }

        private static ExecutorService getExecutor() {
            if (executorService == null) {
                synchronized (ThreadPoolSupport.class) {
                    if (executorService == null) {
                        executorService = Executors.newFixedThreadPool(10);
                    }
                }
            }
            return executorService;
        }

        public static synchronized void execute(Runnable runnable) {
            getExecutor().execute(runnable);
        }

        public static synchronized void close() {
            if (executorService == null) {
                return;
            }
            executorService.shutdown();
            while (true) {
                if (executorService.isTerminated()) {
                    executorService = null;
                    break;
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    //Ignore
                }
            }
        }
    }

    private static void byString() {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < 100000; i++) {
            s += "abc";
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    private static void byStringBuilder() {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append("abc");
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
