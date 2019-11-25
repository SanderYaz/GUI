package com.example.moviefragmant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MovieFragment.OnMovieSelectionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void movieSelected(Movie item) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("movie", item);
        startActivity(intent);
    }
}