/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

/**
 *
 * @author Alejandro
 */
public class PreparedStatements {

    protected static final String SQL_INSERT_ORGANIZATION
            = "insert into organization (name, description, address, city, "
            + "zip, phone, email) values (?, ?, ?, ?, ?, ?, ?)";

    protected static final String SQL_DELETE_ORGANIZATION
            = "delete from organization where organizationId = ?";
    protected static final String SQL_DELETE_ORGANIZATION_FROM_SUPERORGANIZATIONS
            = "delete from superOrganizations where organizationId = ?";
    protected static final String SQL_UPDATE_ORGANIZATION
            = "update organization set name = ?, description = ?, address = ?, "
            + "city = ?, zip = ?, phone = ?, email = ? where organizationId =  ?";
    protected static final String SQL_SELECT_ORGANIZATION
            = "select * from organization where organizationId = ?";
    protected static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from organization";
    protected static final String SQL_SELECT_ORGANIZATIONS_BY_SUPER_ID
            = "select o.* "
            + "from `super` s "
            + "inner join superorganizations so on s.superId = so.superId "
            + "inner join organization o on so.organizationId = o.organizationId "
            + "where s.superId = ?";

    //Location Prepared Statements
    protected static final String SQL_INSERT_LOCATION
            = "insert into location (name, description, address, latitude, "
            + "longitude) values (?, ?, ?, ?, ?)";
    protected static final String SQL_DELETE_LOCATION
            = "delete from location where locationId = ?";
    protected static final String SQL_DELETE_SIGHTING_LOCATION
            = "delete from sighting where locationId = ?";
    protected static final String SQL_UPDATE_LOCATION
            = "update location set name = ?, description = ?, address = ?, "
            + "latitude = ?, longitude = ? where locationId =  ?";
    protected static final String SQL_SELECT_LOCATION
            = "select * from location where locationId = ?";
    protected static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from location";
    protected static final String SQL_SELECT_LOCATIONS_BY_SUPER_ID
            = "select l.* "
            + "from location l "
            + "inner join sighting si on l.locationId = si.locationId "
            + "inner join supersighting ss on si.sightingId = ss.sightingId "
            + "inner join `super` s on ss.superId = s.superId "
            + "where s.superId = ?";
    protected static final String SQL_SELECT_ALL_SUPER_BY_LOCATIONID
            = "select s.* "
            + "from super s "
            + "inner join supersighting ss on s.superId = ss.superId "
            + "inner join sighting si on ss.sightingId = si.sightingId "
            + "inner join location l on si.locationId = l.locationId "
            + "where l.locationId = ?";
    protected static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID
            = "select l.* from location l "
            + "inner join sighting si on l.locationId = si.locationId "
            + "where si.sightingId = ?";

    //SuperPower Prepared Statements
    protected static final String SQL_INSERT_SUPERPOWER
            = "insert into superPowers (name) "
            + "values (?)";
    protected static final String SQL_DELETE_SUPERPOWER
            = "delete from superPowers where superPowerId = ?";
    protected static final String SQL_DELETE_SUPERPOWER_SUPER/////// Is this safe?????
            = "delete from super where superPowerId = ?";
    protected static final String SQL_UPDATE_SUPERPOWER
            = "update superPowers set name = ? "
            + "where superPowerId =  ?";
    protected static final String SQL_SELECT_SUPERPOWER
            = "select * from superPowers where superPowerId = ?";
    protected static final String SQL_SELECT_ALL_SUPERPOWERS
            = "select * from superPowers";
    protected static final String SQL_SELECT_SP_BY_SUPERID
            = "select sp.* from superPowers sp "
            + "inner join `super` s on sp.superPowerId = s.superPowerId "
            + "where superId = ?";

    //Super Prepared Statements
    protected static final String SQL_INSERT_SUPER
            = "insert into `super` "
            + "(name, description, superPowerId) "
            + "values (?, ?, ?)";
    protected static final String SQL_INSERT_SUPERORGANIZATIONS
            = "insert into superOrganizations (organizationId, superId) values (?, ?)";
    protected static final String SQL_DELETE_SUPER
            = "delete from `super` where superId = ?";
    protected static final String SQL_DELETE_SUPER_FROM_SUPERORGANIZATIONS
            = "delete from superOrganizations where superId = ?";
    protected static final String SQL_DELETE_SUPER_FROM_SUPERSIGHTINGS
            = "delete from superSighting where superId = ?";
    protected static final String SQL_UPDATE_SUPER
            = "update `super` set name = ?, description = ?, "
            + "superPowerId = ? "
            + "where superId = ?";
    protected static final String SQL_SELECT_SUPER
            = "select * from `super` where superId = ?";
    protected static final String SQL_SELECT_ALL_SUPERS
            = "select * from `super`";
    protected static final String SQL_SELECT_ALL_SUPERS_BY_ORGANIZATION_ID
            = "select s.* from `super` s "
            + "inner join superorganizations so on s.superId = so.superId "
            + "inner join organization o on so.organizationId = o.organizationId "
            + "where o.organizationId = ?";
    protected static final String SQL_SELECT_SUPER_BY_SIGHTING_ID
            = "select s.* from `super` s "
            + "inner join supersighting ss on s.superId = ss.superId "
            + "inner join sighting si on ss.sightingId = si.sightingId "
            + "where si.sightingId = ?";
    
    
    //Sighting Prepared Statements
    protected static final String SQL_INSERT_SIGTHING
            = "insert into sighting "
            + "(locationId, sightingDate) "
            + "values (?, ?)";
    protected static final String SQL_INSERT_SUPERSIGHTING
            = "insert into superSighting (superId, sightingId) values (?, ?)";
    protected static final String SQL_DELETE_SIGTHING
            = "delete from sighting where sightingId = ?";
    protected static final String SQL_DELETE_SIGHTING_FROM_SUPERSIGHTING
            = "delete from superSighting where sightingId = ?";
    protected static final String SQL_UPDATE_SIGHTING
            = "update sighting set locationId = ?, sightingDate = ? "
            + "where sightingId = ?";
    protected static final String SQL_SELECT_SIGHTING
            = "select * from sighting where sightingId = ?";
    protected static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from sighting";
    protected static final String SQL_SELECT_ALL_SIGHTINGS_BY_SUPER_ID
            = "select si.* from sighting si "
            + "inner join supersighting ss on si.sightingId = ss.sightingId "
            + "inner join `super` s on ss.superId = s.superId "
            + "where s.superId = ?";
    protected static final String SQL_SELECT_ALL_SIGHTINGS_BY_LOCATION_ID
            = "select si.* from sighting si "
            + "inner join location l on si.locationId = l.locationId "
            + "where l.locationId = ?";
    protected static final String SQL_SELECT_SIGHTINGS_ON_PARTICULAR_DATE
            = "select si.* from sighting si "
            + "where si.sightingDate = ?";
    protected static final String SQL_SELECT_SUPERS_ON_PARTICULAR_DATE_AND_LOCATION
            = "select s.* from `super` s "
            + "inner join supersighting ss on s.superId = ss.superId "
            + "inner join sighting si on ss.sightingId = si.sightingId "
            + "where si.sightingDate = ? and si.locationId = ?";
    protected static final String SQL_SELECT_MOST_RECENT_SIGHTINGS
            = "select * from sighting s "
            + "order by s.sightingDate desc limit 10";
}
