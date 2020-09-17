package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
	//method to have all items shown
	public List<ListItem>showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		List<ListItem>allItems = em.createQuery("SELECT i FROM ListItem i").getResultList();
		return allItems;
	}
	
	//method to Remove items
	public void deleteItem(ListItem toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem>typedQuery=em.createQuery("Select li from ListItem li where li.store = :selectedStore and li.item = :selectedItem", ListItem.class);
		
		
	//substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedStore", toDelete.getStore());
		typedQuery.setParameter("selectedItem", toDelete.getItem());
		
		//show one result
		typedQuery.setMaxResults(1);
		
		//get the result and save it into a new list item
		ListItem result = typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	//Methods to search through database and edit/update items
	public List<ListItem> searchForItemByStore(String storeName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem>typedQuery = em.createQuery("Select li from ListItem li where li.store = :selectedStore",ListItem.class); typedQuery.setParameter("selectedStore",storeName);
		
		List<ListItem>foundItems=typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	
	public List<ListItem> updateItem(String itemName) {
		EntityManager em	=	emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem>	typedQuery	=	em.createQuery("select	li	from	ListItem	li	where	li.item	=	:selectedItem",	ListItem.class);
		typedQuery.setParameter("selectedItem",	itemName);
		List<ListItem>	foundItems	=	typedQuery.getResultList();
		em.close();
		return	foundItems;
		
	}
	
	public	ListItem	searchForItemById(int	idToEdit)	{
	EntityManager	em	=	emfactory.createEntityManager();
	em.getTransaction().begin();
	ListItem	found	=	em.find(ListItem.class,	idToEdit);
	em.close();
	return	found;
	}
	
	public	void	updateItem(ListItem	toEdit)	{
	EntityManager	em	=	emfactory.createEntityManager();
	em.getTransaction().begin();
	em.merge(toEdit);
	em.getTransaction().commit();
	em.close();
	}
	
	//clean up method
	public void cleanUp() {
		emfactory.close();
	}
}

