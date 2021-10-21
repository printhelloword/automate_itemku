package bot.itemku.utility;

import bot.itemku.model.Outboxes;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class DBUtilOutboxess {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DBUtilOutboxess.class);
    private static SessionFactory factory;

    static {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public static Session getSession() {
        return factory.openSession();
    }

    public static Integer saveOutbox(Outboxes outbox) {
        Integer generatedOutboxId=null;

        Session session = getSession();

        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.persist(outbox);

            tx.commit();
            generatedOutboxId = outbox.getId();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return generatedOutboxId;
    }

    public static String getOutbox(String trxId) {
        Session session = getSession();
        String response=null;

        try {
            Query query = session.
                    createSQLQuery("select * from outboxes where message like '%"+trxId+"%'");
            query.setMaxResults(1);

            List<Object[]> results = query.getResultList();

            if (results.isEmpty())
                System.out.println(">>>>>RESULT EMPTY");
            else
                System.out.println(">>>>>OUTBOX EXIST");


            for (Object[] row : results) {
                System.out.println(">>>>>.RESULTS : "+row[1]);
                if(row[1].toString()!=null)
                    response = row[1].toString();
                else
                    response=null;
            }
        }
        catch(Exception e) {
            logger.info(e.getMessage());
        }
        finally {
            session.close();
        }

        return response;
    }



}
