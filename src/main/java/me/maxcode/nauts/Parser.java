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
}
