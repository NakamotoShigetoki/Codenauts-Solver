package me.maxcode.nauts;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;

public class  Main {

    public static void calculate(String name) {
        System.out.println("Calculating " + name + "...");
        Instant now = Instant.now();
        try {
            Map.Entry<ArrayList<Integer>, ArrayList<Task>> output;
            output = Utils.parse("cases/" + name + ".txt");
            ArrayList<Task> tasks = output.getValue();
            DayLight dayLight = new DayLight(output.getKey());
            ArrayList<ArrayList<Task>> result = Utils.calculator(tasks, dayLight);
            Utils.saveResult(result, name+".txt");
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println(name+" done in " + (Instant.now().toEpochMilli() - now.toEpochMilli()) + "ms\n");
    }

    public static void main(String[] args) {
        calculate("alfa");
        calculate("beta");
        calculate("gamma");
        calculate("delta");
    }
}