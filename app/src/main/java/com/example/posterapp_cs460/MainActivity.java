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
        poster1.image = R.drawable.spider_man;
        poster1.name = "Spider-Man Into the Spider Verse";
        poster1.createdBy = "Phil Lord & Christopher Miller";
        poster1.rating = 5f;
        poster1.story = "The story of Miles Morales as Spider-Man.";
        posterList.add(poster1);

        Poster poster2 = new Poster();
        poster2.image = R.drawable.the_boy;
        poster2.name = "The Boy, the Mole, the Fox, and the Horse";
        poster2.createdBy = "Charlie Mackesy";
        poster2.rating = 4.5f;
        poster2.story = "The adventure of the boy and his animal friends.";
        posterList.add(poster2);

        Poster poster3 = new Poster();
        poster3.image = R.drawable.puss_in_boots;
        poster3.name = "Puss in Boots: The Last Wish";
        poster3.createdBy = "Joel Crawford";
        poster3.rating = 4.8f;
        poster3.story = "Puss in Boots is at his last life and embarks on an adventure " +
                "to get his life back";
        posterList.add(poster3);

        Poster poster4 = new Poster();
        poster4.image = R.drawable.blink_twice;
        poster4.name = "Blink Twice";
        poster4.createdBy = "Zoë Kravitz";
        poster4.rating = 3.9f;
        poster4.story = "Wild nights soon blend into sun-soaked days, but when strange things" +
                " start to happen, Frida must uncover the truth if she hopes to make it out alive.";
        posterList.add(poster4);

        Poster poster5 = new Poster();
        poster5.image = R.drawable.dune;
        poster5.name = "Dune";
        poster5.createdBy = "Denis Villeneuve";
        poster5.rating = 4.4f;
        poster5.story = "Paul Atreides, a brilliant and gifted young man born into a great" +
                " destiny beyond his understanding, must travel to a dangerous planet in the" +
                " universe to ensure the future of his family and his people.";
        posterList.add(poster5);

        Poster poster6 = new Poster();
        poster6.image = R.drawable.dune_part_two;
        poster6.name = "Dune: Part Two";
        poster6.createdBy = "Denis Villeneuve";
        poster6.rating = 4.7f;
        poster6.story = "Paul Atreides unites with Chani and the Fremen while seeking revenge" +
                " against the conspirators who destroyed his family. Facing a choice between the" +
                " love of his life and the fate of the universe, he must prevent a terrible" +
                " future only he can foresee.";
        posterList.add(poster6);

        Poster poster7 = new Poster();
        poster7.image = R.drawable.transformers;
        poster7.name = "Transformers One";
        poster7.createdBy = "Josh Cooley";
        poster7.rating = 4.6f;
        poster7.story = "Optimus Prime and Megatron, as former friends, bonded like brothers. " +
                "Their relationship ultimately changes Cybertron's fate forever.";
        posterList.add(poster7);

        Poster poster8 = new Poster();
        poster8.image = R.drawable.interstellar;
        poster8.name = "Interstellar";
        poster8.createdBy = "Christopher Nolan";
        poster8.rating = 4.3f;
        poster8.story = "In Earth's future, a global crop blight and second Dust Bowl are slowly" +
                " rendering the planet uninhabitable. Professor Brand (Michael Caine), a" +
                " brilliant NASA physicist, is working on plans to save mankind by transporting" +
                " Earth's population to a new home via a wormhole.";
        posterList.add(poster8);

        Poster poster9 = new Poster();
        poster9.image = R.drawable.oppenheimer;
        poster9.name = "Oppenheimer";
        poster9.createdBy = "Christopher Nolan";
        poster9.rating = 4.7f;
        poster9.story = "Oppenheimer and a team of scientists spend years developing and" +
                " designing the atomic bomb. Their work comes to fruition on July 16, 1945, as" +
                " they witness the world's first nuclear explosion, forever changing the course" +
                " of history.";
        posterList.add(poster9);

        Poster poster10 = new Poster();
        poster10.image = R.drawable.inception;
        poster10.name = "Inception";
        poster10.createdBy = "Christopher Nolan";
        poster10.rating = 4.3f;
        poster10.story = "Dom Cobb (Leonardo DiCaprio) is a thief with the rare ability to" +
                " enter people's dreams and steal their secrets from their subconscious. His" +
                " skill has made him a hot commodity in the world of corporate espionage but has" +
                " also cost him everything he loves.";
        posterList.add(poster10);



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