/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package park.dao;

import park.domain.Users;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class UsersDao extends GenericDao <Users> {

    public Users find1(final String id) {
        Session ss = SessionManager.getSession();
        return (Users) ss.get(Users.class, id);
    }

    public List<Users> findAll() {
        Session ss = SessionManager.getSession();
        return ss.createCriteria(Users.class).list();
    }

   
}

