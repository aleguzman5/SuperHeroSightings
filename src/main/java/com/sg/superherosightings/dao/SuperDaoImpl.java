/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.OrganizationDaoImpl.OrganizationMapper;
import com.sg.superherosightings.dao.SuperPowerDaoImpl.SuperPowerMapper;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Super;
import com.sg.superherosightings.model.SuperPower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alejandro
 */
public class SuperDaoImpl implements SuperDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertSuperOrganizations(Super superh) {
        final int superId = superh.getSuperId();
        final List<Organization> organizations = superh.getOrganizations();
        try {
            for (Organization currentOrg : organizations) {
                jdbcTemplate.update(PreparedStatements.SQL_INSERT_SUPERORGANIZATIONS,
                        currentOrg.getOrganizationId(),
                        superId);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public List<Organization> findOrganizationsForSuper(Super superh) {
        return jdbcTemplate.query(PreparedStatements.SQL_SELECT_ORGANIZATIONS_BY_SUPER_ID,
                new OrganizationMapper(),
                superh.getSuperId());
    }

    @Override
    public SuperPower findSuperPowerForSuper(Super superh) {
        return jdbcTemplate.queryForObject(PreparedStatements.SQL_SELECT_SP_BY_SUPERID,
                new SuperPowerMapper(),
                superh.getSuperId());
    }

    @Override
    public List<Super> associateSuperPowerAndOrganizationsWithSuper(List<Super> superList) {
        for (Super currentSuper : superList) {
            currentSuper.setOrganizations(findOrganizationsForSuper(currentSuper));
            currentSuper.setSuperPower(findSuperPowerForSuper(currentSuper));
        }
        return superList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuper(Super superP) {
        jdbcTemplate.update(PreparedStatements.SQL_INSERT_SUPER,
                superP.getName(),
                superP.getDescription(),
                superP.getSuperPower().getSuperPowerId());
        superP.setSuperId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
        insertSuperOrganizations(superP);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSuper(int superId) {
        jdbcTemplate.update(PreparedStatements.SQL_DELETE_SUPER_FROM_SUPERSIGHTINGS, superId);

        jdbcTemplate.update(PreparedStatements.SQL_DELETE_SUPER_FROM_SUPERORGANIZATIONS, superId);

        jdbcTemplate.update(PreparedStatements.SQL_DELETE_SUPER, superId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSuper(Super superP) {
        jdbcTemplate.update(PreparedStatements.SQL_UPDATE_SUPER,
                superP.getName(),
                superP.getDescription(),
                superP.getSuperPower().getSuperPowerId(),
                superP.getSuperId());
        jdbcTemplate.update(PreparedStatements.SQL_DELETE_SUPER_FROM_SUPERORGANIZATIONS, superP.getSuperId());
        insertSuperOrganizations(superP);
    }

    @Override
    public Super getSuperById(int superId) {
        try {
            Super superh = jdbcTemplate.queryForObject(PreparedStatements.SQL_SELECT_SUPER,
                    new SuperMapper(),
                    superId);
            superh.setOrganizations(findOrganizationsForSuper(superh));
            superh.setSuperPower(findSuperPowerForSuper(superh));
            return superh;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Super> getAllSupers() {
        List<Super> superList = jdbcTemplate.query(PreparedStatements.SQL_SELECT_ALL_SUPERS,
                new SuperMapper());
        return associateSuperPowerAndOrganizationsWithSuper(superList);
    }

    @Override
    public List<Super> getAllSuperByLocationId(int locationId) {
        List<Super> superList = jdbcTemplate.query(PreparedStatements.SQL_SELECT_ALL_SUPER_BY_LOCATIONID,
                new SuperMapper(),
                locationId);
        return associateSuperPowerAndOrganizationsWithSuper(superList);
    }

    @Override
    public List<Super> getAllSuperByOrganization(int organizationId) {
        List<Super> superList = jdbcTemplate.query(PreparedStatements.SQL_SELECT_ALL_SUPERS_BY_ORGANIZATION_ID,
                new SuperMapper(),
                organizationId);
        return associateSuperPowerAndOrganizationsWithSuper(superList);
    }

    public static final class SuperMapper implements RowMapper<Super> {

        @Override
        public Super mapRow(ResultSet rs, int i) throws SQLException {
            Super superh = new Super();
            superh.setSuperId(rs.getInt("superId"));
            superh.setName(rs.getString("name"));
            superh.setDescription(rs.getString("description"));
            return superh;
        }

    }
}
