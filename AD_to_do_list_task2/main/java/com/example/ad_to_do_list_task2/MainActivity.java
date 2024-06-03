package com.example.ad_to_do_list_task2;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTask;
    private Button buttonAdd;
    private ListView listViewTasks;
    private ArrayList<Task> tasks;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = findViewById(R.id.editTextTask);
        buttonAdd = findViewById(R.id.buttonAdd);
        listViewTasks = findViewById(R.id.listViewTasks);

        tasks = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, tasks);
        listViewTasks.setAdapter(taskAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = editTextTask.getText().toString();
                if (!taskName.isEmpty()) {
                    Task task = new Task(taskName);
                    tasks.add(task);
                    taskAdapter.notifyDataSetChanged();
                    editTextTask.setText("");
                }
            }
        });
    }
}
