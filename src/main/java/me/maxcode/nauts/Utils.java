package me.maxcode.nauts;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;

public class Utils {
    
    public static final int MAX_CAPACITY = 20000;

    /** Parse file
     * @param filename
     * @return Map.Entry<ArrayList<Integer>, ArrayList<Task>>
     * @throws IOException
     */
    public static Map.Entry<ArrayList<Integer>, ArrayList<Task>> parse(String filename) throws IOException {

        try(Scanner input = new Scanner(new File(filename)))
        {
            String line = input.nextLine();
            ArrayList<Integer> dayLight = new ArrayList<>(MAX_CAPACITY);

            Scanner daylightScanner = new Scanner(line);
            while (daylightScanner.hasNext())
            {
                dayLight.add(daylightScanner.nextInt());
            }


            ArrayList<Task> tasks = new ArrayList<>(MAX_CAPACITY);

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
        tasks.sort(Comparator.comparingInt(Task::getEnergy));
    }
    
    public static int freeEnergy(ArrayList<ArrayList<Task>> result, int time, DayLight dayLight) {
        int toReturn = dayLight.getEnergy(time);
        int usedEnergy = 0;
        for (Task task : result.get(time)) {
            usedEnergy += task.getEnergy();
        }
        return toReturn - usedEnergy;
    }

    public static boolean addIsPossible(ArrayList<ArrayList<Task>> result, int time, DayLight dayLight, Task task) {
        // Check deadline
        if (time + task.getTime() >= task.getDeadline() || time + task.getTime() > dayLight.getDayLight().size()) {
            return false;
        }
        for (int i = time; i < time + task.getTime(); i++) {
            if (dayLight.getFreeEnergy(i) < task.getEnergy()) {
                return false;
            }
        }
        return true;
    }

    // Put task for N time and remove it from the available tasks list
    public static void putTask(ArrayList<ArrayList<Task>> result, int time, DayLight dayLight, ArrayList<Task> tasks, Task task, boolean check) {
        if(dayLight.putTask(task, time, check)) {
            result.get(time).add(task);
            tasks.remove(task);
        }
    }

    // Create a base layer of tasks under the "minEnergy" limit
    public static void createBaseLayer(ArrayList<ArrayList<Task>> result, DayLight dayLight, ArrayList<Task> tasks) {
        int totalEnergy = 0;
        for (int time = 0; time < dayLight.getDayLight().size(); time++) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                totalEnergy += task.getEnergy();
                if (task.getEnergy()+dayLight.getUsedEnergy(time) <= dayLight.getMinEnergy() && addIsPossible(result, time, dayLight, task)) {
                    putTask(result, time, dayLight, tasks, task, true);
                }
            }
        }
        System.out.println("Total Tasks energy: " + totalEnergy);
        System.out.println("Total time based energy: " + dayLight.getTotalEnergy());
        System.out.println("Total days: " + dayLight.getDayLight().size());
    }

    public static boolean deltaIsPositive(ArrayList<ArrayList<Task>> result, int time, DayLight dayLight, Task task) {
        int extraEnergy = 0;
        for (int t = time; t < time+task.getTime(); t++) {
            extraEnergy += dayLight.getExtraEnergy(time, task);
        }

        int delta = task.getTime() * task.getEnergy() - extraEnergy;
        return delta > 0;
    }


    /**
     * Sort by deadline and by time
     *
     * @param tasks - ArrayList of tasks
     * @return
     */
    public static ArrayList<ArrayList<Task>> calculator(ArrayList<Task> tasks, DayLight dayLight) {
        sortTasksByEnergy(tasks);
        ArrayList<ArrayList<Task>> result = new ArrayList<>(MAX_CAPACITY);
        for (int i = 0; i < dayLight.getDayLight().size(); i++) {
            result.add(new ArrayList<Task>());
        }
        createBaseLayer(result, dayLight, tasks);
        for(int time = 0; time < dayLight.getDayLight().size(); time++) {
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                if (addIsPossible(result, time, dayLight,  t)) {
                    putTask(result, time, dayLight, tasks, t, true);
                }
            }
        }
        for(int time = 0; time < dayLight.getDayLight().size(); time++) {
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                if (deltaIsPositive(result, time, dayLight,  t)) {
                    putTask(result, time, dayLight, tasks, t, false);
                }
            }
        }
        return result;
    }

    public static void saveList(ArrayList<?> lists, String filename) throws IOException {
        try(PrintStream out = new PrintStream(filename)) {
                for (Object i : lists ) {
                    out.print(i);
                    out.print(" ");
                }
                out.println();
        }
    }

    public static void saveResult(ArrayList<ArrayList<Task>> lists,String filename) throws IOException {
        try(PrintStream out = new PrintStream(filename)) {
            for (ArrayList<Task> list : lists.subList(0, lists.size() - 1)) {
                for (Task i : list) {
                    out.print(i.getId());
                    out.print(" ");
                }
                out.println();
            }
        }
    }
}
