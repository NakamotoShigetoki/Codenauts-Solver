package me.maxcode.nauts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        try {
            Map.Entry<ArrayList<Integer>, ArrayList<Task>> output;
            output = Parser.parse("cases/alfa.txt");
            System.out.println(output.getKey().size());
            System.out.println(output.getKey());
            System.out.println(output.getValue().size());
            System.out.println(output.getValue());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}