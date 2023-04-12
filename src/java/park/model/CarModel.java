/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package park.model;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import park.dao.ActionDao;
import park.dao.BillDao;
import park.dao.CarDao;
import park.dao.HistoryDao;
import park.dao.UsersDao;
import park.domain.Action;
import park.domain.Bill;
import park.domain.Car;
import park.domain.History;
import park.domain.Users;

@ManagedBean(name = "cum", eager = true)
@SessionScoped
public class CarModel {
private String pas;
private String un;
    List<Car> allcars = new CarDao().findAll();
    List<History> alloperations = new HistoryDao().findAll();
    Car car = new Car();
    String carid;
    Date last = new Date();
    Bill bill = new Bill();
    List<Bill> bills = new BillDao().findAll();    
    String searchkey;
    String sess;
    History history = new History();
    Action act = new Action();
    Calendar gc = new GregorianCalendar();
    Users u=new Users();
    int t=0;
    
    @PostConstruct
    public void init(){
        sess ="logged out";
        checkSess();
    }
    public void checkSess() {
        try {
            if (sess.equals("logged out")) {
                
                FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
            }
        } catch (Exception e) {
        }
    }
    
    public String register() {
        try {
            int amount=0;
            //car.setFlow_activity("Registered");
            new CarDao().Create(car);
            act = new ActionDao().find1(1);
            history.setCar(car);
            history.setAmount(amount);
            history.setAct_time(Date.from(Instant.now()));
            history.setAction(act);
            new HistoryDao().Create(history);
            alloperations = new HistoryDao().findAll();
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO, " Car " + car.getPlate_no() + " Successfully Registered", "");
            FacesContext.getCurrentInstance().addMessage(null, fmsg);
            car = new Car();
            return "registration_page_1.xhtml";
        } catch (Exception e) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_WARN, " Car " + car.getPlate_no() + " Failed to be Registerd Because " + e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, fmsg);
            return "Admin_Dashboard.xhtml";
        }
    }

    public String car_in() {
        try {
            int amount=0;
            car = new CarDao().find1(carid);
            act = new HistoryDao().lastAction(car);
            if (car == null) {
                FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO, " Car " + carid + " Is Not Found, Please register It First", "");
                FacesContext.getCurrentInstance().addMessage(null, fmsg);
                car = new Car();
                return "registration_page_1.xhtml";
            } else if (act.getAct_name().equalsIgnoreCase("In")) {
                FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_WARN, " Car " + carid + " is In Parking already", "");
                FacesContext.getCurrentInstance().addMessage(null, fmsg);
                return "Car_In.xhtml";
            } else {
                act = new ActionDao().find1(1);
                history.setCar(car);
                history.setAmount(amount);
                history.setAct_time(Date.from(Instant.now()));
                history.setAction(act);
                new HistoryDao().Create(history);
                FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO, " Car " + car.getPlate_no() + " Is now in Parking", "");
                FacesContext.getCurrentInstance().addMessage(null, fmsg);
                carid = new String();
                return "Car_In.xhtml";
            }

        } catch (Exception e) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO, " Car " + car.getPlate_no() + " Is now in Parking " + e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, fmsg);
            carid = new String();
            return "Car_In.xhtml";
        }

    }

    public String carOut() {
        try {
            car = new CarDao().find1(carid);
            
            act = new HistoryDao().lastAction(car);
            if (car == null) {
                FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO, " Car " + carid + " Is not registered", "");
                FacesContext.getCurrentInstance().addMessage(null, fmsg);
                Car car = new Car();
                return "Car_Out.xhtml";
            } else if (act.getAct_name().equalsIgnoreCase("Out")) {
                FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO, " Car " + carid + " Is already Out", "");
                FacesContext.getCurrentInstance().addMessage(null, fmsg);
                return "Car_Out.xhtml";
            } else {
                int amount;
                Date today = new Date();
                int df = ((int) (today.getTime() - last.getTime())/3600000);
                System.out.println(df);
                if (df <= 2) {
                    amount = 500;
                } else {
                    int s= df-2;
                    amount = (s * 200) + 500;
                }
                act = new ActionDao().find1(2);
                history.setAmount(amount);
                history.setCar(car);
                history.setAct_time(Date.from(Instant.now()));
                history.setAction(act);
                new HistoryDao().Create(history);
                last = new HistoryDao().lastDateIn(car);
                
                bill.setAmount(amount);
                bill.setBill_date(Date.from(Instant.now()));
                bill.setCar(car);
                bill.setHistory(history);
                new BillDao().Create(bill);
                FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Car out: " + car.getPlate_no() + " Amount: " + amount + " Frw", "");
                FacesContext.getCurrentInstance().addMessage(null, fmsg);
                carid = new String();
                alloperations=new HistoryDao().findAll();
                bills= new BillDao().findAll();
                return "Car_Out.xhtml";
            }
        } catch (Exception e) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO, " Car " + carid + " has problem " + e.getCause(), "");
            FacesContext.getCurrentInstance().addMessage(null, fmsg);
            return "Car_Out.xhtml";
        }

    }

    public List<History> search() {
        try {
            alloperations = new ArrayList<>();
        List<History> list = new HistoryDao().findAll();
        
        if (searchkey == null || searchkey.isEmpty()) {
            alloperations = list;
        } else {
            for (History b : list) {
                if (b.getCar().getPlate_no().contains(searchkey)
                        || b.getCar().getOwner_Name().contains(searchkey)
                        || b.getAction().getAct_name().equals(searchkey)) {
                    alloperations.add(b);
                }
            }
        }
        } catch (Exception e) {
        }
        return alloperations;
    }
public void logout()
{   
    try {
        sess = "logged out";
        Users u = new Users();
        FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
    } catch (IOException ex) {
        Logger.getLogger(CarModel.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
public String lo()
{
    return "Login.xhtml";
    
}
public String login()
{
    List<Users>list = new UsersDao().findAll();
    try {
        for(Users u:list)
        {
            if(u.getPassword().equals(pas)&&u.getUsername().equals(un))
            {
                sess = "logged in";
                return "Admin_Dashboard.xhtml";
                
            }
        }
    } catch (Exception e) {
    }
        return null;
        
    
}
 
public String getTotalamount(){
        List<History> list = new HistoryDao().findAll();
        double total = 0;
        for(History e: list){
            total += e.getAmount();
        }
        DecimalFormat df = new DecimalFormat("###,### FRW");
        return df.format(total);
    }
public void listHist(){
    alloperations = new HistoryDao().findAll();
    
}


    public void payment() {

    }

    public List<Car> getAllcars() {
        return allcars;
    }


    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey;
    }

    public void setAllcars(List<Car> allcars) {
        this.allcars = allcars;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public List<History> getAlloperations() {
        return alloperations;
    }

    public Date getLast() {
        return last;
    }

    public void setLast(Date last) {
        this.last = last;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public void setAlloperations(List<History> alloperations) {
        this.alloperations = alloperations;
    }

    public Action getAct() {
        return act;
    }

    public void setAct(Action act) {
        this.act = act;
    }

    public Calendar getGc() {
        return gc;
    }

    public void setGc(Calendar gc) {
        this.gc = gc;
    }

    public Users getU() {
        return u;
    }

    public void setU(Users u) {
        this.u = u;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

}
