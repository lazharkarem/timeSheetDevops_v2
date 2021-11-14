package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class DepartementServiceImpl implements IDepartementService {
	Logger logger = LoggerFactory.getLogger(DepartementServiceImpl.class);



@Autowired
DepartementRepository deptRepoistory;
@Autowired
EntrepriseRepository entrepriseRepoistory;

//private static final Logger l = Logger.getLogger(DepartementServiceImpl.class);



public int ajouterDepartement(Departement dep) {
	//l.debug("L'ajout d'un département");
	deptRepoistory.save(dep);
	//l.info("Le département"+dep.getName()+ "est ajouté avec succés");
	return dep.getId();
}

public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
	//Le bout Master de cette relation N:1 est departement  
			//donc il faut rajouter l'entreprise a departement 
			// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
			//Rappel : la classe qui contient mappedBy represente le bout Slave
			//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
	try{		
			//l.debug("L'affectation d'un département a une entreprise");

			Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
			Departement depManagedEntity = deptRepoistory.findById(depId).get();
			
			depManagedEntity.setEntreprise(entrepriseManagedEntity);
			deptRepoistory.save(depManagedEntity);}
			//l.info("L'affectation du depratement"+depManagedEntity.getName()+"à l'entreprise"+entrepriseManagedEntity.getName()+"s'est fait avec succées");}
			catch (Exception e){
				//l.error("Erreur"+e);
			}
}







public void delete() {
try {
	logger.info("delete departement");
	logger.debug("Je vais supprimer un contrat.");
	entrepriseRepoistory.deleteAll();
	logger.debug("delete finish");
	logger.info("Contract deleted Succefully ! ");			
}catch(Exception e) {
	logger.error(e.toString());
}
}








public List<Departement>  getAllDep() {
List<Departement> departement = new ArrayList<>();

try {
	logger.info("getting Departement");
	logger.debug("Je vais afficher les Departement.");
	departement = (List<Departement>) deptRepoistory.findAll();
	logger.debug("Je viens de finir l'opération X.");
	logger.info("Out getAllDep() without errors.");			

}
catch (Exception e) { 
		logger.error(e.toString());
		}

return departement ;	
}

@Transactional
public void deleteDepartementById(int depId) {
//l.debug("La suppression d'un département ");
try{
if(deptRepoistory.findById(depId) != null){
	deptRepoistory.delete(deptRepoistory.findById(depId).orElse(null));
	//l.debug("La suppression fait avec succes ");
}
}
catch(Exception e){
	//l.error("Erreur"+e);
}
}
}



