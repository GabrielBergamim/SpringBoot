package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {
		
		/*Pessoa p1 = new Pessoa(null, "Gabriel", "gabriel@aluno.com.br");
		Pessoa p2 = new Pessoa(null, "Caio", "caio@aluno.com.br");
		Pessoa p3 = new Pessoa(null, "Fabio", "fabio@aluno.com.br");*/
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		
		/*em.getTransaction().begin();
		
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		
		em.getTransaction().commit();*/
		
		Pessoa p = em.find(Pessoa.class, 2);
		/*em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();*/
		System.out.println(p);
		System.out.println("FM, I'm in hell");
		
		em.close();
		emf.close();
		
	}

}
