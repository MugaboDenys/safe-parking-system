/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package park.dao;

import java.util.ArrayList;
import java.util.Date;
import park.domain.History;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import park.domain.Action;
import park.domain.Car;

/**
 *
 * @author User
 */
public class HistoryDao extends GenericDao <History> {

    public History find1(final int id) {
        Session ss = SessionManager.getSession();
        return (History) ss.get(History.class, id);
    }

    public List<History> findAll() {
        Session ss = SessionManager.getSession();
        return ss.createCriteria(History.class).list();
    }
    
    public Action lastAction(Car car){
        Action act= new Action();
        List<History> hist = new HistoryDao().carHistory(car);
        if(hist.isEmpty())
            act=null;
        else {
            for(History h:hist){
                act=h.getAction();
            }
        }
        return act;
    }
    public Date lastDateIn(Car car){
        Date last = new Date();
        List<History> hist = new HistoryDao().carHistory(car);
        if(hist.isEmpty())
            last=null;
        else {
            for(History h:hist){
                if(h.getAction().getAct_name().equalsIgnoreCase("in")) {
                    last=h.getAct_time();
                }
            }
        }
        return last;
    }

    public List<History> carHistory(Car car) {
        Session ss = SessionManager.getSession();
        Query q = SessionManager.getSession().createQuery("from History h where h.Car ='"+car+"' ");
        return q.list();
    }     
    
   
}
