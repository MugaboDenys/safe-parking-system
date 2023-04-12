/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package park.dao;

import park.domain.Car;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class CarDao extends GenericDao <Car> {

    public Car find1(final String id) {
        Session ss = SessionManager.getSession();
        return (Car) ss.get(Car.class, id);
    }

    public List<Car> findAll() {
        Session ss = SessionManager.getSession();
        return ss.createCriteria(Car.class).list();
    }

   
}
