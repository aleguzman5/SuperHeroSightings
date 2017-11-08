/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Super;
import com.sg.superherosightings.model.SuperPower;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Alejandro
 */
public class LocationDaoTest {

    LocationDao locationDao;
    OrganizationDao organizationDao;
    SuperDao superDao;
    SightingDao sightingDao;
    SuperPowerDao superPowerDao;

    public LocationDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);
        superDao = ctx.getBean("superDao", SuperDao.class);
        superPowerDao = ctx.getBean("superPowerDao", SuperPowerDao.class);
        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);

        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            sightingDao.deleteSighting(currentSighting.getSightingId());
        }
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for (Organization currentOrganization : organizations) {
            organizationDao.deleteOrganization(currentOrganization.getOrganizationId());
        }
        List<Super> supers = superDao.getAllSupers();
        for (Super currentSuper : supers) {
            superDao.deleteSuper(currentSuper.getSuperId());
        }
        List<SuperPower> superPowers = superPowerDao.getAllSuperPowers();
        for (SuperPower currentSP : superPowers) {
            superPowerDao.deleteSuperPower(currentSP.getSuperPowerId());
        }
        List<Location> locations = locationDao.getAllLocations();
        for (Location currentLocation : locations) {
            locationDao.deleteLocation(currentLocation.getLocationId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addLocation method, of class LocationDao.
     */
    @Test
    public void testAddGetLocation() {
        Location loc = new Location();
        loc.setName("The Software Guild");
        loc.setDescription("Coding Camp");
        loc.setAddress("5432 Main Ave.");
        loc.setLatitude(44.032145);
        loc.setLongitude(-98.432156);

        locationDao.addLocation(loc);

        Location fromDao = locationDao.getLocationById(loc.getLocationId());
        assertEquals(fromDao, loc);
    }

    /**
     * Test of deleteLocation method, of class LocationDao.
     */
    @Test
    public void testDeleteLocation() {
        Location loc = new Location();
        loc.setName("The Software Guild");
        loc.setDescription("Coding Camp");
        loc.setAddress("5432 Main Ave.");
        loc.setLatitude(44.032145);
        loc.setLongitude(-98.432156);

        locationDao.addLocation(loc);

        Location fromDao = locationDao.getLocationById(loc.getLocationId());
        assertEquals(fromDao, loc);

        locationDao.deleteLocation(loc.getLocationId());
        assertNull(locationDao.getLocationById(loc.getLocationId()));
    }

    /**
     * Test of updateLocation method, of class LocationDao.
     */
    @Test
    public void testUpdateLocation() {
        Location loc = new Location();
        loc.setName("The Software Guild");
        loc.setDescription("Coding Camp");
        loc.setAddress("5432 Main Ave.");
        loc.setLatitude(44.032145);
        loc.setLongitude(-98.432156);

        locationDao.addLocation(loc);

        Location fromDao = locationDao.getLocationById(loc.getLocationId());
        assertEquals(fromDao.getName(), "The Software Guild");

        fromDao.setName("The Software Guild Minneapolis");
        fromDao.setDescription("Multi-Building Campus");
        fromDao.setAddress("526 South Main Street Suite, Minnesota");
        fromDao.setLatitude(41.071827);
        fromDao.setLongitude(-81.527073);
        locationDao.updateLocation(fromDao);

        Location update = locationDao.getLocationById(loc.getLocationId());
        assertEquals(update.getName(), "The Software Guild Minneapolis");
    }

    /**
     * Test of getAllLocations method, of class LocationDao.
     */
    @Test
    public void testGetAllLocations() {

        Location loc = new Location();
        loc.setName("Metropolis");
        loc.setDescription("City of Superman");
        loc.setAddress("123 Nowhere Drive");
        loc.setLatitude(92.9847484);
        loc.setLongitude(-61.213476);
        locationDao.addLocation(loc);

        Location loc2 = new Location();
        loc2.setName("Gotham");
        loc2.setDescription("Batman's City");
        loc2.setAddress("9999 Gotham St.");
        loc2.setLatitude(87.9847484);
        loc2.setLongitude(-23.9898989);
        locationDao.addLocation(loc2);

        List<Location> locations = locationDao.getAllLocations();
        assertEquals(locations.size(), 2);
    }

    /**
     * Test of getAllLocationsBySuperId method, of class LocationDao.
     */
    @Test
    public void testGetAllLocationsBySuperId() {
        SuperPower sp = new SuperPower();
        sp.setName("Fly");

        superPowerDao.addSuperPower(sp);

        Organization org = new Organization();
        org.setName("Avengers");
        org.setDescription("The best");
        org.setAddress("123 Imaginary World");
        org.setCity("No Land");
        org.setZip(11111);
        org.setPhone(222222222);

        organizationDao.addOrganization(org);

        Super s = new Super();
        s.setName("Batman");
        s.setDescription("Even better");
        s.setSuperPower(sp);
        List<Organization> organizations = new ArrayList<>();
        organizations.add(org);
        s.setOrganizations(organizations);

        superDao.addSuper(s);

        Location loc = new Location();
        loc.setName("The Software Guild");
        loc.setDescription("Coding Camp");
        loc.setAddress("5432 Main Ave.");
        loc.setLatitude(44.032145);
        loc.setLongitude(-98.432156);
        locationDao.addLocation(loc);

        Location loc2 = new Location();
        loc2.setName("Gotham");
        loc2.setDescription("Batman's City");
        loc2.setAddress("9999 Gotham St.");
        loc2.setLatitude(87.9847484);
        loc2.setLongitude(-23.9898989);
        locationDao.addLocation(loc2);

        Sighting si = new Sighting();//date-loc-super
        si.setDate(LocalDate.parse("2017-10-19",
                DateTimeFormatter.ISO_DATE));
        si.setLocation(loc);
        List<Super> supers = new ArrayList<>();
        supers.add(s);
        si.setSupers(supers);
        
        Sighting si2 = new Sighting();//date-loc-super
        si2.setDate(LocalDate.parse("2017-10-23",
                DateTimeFormatter.ISO_DATE));
        si2.setLocation(loc2);
        si2.setSupers(supers);
        
        sightingDao.addSighting(si);
        sightingDao.addSighting(si2);
        
        assertEquals(2, locationDao.getAllLocationsBySuperId(s.getSuperId()).size());
    }

}
