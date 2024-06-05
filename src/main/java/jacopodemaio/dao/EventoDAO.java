package jacopodemaio.dao;

import jacopodemaio.entities.Evento;
import jacopodemaio.exceptions.NotFoundExceptions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EventoDAO {


    private EntityManager em;


    public EventoDAO(EntityManager em) {
        this.em = em;
    }


    public void save(Evento evento) {

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(evento);
        transaction.commit();
        System.out.println("l'evento " + evento.getTitle() + " è stato correttamente salvato sul database");
    }

    public Evento findById(long eventId) {
        Evento evento = em.find(Evento.class, eventId);
        if (evento == null) throw new NotFoundExceptions(eventId);
        return evento;

    }

    public void foundByIdAndDelete(long eventId) {
        Evento foundedEvento = this.findById(eventId);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(foundedEvento);
        transaction.commit();
        System.out.println("l'evento " + foundedEvento.getTitle() + " è stato eliminato con successo dal database");

    }


}
