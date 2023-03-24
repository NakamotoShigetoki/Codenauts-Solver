package me.maxcode.nauts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class  Main {


    public static void main(String[] args) {
        try {
            Map.Entry<ArrayList<Integer>, ArrayList<Task>> output;
            output = Utils.parse("cases/alfa.txt");
            System.out.println(output.getKey().size());
            System.out.println(output.getKey());
            System.out.println(output.getValue().size());
            System.out.println(output.getValue());
            Utils.sortTasks(output.getValue());
            System.out.println(output.getValue());
            Utils.saveList(output.getValue(), "output.txt");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}