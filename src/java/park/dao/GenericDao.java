/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package park.dao;

import org.hibernate.Session;

/**
 *
 * @author User
 */
public abstract class GenericDao <T> {
    public void Create(T t){
        Session ss= SessionManager.getSession();
        ss.beginTransaction();
        ss.save(t);
        ss.getTransaction().commit();
        ss.close();
    }
    public void Delete(T t){
        Session ss= SessionManager.getSession();
        ss.beginTransaction();
        ss.delete(t);
        ss.getTransaction().commit();
        ss.close();
    }
    public void Update(T t){
        Session ss= SessionManager.getSession();
        ss.beginTransaction();
        ss.update(t);
        ss.getTransaction().commit();
        ss.close();
    }
}
