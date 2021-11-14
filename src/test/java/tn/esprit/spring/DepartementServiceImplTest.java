package tn.esprit.spring;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.DepartementServiceImpl;
import tn.esprit.spring.services.EntrepriseServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartementServiceImplTest {
	

private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DepartementServiceImplTest.class);
	
	@Autowired EntrepriseServiceImpl entservice;
	@Autowired DepartementServiceImpl depservice;
	@Autowired EntrepriseRepository  entrepriserepository;
	@Autowired DepartementRepository  departementrepository;
	
	
/*	
@Test
	public void testAjoutDepartement(){
		
		LOGGER.debug("L'ajout d'un département");
		Departement dep=new Departement("Rh");
		 int rslt=depservice.ajouterDepartement(dep);
		assertNotNull(rslt);
		LOGGER.info("Le département  "+dep.getName()+ "  est ajouté avec succés");
		//System.out.println("/n");
	}

 */

@Test

public void testAffecterDepartementAEntreprise(){
	//int id_entreprise=3;
	//int id_departement=1;
	depservice.affecterDepartementAEntreprise(1,1);
	
	 
}

/*
@Test
public void testDeleteDepartementById(){
     int iddep=5;
     boolean isExistBeforeDelete=departementrepository.findById(iddep).isPresent();
      entservice.deleteDepartementById(iddep);
     boolean notExistAfterDelete=departementrepository.findById(iddep).isPresent();
     assertTrue(isExistBeforeDelete);
     assertFalse(notExistAfterDelete);

   }  
*/

}


