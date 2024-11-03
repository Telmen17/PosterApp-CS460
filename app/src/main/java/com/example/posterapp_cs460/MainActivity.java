package com.example.posterapp_cs460;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PostersListener {

    private Button buttonAddToWatchList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView postersRecyclerView = findViewById(R.id.postersRecyclerView);
        buttonAddToWatchList = findViewById(R.id.buttonAddToWatchlist);

        //Prepare data

        List<Poster> posterList = new ArrayList<>();

        Poster poster1 = new Poster();
        poster1.image = R.drawable.spiderman;
        poster1.name = "Spider-Man Into the Spider Verse";
        poster1.createdBy = "Phil Lord & Christopher Miller";
        poster1.rating = 5f;
        poster1.story = "The story of Miles Morales as Spider-Man.";

        posterList.add(poster1);

        final PosterAdapter posterAdapter = new PosterAdapter(posterList, this);
        postersRecyclerView.setAdapter(posterAdapter);

        buttonAddToWatchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Poster> selectPosters = posterAdapter.getSelectedPosters();

                StringBuilder posterNames = new StringBuilder();
                for (int i = 0; i < selectPosters.size(); i++) {
                    if (i == 0) {
                        posterNames.append(selectPosters.get(i).name);
                    } else {
                        posterNames.append("\n").append(selectPosters.get(i).name);
                    }
                }
                Toast.makeText(MainActivity.this, posterNames.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPosterAction(Boolean isSelected) {
        if (isSelected) {
            buttonAddToWatchList.setVisibility(View.VISIBLE);
        } else {
            buttonAddToWatchList.setVisibility(View.GONE);
        }
    }
}