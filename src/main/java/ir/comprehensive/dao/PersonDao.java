package ir.comprehensive.dao;

import ir.comprehensive.domain.Person;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao extends BaseDao<Person> {
    void search() {

        Session session = getSession();
        session.save(new Person());
    }
}
