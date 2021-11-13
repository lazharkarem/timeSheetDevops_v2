package tn.esprit.spring;




import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import tn.esprit.spring.entities.Entreprise;

import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEntrepriseService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;



@SpringBootTest
//@RunWith(SpringRunner.class)
public class EntrepriseServiceImplTest {
	
	
	@Autowired IEntrepriseService entservice;
	@Autowired EntrepriseRepository  entrepriserepository;
	@Autowired DepartementRepository  departementrepository;
	Integer idEntreprise;
	
	
	@Test
	
	public void testAjoutEntreprise(){
		
		
		 Entreprise ent=new Entreprise("MitraSociety","mitra");
		 idEntreprise=entservice.ajouterEntreprise(ent);
		 assertNotNull(idEntreprise);
		 
		
	}
	


	 

@Test
public void testGetEtrepriseById(){
    
    Entreprise ent=entservice.getEntrepriseById(1);
    assertEquals(1, ent.getId());
    
}


@Test
public void testDeleteEntrepriseById(){
	
	if(idEntreprise!=null){
		int i = entservice.deleteEntrepriseById(idEntreprise);
		
		assertEquals(0, i);
		}
		else {
			int i = entservice.deleteEntrepriseById(5);
			
			assertEquals(0, i);
			}
       
	
	

}
@Test
public void testGetAllDepartementsNamesByEntreprise() {
	
	List<String> depNames = entservice.getAllDepartementsNamesByEntreprise(1);
	assertNotNull(depNames);
}




}