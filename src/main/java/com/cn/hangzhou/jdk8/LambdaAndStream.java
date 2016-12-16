package com.cn.hangzhou.jdk8;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by smile on 2016-12-15.
 *
 * http://www.importnew.com/16436.html
 * 1、Lambda 表达式
 * 2、集合之流式操作
 * 3、接口的增强
 *
 * http://stackoverflow.com/questions/27841225/java-8-lambda-filter-hashmap-cannot-resolve-method
 *
 * Java8 lambda表达式10个示例
 * http://www.importnew.com/16436.html
 *
 * Java 8 lambda表达式的语法:
 *    (params) -> expression
 *    (params) -> statement
 *    (params) -> { statements }
 *
 * IntelliJ inspection gives “Cannot resolve symbol” but still compiles code
 * http://stackoverflow.com/questions/5905896/intellij-inspection-gives-cannot-resolve-symbol-but-still-compiles-code
 *
 */
public class LambdaAndStream {

    public static void main(String[] args) {

//        iteration();
//        compareTo();
//        runnable();
//        filter();
//        joining();
//        sequential();
//        parallel();
//        reduce();
//        summaryStatistics();

    }

    /**
     * 列表迭代
     * 方法引用由::双冒号操作符标示，
     * 看起来像C++的作用域解析运算符
     */
    public static void  iteration() {
          List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
          features.forEach(e -> System.out.println(e));
//          features.forEach(System.out::println);
    }

    /**
     * sorting排序
     */
    public static void compareTo() {
        List<String> list = Arrays.asList( "j", "b", "d" );
        list.sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
        list.forEach(System.out::println);
    }

    /**
     * 用lambda表达式实现Runnable
     * new Thread(new Runnable() {
     *  @Override
     *   public void run() {
     *      System.out.println("Hello from thread");
     *   }
     *   }).start();
     */
    public static void runnable() {
        //新方法:
        new Thread(
                () -> System.out.println("Hello from thread")
        ).start();
    }

    /**
     * Java 8 lambda表达式进行事件处理
     * JButton show =  new JButton("Show");
     * show.addActionListener(new ActionListener() {
     *       @Override
     *       public void actionPerformed(ActionEvent e) {
     *           System.out.println("Event handling without lambda expression is boring");
     *       }
     *  });
     */
    public static void addActionListener() {
        JButton show =  new JButton("Show");
        show.addActionListener((e) -> {
            System.out.println("Light, Camera, Action !! Lambda expressions Rocks");
        });
    }

    /**
     * 创建一个子列表
     * @param strList
     * @return
     */
    public List<String> getAllJavaArticles(List<String> strList) {
        return strList.stream().filter(x -> x.contains("Java")).collect(Collectors.toList());
    }

    /**
     * 通过过滤创建一个String列表
     * 创建一个字符串列表，每个字符串长度大于2
     */
    public static void filter(){
        List<String> strList = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        List<String> filtered = strList.stream().filter(x -> x.length()> 7).collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
    }

    /**
     * 对列表的每个元素应用函数
     * 将字符串换成大写并用逗号链接起来
     */
    public static void joining(){
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);
    }

    /**
     * 串行和并行的流,串行
     */
    public static void sequential(){
        List<String> list = new ArrayList<String>();
        for(int i=0;i<10000;i++){
            double d = Math.random()*1000;
            list.add(d+"");
        }
        long start = System.nanoTime();//获取系统开始排序的时间点
        int count= (int) ((Stream) list.stream().sequential()).sorted().count();
        long end = System.nanoTime();//获取系统结束排序的时间点
        long ms = TimeUnit.NANOSECONDS.toMillis(end-start);//得到串行排序所用的时间
        System.out.println(ms+"ms");
    }

    /**
     * 串行和并行的流,并行
     */
    public static void parallel(){
        List<String> list = new ArrayList<String>();
        for(int i=0;i<10000;i++){
            double d = Math.random()*1000;
            list.add(d+"");
        }
        long start = System.nanoTime();//获取系统开始排序的时间点
        ((Stream)list.stream().parallel()).sorted().forEach(System.out::println);
        long end = System.nanoTime();//获取系统结束排序的时间点
        long ms = TimeUnit.NANOSECONDS.toMillis(end-start);//得到并行排序所用的时间
        System.out.println(ms+"ms");
    }

    /**
     * reduce() 方法计算总和：
     * Java 8 中使用lambda表达式的Map和Reduce示例
     * <p>For example, given a stream of {@code Person}, to calculate tallest
     * person in each city:
     * <pre>{@code
     *     Comparator<Person> byHeight = Comparator.comparing(Person::getHeight);
     *     Map<City, Person> tallestByCity
     *         = people.stream().collect(groupingBy(Person::getCity, reducing(BinaryOperator.maxBy(byHeight))));
     * }</pre>
     */
    public static void reduce() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            total = total + price;
        }
        System.out.println("Total : " + total);

        double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);
    }

    /**
     * 计算集合元素的最大值、最小值、总和以及平均值
     */
    public static void summaryStatistics() {
        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }

}

















