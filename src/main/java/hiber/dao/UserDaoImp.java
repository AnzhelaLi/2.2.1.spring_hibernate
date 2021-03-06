package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<Car> carList() {
        TypedQuery<Car> carList = sessionFactory.getCurrentSession().createQuery("from Car");
        return carList.getResultList();

    }

    @Override
    public String findUserByCarModelAndSeries() {
        return sessionFactory.getCurrentSession().
                createQuery("from Car car left outer join fetch car.user" +
                        "  where  car.series = :carSeries and car.model = :carModel", Car.class)
                .setParameter("carModel", "Tesla")
                .setParameter("carSeries", 1234567).uniqueResult().getUser().getFirstName();
    }
}
