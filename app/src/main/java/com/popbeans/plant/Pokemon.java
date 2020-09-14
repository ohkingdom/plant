package com.popbeans.plant;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Pokemon implements Serializable {

    String name;
    Integer level;
    List<String> species;
    List<Integer> sprites;
    enum Type {
        BUG,
        DARK,
        DRAGON,
        ELECTRIC,
        FAIRY,
        FIGHTING,
        FIRE,
        FLYING,
        GHOST,
        GRASS,
        GROUND,
        ICE,
        NORMAL,
        POISON,
        PSYCHIC,
        ROCK,
        STEEL,
        WATER
    }
    Type type;
    PokemonStatistics statistics;

    public Pokemon() {
        level = 1;
        statistics = new PokemonStatistics();
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public int getSprite(){
        return sprites.get(level - 1);
    }

    public int getCurrent(int value) {
        return statistics.getCurrent(value);
    }

    public int getMaximum(int value) {
        return statistics.getMaximum(value);
    }

    public void increaseCurrent(int value) {
        statistics.increaseCurrent(value);
    }

    public boolean evolve() {
        if (statistics.verify()) {
            level++;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return name.equals("") ? species.get(level - 1) : name + " the " + species.get(level - 1);
    }

}

class Bulbasaur extends Pokemon {
    public Bulbasaur() {
        species = Arrays.asList("Bulbasaur", "Ivysaur", "Venusaur");
        sprites = Arrays.asList(R.drawable.spr_5b_001, R.drawable.spr_5b_002, R.drawable.spr_5b_003_m);
        type = Type.GRASS;
    }
}

class Charmander extends Pokemon {
    public Charmander() {
        species = Arrays.asList("Charmander", "Charmeleon", "Charizard");
        sprites = Arrays.asList(R.drawable.spr_5b_004, R.drawable.spr_5b_005, R.drawable.spr_5b_006);
        type = Type.FIRE;
    }
}

class Squirtle extends Pokemon {
    public Squirtle() {
        species = Arrays.asList("Squirtle", "Wartortle", "Blastoise");
        sprites = Arrays.asList(R.drawable.spr_5b_007, R.drawable.spr_5b_008, R.drawable.spr_5b_009);
        type = Type.WATER;
    }
}

class Pichu extends Pokemon {
    public Pichu() {
        species = Arrays.asList("Pichu", "Pikachu", "Raichu");
        sprites = Arrays.asList(R.drawable.spr_5b2_172, R.drawable.spr_5b2_025_f, R.drawable.spr_5b2_026_f);
        type = Type.ELECTRIC;
    }
}

class Ghastly extends Pokemon {
    public Ghastly() {
        species = Arrays.asList("Ghastly", "Haunter", "Gengar");
        sprites = Arrays.asList(R.drawable.spr_5b_092, R.drawable.spr_5b_093, R.drawable.spr_5b_094);
        type = Type.GHOST;
    }
}

class Abra extends Pokemon {
    public Abra() {
        species = Arrays.asList("Abra", "Kadabra", "Alakazam");
        sprites = Arrays.asList(R.drawable.spr_5b_063, R.drawable.spr_5b_064_m, R.drawable.spr_5b_065_m);
        type = Type.PSYCHIC;
    }
}

class Beldum extends Pokemon {
    public Beldum() {
        species = Arrays.asList("Beldum", "Metang", "Metagross");
        sprites = Arrays.asList(R.drawable.spr_5b_374, R.drawable.spr_5b_375, R.drawable.spr_5b_376);
        type = Type.STEEL;
    }
}

class Deino extends Pokemon {
    public Deino() {
        species = Arrays.asList("Deino", "Zweilous", "Hydreigon");
        sprites = Arrays.asList(R.drawable.spr_5b_633, R.drawable.spr_5b_634, R.drawable.spr_5b_635);
        type = Type.DRAGON;
    }
}