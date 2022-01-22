package com.company;

import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            Stack<Character> stack = new Stack<>();
            Stack<Character> temp = new Stack<>();

            String text = br.readLine();
            for(int i=0; i<text.length(); i++){
                stack.push(text.charAt(i));
            }

            int n = Integer.parseInt(br.readLine());

            for(int i=0; i<n; i++){
                String order = br.readLine();

                if(order.startsWith("L")){
                    if(!stack.empty()) temp.push(stack.pop());
                }else if(order.startsWith("D")){
                    if(!temp.empty()) stack.push(temp.pop());
                }else if(order.startsWith("B")){
                    if(!stack.empty()) stack.pop();
                }else if(order.startsWith("P")) {
                    stack.push(order.charAt(2));
                }
            }

            for(int i=0; i<stack.size(); i++){
                bw.write(stack.get(i));
            }
            for(int i=temp.size()-1; i>=0; i--){
                bw.write(temp.get(i));
            }
            br.close();
            bw.flush();
            bw.close();
    }
}
