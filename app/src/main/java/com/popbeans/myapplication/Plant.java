package com.popbeans.myapplication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Plant {

    private String plantName;
    private Integer plantLevel;
    private Map<String, Integer> statValCur;
    private Map<String, Integer> statValMax;

    // Default Values
    private List<String> stats = Arrays.asList("sun", "water", "love");
    private List<String> names = Arrays.asList("Karl the Fern", "Christine the Daisy", "Shikar the Orchid", "Dawid the Rose");

    public Plant() {
        init();
        nameGenerator();
        statGenerator();
    }

    private void init() {
        plantName = "Planty McPlantface";
        plantLevel = 1;
        statValCur = new HashMap<>();
        statValMax = new HashMap<>();
    }

    private void nameGenerator() {
        plantName = names.get(new Random().nextInt(names.size()));
    }

    private void statGenerator() {
        for (int i = 0; i < stats.size(); i++) {
            statValCur.put(stats.get(i), new Random().nextInt(3));
            statValMax.put(stats.get(i), 5);
        }
    }

    public void incVal(String statName) {
        statValCur.put(statName, statValCur.get(statName) + 1);
    }

    private void incMax() {
        for (int i = 0; i < stats.size(); i++) {
            statValMax.put(stats.get(i), statValMax.get(stats.get(i)) + new Random().nextInt(10) + 1);
        }
    }

    public String getPlantName() {
        return plantName;
    }

    public int getPlantLevel() {
       return plantLevel;
    }

    public int getValCur(String statName) {
        return statValCur.get(statName);
    }

    public int getValMax(String statName) {
        return statValMax.get(statName);
    }

    private boolean check(String key) {
        return statValCur.get(key).equals(statValMax.get(key));
    }

    public boolean evolve() {
        if ((check("sun")) && (check("water")) && check("love")) {
            plantLevel++;
            incMax();
            return true;
        }
        return false;
    }

}
