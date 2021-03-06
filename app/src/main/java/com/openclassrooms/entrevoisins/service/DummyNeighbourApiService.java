package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public List<Neighbour> getFavorites() {
        List<Neighbour> FavoriteNeighbours = new ArrayList<>();
        for (int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i).getFavorite()) {
                FavoriteNeighbours.add(neighbours.get(i));
            }
        }
        return FavoriteNeighbours;
    }

    @Override
    public Neighbour getNeighbour(long id) {
        Neighbour neighbour = null;
        for (int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i).getId() == id) {
                neighbour = neighbours.get(i);
                break;
            }
        }
        return neighbour;
    }

    @Override
    public void setFavoriteById(long id, boolean favorite) {
        for (int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i).getId() == id) {
                neighbours.get(i).setFavorite(favorite);
                break;
            }
        }
    }
}
