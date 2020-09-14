package com.popbeans.plant;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.penfeizhou.animation.apng.APNGDrawable;
import com.github.penfeizhou.animation.gif.GifDrawable;
import com.github.penfeizhou.animation.loader.ResourceStreamLoader;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Pokemon pokemon;
    private TextView pokemon_name_text;
    private ImageView pokemon_sprite;
    private ImageView pokemon_sprite_overlay;
    private TextView pokemon_level_text;

    private MathGame game;

    private TextView value_sun_current_text;
    private TextView value_sun_max_text;
    private ProgressBar progress_sun;
    private Button button_sun;

    private TextView value_water_current_text;
    private TextView value_water_max_text;
    private ProgressBar progress_water;
    private Button button_water;

    private TextView value_love_current_text;
    private TextView value_love_max_text;
    private ProgressBar progress_love;
    private Button button_love;

    private Integer activeButtonContext;

    private ImageButton button_evolve;

    // Call to Redraw UI Elements

    @SuppressLint("SetTextI18n")
    public void drawDetails() {

        // Draw Avatar and Details
        ResourceStreamLoader resourceLoader = new ResourceStreamLoader(MainActivity.this, pokemon.getSprite());
        APNGDrawable apngDrawable = new APNGDrawable((resourceLoader));
        pokemon_sprite.setImageDrawable(apngDrawable);
        pokemon_name_text.setText(pokemon.toString());
        pokemon_level_text.setText("Level. " + pokemon.getLevel());
    }

    @SuppressLint("SetTextI18n")
    public void drawStatistics() {

        int valueSunCurrent = pokemon.getCurrent(0);
        int valueSunMaximum = pokemon.getMaximum(0);
        int valueWaterCurrent = pokemon.getCurrent(1);
        int valueWaterMaximum = pokemon.getMaximum(1);
        int valueLoveCurrent = pokemon.getCurrent(2);
        int valueLoveMaximum = pokemon.getMaximum(2);

        if (valueSunCurrent == valueSunMaximum) {
            button_sun.setEnabled(false);
        } else {
            button_sun.setEnabled(true);
        }

        if (valueWaterCurrent == valueWaterMaximum) {
            button_water.setEnabled(false);
        } else {
            button_water.setEnabled(true);
        }

        if (valueLoveCurrent == valueLoveMaximum) {
            button_love.setEnabled(false);
        } else {
            button_love.setEnabled(true);
        }

        if (pokemon.getLevel() == 3) {
            button_evolve.setEnabled(false);
        }

        // Draw Statistic Values
        value_sun_current_text.setText(Integer.toString(valueSunCurrent));
        value_sun_max_text.setText(Integer.toString(valueSunMaximum));
        value_water_current_text.setText(Integer.toString(valueWaterCurrent));
        value_water_max_text.setText(Integer.toString(valueWaterMaximum));
        value_love_current_text.setText(Integer.toString(valueLoveCurrent));
        value_love_max_text.setText(Integer.toString(valueLoveMaximum));

        // Draw Progress Bars
        progress_sun.setProgress(valueSunCurrent);
        progress_sun.setMax(valueSunMaximum);
        progress_water.setProgress(valueWaterCurrent);
        progress_water.setMax(valueWaterMaximum);
        progress_love.setProgress(valueLoveCurrent);
        progress_love.setMax(valueLoveMaximum);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pokemon = (Pokemon) extras.getSerializable("Pokemon");
            pokemon.setName(extras.getString("Name"));
        } else {
            pokemon = new Pokemon();
        }

        pokemon_name_text = findViewById(R.id.pokemon_name_text);
        pokemon_name_text.setText(pokemon.toString());
        pokemon_sprite = findViewById(R.id.pokemon_sprite);
        pokemon_sprite_overlay = findViewById(R.id.pokemon_sprite_overlay);
        pokemon_level_text = findViewById(R.id.pokemon_level_text);

        game = new MathGame();

        value_sun_current_text = findViewById(R.id.value_sun_current_text);
        value_sun_max_text = findViewById(R.id.value_sun_max_text);
        value_water_current_text = findViewById(R.id.value_water_current_text);
        value_water_max_text = findViewById(R.id.value_water_max_text);
        value_love_current_text = findViewById(R.id.value_love_current_text);
        value_love_max_text = findViewById(R.id.value_love_max_text);

        progress_sun = findViewById(R.id.progress_sun);
        progress_water = findViewById(R.id.progress_water);
        progress_love = findViewById(R.id.progress_love);

        button_sun = findViewById(R.id.button_sun);
        button_water = findViewById(R.id.button_water);
        button_love = findViewById(R.id.button_love);
        button_evolve = findViewById(R.id.button_evolve);

        drawDetails();
        drawStatistics();

        button_sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activeButtonContext = 0;
                mathDialog(savedInstanceState).show();
            }
        });

        button_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activeButtonContext = 1;
                mathDialog(savedInstanceState).show();
            }
        });

        button_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activeButtonContext = 2;
                mathDialog(savedInstanceState).show();
            }
        });

        button_evolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toastMessage;
                if (pokemon.evolve()) {
                    drawDetails();
                    drawStatistics();
                    drawSpriteOverlay();
                    game.setDifficulty(pokemon.getLevel());
                    toastMessage = "Evolution Successful!";
                } else {
                    toastMessage = "Evolution Failed!";
                }
                Toast toast = Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }

    private void drawSpriteOverlay() {
        ResourceStreamLoader resourceLoader = new ResourceStreamLoader(MainActivity.this, R.drawable.shiny_effect_blue);
        GifDrawable gifDrawable = new GifDrawable((resourceLoader));
        pokemon_sprite_overlay.setImageDrawable(gifDrawable);
        refreshSpriteOverlay();
    }

    private void refreshSpriteOverlay() {
        int delay = 3000;
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> pokemon_sprite_overlay.setImageResource(R.drawable.placeholder), delay, TimeUnit.MILLISECONDS);
    }

    public Dialog mathDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = (inflater.inflate(R.layout.math_dialog, null));
        builder.setView(dialogView);
        builder.setTitle(R.string.header_math_modal_text);
        TextView constantTextView = (TextView) dialogView.findViewById(R.id.value_constant_text);
        constantTextView.setText(game.getConstant());
        TextView operatorTextView = (TextView) dialogView.findViewById(R.id.value_operator_text);
        operatorTextView.setText(game.getOperator());
        TextView sumTextView = (TextView) dialogView.findViewById(R.id.value_sum_text);
        sumTextView.setText(game.getSum());
        final EditText variableEditText = (EditText) dialogView.findViewById(R.id.value_variable_text);
        builder.setPositiveButton(R.string.button_solve_text, (dialogInterface, i) -> {
            try {
                if (game.answer(Integer.parseInt(variableEditText.getText().toString()))) {
                    if (!(pokemon.getCurrent(activeButtonContext) == pokemon.getMaximum(activeButtonContext))) {
                        pokemon.increaseCurrent(activeButtonContext);
                        drawStatistics();
                        Toast toast = Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Incorrect, try again!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            } catch (Exception e) {
                System.out.println("Error!");
            }
        });
        builder.setNegativeButton(R.string.button_cancel_text, (dialogInterface, i) -> {
        });
        return builder.create();
    }

}