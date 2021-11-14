package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.DepartementServiceImpl;
import tn.esprit.spring.services.EntrepriseServiceImpl;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)

@SpringBootTest
class DepartementServiceImplTest {

	@Autowired EntrepriseServiceImpl entservice;
	@Autowired DepartementServiceImpl depservice;
	@Autowired EntrepriseRepository  entrepriserepository;
	@Autowired DepartementRepository  departementrepository;
	
	int iddep = 0 ;

	

  @Test

	public void testAjoutDepartement(){
		
		Departement dep=new Departement("Technique");
		
		 int rslt=depservice.ajouterDepartement(dep);
		assertNotNull(rslt);
	}



  @Test
 public void testAffecterDepartementAEntreprise(){
	depservice.affecterDepartementAEntreprise(2,1);
	Departement dep=departementrepository.findById(1).orElse(null);
	assertEquals(1,1);	

	 
}
  
  
  @Test 
	public void testalldeps() {
		depservice.getAllDep();
	}
  
  @Test 
 	public void deleteAll() {
 		depservice.delete();
 	}
  


}
