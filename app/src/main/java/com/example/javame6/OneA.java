package com.example.javame6;

import android.app.*;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class OneA extends Activity implements DialogInterface.OnClickListener {

    final int DIALOG_EXIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
    }

    public void onclick(View v) {
        showDialog(DIALOG_EXIT);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_EXIT) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);

            adb.setTitle(R.string.exit);

            adb.setMessage(R.string.save_data);

            adb.setIcon(android.R.drawable.ic_dialog_info);

            adb.setPositiveButton(R.string.yes, this);

            adb.setNegativeButton(R.string.no, this);

            adb.setNeutralButton(R.string.cancel, this);

            return adb.create();
        }
        return super.onCreateDialog(id);
    }

    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                saveData();
                finish();
                break;
            case Dialog.BUTTON_NEGATIVE:
                finish();
                break;
            case Dialog.BUTTON_NEUTRAL:
                break;
        }
    }

    void saveData() {
        Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
    }
}