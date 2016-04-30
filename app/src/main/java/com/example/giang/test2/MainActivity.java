package com.example.giang.test2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText mEditText;

    TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mEditText = (EditText) findViewById(R.id.edit_text);
        mResult = (TextView) findViewById(R.id.result);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mResult.setText(convertString(s.toString(), 5));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private String convertString(String s, int maxCharALine) {
        s = s.trim();

        if (s.length() <= maxCharALine) {
            //can show in a line
            return s;
        }

        //can not show in a line
        if (!s.contains(" ")) {
            //do not have space halfsize
            return splitNormal(s);
        }

        //have space halfsize
        int length = s.length();
        int firstSpace = s.indexOf(" ");
        if (firstSpace <= maxCharALine && length - firstSpace - 1 <= maxCharALine) {
            //Can show in 2 line
            return splitString(s,firstSpace);
        }

        //Can not show in 2 line
        int secoundSpace = s.substring(firstSpace + 1).indexOf(" ") + firstSpace;
        if (secoundSpace <= maxCharALine && length - secoundSpace - 1 <= maxCharALine) {
            //Can show in 2 line
            return splitString(s,secoundSpace);
        }

        return splitNormal(s);
    }

    private String splitNormal(String s) {
        if (s.length() < 1) {
            return s;
        }

        int length = (s.length() - 1) / 2;

        return splitString(s, length);
    }

    private String splitString(String s, int index) {
        return s.substring(0, index + 1).trim() + "\n" + s.substring(index + 1, s.length()).trim();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
