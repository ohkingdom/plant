package com.popbeans.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Plant plant;
    private TextView plantName;
    private ImageView plantAvatar;
    private TextView plantLevel;

    private TextView statSunValCur;
    private TextView statSunValMax;
    private ProgressBar progSun;
    private Button btnSun;

    private TextView statWaterValCur;
    private TextView statWaterValMax;
    private ProgressBar progWater;
    private Button btnWater;

    private TextView statLoveValCur;
    private TextView statLoveValMax;
    private ProgressBar progLove;
    private Button btnLove;

    private ImageButton btnEvolve;

    // Call to redraw UI elements

    public void drawDetails() {
        // Draw Details
        if (plant.getPlantLevel() == 1) {
            plantAvatar.setImageResource(R.drawable.avatar_1);
        }
        if (plant.getPlantLevel() == 2) {
            plantAvatar.setImageResource(R.drawable.avatar_2);
        }
        if (plant.getPlantLevel() == 3) {
            plantAvatar.setImageResource(R.drawable.avatar_3);
        }

        plantLevel.setText("Level. " + Integer.toString(plant.getPlantLevel()));
    }

    public void drawStatistics() {

        // Draw Statistic Values
        statSunValCur.setText(Integer.toString(plant.getValCur("sun")));
        statSunValMax.setText(Integer.toString(plant.getValMax("sun")));
        statWaterValCur.setText(Integer.toString(plant.getValCur("water")));
        statWaterValMax.setText(Integer.toString(plant.getValMax("water")));
        statLoveValCur.setText(Integer.toString(plant.getValCur("love")));
        statLoveValMax.setText(Integer.toString(plant.getValMax("love")));

        // Draw Progress Bars
        progSun.setProgress(plant.getValCur("sun"));
        progSun.setMax(plant.getValMax("sun"));
        progWater.setProgress(plant.getValCur("water"));
        progWater.setMax(plant.getValMax("water"));
        progLove.setProgress(plant.getValCur("love"));
        progLove.setMax(plant.getValMax("love"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plant = new Plant();
        plantName = (TextView) findViewById(R.id.plantName);
        plantName.setText(plant.getPlantName());
        plantAvatar = (ImageView) findViewById(R.id.plantAvatar);
        plantLevel = (TextView) findViewById(R.id.plantLevel);

        statSunValCur = (TextView) findViewById(R.id.statSunValCur);
        statSunValMax = (TextView) findViewById(R.id.statSunValMax);
        statWaterValCur = (TextView) findViewById(R.id.statWaterValCur);
        statWaterValMax = (TextView) findViewById(R.id.statWaterValMax);
        statLoveValCur = (TextView) findViewById(R.id.statLoveValCur);
        statLoveValMax = (TextView) findViewById(R.id.statLoveValMax);

        progSun = (ProgressBar) findViewById(R.id.progSun);
        progWater = (ProgressBar) findViewById(R.id.progWater);
        progLove = (ProgressBar) findViewById(R.id.progLove);

        btnSun = (Button) findViewById(R.id.btnSun);
        btnWater = (Button) findViewById(R.id.btnWater);
        btnLove = (Button) findViewById(R.id.btnLove);
        btnEvolve = (ImageButton) findViewById(R.id.btnEvolve);

        drawDetails();
        drawStatistics();

        btnSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(plant.getValCur("sun") == plant.getValMax("sun"))) {
                    plant.incVal("sun");
                    drawStatistics();
                }
            }
        });

        btnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(plant.getValCur("water") == plant.getValMax("water"))) {
                    plant.incVal("water");
                    drawStatistics();
                }
            }
        });

        btnLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(plant.getValCur("love") == plant.getValMax("love"))) {
                    plant.incVal("love");
                    drawStatistics();
                }
            }
        });

        btnEvolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (plant.evolve()) {
                    drawDetails();
                    drawStatistics();
                    Toast toast = Toast.makeText(getApplicationContext(), "Evolution Successful!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }
}