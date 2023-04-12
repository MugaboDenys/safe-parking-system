/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package park.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Bill {
    @Id
    @GeneratedValue
    private int bill_id;
    @Column
    private Date bill_date;
    @OneToOne
    private History history;
    @ManyToOne
    @JoinColumn (name="Car") 
    private Car Car;
    private int amount;

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public Date getBill_date() {
        return bill_date;
    }

    public void setBill_date(Date bill_date) {
        this.bill_date = bill_date;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public Car getCar() {
        return Car;
    }

    public void setCar(Car Car) {
        this.Car = Car;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
}
