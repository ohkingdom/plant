package com.popbeans.plant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Pokemon {

    private boolean testMode = true;

    private String pokemonName;
    private List<String> pokemonNameRef;
    private List<Integer> pokemonSpriteRef;
    private Integer pokemonLevel;
    private String pokemonType;
    private Map<String, Integer> statValCur;
    private Map<String, Integer> statValMax;

    // Default Values
    private final List<String> stats = Arrays.asList("sun", "water", "love");
    private final List<String> names = Arrays.asList("Christine", "Shikar", "Dominic", "Dilushinie");

    public Pokemon(String type, String name) {
        init(type, name);
        setReferences();
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

    private void setReferences() {
        switch (pokemonType) {
            case "grass" :
                pokemonNameRef = Arrays.asList("Bulbasaur", "Ivysaur", "Venusaur");
                pokemonSpriteRef = Arrays.asList(R.drawable.spr_5b_001, R.drawable.spr_5b_002, R.drawable.spr_5b_003_m);
                break;
            case "water" :
                pokemonNameRef = Arrays.asList("Squirtle", "Wartortle", "Blastoise");
                pokemonSpriteRef = Arrays.asList(R.drawable.spr_5b_007, R.drawable.spr_5b_008, R.drawable.spr_5b_009);
                break;
            case "fire" :
                pokemonNameRef = Arrays.asList("Charmander", "Charmeleon", "Charizard");
                pokemonSpriteRef = Arrays.asList(R.drawable.spr_5b_004, R.drawable.spr_5b_005, R.drawable.spr_5b_006);
                break;
            case "electric" :
                pokemonNameRef = Arrays.asList("Pichu", "Pikachu", "Raichu");
                pokemonSpriteRef = Arrays.asList(R.drawable.spr_5b2_172, R.drawable.spr_5b2_025_f, R.drawable.spr_5b2_026_f);
                break;
            case "ghost" :
                pokemonNameRef = Arrays.asList("Ghastly", "Haunter", "Gengar");
                pokemonSpriteRef = Arrays.asList(R.drawable.spr_5b_092, R.drawable.spr_5b_093, R.drawable.spr_5b_094);
                break;
            case "psychic" :
                pokemonNameRef = Arrays.asList("Abra", "Kadabra", "Alakazam");
                pokemonSpriteRef = Arrays.asList(R.drawable.spr_5b_063, R.drawable.spr_5b_064_m, R.drawable.spr_5b_065_m);
                break;
            case "steel" :
                pokemonNameRef = Arrays.asList("Beldum", "Metang", "Metagross");
                pokemonSpriteRef = Arrays.asList(R.drawable.spr_5b_374, R.drawable.spr_5b_375, R.drawable.spr_5b_376);
                break;
            case "dragon" :
                pokemonNameRef = Arrays.asList("Deino", "Zweilous", "Hydreigon");
                pokemonSpriteRef = Arrays.asList(R.drawable.spr_5b_633, R.drawable.spr_5b_634, R.drawable.spr_5b_635);
                break;
        }
    }

    private void statGenerator() {
        if (!testMode) {
            for (int i = 0; i < stats.size(); i++) {
                statValCur.put(stats.get(i), new Random().nextInt(2));
                statValMax.put(stats.get(i), 1 + 1);
            }
        } else {
            for (int i = 0; i < stats.size(); i++) {
                statValCur.put(stats.get(i), 2);
                statValMax.put(stats.get(i), 2);
            }
            statValCur.put(stats.get(0), 1);
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
                statValMax.put(stats.get(i), statValMax.get(stats.get(i)) + new Random().nextInt(2) + 1);
            }
        } catch (Exception e) {
            throw new NullPointerException("Error: Invalid key reference");
        }
    }

    public String getPokemonName() {
        return pokemonName + " the " + pokemonNameRef.get(pokemonLevel - 1);
    }

    public void setPokemonName(String name) {
        pokemonName = name;
    }

    public int getPokemonSprite() {
        return pokemonSpriteRef.get(getPokemonLevel() - 1);
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
