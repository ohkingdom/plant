package com.popbeans.plant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Pokemon {

    private String pokemonName;
    private List<String> pokemonRealName;
    private Integer pokemonLevel;
    private String pokemonType;
    private Map<String, Integer> statValCur;
    private Map<String, Integer> statValMax;

    // Default Values
    private final List<String> stats = Arrays.asList("sun", "water", "love");
    private final List<String> names = Arrays.asList("Christine", "Shikar", "Dominic", "Dilushinie");

    public Pokemon(String type, String name) {
        init(type, name);
        setRealName();
        statGenerator();
    }

    private void init(String type, String name) {
        if (name.equals("")) {
            nameGenerator();
        } else {
            pokemonName = name;
        }
        if (type == null) {
            pokemonType = "grass";
        }
        pokemonLevel = 1;
        pokemonType = type;
        statValCur = new HashMap<String, Integer>();
        statValMax = new HashMap<String, Integer>();
    }

    private void nameGenerator() {
        pokemonName = names.get(new Random().nextInt(names.size()));
    }

    private void setRealName() {
        switch (pokemonType) {
            case "grass" :
                pokemonRealName = Arrays.asList("Bulbasaur", "Ivysaur", "Venusaur");
                break;
            case "water" :
                pokemonRealName = Arrays.asList("Squirtle", "Wartortle", "Blastoise");
                break;
            case "fire" :
                pokemonRealName = Arrays.asList("Charmander", "Charmeleon", "Charizard");
                break;
        }
    }

    private void statGenerator() {
        for (int i = 0; i < stats.size(); i++) {
            statValCur.put(stats.get(i), new Random().nextInt(2));
            statValMax.put(stats.get(i), 2 + 1);
        }
    }

    public void incVal(String statName) {
        try {
            statValCur.put(statName, statValCur.get(statName) + 1);
        } catch (Exception e) {
            throw new NullPointerException("Error: Invalid key reference");
        }
    }

    private void incMax() {
        try {
            for (int i = 0; i < stats.size(); i++) {
                statValMax.put(stats.get(i), statValMax.get(stats.get(i)) + new Random().nextInt(2) + 2);
            }
        } catch (Exception e) {
            throw new NullPointerException("Error: Invalid key reference");
        }
    }

    public String getPokemonName() {
        return pokemonName + " the " + pokemonRealName.get(pokemonLevel - 1);
    }

    public void setPokemonName(String name) {
        pokemonName = name;
    }

    public int getPokemonLevel() {
        return pokemonLevel;
    }

    public String getPokemonType() {
        return pokemonType;
    }

    public int getValCur(String statName) {
        try {
            return statValCur.get(statName);
        } catch (Exception e) {
            throw new NullPointerException("Error: Invalid key reference");
        }
    }

    public int getValMax(String statName) {
        try {
            return statValMax.get(statName);
        } catch (Exception e) {
            throw new NullPointerException("Error: Invalid key reference");
        }
    }

    private boolean check(String key) {
        try {
            return statValCur.get(key).equals(statValMax.get(key));
        } catch (Exception e) {
            throw new NullPointerException("Error: Invalid key reference");
        }
    }

    public boolean evolve() {
        if ((check("sun")) && (check("water")) && check("love") && (pokemonLevel < 3)) {
            pokemonLevel++;
            incMax();
            return true;
        }
        return false;
    }

}
