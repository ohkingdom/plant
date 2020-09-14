package com.popbeans.plant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PokemonStatistics implements Serializable {

    private boolean testMode;
    private Map<Integer, Integer> current;
    private Map<Integer, Integer> maximum;

    public PokemonStatistics() {
        current = new HashMap<>();
        maximum = new HashMap<>();
        init();
    }

    public void init() {
        for (int i = 0; i < 3; i++) {
            current.put(i, new Random().nextInt(2) + 1);
            maximum.put(i, 3);
        }
    }

    public int getCurrent(int value) {
        return current.get(value);
    }

    public int getMaximum(int value) {
        return maximum.get(value);
    }

    public void increaseCurrent(int value) {
        int i = current.get(value);
        if (i != maximum.get(value)) {
            current.put(value, i + 1);
        }
    }

    public void increaseMaximum() {
        for (int i = 0; i < 3; i++) {
            int j = maximum.get(i);
            maximum.put(i, j + 3);
        }
    }

    public boolean verify() {
        int j = 0;
        for (int i = 0; i < 3; i++) {
            if (current.get(i).equals(maximum.get(i))) {
                j++;
            }
        }
        increaseMaximum();
        return j == 3;
    }

}
