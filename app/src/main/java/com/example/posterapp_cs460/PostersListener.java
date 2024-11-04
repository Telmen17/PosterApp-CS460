/**
 * The PosterListener Interface that is implemented by other classes.
 * @author Telmen Enkhtuvshin
 */
package com.example.posterapp_cs460;

public interface PostersListener {
    /**
     * An action listener method that handles the event when the poster is clicked.
     * @param isSelected A boolean value that determines whether or not the poster is selected.
     */
    void onPosterAction(Boolean isSelected);
}
