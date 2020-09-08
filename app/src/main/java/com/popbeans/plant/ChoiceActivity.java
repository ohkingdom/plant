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

        ImageButton fireButton = findViewById(R.id.fireSelect);
        ImageButton grassButton = findViewById(R.id.grassSelect);
        ImageButton waterButton = findViewById(R.id.waterSelect);

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

    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ChoiceActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = (inflater.inflate(R.layout.name_dialog, null));
        builder.setView(dialogView);
        builder.setTitle(R.string.nameModalHeader);
        final EditText nameInput = (EditText) dialogView.findViewById(R.id.nameField);
        builder.setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(ChoiceActivity.this, MainActivity.class);
                intent.putExtra("Name", nameInput.getText().toString());
                intent.putExtra("Type", typeChoice);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return builder.create();
    }

}