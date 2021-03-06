package fr.adaming.forum.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import fr.adaming.forum.entity.Role;
import fr.adaming.forum.entity.Topic;

@Component
public class TopicDaoImpl implements ITopicDao {

	@PersistenceContext
	private EntityManager em;

	Logger log = Logger.getLogger("TopicDaoImpl");

	@Override
	public Topic addTopic(Topic topic) {
		em.persist(topic);

		log.info("Le topic " + topic.getIdTopic() + " a bien �t� ajout� !");
		return topic;
	}

	@Override
	public Topic updateTopic(Topic topic) {
		em.merge(topic);

		log.info("Le topic " + topic.getIdTopic() + " a bien �t� modifi� !");
		return em.find(Topic.class, topic.getIdTopic());
	}

	@Override
	public Topic deleteTopic(Long idTopic) {
		Topic topic = em.find(Topic.class, idTopic);
		if (topic != null) {
			em.remove(topic);
			log.info("Le topic " + topic.getIdTopic() + " � bien �t� supprim� !");
		}
		return topic;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getAllTopic() {
		Query query = em.createQuery("From Topic");
		List<Topic> result = query.getResultList();
		log.info(result.size() + " topic(s) ont bien �t� trouv� !");
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getTopicByKeyWord(String keyWord) {
		Query query = em.createQuery("From Topic t Where t.title like :x");
		query.setParameter("x", "%" + keyWord + "%");
		List<Topic> result = query.getResultList();
		log.info(result.size() + " topic(s) ont bien �t� trouv� !");
		return result;
	}

	@Override
	public Topic getTopicById(Long idTopic) {

		Topic topic = em.find(Topic.class, idTopic);
		if (topic != null) {
			log.info("Le topic " + topic.getIdTopic() + " � bien �t� trouv� !");
		} else {
			log.warning("Le role " + idTopic + " n'est pas dans la base de donn�e");
		}

		return topic;
	}

}
