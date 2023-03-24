package me.maxcode.nauts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DayLight {
    private List<Pair> dayLightAndConsume;
    private int minEnergy;

    public DayLight(ArrayList<Integer> dayLight) {
        this.minEnergy = dayLight.stream().min(Integer::compareTo).get();
        dayLightAndConsume = dayLight.stream().map(e -> new Pair(e, 0)).toList();
    }

    public int getMinEnergy() {
        return minEnergy;
    }

    public List<Pair> getDayLight() {
        return dayLightAndConsume;
    }

    public int getTotalEnergy() {
        return dayLightAndConsume.stream().mapToInt(Pair::getA).sum();
    }
    public int getEnergy(int index) {
        return dayLightAndConsume.get(index).getA();
    }
    public int getUsedEnergy(int index) {
        return dayLightAndConsume.get(index).getB();
    }

    public int getFreeEnergy(int index) {
        return getEnergy(index) - getUsedEnergy(index);
    }

    public int getExtraEnergy(int index, Task task){
        return getUsedEnergy(index) + task.getEnergy() - getEnergy(index);
    }

    public boolean putTask(Task task, int index, boolean check)
    {

        if (task.getTime() + index >=  dayLightAndConsume.size()) return false;
        if (check)
        for(int i = index; i < index + task.getTime(); i++)
        {
            if (getUsedEnergy(i) + task.getEnergy() > getEnergy(i)) return false;
        }

        for(int i = index; i < index + task.getTime(); i++)
        {
            dayLightAndConsume.get(i).setB(dayLightAndConsume.get(i).getB() + task.getEnergy());
        }
        return true;
    }


}
