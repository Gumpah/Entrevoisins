package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
        assertFalse(expectedNeighbours.isEmpty());
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void setFavoriteNeighbourWithSuccess() {
        Neighbour neighbourToSetFavorite = service.getNeighbours().get(0);
        neighbourToSetFavorite.setFavorite(true);
        assertTrue(neighbourToSetFavorite.getFavorite());
        neighbourToSetFavorite.setFavorite(false);
        assertFalse(neighbourToSetFavorite.getFavorite());
    }

    @Test
    public void getFavoriteNeighboursListWithSuccess() {
        List<Neighbour> favoriteNeighboursExpected = Arrays.asList(service.getNeighbours().get(0),service.getNeighbours().get(1),service.getNeighbours().get(2));
        service.getNeighbours().get(0).setFavorite(true);
        service.getNeighbours().get(1).setFavorite(true);
        service.getNeighbours().get(2).setFavorite(true);
        List<Neighbour> favoriteNeighboursActual = service.getFavorites();
        assertThat(favoriteNeighboursActual, IsIterableContainingInAnyOrder.containsInAnyOrder(favoriteNeighboursExpected.toArray()));

    }

    @Test
    public void getNeighboursAttributesWithSuccess() {
        long idExpected = System.currentTimeMillis();
        String nameExpected = "Bertrand";
        String avatarUrlExpected = "https://i.pravatar.cc/150?u=" + System.currentTimeMillis();
        String addressExpected = "45 Avenue des Tuileries";
        String phoneNumberExpected = "+33 6 55 59 70 78";
        String aboutMeExpected = "Lorem ipsum dolor sit amet. Non quas repellendus a dicta blanditiis hic optio maxime vel asperiores aspernatur ea sint amet.";
        boolean favoriteExpected = false;
        Neighbour neighbour = new Neighbour(idExpected,nameExpected,avatarUrlExpected,addressExpected,phoneNumberExpected,aboutMeExpected,favoriteExpected);
        service.createNeighbour(neighbour);
        assertEquals(idExpected, neighbour.getId());
        assertEquals(nameExpected, neighbour.getName());
        assertEquals(avatarUrlExpected, neighbour.getAvatarUrl());
        assertEquals(addressExpected, neighbour.getAddress());
        assertEquals(phoneNumberExpected, neighbour.getPhoneNumber());
        assertEquals(aboutMeExpected, neighbour.getAboutMe());
        assertEquals(favoriteExpected, neighbour.getFavorite());
    }

    
}
