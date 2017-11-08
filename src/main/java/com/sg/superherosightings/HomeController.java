package com.sg.superherosightings;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dao.SuperDao;
import com.sg.superherosightings.dao.SuperPowerDao;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Super;
import com.sg.superherosightings.model.SuperPower;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    OrganizationDao organizationDao;
    SightingDao sightingDao;
    LocationDao locationDao;
    SuperDao superDao;
    SuperPowerDao spDao;

    @Inject
    public HomeController(SightingDao sightingDao, OrganizationDao organizationDao, LocationDao locationDao, SuperDao superDao, SuperPowerDao spDao) {
        this.sightingDao = sightingDao;
        this.organizationDao = organizationDao;
        this.locationDao = locationDao;
        this.superDao = superDao;
        this.spDao = spDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displaySightings(Model model) {
        List<Sighting> sightingList = sightingDao.findLastSightings();
        model.addAttribute("sightingList", sightingList);
        return "home";
    }

    @RequestMapping(value = "/organization", method = RequestMethod.GET)
    public String displayOrganization(Model model) {
        List<Organization> organizationList = organizationDao.getAllOrganizations();

        model.addAttribute("organizationList", organizationList);

        return "organization";
    }

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public String displayLocation(Model model) {
        List<Location> locationList = locationDao.getAllLocations();

        model.addAttribute("locationList", locationList);

        return "location";
    }

    @RequestMapping(value = "/super", method = RequestMethod.GET)
    public String displaySuper(Model model) {

        List<Super> superList = superDao.getAllSupers();
        List<SuperPower> spList = spDao.getAllSuperPowers();
        List<Organization> organizationList = organizationDao.getAllOrganizations();

        model.addAttribute("superList", superList);
        model.addAttribute("spList", spList);
        model.addAttribute("organizationList", organizationList);

        return "super";
    }
    
    @RequestMapping(value = "/sighting", method = RequestMethod.GET)
    public String displaySighting(Model model) {
        List<Sighting> sightingList = sightingDao.getAllSightings();
        List<Super> superList = superDao.getAllSupers();
        List<Location> locationList = locationDao.getAllLocations();

        model.addAttribute("sightingList", sightingList);
        model.addAttribute("superList", superList);
        model.addAttribute("locationList", locationList);

        return "sighting";
    }
}
