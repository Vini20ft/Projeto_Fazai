package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UtilJPA {
	
    /**
     *
     */
    public static final String PERSISTENCE = "FazAi";
    
	private static final EntityManagerFactory emf =         
		Persistence.createEntityManagerFactory(PERSISTENCE);
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public static void closeEntityManagerFactory() {
		emf.close();
	}
	
}