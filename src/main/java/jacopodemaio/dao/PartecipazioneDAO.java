package jacopodemaio.dao;

import jacopodemaio.entities.Partecipazione;
import jacopodemaio.exceptions.NotFoundExceptions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PartecipazioneDAO {
    private EntityManager em;

    public PartecipazioneDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Partecipazione partecipazione) {

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(partecipazione);
        transaction.commit();
        System.out.println("la partecipazione " + partecipazione.getEvento().getTitle() + " è stato correttamente salvato sul database");
    }

    public Partecipazione findById(long partecipazioneId) {
        Partecipazione partecipazione = em.find(Partecipazione.class, partecipazioneId);
        if (partecipazione == null) throw new NotFoundExceptions(partecipazioneId);
        return partecipazione;

    }

    public void foundByIdAndDelete(long partecipazioneId) {
        Partecipazione foundedPartecipazione = this.findById(partecipazioneId);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(foundedPartecipazione);
        transaction.commit();
        System.out.println("l'evento " + foundedPartecipazione.getEvento().getTitle() + " è stato eliminato con successo dal database");

    }

}
