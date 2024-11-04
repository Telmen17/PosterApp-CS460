/**
 * A PosterAdapter class that binds the movie poster information onto the screen and
 * handles the movie poster interaction with the user.
 * @author Telmen Enkhtuvshin
 */
package com.example.posterapp_cs460;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {

    /**
     * PosterAdapter fields
     */
    private List<Poster> posterList;
    private PostersListener postersListener;

    /**
     * Constructor for the PosterAdapter class. Movie list and a poster listener are connected.
     * @param posterList An Arraylist that holds movie poster objects.
     * @param postersListener An action listener object that would control user interaction.
     */
    public PosterAdapter(List<Poster> posterList, PostersListener postersListener) {
        this.posterList = posterList;
        this.postersListener = postersListener;
    }

    /**
     * An overridden action listener method that reacts when the ViewHolder is created.
     * Once called, it returns a new PosterViewHolder object element.
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new PosterViewHolder object that holds the poster on the screen.
     */
    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false));
    }

    /**
     * An overridden action listener method that reacts when the ViewHolder is bound.
     * This method binds the movie poster objects to the GUI elements to be displayed.
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPosters(posterList.get(position));
    }

    /**
     * An overridden getter method that returns the number of items in the posterList ArrayList.
     * @return Returns the number of elements in the posterList ArrayList.
     */
    @Override
    public int getItemCount() {
        return posterList.size();
    }

    /**
     * A method that returns the selected posters on the app as an ArrayList.
     * @return Returns the selected posters as an ArrayList.
     */
    public List<Poster> getSelectedPosters() {
        List<Poster> selectedPosters = new ArrayList<>();
        for (Poster poster : this.posterList) {
            if (poster.isSelected) {
                selectedPosters.add(poster);
            }
        }
        return selectedPosters;
    }

    /**
     * A class that is responsible for managing ViewHolder of posters and recycles the views
     * for more movie posters to be displayed.
     */
    class PosterViewHolder extends RecyclerView.ViewHolder {

        /**
         * Fields that should be connected to GUI elements.
         */
        ConstraintLayout layoutPosters;
        View viewBackground;
        RoundedImageView imagePoster;
        TextView textName, textCreatedBy, textStory;
        RatingBar ratingBar;
        ImageView imageSelected;

        /**
         * A PosterViewHolder constructor that creates the class object. GUI elements
         * are connected to the fields.
         * @param itemView The view screen of the movie poster app.
         */
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutPosters = itemView.findViewById(R.id.layoutPosters);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreatedBy = itemView.findViewById(R.id.textCreatedBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }

        /**
         * A method that binds an input poster object attributes to the UI elements.
         * @param poster Poster class object that contains movie information.
         */
        void bindPosters(final Poster poster) {
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreatedBy.setText(poster.createdBy);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);

            if (poster.isSelected) {
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            } else {
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }

            layoutPosters.setOnClickListener(new View.OnClickListener() {

                /**
                 * An action listener method that reacts to an event when a poster is clicked.
                 * It turns off the selection style according to the poster selection state.
                 * @param view The view object of the app screen.
                 */
                @Override
                public void onClick(View view) {
                    //If the poster is selected, visibility of the selection style is turned off.
                    if (poster.isSelected) {
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;

                        if (getSelectedPosters().isEmpty()) {
                            postersListener.onPosterAction(false);
                        }
                    //Else, the poster selected state style becomes visible.
                    } else {
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected = true;
                        postersListener.onPosterAction(true);
                    }
                }
            });
        }
    }
}
