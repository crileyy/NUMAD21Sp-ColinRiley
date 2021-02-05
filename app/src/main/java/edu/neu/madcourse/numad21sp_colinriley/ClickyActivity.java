package edu.neu.madcourse.numad21sp_colinriley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
}