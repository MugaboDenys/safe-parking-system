/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package park.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author User
 */
class SessionManager {  
    
    public static SessionFactory fact=null;
        
        public SessionManager(){
            
        }
    static Session getSession() {
        if(fact==null){
            Configuration cf= new Configuration().configure();
            fact=cf.buildSessionFactory();
        }
        
        return fact.openSession();
    }
    
}
