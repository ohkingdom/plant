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

public class ChoiceActivity extends AppCompatActivity {

    private String typeChoice;

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

        fireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeChoice = "fire";
                onCreateDialog(savedInstanceState).show();
            }
        });

        grassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeChoice = "grass";
                onCreateDialog(savedInstanceState).show();
            }
        });

        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeChoice = "water";
                onCreateDialog(savedInstanceState).show();
            }
        });

        dragonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeChoice = "dragon";
                onCreateDialog(savedInstanceState).show();
            }
        });

        electricButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeChoice = "electric";
                onCreateDialog(savedInstanceState).show();
            }
        });

        steelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeChoice = "steel";
                onCreateDialog(savedInstanceState).show();
            }
        });

        psychicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeChoice = "psychic";
                onCreateDialog(savedInstanceState).show();
            }
        });

        ghostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeChoice = "ghost";
                onCreateDialog(savedInstanceState).show();
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
                Intent intent = new Intent(ChoiceActivity.this, MainActivity.class);
                intent.putExtra("Name", nameInput.getText().toString());
                intent.putExtra("Type", typeChoice);
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