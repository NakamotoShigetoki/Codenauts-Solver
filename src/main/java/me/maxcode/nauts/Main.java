package me.maxcode.nauts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class  Main {

    public static void calculate(String name) {
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
    }

    public static void main(String[] args) {
        calculate("alfa");
        calculate("beta");
        calculate("gamma");
        calculate("delta");
    }
}