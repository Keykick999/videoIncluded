package com.example.oldestVersion.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        int[] arr = new int[4];
        for(int i=0;i<3;i++){
            arr[i] = i;
        }
        arr[3] = 1;


        //sorted
        Arrays.stream(arr)
                .boxed()
                .sorted()
                .forEach(System.out::print);
        System.out.println();




        //filter
        Arrays.stream(arr)
                .filter(x -> x>3)
                .forEach(System.out::print);
        System.out.println();



        //distinct
        Arrays.stream(arr)
                .distinct()
                .forEach(System.out::print);
        System.out.println();



        //collect
        List<int[]> list = Stream.of(arr)
                .collect(Collectors.toList());

        //reduce
        int sum = Arrays.stream(arr).reduce(0,(a,b) -> a + b);
        System.out.println(sum);
        System.out.println();



        //map
        Arrays.stream(arr)
                .map(x -> x += 2)
                .forEach(System.out::print);
        System.out.println();



        //anyMatch,noneMatch,allMatch
        boolean bool1 = Arrays.stream(arr)
                .boxed()
                .anyMatch(x -> x>3);
        System.out.println(bool1);
        System.out.println();



        boolean bool2 = Arrays.stream(arr)
                .noneMatch(x -> x>5);

        System.out.println(bool2);
        System.out.println();



        boolean bool3 = Arrays.stream(arr)
                .allMatch(x -> x<5);
        System.out.println(bool3);
        System.out.println();




        //parallelStream,sequential
        Stream.of(arr)
                .parallel()
                .forEach(System.out::print);
        System.out.println();



        //sequential
        Stream.of(arr)
                .sequential()
                .forEach(System.out::print);
        System.out.println();




        //findFirst,findAny
        Optional<int[]> any = Stream.of(arr)
                .findAny();

        System.out.println(any);
        System.out.println();


        Optional<int[]> first = Stream.of(arr)
                .findFirst();

        System.out.println(first);
        System.out.println();

        Arrays.stream(arr)
                .skip(2)
                .forEach(System.out::print);
        System.out.println();

        Arrays.stream(arr)
                .limit(2)
                .forEach(System.out::print);
    }
}
