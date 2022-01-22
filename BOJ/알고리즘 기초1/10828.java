package com.company;

import java.io.*;
import java.util.Stack;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) throws IOException {
        Stack<Integer> stack = new Stack<Integer>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = parseInt(br.readLine());
        for(int i=0; i<n; i++){
            String order = br.readLine();
            if(order.startsWith("push")){
                stack.push(Integer.parseInt(order.substring(5)));
            }else if(order.startsWith("pop")){
                if(stack.empty()) bw.write("-1" + "\n");
                else bw.write(stack.pop() + "\n");
            }else if(order.startsWith("size")){
                bw.write(stack.size() + "\n");
            }else if(order.startsWith("empty")){
                if(stack.empty()) bw.write("1" + "\n");
                else bw.write("0" + "\n");
            }else if(order.startsWith("top")){
                if(stack.empty()) bw.write("-1" + "\n");
                else bw.write(stack.peek() + "\n");
            }
        }
        bw.close();
    }
}
