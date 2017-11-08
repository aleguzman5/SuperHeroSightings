/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dao.SuperDao;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Super;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Alejandro
 */
@Controller
public class SightingController {
    
    SuperDao superDao;
    LocationDao locationDao;
    SightingDao sightingDao;

    @Inject
    public SightingController(SuperDao superDao, LocationDao locationDao, SightingDao sightingDao) {
        this.superDao = superDao;
        this.locationDao = locationDao;
        this.sightingDao = sightingDao;
    }

    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(HttpServletRequest request) {

        Sighting s = new Sighting();
        List<Super> superList = new ArrayList<>();
        String[]superIds = request.getParameterValues("superIds");
        
        for(String currentSuperId : superIds) {
            superList.add(superDao.getSuperById(Integer.parseInt(currentSuperId)));
        }
        s.setSupers(superList);
        
        s.setDate(LocalDate.parse(request.getParameter("sightingDate")));
        
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        s.setLocation(locationDao.getLocationById(locationId));
        
        sightingDao.addSighting(s);
        return "redirect:sighting";
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        int sightingId = Integer.parseInt(request.getParameter("sightingId"));
        sightingDao.deleteSighting(sightingId);
        return "redirect:sighting";
    }
    

    @RequestMapping(value = "displayEditSighting", method = RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) {
        int sightingId = Integer.parseInt(request.getParameter("sightingId"));
        Sighting sighting = sightingDao.getSightingById(sightingId);
        model.addAttribute("sighting", sighting);
        
        List<Super> supers = superDao.getAllSupers();
        model.addAttribute("superList", supers);
        List<Location> locList = locationDao.getAllLocations();
        model.addAttribute("locList", locList);

        return "editSighting";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(@ModelAttribute("sighting") Sighting sighting, HttpServletRequest request, BindingResult result) {

        int locationId = Integer.parseInt(request.getParameter("locationList"));
        sighting.setLocation(locationDao.getLocationById(locationId));
        List<Super> supers = new ArrayList<>();
        String[] superIds = request.getParameterValues("superList");
        
            for (String currentSuperId : superIds) {
                supers.add(superDao.getSuperById(Integer.parseInt(currentSuperId)));
            }
            sighting.setSupers(supers);
            
        sightingDao.updateSighting(sighting);
        return "redirect:sighting";
    }
}
