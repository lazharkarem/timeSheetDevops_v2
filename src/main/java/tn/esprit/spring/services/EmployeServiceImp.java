package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
//import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImp implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	//@Autowired
	//ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	
	private static final Logger logger = Logger.getLogger(EmployeServiceImp.class);
	public int ajouterEmploye(Employe employe) {
		try {
			logger.debug("Process ajout d'un employe");
			employeRepository.save(employe);
			logger.info("Employe ajouté avec success! ");
		}catch(Exception e) {
			logger.error("Erreur dans ajouterEmploye():"+ e);
		}finally {
			logger.info("l'ajout d'un employé est términé");
		}
		
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		try {
			logger.debug("Process de mise ajour de l'email d'un employe");
		Employe employe = employeRepository.findById(employeId).get();
		employe.setEmail(email);
		employeRepository.save(employe);
		logger.info("l'employé "+employe.getNom()+"a changé son email à"+email );
		}catch(Exception e) {
			logger.error("Erreur dansmettreAjourEmailByEmployeId:"+ e);
		}finally {
			logger.info("mettreAjourEmailByEmployeId est terminée");
		}

	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		Departement depManagedEntity = deptRepoistory.findById(depId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();

		if(depManagedEntity.getEmployes() == null){
			logger.info("verifier s'il y'a deja une liste d'employe et creation d'une nouvelle s'il n'ya pas");
			List<Employe> employes = new ArrayList<>();
			employes.add(employeManagedEntity);
			depManagedEntity.setEmployes(employes);
		}else{

			depManagedEntity.getEmployes().add(employeManagedEntity);

		}
		logger.info("affecterEmployeADepartement est términé");

	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		logger.debug("desaffecterEmployeDuDepartement est en cours");
		Departement dep = deptRepoistory.findById(depId).get();

		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				logger.info("l'id doit exister");
				dep.getEmployes().remove(index);
				break;//a revoir
			}
		}
	}

	//public int ajouterContrat(Contrat contrat) {
		//contratRepoistory.save(contrat);
		//return contrat.getReference();
	//}
	//public void affecterContratAEmploye(int contratId, int employeId) {
		//Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		//Employe employeManagedEntity = employeRepository.findById(employeId).get();

		//contratManagedEntity.setEmploye(employeManagedEntity);
		//contratRepoistory.save(contratManagedEntity);
		
	//}
	public String getEmployePrenomById(int employeId) {
		logger.debug("getEmployePrenomById est en cours");
		Employe employeManagedEntity = employeRepository.findById(employeId).get();
		logger.info("find l'mploye par son id ");
		return employeManagedEntity.getPrenom();
	}
	public void deleteEmployeById(int employeId)
	{
		logger.debug("deleteEmployeById est en cours");
		Employe employe = employeRepository.findById(employeId).get();
		logger.info("select employe a partir de son id");
		//Desaffecter l'employe de tous les departements
		//c'est le bout master qui permet de mettre a jour
		//la table d'association
//		for(Departement dep : employe.getDepartements()){
//			dep.getEmployes().remove(employe);
//		}

		employeRepository.delete(employe);
	}

	/**public void deleteContratById(int contratId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		contratRepoistory.delete(contratManagedEntity);

	}**/

	public int getNombreEmployeJPQL() {
		logger.debug("getNombreEmployeJPQL() est en cours");
		return employeRepository.countemp();
	}
	
	public List<String> getAllEmployeNamesJPQL() {
		logger.debug("getAllEmployeNamesJPQL() est en cours");
		return employeRepository.employeNames();

	}
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		List<Employe> employes=null;
		try {
			logger.debug("getAllEmployeByEntreprise est en cours");
		employes=employeRepository.getAllEmployeByEntreprisec(entreprise);
		logger.info("liste des employes selon entreprise");
		}catch (Exception e) {
			logger.error("Erreur dans getAllEmployeByEntreprise(): "+ e);
		}
		return employes;
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		logger.debug("mettreAjourEmailByEmployeIdJPQL est en cours");
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	/**public void deleteAllContratJPQL() {
         employeRepository.deleteAllContratJPQL();
	}**/
	
	/**public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}**/

	/**public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}**/
	
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		List<Timesheet> timesheets=null;
		try {
			logger.debug("getTimesheetsByMissionAndDate est en cours");
		 timesheets = timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
		 logger.info("affichage des listes des timesheet selon missions et date");
		}catch(Exception e) {logger.error("Erreur dans getTimesheetsByMissionAndDate(): "+ e);}
		return timesheets;
	}

	public List<Employe> getAllEmployes() {
				logger.debug("affichage de la liste de tout les employes ");
				return (List<Employe>) employeRepository.findAll();
	}
}