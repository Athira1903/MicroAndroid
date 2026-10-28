package com.example.myapplication;

import android.os.Bundle;
import android.database.Cursor;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText contactIdInput, nameInput, phoneInput;
    Button saveButton, searchButton, updateButton;
    Button deleteButton, viewButton;
    TextView resultText;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets bars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());
                    v.setPadding(bars.left, bars.top,
                            bars.right, bars.bottom);
                    return insets;
                });

        contactIdInput = findViewById(R.id.contactIdInput);
        nameInput = findViewById(R.id.nameInput);
        phoneInput = findViewById(R.id.phoneInput);

        saveButton = findViewById(R.id.saveButton);
        searchButton = findViewById(R.id.searchButton);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);
        viewButton = findViewById(R.id.viewButton);
        resultText = findViewById(R.id.resultText);

        databaseHelper = new DatabaseHelper(this);

        // INSERT
        saveButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim();

            if (name.isEmpty() || phone.isEmpty()) {
                showMessage("Enter name and phone");
                return;
            }

            if (databaseHelper.insertContact(name, phone)) {
                showMessage("Contact saved successfully");
                clearNameAndPhone();
            } else {
                showMessage("Failed to save contact");
            }
        });

        // SEARCH
        searchButton.setOnClickListener(v -> {
            Integer id = getContactId();
            if (id == null) return;

            Cursor cursor = databaseHelper.searchContact(id);

            try {
                if (cursor.moveToFirst()) {
                    nameInput.setText(cursor.getString(1));
                    phoneInput.setText(cursor.getString(2));

                    resultText.setText(
                            "ID: " + cursor.getInt(0) +
                                    "\nName: " + cursor.getString(1) +
                                    "\nPhone: " + cursor.getString(2));

                    showMessage("Contact found");
                } else {
                    resultText.setText("Contact not found");
                    showMessage("Contact not found");
                }
            } finally {
                cursor.close();
            }
        });

        // UPDATE
        updateButton.setOnClickListener(v -> {
            Integer id = getContactId();
            if (id == null) return;

            String name = nameInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim();

            if (name.isEmpty() || phone.isEmpty()) {
                showMessage("Enter the new name and phone");
                return;
            }

            if (databaseHelper.updateContact(id, name, phone)) {
                showMessage("Contact updated successfully");
                resultText.setText("Contact updated: " + id);
            } else {
                showMessage("Contact ID not found");
            }
        });

        // DELETE
        deleteButton.setOnClickListener(v -> {
            Integer id = getContactId();
            if (id == null) return;

            if (databaseHelper.deleteContact(id)) {
                showMessage("Contact deleted");
                contactIdInput.setText("");
                clearNameAndPhone();
                resultText.setText("Contact deleted");
            } else {
                showMessage("Contact ID not found");
            }
        });

        // VIEW ALL
        viewButton.setOnClickListener(v -> {
            Cursor cursor = databaseHelper.getContacts();
            StringBuilder data = new StringBuilder();

            try {
                while (cursor.moveToNext()) {
                    data.append("ID: ").append(cursor.getInt(0))
                            .append("\nName: ").append(cursor.getString(1))
                            .append("\nPhone: ").append(cursor.getString(2))
                            .append("\n\n");
                }
            } finally {
                cursor.close();
            }

            if (data.length() == 0) {
                data.append("No contacts found");
            }

            resultText.setText(data.toString());
        });
    }

    private Integer getContactId() {
        String text = contactIdInput.getText().toString().trim();

        if (text.isEmpty()) {
            showMessage("Enter contact ID");
            return null;
        }

        try {
            int id = Integer.parseInt(text);

            if (id <= 0) {
                showMessage("Enter a valid ID");
                return null;
            }

            return id;
        } catch (NumberFormatException e) {
            showMessage("Invalid contact ID");
            return null;
        }
    }

    private void clearNameAndPhone() {
        nameInput.setText("");
        phoneInput.setText("");
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
