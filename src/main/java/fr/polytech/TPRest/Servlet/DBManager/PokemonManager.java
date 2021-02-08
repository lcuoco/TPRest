package fr.polytech.TPRest.Servlet.DBManager;

import fr.polytech.TPRest.Servlet.Metier.Pokemon;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.core.Response;
import java.util.List;

import static fr.polytech.TPRest.Servlet.DBManager.HibernateFactory.getSession;

public class PokemonManager {
    /**
     * Permet de récupérer tous les magasins
     *
     * @return liste de tous les magasins
     */
    public Pokemon create(Pokemon pokemon) {
        Session session = getSession();

        session.beginTransaction();

        session.save(pokemon);
        session.getTransaction().commit();
        Query query = session.createQuery("select pokemon from Pokemon as pokemon where pokemon.id =:id");
        query.setParameter("id", pokemon.getId());
        return (Pokemon) query.getSingleResult();
    }

    public List<Pokemon> getAll() {
        Session session = getSession();
        Query query = session.createQuery("select pokemon from Pokemon as pokemon");
        return query.getResultList();
    }

    public Pokemon getById(int id)
    {
        Session session = getSession();
        Query query = session.createQuery("select pokemon from Pokemon as pokemon where pokemon.id=:id");
        query.setParameter("id", id);
        return (Pokemon) query.getSingleResult();
    }


    public Pokemon update(Pokemon pokemon) {
        Session session = getSession();

        session.beginTransaction();

        session.update(pokemon);
        session.getTransaction().commit();
        return pokemon;
    }


    public Response delete(Pokemon pokemon)
    {
        Session session = getSession();

        session.beginTransaction();

        session.delete(pokemon);
        session.getTransaction().commit();
        return Response.ok().build();
    }

}
