package com.company;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for(int i=0; i<n; i++){
            String str = scan.next();
            Stack<Character> stack = new Stack<>();
            int is_break = 0;

            for(int j=0; j<str.length(); j++){
                char ch = str.charAt(j);
                if(ch == '(') {
                    stack.push(ch);
                }else if(ch == ')'){
                    if(stack.empty()){
                        is_break = 1;
                        break;
                    }else stack.pop();
                }
            }
            if(!stack.empty() || is_break == 1) System.out.println("NO");
            else System.out.println("YES");

    //    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    //    Stack<Character> stack = new Stack<Character>();
    //    int n = parseInt(br.readLine());
    //        for(int i=0; i<n; i++) {
    //        String line = br.readLine();
    //        while (line.contains("()")){
    //        line = line.replaceAll("\\(\\)", "");
    //        }
    //        if(line.length() != 0) bw.write("NO" + "\n");
    //        else bw.write("YES" + "\n");
    //        }
    //        bw.close();
        }
    }
}
