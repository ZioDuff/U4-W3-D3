package jacopodemaio;

import jacopodemaio.dao.EventoDAO;
import jacopodemaio.dao.LocationDAO;
import jacopodemaio.dao.PartecipazioneDAO;
import jacopodemaio.dao.PersonaDAO;
import jacopodemaio.entities.Evento;
import jacopodemaio.entities.Location;
import jacopodemaio.entities.Partecipazione;
import jacopodemaio.entities.Persona;
import jacopodemaio.enums.Sesso;
import jacopodemaio.enums.Stato;
import jacopodemaio.enums.TipoEvento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        try {
            EventoDAO evd = new EventoDAO(em);
            PersonaDAO prsnd = new PersonaDAO(em);
            PartecipazioneDAO prtd = new PartecipazioneDAO(em);
            LocationDAO ld = new LocationDAO(em);

            Persona person3 = new Persona("fhosdhfodbhnfgv", "la trottola", "k2@vattelapescha.com", LocalDate.of(1960, 12, 30), Sesso.M);
            prsnd.save(person3);

            Location location3 = new Location("casa mia", "Roma");
            ld.save(location3);

            Location locationFromDb = ld.findById(152);

            Evento evento1 = new Evento("ma che ne so", LocalDate.now(), "bella", TipoEvento.PRIVATO, 30, locationFromDb);
            evd.save(evento1);

            Partecipazione partecipazione1 = new Partecipazione(person3, evento1, Stato.CONFERMATA);
            prtd.save(partecipazione1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            em.close();
            emf.close();
        }


    }
}
