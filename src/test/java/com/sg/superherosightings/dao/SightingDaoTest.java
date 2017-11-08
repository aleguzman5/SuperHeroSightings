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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Alejandro
 */
public class SightingDaoTest {

    SightingDao sightingDao;
    SuperDao superDao;
    LocationDao locationDao;
    OrganizationDao organizationDao;
    SuperPowerDao superPowerDao;

    public SightingDaoTest() {
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

    @Test
    public void testAddGetSighting() {
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

        Sighting si = new Sighting();//date-loc-super
        si.setDate(LocalDate.parse("2017-10-19",
                DateTimeFormatter.ISO_DATE));
        si.setLocation(loc);
        List<Super> supers = new ArrayList<>();
        supers.add(s);
        si.setSupers(supers);

        sightingDao.addSighting(si);

        Sighting fromDao = sightingDao.getSightingById(si.getSightingId());

        assertEquals(fromDao, si);

    }

    @Test
    //@Ignore
    public void testDeleteSighting() {
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

        Sighting si = new Sighting();//date-loc-super
        si.setDate(LocalDate.parse("2017-10-19",
                DateTimeFormatter.ISO_DATE));
        si.setLocation(loc);
        List<Super> supers = new ArrayList<>();
        supers.add(s);
        si.setSupers(supers);

        sightingDao.addSighting(si);

        Sighting fromDao = sightingDao.getSightingById(si.getSightingId());

        assertEquals(fromDao, si);
        sightingDao.deleteSighting(si.getSightingId());
        assertNull(sightingDao.getSightingById(si.getSightingId()));
    }

    @Test
    //@Ignore
    public void testUpdateSighting() {
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

        Sighting si = new Sighting();//date-loc-super
        si.setDate(LocalDate.parse("2017-10-19",
                DateTimeFormatter.ISO_DATE));
        si.setLocation(loc);
        List<Super> supers = new ArrayList<>();
        supers.add(s);
        si.setSupers(supers);

        sightingDao.addSighting(si);

        si.setDate(LocalDate.parse("2017-10-30",
                DateTimeFormatter.ISO_DATE));
        sightingDao.updateSighting(si);
        Sighting fromDao = sightingDao.getSightingById(si.getSightingId());
        assertEquals(fromDao.getDate(), si.getDate());
    }

    @Test
    public void testGetAllSightings() {
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
        si2.setLocation(loc);
        si2.setSupers(supers);
        
        sightingDao.addSighting(si);
        sightingDao.addSighting(si2);
        
        assertEquals(2, sightingDao.getAllSightings().size());
    }

    @Test
    public void testGetAllSightingByLocationId() {
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
        
        Sighting si3 = new Sighting();//date-loc-super
        si3.setDate(LocalDate.parse("2017-10-15",
                DateTimeFormatter.ISO_DATE));
        si3.setLocation(loc2);
        si3.setSupers(supers);
        
        sightingDao.addSighting(si);
        sightingDao.addSighting(si2);
        sightingDao.addSighting(si3);
        
        assertEquals(2, sightingDao.getAllSightingByLocationId(loc2.getLocationId()).size());
    }

    @Test
    public void testGetAllSightingBySuperId() {
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
        
        Sighting si3 = new Sighting();//date-loc-super
        si3.setDate(LocalDate.parse("2017-10-15",
                DateTimeFormatter.ISO_DATE));
        si3.setLocation(loc2);
        si3.setSupers(supers);
        
        sightingDao.addSighting(si);
        sightingDao.addSighting(si2);
        sightingDao.addSighting(si3);
        
        assertEquals(3, sightingDao.getAllSightingBySuperId(s.getSuperId()).size());
    }

    @Test
    public void testFindSightingsForParticularDate() {
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

        Sighting si3 = new Sighting();//date-loc-super
        si3.setDate(LocalDate.parse("2017-10-19",
                DateTimeFormatter.ISO_DATE));
        si3.setLocation(loc2);
        si3.setSupers(supers);

        sightingDao.addSighting(si);
        sightingDao.addSighting(si2);
        sightingDao.addSighting(si3);
        
        assertEquals(2, sightingDao.findSightingsForParticularDate(si3.getDate().toString()).size());
    }

    @Test
    public void testFindSupersForParticularLocationAndDate() {
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

        Sighting si3 = new Sighting();//date-loc-super
        si3.setDate(LocalDate.parse("2017-10-19",
                DateTimeFormatter.ISO_DATE));
        si3.setLocation(loc2);
        si3.setSupers(supers);

        sightingDao.addSighting(si);
        sightingDao.addSighting(si2);
        sightingDao.addSighting(si3);
        
        assertEquals(1, sightingDao.findSupersForParticularLocationAndDate(loc2, si.getDate().toString()).size());
    }
}
