package com.popbeans.plant;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class ChoiceActivity extends AppCompatActivity {

    private Intent intent;
    private Pokemon pokemon;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        ImageButton fireButton = findViewById(R.id.button_select_fire);
        ImageButton grassButton = findViewById(R.id.button_select_grass);
        ImageButton waterButton = findViewById(R.id.button_select_water);
        ImageButton dragonButton = findViewById(R.id.button_select_dragon);
        ImageButton electricButton = findViewById(R.id.button_select_electric);
        ImageButton steelButton = findViewById(R.id.button_select_steel);
        ImageButton psychicButton = findViewById(R.id.button_select_psychic);
        ImageButton ghostButton = findViewById(R.id.button_select_ghost);

        SwitchCompat testModeSwitch = findViewById(R.id.switch_test_mode);

        intent = new Intent(ChoiceActivity.this, MainActivity.class);

        fireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokemon = new Charmander();
                onCreateDialog(savedInstanceState).show();
            }
        });

        grassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokemon = new Bulbasaur();
                onCreateDialog(savedInstanceState).show();
            }
        });

        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokemon = new Squirtle();
                onCreateDialog(savedInstanceState).show();
            }
        });

        dragonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokemon = new Deino();
                onCreateDialog(savedInstanceState).show();
            }
        });

        electricButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokemon = new Pichu();
                onCreateDialog(savedInstanceState).show();
            }
        });

        steelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokemon = new Beldum();
                onCreateDialog(savedInstanceState).show();
            }
        });

        psychicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokemon = new Abra();
                onCreateDialog(savedInstanceState).show();
            }
        });

        ghostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokemon = new Ghastly();
                onCreateDialog(savedInstanceState).show();
            }
        });

        testModeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (testModeSwitch.isChecked()) {
                    intent.putExtra("TestMode", true);
                } else {
                    intent.putExtra("TestMode", false);
                }
            }
        });

    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ChoiceActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = (inflater.inflate(R.layout.name_dialog, null));
        builder.setView(dialogView);
        builder.setTitle(R.string.header_name_modal_text);
        final EditText nameInput = (EditText) dialogView.findViewById(R.id.field_name_modal_text);
        builder.setPositiveButton(R.string.button_submit_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                intent.putExtra("Name", nameInput.getText().toString());
                intent.putExtra("Pokemon", pokemon);
                startActivity(intent);
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