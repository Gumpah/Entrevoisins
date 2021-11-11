package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user removes a favorite Neighbour
 */
public class RemoveFavoriteNeighbourEvent {

    /**
     * Neighbour removed
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public RemoveFavoriteNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}