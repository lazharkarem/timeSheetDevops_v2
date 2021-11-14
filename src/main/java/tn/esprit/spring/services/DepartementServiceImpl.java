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



@Service
public class DepartementServiceImpl implements IDepartementService {


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



