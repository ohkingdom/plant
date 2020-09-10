package com.popbeans.plant;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import com.github.penfeizhou.animation.loader.ResourceStreamLoader;

public class MainActivity extends AppCompatActivity {

    private Pokemon pokemon;
    private TextView pokemon_name_text;
    private ImageView pokemon_sprite;
    private TextView pokemon_level_text;
    private String activeButtonContext;

    private MathGame game;

    private TextView value_sun_current_text;
    private TextView value_sun_max_text;
    private ProgressBar progress_sun;

    private TextView value_water_current_text;
    private TextView value_water_max_text;
    private ProgressBar progress_water;

    private TextView value_love_current_text;
    private TextView value_love_max_text;
    private ProgressBar progress_love;

    // Call to redraw UI elements

    @SuppressLint("SetTextI18n")
    public void drawDetails() {

        // Draw Avatar and Details
        ResourceStreamLoader resourceLoader = new ResourceStreamLoader(MainActivity.this, pokemon.getPokemonSprite());
        APNGDrawable apngDrawable = new APNGDrawable((resourceLoader));
        pokemon_sprite.setImageDrawable(apngDrawable);
        pokemon_name_text.setText(pokemon.getPokemonName());
        pokemon_level_text.setText("Level. " + pokemon.getPokemonLevel());
    }

    @SuppressLint("SetTextI18n")
    public void drawStatistics() {

        // Draw Statistic Values
        value_sun_current_text.setText(Integer.toString(pokemon.getValCur("sun")));
        value_sun_max_text.setText(Integer.toString(pokemon.getValMax("sun")));
        value_water_current_text.setText(Integer.toString(pokemon.getValCur("water")));
        value_water_max_text.setText(Integer.toString(pokemon.getValMax("water")));
        value_love_current_text.setText(Integer.toString(pokemon.getValCur("love")));
        value_love_max_text.setText(Integer.toString(pokemon.getValMax("love")));

        // Draw Progress Bars
        progress_sun.setProgress(pokemon.getValCur("sun"));
        progress_sun.setMax(pokemon.getValMax("sun"));
        progress_water.setProgress(pokemon.getValCur("water"));
        progress_water.setMax(pokemon.getValMax("water"));
        progress_love.setProgress(pokemon.getValCur("love"));
        progress_love.setMax(pokemon.getValMax("love"));
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pokemon = new Pokemon(extras.getString("Type"), extras.getString("Name"));
        } else {
            pokemon = new Pokemon(null, null);
        }

        pokemon = new Pokemon(extras.getString("Type"), extras.getString("Name"));
        pokemon_name_text = findViewById(R.id.pokemon_name_text);
        pokemon_name_text.setText(pokemon.getPokemonName());
        pokemon_sprite = findViewById(R.id.pokemonAvatar);
        pokemon_level_text = findViewById(R.id.pokemon_level_text);

        game = new MathGame();
        game.setDifficulty(1);

        value_sun_current_text = findViewById(R.id.value_sun_current_text);
        value_sun_max_text = findViewById(R.id.value_sun_max_text);
        value_water_current_text = findViewById(R.id.value_water_current_text);
        value_water_max_text = findViewById(R.id.value_water_max_text);
        value_love_current_text = findViewById(R.id.value_love_current_text);
        value_love_max_text = findViewById(R.id.value_love_max_text);

        progress_sun = findViewById(R.id.progress_sun);
        progress_water = findViewById(R.id.progress_water);
        progress_love = findViewById(R.id.progress_love);

        Button button_sun = findViewById(R.id.button_sun);
        Button button_water = findViewById(R.id.button_water);
        Button button_love = findViewById(R.id.button_love);
        ImageButton button_evolve = findViewById(R.id.button_evolve);

        drawDetails();
        drawStatistics();

        button_sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activeButtonContext = "sun";
                mathDialog(savedInstanceState).show();
            }
        });

        button_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activeButtonContext = "water";
                mathDialog(savedInstanceState).show();
            }
        });

        button_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activeButtonContext = "love";
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
                    game.setDifficulty(pokemon.getPokemonLevel());
                    toastMessage = "Evolution Successful!";

                } else {
                    toastMessage = "Evolution Failed!";
                }
                Toast toast = Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_LONG);
                toast.show();
            }
        });

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
        builder.setPositiveButton(R.string.button_solve_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    if (game.answer(Integer.parseInt(variableEditText.getText().toString()))) {
                        if (!(pokemon.getValCur(activeButtonContext) == pokemon.getValMax(activeButtonContext))) {
                            pokemon.incVal(activeButtonContext);
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
            }
        });
        builder.setNegativeButton(R.string.button_cancel_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return builder.create();
    }

}