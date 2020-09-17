package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.ListItem;

public class ListItemHelper {
	
	//Entity Manager
	static EntityManagerFactory emfactory = 
			Persistence.createEntityManagerFactory("ConsoleShoppingList");
	
	//method used to accept List Item to add
	public void insertItem(ListItem li) {
		//Declaration of the Entity Manger to find and grab items to add to the list
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
}
