/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings;

import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.SuperDao;
import com.sg.superherosightings.dao.SuperPowerDao;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Super;
import com.sg.superherosightings.model.SuperPower;
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
public class SuperController {

    SuperDao superDao;
    SuperPowerDao superPowerDao;
    OrganizationDao organizationDao;

    @Inject
    public SuperController(SuperDao superDao, SuperPowerDao superPowerDao, OrganizationDao organizationDao) {
        this.superDao = superDao;
        this.superPowerDao = superPowerDao;
        this.organizationDao = organizationDao;
    }

    @RequestMapping(value = "/createSuper", method = RequestMethod.POST)
    public String createSuper(HttpServletRequest request) {

        Super s = new Super();
        s.setName(request.getParameter("superName"));
        s.setDescription(request.getParameter("superDescription"));
        List<Organization> orgs = new ArrayList<>();
        String[] orgIds = request.getParameterValues("organizationId");

        int spId = Integer.parseInt(request.getParameter("superPowerId"));
        s.setSuperPower(superPowerDao.getSuperPowerById(spId));

        try {
            for (String currentOrgId : orgIds) {
                orgs.add(organizationDao.getOrganizationById(Integer.parseInt(currentOrgId)));
            }
            s.setOrganizations(orgs);
        } catch (Exception e) {
        }

        superDao.addSuper(s);

        return "redirect:super";
    }

    @RequestMapping(value = "/deleteSuper", method = RequestMethod.GET)
    public String deleteSuper(HttpServletRequest request) {
        int superId = Integer.parseInt(request.getParameter("superId"));
        superDao.deleteSuper(superId);
        return "redirect:super";
    }
    

    @RequestMapping(value = "displayEditSuper", method = RequestMethod.GET)
    public String displayEditSuperForm(HttpServletRequest request, Model model) {
        int superId = Integer.parseInt(request.getParameter("superId"));
        Super superP = superDao.getSuperById(superId);
        model.addAttribute("hero", superP);
        
        List<Organization> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        List<SuperPower> spList = superPowerDao.getAllSuperPowers();
        model.addAttribute("spList", spList);

        return "editSuper";
    }

    @RequestMapping(value = "editSuper", method = RequestMethod.POST)
    public String editSuper(@ModelAttribute("hero") Super superP, HttpServletRequest request, BindingResult result) {

        int spId = Integer.parseInt(request.getParameter("pwrList"));
        superP.setSuperPower(superPowerDao.getSuperPowerById(spId));
        List<Organization> orgs = new ArrayList<>();
        String[] orgIds = request.getParameterValues("orgList");
        
        try {
            for (String currentOrgId : orgIds) {
                orgs.add(organizationDao.getOrganizationById(Integer.parseInt(currentOrgId)));
            }
            superP.setOrganizations(orgs);
        } catch (Exception e) {
        }
        
        superDao.updateSuper(superP);
        return "redirect:super";
    }
}
