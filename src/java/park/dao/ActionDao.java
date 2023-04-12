/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package park.dao;

import park.domain.Action;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class ActionDao extends GenericDao <Action> {

    public Action find1(final int id) {
        Session ss = SessionManager.getSession();
        return (Action) ss.get(Action.class, id);
        
    }

    public List<Action> findAll() {
        Session ss = SessionManager.getSession();
        return ss.createCriteria(Action.class).list();
    }

   
}
