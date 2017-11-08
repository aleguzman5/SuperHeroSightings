/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.model.Location;
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
public class LocationController {

    LocationDao locationDao;

    @Inject
    public LocationController(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(HttpServletRequest request) {

        Location location = new Location();
        location.setName(request.getParameter("locationName"));
        location.setDescription(request.getParameter("locationDescription"));
        location.setAddress(request.getParameter("locationAddress"));
        location.setLatitude(Double.parseDouble(request.getParameter("locationLatitude")));
        location.setLongitude(Double.parseDouble(request.getParameter("locationLongitude")));

        locationDao.addLocation(location);

        return "redirect:location";
    }

    @RequestMapping(value = "displayEditLocation", method = RequestMethod.GET)
    public String displayEditContactForm(HttpServletRequest request, Model model) {
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        Location location = locationDao.getLocationById(locationId);
        model.addAttribute("location", location);

        return "editLocation";
    }

    @RequestMapping(value = "editLocation", method = RequestMethod.POST)
    public String editLocation(@ModelAttribute("location") Location location, BindingResult result) {

        locationDao.updateLocation(location);
        return "redirect:location";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        locationDao.deleteLocation(locationId);
        return "redirect:location";
    }

    @RequestMapping(value = "/displayLocationDetails", method = RequestMethod.GET)
    public String displayLoctaionDetails(HttpServletRequest request, Model model) {
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        
        Location location = locationDao.getLocationById(locationId);

        model.addAttribute("location", location);

        return "locationDetails";
    }
}
