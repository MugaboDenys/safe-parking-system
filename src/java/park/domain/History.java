/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package park.domain;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import park.dao.HistoryDao;

@Entity
public class History {
    @Id
    @GeneratedValue
    private int act_id;
    @Column
    private Date act_time;
    @ManyToOne
    @JoinColumn(name="Car")
    private Car Car;
    @ManyToOne
    @JoinColumn(name="Action")
    private Action Action;
    @Column
     private int amount;
   
    
    
    public String getDateTime(){
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return f.format(act_time);
    } 
    public Action getAction() {
        return Action;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void setAction(Action Action) {
        this.Action = Action;
    }

    public int getAct_id() {
        return act_id;
    }

    public Date getAct_time() {
        return act_time;
    }

    public void setAct_time(Date act_time) {
        this.act_time = act_time;
    }

    public void setAct_id(int act_id) {
        this.act_id = act_id;
    }
   
    public Car getCar() {
        return Car;
    }

    public void setCar(Car car) {
        this.Car = car;
    }
@Override
public String toString(){
    return Action.getAct_name()+" "+Car.getPlate_no();
}
    
   
}
