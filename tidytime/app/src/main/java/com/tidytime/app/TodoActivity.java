package com.tidytime.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TodoActivity extends AppCompatActivity {
    private TodoAdapter todoAdapter;
    private List<TodoItem> todoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        RecyclerView todoRecyclerView = findViewById(R.id.todoRecyclerView);
        todoItems = new ArrayList<>();
        todoAdapter = new TodoAdapter(todoItems);

        todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        todoRecyclerView.setAdapter(todoAdapter);

        FloatingActionButton addTodoButton = findViewById(R.id.addTodoButton);
        addTodoButton.setOnClickListener(v -> showAddTodoDialog());
    }

    private void showAddTodoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_todo, null);

        EditText titleEditText = dialogView.findViewById(R.id.titleEditText);
        EditText descriptionEditText = dialogView.findViewById(R.id.descriptionEditText);

        builder.setView(dialogView)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    String title = titleEditText.getText().toString();
                    String description = descriptionEditText.getText().toString();
                    if (!title.isEmpty()) {
                        addTodoItem(new TodoItem(title, description));
                    }
                })
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    private void addTodoItem(TodoItem item) {
        todoItems.add(item);
        todoAdapter.notifyItemInserted(todoItems.size() - 1);
    }
}