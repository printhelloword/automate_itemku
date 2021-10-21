package bot.itemku.utility;

import bot.itemku.model.Inboxes;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class DBUtilInboxes {

    private static SessionFactory factory;

    static {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public static Session getSession() {
        return factory.openSession();
    }

    public static Integer saveInbox(Inboxes inbox) {
        Integer generatedID = null;

        Session session = getSession();

        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.persist(inbox);

            tx.commit();

            generatedID = inbox.getId();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return generatedID;

    }

    public static boolean isTrxIdExists(String trxId) {

        Session session = getSession();
        boolean result = false;

        if (!trxId.equalsIgnoreCase("0")) {

            try {
                Query query = session.
                        createSQLQuery("select * from inboxes where trx_id=:trxId");
                query.setParameter("trxId", trxId);
                query.setMaxResults(1);

                List<Object[]> results = query.getResultList();
                if (!results.isEmpty()) {
                    result = true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                session.close();
            }
        }

        return result;
    }

}
