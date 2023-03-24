package me.maxcode.nauts;

public class Task {
    private final int time;
    private final int energy;
    private final int id;
    private final int deadline;

    public Task(int time, int energy, int deadline, int id) {
        this.time = time;
        this.energy = energy;
        this.id = id;
        this.deadline = deadline;
    }

    public int getTime() {
        return time;
    }

    public int getEnergy() {
        return energy;
    }

    public int getId() {
        return id;
    }

    public int getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "Task{" +
            "time=" + time +
            ", energy=" + energy +
            ", id=" + id +
            ", deadline=" + deadline +
            '}';
    }
}
