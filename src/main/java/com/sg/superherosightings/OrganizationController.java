/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings;

import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.model.Organization;
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
public class OrganizationController {

    OrganizationDao organizationDao;

    @Inject
    public OrganizationController(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public String createOrganization(HttpServletRequest request) {

        Organization org = new Organization();
        org.setName(request.getParameter("organizationName"));
        org.setDescription(request.getParameter("organizationDescription"));
        org.setAddress(request.getParameter("organizationAddress"));
        org.setCity(request.getParameter("organizationCity"));
        try {
            org.setZip(Integer.parseInt(request.getParameter("organizationZip")));
        } catch (Exception e) {
            org.setZip(121212);
        }
        try {
            org.setPhone(Long.parseLong(request.getParameter("organizationPhone")));
        } catch (Exception e) {
            org.setPhone(111111111);
        }
        org.setEmail(request.getParameter("organizationEmail"));

        organizationDao.addOrganization(org);
        return "redirect:organization";
    }

    @RequestMapping(value = "displayEditOrganization", method = RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) {
        int orgId = Integer.parseInt(request.getParameter("organizationId"));
        Organization org = organizationDao.getOrganizationById(orgId);
        model.addAttribute("organization", org);

        return "editOrganization";
    }

    @RequestMapping(value = "editOrganization", method = RequestMethod.POST)
    public String editOrganization(@ModelAttribute("organization") Organization organization, BindingResult result) {

        organizationDao.updateOrganization(organization);
        return "redirect:organization";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        int organizationId = Integer.parseInt(request.getParameter("organizationId"));
        organizationDao.deleteOrganization(organizationId);
        return "redirect:organization";
    }

    @RequestMapping(value = "/displayOrganizationDetails", method = RequestMethod.GET)
    public String displayLoctaionDetails(HttpServletRequest request, Model model) {
        int organizationId = Integer.parseInt(request.getParameter("organizationId"));

        Organization org = organizationDao.getOrganizationById(organizationId);

        model.addAttribute("organization", org);

        return "organizationDetails";
    }
}
