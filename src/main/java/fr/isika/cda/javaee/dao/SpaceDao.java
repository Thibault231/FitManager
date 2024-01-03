package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.spaces.Administrative;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.spaces.Style;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceForm;

@Stateless
public class SpaceDao /* implements IDaoSpace */ {

	@PersistenceContext
	private EntityManager em;

	public Long createSpace(SpaceForm spaceForm) {
		Space space = new Space();
		Administrative administrative = new Administrative();
		Style style = new Style();

		space.setName(spaceForm.getName());

		administrative.setSiret(spaceForm.getSiret());
		administrative.setSiren(spaceForm.getSiren());
		space.setAdministrative(administrative);

		style.setLogo(spaceForm.getLogo());
		space.setStyle(style);

		em.persist(administrative);
		em.persist(space);

		return space.getIdSubscription();
	}

//	@Override
//	public Long createSpace(Space spaceToCreate) {
//		em.persist(spaceToCreate);
//		return spaceToCreate.getIdSubscription();
//	}

	public boolean deleteSpace(Long SpaceToDeleteIdSubscription) {
		Space spaceTodelete = em.find(Space.class, SpaceToDeleteIdSubscription);
		if (spaceTodelete != null) {
			em.remove(SpaceToDeleteIdSubscription);
			return true;
		} else {
			return false;
		}
	}

	public Space getSpaceByIdSubscription(Long spaceToGetIdSubscription) {
		return em.find(Space.class, spaceToGetIdSubscription);
	}

	public Space getSpaceByName(String spaceToGetName) {
		return em.createQuery("SELECT u FROM Space u WHERE u.isActive = 1", Space.class).getSingleResult();
	}

	public List<Space> getAllSpace() {
		return em.createQuery("SELECT u FROM Space u WHERE u.isActive = 1", Space.class).getResultList();
	}

}
