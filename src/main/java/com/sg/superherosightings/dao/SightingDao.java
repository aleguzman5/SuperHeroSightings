/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Super;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public interface SightingDao {
    
    public void addSighting(Sighting sighting);

    public void deleteSighting(int sightingId);

    public void updateSighting(Sighting sighting);

    public Sighting getSightingById(int sightingId);

    public List<Sighting> getAllSightings();

    public List<Sighting> getAllSightingByLocationId(int locationId);
    
    public List<Sighting> getAllSightingBySuperId(int superId);

    public List<Sighting> findSightingsForParticularDate(String date);
    
    public List<Super> findSupersForParticularLocationAndDate(Location location, String date);
    
    public List<Sighting> findLastSightings();
}
