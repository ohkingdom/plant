package com.popbeans.plant;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.penfeizhou.animation.apng.APNGDrawable;
import com.github.penfeizhou.animation.loader.ResourceStreamLoader;

public class MainActivity extends AppCompatActivity {

    private Pokemon pokemon;
    private TextView pokemonName;
    private ImageView pokemonSprite;
    int avatarDraw = R.drawable.spr_5b_151;
    private TextView pokemonLevel;

    private MathGame game;

    private TextView statSunValCur;
    private TextView statSunValMax;
    private ProgressBar progressBarSun;

    private TextView statWaterValCur;
    private TextView statWaterValMax;
    private ProgressBar progressBarWater;

    private TextView statLoveValCur;
    private TextView statLoveValMax;
    private ProgressBar progressBarLove;

    // Call to redraw UI elements

    @SuppressLint("SetTextI18n")
    public void drawDetails() {

        // Draw Avatar and Details

        switch (pokemon.getPokemonLevel()) {
            case 1 :
                if (pokemon.getPokemonType().equals("fire")) {
                    avatarDraw = R.drawable.spr_5b_004;
                }
                if (pokemon.getPokemonType().equals("grass")) {
                    avatarDraw = R.drawable.spr_5b_001;
                }
                if (pokemon.getPokemonType().equals("water")) {
                    avatarDraw = R.drawable.spr_5b_007;
                }
                break;
            case 2 :
                if (pokemon.getPokemonType().equals("fire")) {
                    avatarDraw = R.drawable.spr_5b_005;
                }
                if (pokemon.getPokemonType().equals("grass")) {
                    avatarDraw = R.drawable.spr_5b_002;
                }
                if (pokemon.getPokemonType().equals("water")) {
                    avatarDraw = R.drawable.spr_5b_008;
                }
                break;
            case 3 :
                if (pokemon.getPokemonType().equals("fire")) {
                    avatarDraw = R.drawable.spr_5b_006;
                }
                if (pokemon.getPokemonType().equals("grass")) {
                    avatarDraw = R.drawable.spr_5b_003_m;
                }
                if (pokemon.getPokemonType().equals("water")) {
                    avatarDraw = R.drawable.spr_5b_009;
                }
                break;
        }
        ResourceStreamLoader resourceLoader = new ResourceStreamLoader(MainActivity.this, avatarDraw);
        APNGDrawable apngDrawable = new APNGDrawable((resourceLoader));
        pokemonSprite.setImageDrawable(apngDrawable);

        pokemonName.setText(pokemon.getPokemonName());
        pokemonLevel.setText("Level. " + pokemon.getPokemonLevel());
    }

    @SuppressLint("SetTextI18n")
    public void drawStatistics() {

        // Draw Statistic Values
        statSunValCur.setText(Integer.toString(pokemon.getValCur("sun")));
        statSunValMax.setText(Integer.toString(pokemon.getValMax("sun")));
        statWaterValCur.setText(Integer.toString(pokemon.getValCur("water")));
        statWaterValMax.setText(Integer.toString(pokemon.getValMax("water")));
        statLoveValCur.setText(Integer.toString(pokemon.getValCur("love")));
        statLoveValMax.setText(Integer.toString(pokemon.getValMax("love")));

        // Draw Progress Bars
        progressBarSun.setProgress(pokemon.getValCur("sun"));
        progressBarSun.setMax(pokemon.getValMax("sun"));
        progressBarWater.setProgress(pokemon.getValCur("water"));
        progressBarWater.setMax(pokemon.getValMax("water"));
        progressBarLove.setProgress(pokemon.getValCur("love"));
        progressBarLove.setMax(pokemon.getValMax("love"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pokemon = new Pokemon(extras.getString("Type"), extras.getString("Name"));
        } else {
            pokemon = new Pokemon(null, null);
        }

        pokemon = new Pokemon(extras.getString("Type"), extras.getString("Name"));
        pokemonName = findViewById(R.id.pokemonName);
        pokemonName.setText(pokemon.getPokemonName());
        pokemonSprite = findViewById(R.id.pokemonAvatar);
        pokemonLevel = findViewById(R.id.pokemonLevel);

        game = new MathGame();

        statSunValCur = findViewById(R.id.statSunValCur);
        statSunValMax = findViewById(R.id.statSunValMax);
        statWaterValCur = findViewById(R.id.statWaterValCur);
        statWaterValMax = findViewById(R.id.statWaterValMax);
        statLoveValCur = findViewById(R.id.statLoveValCur);
        statLoveValMax = findViewById(R.id.statLoveValMax);

        progressBarSun = findViewById(R.id.progressBarSun);
        progressBarWater = findViewById(R.id.progressBarWater);
        progressBarLove = findViewById(R.id.progressBarLove);

        Button btnSun = findViewById(R.id.btnSun);
        Button btnWater = findViewById(R.id.btnWater);
        Button btnLove = findViewById(R.id.btnLove);
        ImageButton btnEvolve = findViewById(R.id.btnEvolve);

        drawDetails();
        drawStatistics();

        btnSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(pokemon.getValCur("sun") == pokemon.getValMax("sun"))) {
                    pokemon.incVal("sun");
                    drawStatistics();
                }
            }
        });

        btnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(pokemon.getValCur("water") == pokemon.getValMax("water"))) {
                    pokemon.incVal("water");
                    drawStatistics();
                }
            }
        });

        btnLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(pokemon.getValCur("love") == pokemon.getValMax("love"))) {
                    pokemon.incVal("love");
                    drawStatistics();
                }
            }
        });

        btnEvolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toastMessage;
                if (pokemon.evolve()) {
                    drawDetails();
                    drawStatistics();
                    game.setDifficulty(game.getDifficulty() + 1);
                    toastMessage = "Evolution Successful!";

                } else {
                    toastMessage = "Evolution Failed!";
                }
                Toast toast = Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }

    public void mathPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Solve for X!").setMessage("Haha, content.");
        builder.setPositiveButton(R.string.solve, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}