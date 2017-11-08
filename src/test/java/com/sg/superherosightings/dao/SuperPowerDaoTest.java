/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

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
public class SuperPowerDaoTest {
    
    SuperPowerDao superPowerDao;
    SuperDao superDao;
    OrganizationDao organizationDao;
    
    public SuperPowerDaoTest() {
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

        superPowerDao = ctx.getBean("superPowerDao", SuperPowerDao.class);
        superDao = ctx.getBean("superDao", SuperDao.class);
        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);

        List<Super> supers = superDao.getAllSupers();
        for (Super currentSuper : supers) {
            superDao.deleteSuper(currentSuper.getSuperId());
        }
        List<SuperPower> superPowers = superPowerDao.getAllSuperPowers();
        for (SuperPower currentSP : superPowers) {
            superPowerDao.deleteSuperPower(currentSP.getSuperPowerId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addSuperPower method, of class SuperPowerDao.
     */
    @Test
    public void testAddGetSuperPower() {
        SuperPower sp = new SuperPower();
        sp.setName("Fly");
        
        superPowerDao.addSuperPower(sp);
        
        SuperPower fromDao = superPowerDao.getSuperPowerById(sp.getSuperPowerId());
        assertEquals(fromDao, sp);
    }

    /**
     * Test of deleteSuperPower method, of class SuperPowerDao.
     */
    @Test
    public void testDeleteSuperPower() {
        SuperPower sp = new SuperPower();
        sp.setName("Fly");
        
        superPowerDao.addSuperPower(sp);
        
        SuperPower fromDao = superPowerDao.getSuperPowerById(sp.getSuperPowerId());
        assertEquals(fromDao, sp);
        
        superPowerDao.deleteSuperPower(sp.getSuperPowerId());
        assertNull(superPowerDao.getSuperPowerById(sp.getSuperPowerId()));
    }

    /**
     * Test of updateSuperPower method, of class SuperPowerDao.
     */
    @Test
    public void testUpdateSuperPower() {
        SuperPower sp = new SuperPower();
        sp.setName("Fly");
        
        superPowerDao.addSuperPower(sp);
        
        SuperPower fromDao = superPowerDao.getSuperPowerById(sp.getSuperPowerId());
        assertEquals(fromDao, sp);
        
        fromDao.setName("Teleport");
        superPowerDao.updateSuperPower(fromDao);
        assertEquals("Teleport", superPowerDao.getSuperPowerById(sp.getSuperPowerId()).getName());
    }

    /**
     * Test of getAllSuperPowers method, of class SuperPowerDao.
     */
    @Test
    public void testGetAllSuperPowers() {
        SuperPower sp = new SuperPower();
        sp.setName("Fly");
        superPowerDao.addSuperPower(sp);
        
        SuperPower sp2 = new SuperPower();
        sp2.setName("Fly");
        superPowerDao.addSuperPower(sp2);
        
        List<SuperPower> superPowers = superPowerDao.getAllSuperPowers();
        assertEquals(2, superPowers.size());
    }

    /**
     * Test of getSuperPowerBySuperId method, of class SuperPowerDao.
     */
    @Test
    public void testGetSuperPowerBySuperId() {
        
        SuperPower sp = new SuperPower();
        sp.setName("Fly");
        SuperPower sp2 = new SuperPower();
        sp2.setName("Gadgets");

        superPowerDao.addSuperPower(sp);
        superPowerDao.addSuperPower(sp2);

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
        s.setSuperPower(sp2);
        List<Organization> organizations = new ArrayList<>();
        organizations.add(org);
        s.setOrganizations(organizations);

        Super s2 = new Super();
        s2.setName("Superman");
        s2.setDescription("Stronger");
        s2.setSuperPower(sp);
        s2.setOrganizations(organizations);
        
        superDao.addSuper(s);
        superDao.addSuper(s2);
        
        SuperPower sP = new SuperPower();
        sP = superPowerDao.getSuperPowerBySuperId(s2.getSuperId());
        assertEquals("Fly", sP.getName());
    }
}
