/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package park.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Action {
   @Id
   @GeneratedValue( strategy = GenerationType.AUTO)
   private int act_id;
   @Column
   private String act_name;
   @OneToMany(mappedBy = "Action")
   private List<History> History;

    public List<History> getHistory() {
        return History;
    }

    public void setHistory(List<History> History) {
        this.History = History;
    }

    
   
   
    public int getAct_id() {
        return act_id;
    }

    public void setAct_id(int act_id) {
        this.act_id = act_id;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }
   
   @Override
   public String toString(){
       return act_name;
   }
}
