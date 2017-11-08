/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.LocationDaoImpl.LocationMapper;
import com.sg.superherosightings.dao.SuperDaoImpl.SuperMapper;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Super;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alejandro
 */
public class SightingDaoImpl implements SightingDao {

    private JdbcTemplate jdbcTemplate;
    SuperDao superDao;

    @Inject
    public SightingDaoImpl(SuperDao superDao) {
        this.superDao = superDao;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void insertSightingSupers(Sighting sighting) {
        final int sightingId = sighting.getSightingId();
        final List<Super> supers = sighting.getSupers();

        for (Super currentSuper : supers) {
            jdbcTemplate.update(PreparedStatements.SQL_INSERT_SUPERSIGHTING,
                    currentSuper.getSuperId(),
                    sightingId);
        }
    }

    private List<Super> findSupersForSighting(Sighting sighting) {
        List<Super> superList = jdbcTemplate.query(PreparedStatements.SQL_SELECT_SUPER_BY_SIGHTING_ID,
                new SuperMapper(),
                sighting.getSightingId());
        return superDao.associateSuperPowerAndOrganizationsWithSuper(superList);
    }

    private Location findLocationForSighting(Sighting sighting) {
        return jdbcTemplate.queryForObject(PreparedStatements.SQL_SELECT_LOCATION_BY_SIGHTING_ID,
                new LocationMapper(),
                sighting.getSightingId());
    }

    private List<Sighting> associateLocationAndSupersWithSighting(List<Sighting> sightingList) {
        for (Sighting currentSighting : sightingList) {
            currentSighting.setSupers(findSupersForSighting(currentSighting));
            currentSighting.setLocation(findLocationForSighting(currentSighting));
        }
        return sightingList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(PreparedStatements.SQL_INSERT_SIGTHING,
                sighting.getLocation().getLocationId(),
                sighting.getDate().toString());
        sighting.setSightingId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
        insertSightingSupers(sighting);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSighting(int sightingId) {
        jdbcTemplate.update(PreparedStatements.SQL_DELETE_SIGHTING_FROM_SUPERSIGHTING, sightingId);

        jdbcTemplate.update(PreparedStatements.SQL_DELETE_SIGTHING, sightingId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(PreparedStatements.SQL_UPDATE_SIGHTING,
                sighting.getLocation().getLocationId(),
                sighting.getDate().toString(),
                sighting.getSightingId());
        jdbcTemplate.update(PreparedStatements.SQL_DELETE_SIGHTING_FROM_SUPERSIGHTING, sighting.getSightingId());
        insertSightingSupers(sighting);

    }

    @Override
    public Sighting getSightingById(int sightingId) {
        try {
            Sighting sighting = jdbcTemplate.queryForObject(PreparedStatements.SQL_SELECT_SIGHTING,
                    new SightingMapper(),
                    sightingId);
            sighting.setSupers(findSupersForSighting(sighting));
            sighting.setLocation(findLocationForSighting(sighting));
            return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightingList = jdbcTemplate.query(PreparedStatements.SQL_SELECT_ALL_SIGHTINGS,
                new SightingMapper());
        return associateLocationAndSupersWithSighting(sightingList);
    }

    @Override
    public List<Sighting> getAllSightingByLocationId(int locationId) {
        List<Sighting> sightingList = jdbcTemplate.query(PreparedStatements.SQL_SELECT_ALL_SIGHTINGS_BY_LOCATION_ID,
                new SightingMapper(),
                locationId);
        return associateLocationAndSupersWithSighting(sightingList);
    }

    @Override
    public List<Sighting> getAllSightingBySuperId(int superId) {
        List<Sighting> sightingList = jdbcTemplate.query(PreparedStatements.SQL_SELECT_ALL_SIGHTINGS_BY_SUPER_ID,
                new SightingMapper(),
                superId);
        return associateLocationAndSupersWithSighting(sightingList);
    }

    @Override
    public List<Sighting> findSightingsForParticularDate(String date) {
        List<Sighting> sightingList = jdbcTemplate.query(PreparedStatements.SQL_SELECT_SIGHTINGS_ON_PARTICULAR_DATE,
                new SightingMapper(),
                date);
        return associateLocationAndSupersWithSighting(sightingList);
    }

    @Override
    public List<Super> findSupersForParticularLocationAndDate(Location location, String date) {
        List<Super> supers = jdbcTemplate.query(PreparedStatements.SQL_SELECT_SUPERS_ON_PARTICULAR_DATE_AND_LOCATION,
                new SuperMapper(),
                date, location.getLocationId());
        return superDao.associateSuperPowerAndOrganizationsWithSuper(supers);
    }

    @Override
    public List<Sighting> findLastSightings() {
        List<Sighting> sightingList = jdbcTemplate.query(PreparedStatements.SQL_SELECT_MOST_RECENT_SIGHTINGS,
                new SightingMapper());
        return associateLocationAndSupersWithSighting(sightingList);
    }

    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("sightingId"));
            sighting.setDate(rs.getTimestamp("sightingDate").toLocalDateTime().toLocalDate());
            return sighting;
        }

    }
}
