package tn.esprit.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import tn.esprit.spring.controller.RestControlEmploye;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeServiceImpTest {
	@Autowired
    RestControlEmploye controllerEmploye;
	
	@Test
    public void testAjouterEmploye(){

		 Employe employe= controllerEmploye.ajouterEmploye (new Employe("lazhar","karem","karem.lazhar@gmail.com",true,Role.ADMINISTRATEUR ));
		 assertNotEquals(employe.getId(),0);
		 controllerEmploye.deleteEmployeById(employe.getId());
    }
	@Test
	public void testGetEmployePrenomById() {
		Employe employe= controllerEmploye.ajouterEmploye (new Employe("lazhar","karem","karem.lazhar@gmail.com",true,Role.ADMINISTRATEUR ));
		assertEquals("karem",controllerEmploye.getEmployePrenomById(employe.getId()));
		controllerEmploye.deleteEmployeById(employe.getId());
	}	
	
	@Test 
	public void testGetNombreEmployeJPQL() {
		controllerEmploye.getNombreEmployeJPQL();
	}
	@Test 
	public void testGetAllEmployeNamesJPQL() {
		controllerEmploye.getAllEmployeNamesJPQL();
	}
	@Test
	public void testMettreAjourEmailByEmployeIdJPQL() {
		controllerEmploye.mettreAjourEmailByEmployeIdJPQL("karem.lazhar@esprit.tn", 1);;
	}
	@Test
	public void testDeleteEmployeById() {
		Employe employe= controllerEmploye.ajouterEmploye (new Employe("lazhar","karem","karem.lazhar@gmail.com",true,Role.ADMINISTRATEUR ));
		assertNotEquals(employe.getId(),0);
		controllerEmploye.deleteEmployeById(employe.getId());
		
	}

}