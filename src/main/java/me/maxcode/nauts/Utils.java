package me.maxcode.nauts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Parser {

    public static Map.Entry<ArrayList<Integer>, ArrayList<Task>> parse(String filename) throws IOException {

        try(Scanner input = new Scanner(new File(filename)))
        {
            String line = input.nextLine();
            ArrayList<Integer> dayLight = new ArrayList<>(20000);

            Scanner daylightScanner = new Scanner(line);
            while (daylightScanner.hasNext())
            {
                dayLight.add(daylightScanner.nextInt());
            }


            ArrayList<Task> tasks = new ArrayList<>(20000);

            for (int id = 0; input.hasNext(); id++)
            {
                tasks.add(new Task(input.nextInt(), input.nextInt(), input.nextInt(), id));
            }


            //parsing
            return Map.entry(dayLight, tasks);
        }

    }

    public static void sortTasksByDeadline(ArrayList<Task> tasks) {
        tasks.sort((o1, o2) -> o1.getDeadline() - o2.getDeadline());
    }

    public static void sortTasksByTime(ArrayList<Task> tasks) {
        tasks.sort((o1, o2) -> o1.getTime() - o2.getTime());
    }

    public static void sortTasksByEnergy(ArrayList<Task> tasks) {
        tasks.sort((o1, o2) -> o1.getEnergy() - o2.getEnergy());
    }

    /** Sort by deadline and by time
     * @param tasks - ArrayList of tasks
     */
    public static void sortTasks(ArrayList<Task> tasks) {
        tasks.sort((o1, o2) -> o1.getDeadline() - o2.getDeadline() - o1.getTime() + o2.getTime());
    }

    public static void saveList(ArrayList<? extends Object> lists,String filename) throws IOException {
        try(PrintStream out = new PrintStream(filename)) {
                for (Object i : lists) {
                    out.print(i);
                    out.print(" ");
                }
                out.println();
        }
    }

    public static void saveResult(ArrayList<ArrayList<Integer>> lists,String filename) throws IOException {
        try(PrintStream out = new PrintStream(filename)) {
            for (ArrayList<Integer> list : lists) {
                for (int i : list) {
                    out.print(i);
                    out.print(" ");
                }
                out.println();
            }
            out.println();
        }
    }
}
