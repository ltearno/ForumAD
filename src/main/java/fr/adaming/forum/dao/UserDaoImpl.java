package fr.adaming.forum.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import fr.adaming.forum.entity.User;

@Component
public class UserDaoImpl implements IUserDao {

	@PersistenceContext
	private EntityManager em;

	Logger log = Logger.getLogger("UserDaoImpl");

	@Override
	public User addUser(User user) {
		em.persist(user);

		log.info("L'utilisateur " + user.getIdUser() + " � bien �t� ajout� !");
		return user;
	}

	@Override
	public User updateUser(User user) {
		em.merge(user);

		log.info("L'utilisateur " + user.getIdUser() + " � bien �t� modifi� !");
		return user;
	}

	@Override
	public User deleteUser(Long idUser) {
		User user = em.find(User.class, idUser);
		if (user != null) {
			em.remove(user);
			log.info("L'utilisateur " + user.getIdUser() + " � bien �t� supprim� !");
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {
		Query query = em.createQuery("From User");

		log.info("Il y a " + query.getResultList().size() + " utilisateur(s) !");
		return query.getResultList();
	}

	@Override
	public User getUserById(Long idUser) {
		User user = em.find(User.class, idUser);
		if (user != null) {
			log.info("L'utilisateur " + user.getIdUser() + " � bien �t� trouv� !");
		} else {
			log.warning("L'utilisateur " + idUser + " n'a pas �t� trouv� !");
		}

		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByKeyWord(String keyWord) {
		Query query = em.createQuery(
				"FROM User u INNER JOIN u.formation f JOIN u.company c WHERE u.firstName LIKE :x OR u.name LIKE :x OR f.formationName LIKE :x OR c.companyName LIKE :x"); // OR
																																											// u.formation.name
																																											// LIKE
																																											// :x
		query.setParameter("x", "%" + keyWord + "%");

		log.info(query.getResultList().size() + "utilisateur(s) ont �t� trouv� !");
		return query.getResultList();
	}

}
