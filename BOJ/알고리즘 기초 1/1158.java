package com.company;

import java.io.*;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        LinkedList<Integer> queue = new LinkedList<>();
        String[] input = new String[2];
        StringBuilder sb = new StringBuilder();

        input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        for(int i=1; i<=Integer.parseInt(input[0]); i++){
            queue.add(i);
        }
        sb.append("<");
        for(int i=0; i<N-1; i++){
            for(int j=0; j<K-1; j++){
                queue.offer(queue.poll());
            }
            sb.append(queue.poll() + ", ");
        }
        sb.append(queue.poll() +">");
        bw.write(String.valueOf(sb));

        br.close();
        bw.flush();
        bw.close();
    }
}
