package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.presentation.util.SessionUtils;

@Stateless
public class SpaceDao implements IDaoSpace {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Long createSpace(Space spaceToCreate) {
		em.persist(spaceToCreate.getInfos().getConfiguration().getStyle());
		em.persist(spaceToCreate.getInfos().getAdministrative());
		em.persist(spaceToCreate.getInfos().getConfiguration());
		em.persist(spaceToCreate.getOnlineShop());
		em.persist(spaceToCreate.getPlanning());
		em.persist(spaceToCreate.getInfos());
		em.persist(spaceToCreate);
		return spaceToCreate.getSpaceId();
	}

	@Override
	public boolean deleteSpace(Long SpaceToDeleteId) {
		Space spaceTodelete = em.find(Space.class, SpaceToDeleteId);
		if (spaceTodelete != null) {
			em.remove(SpaceToDeleteId);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Space getSpaceById(Long spaceToGetId) {
		return em.find(Space.class, spaceToGetId);
	}

	@Override
	public Space getSpaceByName(String spaceToGetName) {
		return em.createQuery("SELECT u FROM Space u WHERE u.isActive = 1", Space.class).getSingleResult();
	}

	@Override
	public List<Space> getAllSpace() {
		return em.createQuery("SELECT u FROM Space u", Space.class).getResultList();

	}

	@Override
	public void updateSpace(Space spaceToUpdate) {
		em.merge(spaceToUpdate);
	}

	@Override
	public Space getSpaceWithMembers(Long currentSpaceId) {
		return em.createQuery("SELECT s FROM Space s LEFT JOIN FETCH s.users WHERE s.id = :spaceIdParam", Space.class)
				.setParameter("spaceIdParam", currentSpaceId).getSingleResult();
	}

	@Override
	public List<Space> getManagerSpaces(Long managerId) {
		return em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.linkedSpaces WHERE u.userId = :managerIdParam",
				Space.class).setParameter("managerIdParam", managerId).getResultList();
	}
}
