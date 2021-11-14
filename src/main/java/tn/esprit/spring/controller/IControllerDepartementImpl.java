package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IDepartementService;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;


@Controller
public class IControllerDepartementImpl {

	@Autowired
	IEntrepriseService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	ITimesheetService itimesheetservice;
	@Autowired
	IDepartementService idepartementservice;


	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		idepartementservice.affecterDepartementAEntreprise(depId, entrepriseId);
	}
	
	
	public int ajouterDepartement(Departement dep) {
		return idepartementservice.ajouterDepartement(dep);
	}
	
	
	public void deleteDepartementById(int depId) {
		idepartementservice.deleteDepartementById(depId);

	}
}

