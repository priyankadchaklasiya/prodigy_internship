package com.example.ad_to_do_list_task2;


import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Task task = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        }

        TextView textViewTask = convertView.findViewById(R.id.textViewTask);
        Button buttonEdit = convertView.findViewById(R.id.buttonEdit);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);

        textViewTask.setText(task.getName());

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditTaskDialog(task, position);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(task);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private void showEditTaskDialog(final Task task, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dialog_edit_task, null);
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();
        dialog.show();

        final EditText editTextEditTask = dialogView.findViewById(R.id.editTextEditTask);
        Button buttonUpdate = dialogView.findViewById(R.id.buttonUpdate);

        editTextEditTask.setText(task.getName());

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.setName(editTextEditTask.getText().toString());
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }
}
