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
public class SpaceDao implements IDaoSpace {

	@PersistenceContext
	private EntityManager em;

	@Override
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
		em.persist(style);

		return space.getIdSubscription();
	}

//	@Override
//	public Long createSpace(Space spaceToCreate) {
//		em.persist(spaceToCreate);
//		return spaceToCreate.getIdSubscription();
//	}

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
	public Space getSpaceByIdSubscription(Long spaceToGetIdSubscription) {
		return em.find(Space.class, spaceToGetIdSubscription);
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

	@Override
	public Space getSpaceById(Long spaceId) {
		return em.createQuery("SELECT u FROM Space u WHERE u.idSubscription = :spaceIdParam", Space.class)
				.setParameter("spaceIdParam", spaceId)
				.getSingleResult();
	}
	
	@Override
	public Long saveSpaceAndRelations(Space space) {
		em.persist(space.getAdministrative());
		em.persist(space);
		return space.getIdSubscription();
	}
}
