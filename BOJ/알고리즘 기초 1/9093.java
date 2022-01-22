package com.company;

import java.io.*;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] arr = new String[20];
        int n = parseInt(br.readLine());
        for(int i=0; i<n; i++){
            arr = br.readLine().split(" ");
            for(int j=0; j<arr.length; j++){
                for(int k=arr[j].length()-1; k>=0; k-- ){
                    bw.write(arr[j].charAt(k));
                }
                bw.write(" ");
            }
            bw.write("\n");
        }
        bw.close();
    }
}
