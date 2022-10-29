package org.luggage_delivery.session_factory_config;
/*
  User: admin
  Cur_date: 12.10.2022
  Cur_time: 21:30
*/

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.luggage_delivery.entity.*;

public class HibernateUtil {

    private volatile static SessionFactory sessionFactory;

    private HibernateUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory != null)
            return sessionFactory;

        synchronized (SessionFactory.class) {
            try {
                if (sessionFactory == null) {
                    Configuration configuration = new Configuration();
                    ServiceRegistry serviceRegistry
                            = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties()).build();
                    //add mapping
                    configuration.addAnnotatedClass(Delivery.class);
                    configuration.addAnnotatedClass(DeliveryStatus.class);
                    configuration.addAnnotatedClass(Role.class);
                    configuration.addAnnotatedClass(Route.class);
                    configuration.addAnnotatedClass(Tariff.class);
                    configuration.addAnnotatedClass(User.class);

                    sessionFactory = configuration
                            .buildSessionFactory(serviceRegistry);

                }
                return sessionFactory;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("There is issue in hibernate util");
            }
        }
    }
}
