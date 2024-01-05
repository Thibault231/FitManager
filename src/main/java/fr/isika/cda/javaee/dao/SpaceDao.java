package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.spaces.Administrative;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.spaces.Style;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceViewModel;

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
	public boolean deleteSpace(Long SpaceToDeleteIdSubscription) {
		Space spaceTodelete = em.find(Space.class, SpaceToDeleteIdSubscription);
		if (spaceTodelete != null) {
			em.remove(SpaceToDeleteIdSubscription);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Space getSpaceById(Long spaceToGetId) {
		return em.find(Space.class, spaceToGetId);
	}

	// FIXME : attention il n'y plus de isActive dans Space !!!!
	@Override
	public Space getSpaceByName(String spaceToGetName) {
		return em.createQuery("SELECT u FROM Space u WHERE u.isActive = 1", Space.class).getSingleResult();
	}

	// FIXME : attention il n'y plus de isActive dans Space !!!!
	@Override
	public List<Space> getAllSpace() {
		return em.createQuery("SELECT u FROM Space u", Space.class).getResultList();
	}

}
