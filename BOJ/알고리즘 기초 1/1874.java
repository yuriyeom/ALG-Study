package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            Stack<Integer> stack = new Stack<>();

            int n = scan.nextInt();
            ArrayList<String> result = new ArrayList<>();

            int num = 0;
            for(int i=0; i<n; i++) {
                int input = scan.nextInt();
                if(input > num) {
                    for(int j=num; j<input; j++){
                        num++;
                        stack.add(num);
                        result.add("+");
                    }
                }else if(input < num ){
                    if(stack.peek() == input) {
                        stack.pop();
                        result.add("-");
                    }else break;
                }

                if(input == num) {
                    stack.pop();
                    result.add("-");
                }
            }
            if(stack.empty()){
                for(int i=0; i<result.size(); i++){
                    System.out.println(result.get(i));
                }
            }
            else System.out.println("NO");
    }
}
