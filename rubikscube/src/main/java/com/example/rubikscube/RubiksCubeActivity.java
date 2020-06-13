package com.example.rubikscube;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rubikscube.cube_fragment.CubeFragment;

public class RubiksCubeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubiks_cube);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new CubeFragment())
                .commitNow();
    }
}
