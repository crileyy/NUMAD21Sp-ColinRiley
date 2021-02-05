package edu.neu.madcourse.numad21sp_colinriley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ClickyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky);
    }

    public void onClickPrevious(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickA(View view) {
        setPressedValue("A");
    }

    public void onClickB(View view) {
        setPressedValue("B");
    }

    public void onClickC(View view) {
        setPressedValue("C");
    }

    public void onClickD(View view) {
        setPressedValue("D");
    }

    public void onClickE(View view) {
        setPressedValue("E");
    }

    public void onClickF(View view) {
        setPressedValue("F");
    }

    private void setPressedValue(String val) {
        TextView tv = (TextView)findViewById(R.id.textView_pressed);
        tv.setText("Pressed: " + val);
    }
}