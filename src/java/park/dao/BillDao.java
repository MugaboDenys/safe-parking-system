/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package park.dao;

import park.domain.Bill;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class BillDao extends GenericDao <Bill> {

    public Bill find1(final int id) {
        Session ss = SessionManager.getSession();
        
        return (Bill) ss.get(Bill.class, id);
    }

    public List<Bill> findAll() {
        Session ss = SessionManager.getSession();
        
        return ss.createCriteria(Bill.class).list();
    }

   
}
