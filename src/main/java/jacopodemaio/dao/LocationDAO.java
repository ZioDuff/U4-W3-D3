package jacopodemaio.dao;

import jacopodemaio.entities.Location;
import jacopodemaio.exceptions.NotFoundExceptions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class LocationDAO {
    private EntityManager em;

    public LocationDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Location location) {

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(location);
        transaction.commit();
        System.out.println("la location  " + location.getNome() + " è stato correttamente salvato sul database");
    }

    public Location findById(long locationId) {
        Location location = em.find(Location.class, locationId);
        if (location == null) throw new NotFoundExceptions(locationId);
        return location;

    }

    public void foundByIdAndDelete(long locationId) {
        Location foundedLocation = this.findById(locationId);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(foundedLocation);
        transaction.commit();
        System.out.println("l'evento " + foundedLocation.getNome() + " è stato eliminato con successo dal database");

    }
}
