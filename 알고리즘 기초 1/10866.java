package com.company;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Deque<Integer> deque = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++){
            String order = br.readLine();
            if(order.startsWith("push_front")){
                deque.offerFirst(Integer.parseInt(order.substring(11)));
            }else if(order.startsWith("push_back")){
                deque.offerLast(Integer.parseInt(order.substring(10)));
            }else if(order.startsWith("pop_front")){
                if(deque.isEmpty()) bw.write("-1"+ "\n");
                else bw.write(deque.pollFirst() + "\n");
            }else if(order.startsWith("pop_back")){
                if(deque.isEmpty()) bw.write("-1"+ "\n");
                else bw.write(deque.pollLast()+ "\n");
            }else if(order.startsWith("size")){
                bw.write(deque.size()+ "\n");
            }else if(order.startsWith("empty")){
                if(deque.isEmpty()) bw.write("1"+ "\n");
                else bw.write("0"+ "\n");
            }else if(order.startsWith("front")){
                if(deque.isEmpty()) bw.write("-1"+ "\n");
                else bw.write(deque.peekFirst()+ "\n");
            }else if(order.startsWith("back")){
                if(deque.isEmpty()) bw.write("-1"+ "\n");
                else bw.write(deque.peekLast()+ "\n");
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}