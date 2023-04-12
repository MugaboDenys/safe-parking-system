/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package park.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Car {
    @Id
    private String plate_no;
    @Column
    private String Owner_Name;
    @Column
    private String Telephone;
    @Column
    private String flow_activity;
    @OneToMany(mappedBy = "Car")
    private List<History> History;
    @OneToMany(mappedBy = "Car")
    private List<Bill> bills;
    
    public String getFlow_activity() {
        return flow_activity;
    }
   
    public void setFlow_activity(String flow_activity) {
        this.flow_activity = flow_activity;
    }

    public List<History> getHistory() {
        return History;
    }

    public void setHistory(List<History> History) {
        this.History = History;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public String getPlate_no() {
        return plate_no;
    }

    public void setPlate_no(String plate_no) {
        this.plate_no = plate_no;
    }

    public String getOwner_Name() {
        return Owner_Name;
    }

    public void setOwner_Name(String Owner_Name) {
        this.Owner_Name = Owner_Name;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }
    @Override
    public String toString(){
        return plate_no;
    }
}
