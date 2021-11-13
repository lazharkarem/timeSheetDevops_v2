package tn.esprit.spring.services; 

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import lombok.NonNull;
import tn.esprit.spring.entities.Departement;

import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
//import org.apache.log4j.Logger;


@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	//private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class);
	//adding Loggers 
	
	public int ajouterEntreprise(Entreprise entreprise) {
		//l.debug("Method ajouterEntreprise");
		//entrepriseRepoistory.save(entreprise);
		//l.info("entreprise ajoutée avec succés et son  id egal = "+entreprise.getId());
		return entreprise.getId();
	}

	
	
	
	//adding exeptions
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		//l.debug("method getAllDepartementsNamesByEntreprise ");
		List<String> depNames = new ArrayList<>();
		try {
			Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
			
			if(entrepriseManagedEntity!=null && entrepriseManagedEntity.getDepartements()!=null){
			for(Departement dep : entrepriseManagedEntity.getDepartements()){
				depNames.add(dep.getName());
			}
			//l.debug("methode  fini avec succés ");
			return depNames;
			}
			else {
				//l.error("erreur method : " );
				return depNames;
			}
		} catch (Exception e) {
			//l.error("erreur method : " +e);
			return depNames;
		}
	}

	@Transactional
	public int deleteEntrepriseById(  int entrepriseId) {
		//l.debug("methode deleteEntrepriseById ");
		
		try {
			if(entrepriseRepoistory.findById( entrepriseId).orElse(null) != null){
				
			entrepriseRepoistory.delete( entrepriseRepoistory.findById( entrepriseId).orElse(null));
			//l.debug("deleteEntrepriseById fini avec succes ");
			return 0;
			}
			else {
				//l.error("erreur methode deleteEntrepriseById : " );
				return -1;
			}
		} catch (Exception e) {
			//l.error("erreur methode deleteEntrepriseById : " +e);
			return -1;
		}		
	}


	


	public Entreprise getEntrepriseById( int entrepriseId) {
    //l.debug("methode getEntrepriseById ");
		
		
		try {
			Entreprise et= entrepriseRepoistory.findById(entrepriseId).orElse(null);
			//l.debug("getEntrepriseById fini avec succes ");
			return et;
		} catch (Exception e) {
			//l.error("erreur methode getEntrepriseById : " +e);
			return null;
		}	
		
		
	}

}
