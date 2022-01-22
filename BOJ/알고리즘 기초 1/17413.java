package com.company;

import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> stack = new Stack<>();
        boolean tag = false;
        char[] arr = (br.readLine() + "\n").toCharArray();

        for(char a:arr){
            if(a == '<') tag = true;

            if(tag || a == ' ' || a == '\n') {
                while(!stack.empty()) bw.write(stack.pop());
                bw.write(a);
            }else
                stack.push(a);

            if(a == '>') tag = false;
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
