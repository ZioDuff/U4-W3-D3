package jacopodemaio.dao;

import jacopodemaio.entities.Persona;
import jacopodemaio.exceptions.NotFoundExceptions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PersonaDAO {
    private EntityManager em;

    public PersonaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Persona persona) {

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(persona);
        transaction.commit();
        System.out.println("la persona " + persona.getNome() + " è stato correttamente salvato sul database");
    }

    public Persona findById(long personaId) {
        Persona persona = em.find(Persona.class, personaId);
        if (persona == null) throw new NotFoundExceptions(personaId);
        return persona;

    }

    public void foundByIdAndDelete(long personaId) {
        Persona foundedPersona = this.findById(personaId);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(foundedPersona);
        transaction.commit();
        System.out.println("l'evento " + foundedPersona.getNome() + " è stato eliminato con successo dal database");

    }
}
