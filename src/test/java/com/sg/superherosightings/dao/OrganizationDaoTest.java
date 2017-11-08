/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Super;
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
public class OrganizationDaoTest {

    OrganizationDao organizationDao;
    SuperDao superDao;

    public OrganizationDaoTest() {
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

        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);
        superDao = ctx.getBean("superDao", SuperDao.class);

        List<Super> supers = superDao.getAllSupers();
        for (Super currentSuper : supers) {
            superDao.deleteSuper(currentSuper.getSuperId());
        }
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for (Organization currentOrganization : organizations) {
            organizationDao.deleteOrganization(currentOrganization.getOrganizationId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addOrganization method, of class OrganizationDao.
     */
    @Test
    public void testAddGetOrganization() {
        Organization org = new Organization();
        org.setName("Avengers");
        org.setDescription("The best");
        org.setAddress("123 Imaginary World");
        org.setCity("No Land");
        org.setZip(11111);
        org.setPhone(222222222);

        organizationDao.addOrganization(org);

        Organization fromDao = organizationDao.getOrganizationById(org.getOrganizationId());
        assertEquals(fromDao, org);
    }

    /**
     * Test of deleteOrganization method, of class OrganizationDao.
     */
    @Test
    public void testDeleteOrganization() {
        Organization org = new Organization();
        org.setName("Avengers");
        org.setDescription("The best");
        org.setAddress("123 Imaginary World");
        org.setCity("No Land");
        org.setZip(11111);
        org.setPhone(222222222);

        organizationDao.addOrganization(org);

        Organization fromDao = organizationDao.getOrganizationById(org.getOrganizationId());
        assertEquals(fromDao, org);

        organizationDao.deleteOrganization(org.getOrganizationId());
        assertNull(organizationDao.getOrganizationById(org.getOrganizationId()));
    }

    /**
     * Test of updateOrganization method, of class OrganizationDao.
     */
    @Test
    public void testUpdateOrganization() {

        Organization org = new Organization();
        org.setName("Avengers");
        org.setDescription("The best");
        org.setAddress("123 Imaginary World");
        org.setCity("No Land");
        org.setZip(11111);
        org.setPhone(222222222);

        organizationDao.addOrganization(org);
        
        Organization fromDao = organizationDao.getOrganizationById(org.getOrganizationId());
        assertEquals(fromDao, org);

        org.setName("Avengers Reloaded");
        org.setDescription("The best");
        org.setAddress("123 Imaginary World");
        org.setCity("No Land");
        org.setZip(11111);
        org.setPhone(222222222);
        organizationDao.updateOrganization(org);

        fromDao = organizationDao.getOrganizationById(org.getOrganizationId());
        assertEquals(fromDao.getName(), "Avengers Reloaded");

    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDao.
     */
    @Test
    public void testGetAllOrganizations() {
        Organization org = new Organization();
        org.setName("Avengers");
        org.setDescription("The best");
        org.setAddress("123 Imaginary World");
        org.setCity("No Land");
        org.setZip(11111);
        org.setPhone(222222222);

        organizationDao.addOrganization(org);
        
        Organization org2 = new Organization();
        org2.setName("A-1");
        org2.setDescription("The second best");
        org2.setAddress("We can't tell you");
        org2.setCity("Guess");
        org2.setZip(99999);
        org2.setPhone(66666666);

        organizationDao.addOrganization(org2);
        
        List<Organization> organizations = organizationDao.getAllOrganizations();
        assertEquals(2, organizations.size());
    }

    /**
     * Test of getAllOrganizationsBySuper method, of class OrganizationDao.
     */
    @Test
    public void testGetAllOrganizationsBySuper() {
    }
}
