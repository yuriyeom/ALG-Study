package com.company;

import java.io.*;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        LinkedList<Integer> queue = new LinkedList<>();

        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            String order = br.readLine();
            if(order.startsWith("push")){
                queue.add(Integer.parseInt(order.substring(5)));
            }else if(order.startsWith("pop")){
                if(queue.isEmpty()) bw.write("-1" +"\n");
                else bw.write(String.valueOf(queue.poll())+"\n");
            }else if(order.startsWith("size")){
                bw.write(String.valueOf(queue.size())+"\n");
            }else if(order.startsWith("empty")){
                bw.write(String.valueOf(queue.isEmpty() ? 1 : 0)+"\n");
            }else if(order.startsWith("front")){
                if(queue.isEmpty()) bw.write("-1"+"\n");
                else bw.write(String.valueOf(queue.peek())+"\n");
            }else if(order.startsWith("back")){
                if(queue.isEmpty()) bw.write("-1"+"\n");
                else bw.write(String.valueOf(queue.getLast())+"\n");
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}