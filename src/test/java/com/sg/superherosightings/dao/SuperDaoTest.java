/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Super;
import com.sg.superherosightings.model.SuperPower;
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
public class SuperDaoTest {
    
    SuperDao superDao;
    LocationDao locationDao;
    OrganizationDao organizationDao;
    SuperPowerDao superPowerDao;
    
    public SuperDaoTest() {
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
        superDao = ctx.getBean("superDao", SuperDao.class);
        superPowerDao = ctx.getBean("superPowerDao", SuperPowerDao.class);
        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        
        List<Super> supers = superDao.getAllSupers();
        for (Super currentSuper : supers) {
            superDao.deleteSuper(currentSuper.getSuperId());
        }
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for (Organization currentOrganization : organizations) {
            organizationDao.deleteOrganization(currentOrganization.getOrganizationId());
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
     * Test of addSuper method, of class SuperDao.
     */
    @Test
    public void testAddGetSuper() {
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

        Super fromDao = superDao.getSuperById(s.getSuperId());
        
        assertEquals(fromDao, s);
    }

    /**
     * Test of deleteSuper method, of class SuperDao.
     */
    @Test
    public void testDeleteSuper() {
        
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

        Super fromDao = superDao.getSuperById(s.getSuperId());
        
        assertEquals(fromDao, s);
        superDao.deleteSuper(s.getSuperId());
        assertNull(superDao.getSuperById(s.getSuperId()));
    }

    /**
     * Test of updateSuper method, of class SuperDao.
     */
    @Test
    public void testGetUpdateSuper() {
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

        Super fromDao = superDao.getSuperById(s.getSuperId());
        
        assertEquals(fromDao, s);
        s.setName("Superman");
        superDao.updateSuper(s);
        assertEquals("Superman", superDao.getSuperById(s.getSuperId()).getName());
    }

    /**
     * Test of getAllSupers method, of class SuperDao.
     */
    @Test
    public void testGetAllSupers() {
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

        Super s2 = new Super();
        s2.setName("Superman");
        s2.setDescription("Stronger");
        s2.setSuperPower(sp);
        organizations.add(org);
        s2.setOrganizations(organizations);
        
        superDao.addSuper(s2);
        
        assertEquals(2, superDao.getAllSupers().size());
    }

    /**
     * Test of getAllSuperByLocationId method, of class SuperDao.
     */
    @Test
    public void testGetAllSuperByLocationId() {
    }

    /**
     * Test of getAllSuperByOrganization method, of class SuperDao.
     */
    @Test
    public void testGetAllSuperByOrganization() {
        
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
        
        Organization org2 = new Organization();
        org2.setName("A-1");
        org2.setDescription("We try...");
        org2.setAddress("Mystery Road");
        org2.setCity("Around the corner");
        org2.setZip(4321);
        org2.setPhone(555555555);

        organizationDao.addOrganization(org);
        organizationDao.addOrganization(org2);
        
        Super s = new Super();
        s.setName("Superman");
        s.setDescription("Stronger");
        s.setSuperPower(sp);
        List<Organization> organizations = new ArrayList<>();
        organizations.add(org);
        s.setOrganizations(organizations);
        
        superDao.addSuper(s);

        Super s2 = new Super();
        s2.setName("Batman");
        s2.setDescription("Even better");
        s2.setSuperPower(sp);
        List<Organization> organizations2 = new ArrayList<>();
        organizations2.add(org);
        organizations2.add(org2);
        s2.setOrganizations(organizations2);
        
        superDao.addSuper(s2);
        
        assertEquals(2, superDao.getAllSuperByOrganization(org.getOrganizationId()).size());
        assertEquals(1, superDao.getAllSuperByOrganization(org2.getOrganizationId()).size());
    }
}
