package com.example.vehiclesharing.model;

import com.example.vehiclesharing.constants.AdminCredentials;
import com.example.vehiclesharing.constants.IAppConstants;
import com.example.vehiclesharing.constants.IAppMessages;
import com.example.vehiclesharing.dao.DriverDAO;
import com.example.vehiclesharing.dao.PassengerDAO;
import com.example.vehiclesharing.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Passenger implements IPassenger{
    private int passenger_id;
    private String passenger_fname;
    private String passenger_lname;
    private String passenger_email;
    private String passenger_password;
    private float passenger_credits;

    public float getPassenger_credits() {
        return passenger_credits;
    }

    public void setPassenger_credits(float passenger_credits) {
        this.passenger_credits = passenger_credits;
    }


    //private String passenger_contact;

    public Passenger( String passenger_fname, String passenger_lname, String passenger_email, String passenger_password) {
        this.passenger_fname = passenger_fname;
        this.passenger_lname = passenger_lname;
        this.passenger_email = passenger_email;
        this.passenger_password = passenger_password;
    }

    public Passenger(){}


    @Autowired
    PassengerDAO passengerDAO;

    @Autowired
    DriverDAO driverDAO;

    @Autowired
    private Notification notification;


    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    public String getPassenger_fname() {
        return passenger_fname;
    }

    public void setPassenger_fname(String passenger_fname) {
        this.passenger_fname = passenger_fname;
    }

    public String getPassenger_lname() {
        return passenger_lname;
    }

    public void setPassenger_lname(String passenger_lname) {
        this.passenger_lname = passenger_lname;
    }

    public String getPassenger_email() {
        return passenger_email;
    }

    public void setPassenger_email(String passenger_email) {
        this.passenger_email = passenger_email;
    }

    public String getPassenger_password() {
        return passenger_password;
    }

    public void setPassenger_password(String passenger_password) {
        this.passenger_password = passenger_password;
    }


    @Override
    public boolean savePassenger(User user) {
        Passenger passenger=new Passenger(user.getFirst_name(),user.getLast_name(),user.getEmail(),user.getPassword());
        boolean isPassengerSaved= passengerDAO.save(passenger);
        if(isPassengerSaved)
        {
            notification.sendEmail(IAppMessages.USER_REGISTERED_NOTIFY_MESSAGE,IAppMessages.USER_REGISTERED_NOTIFY_SUBJECT,user.getEmail());
        }
        return isPassengerSaved;
    }

    @Override
    public Passenger getPassengerByEmail(String email) {
        Passenger passenger= (Passenger) passengerDAO.getObjectByEmail(email);
        return passenger;
    }

    @Override
    public Passenger getPassengerById(int id) {
        Passenger passenger= (Passenger) passengerDAO.getObjectById(id);
        return passenger;
    }


    //admin
    @Override
    public List<Passenger> viewPassengerDetails() {
        try{
            List<Object> passenger=  passengerDAO.getObjectsList();
            List<Passenger> passengerList= (List<Passenger>) (List) passenger;
            return passengerList;
        }
        catch(Exception exception){
            return null;

        }
    }

    @Override
    public boolean deletePassenger(int passenger_id) {
        return passengerDAO.removeObject(passenger_id);
    }

    @Override
    public boolean addCredits(String passenger_email, String columnName, float value) {
        Passenger passenger=getPassengerByEmail(passenger_email);
        float passenger_credits= passenger.getPassenger_credits()+value;
        boolean isUpdated= passengerDAO.updateObject(passenger_email, IAppConstants.PASSENGER_CREDITS,passenger_credits);
        return isUpdated;    }

    @Override
    public boolean debitCreditsFromPassenger(String passenger_email, String columnName, float value) {
        Passenger passenger=getPassengerByEmail(passenger_email);
        if(passenger.getPassenger_credits()<value)
        {
            return false;
        }
        float passenger_credits= passenger.getPassenger_credits()+value;
        boolean isUpdated= passengerDAO.updateObject(passenger_email, IAppConstants.PASSENGER_CREDITS,passenger_credits);
        return isUpdated;
    }

    @Override
    public boolean resetPassword(String email, String newPassword) {
        if (email == null) {
            return false;
        }
        else{
            return passengerDAO.resetPassword(email, newPassword);
        }


    }

    @Override
    public Passenger convertObject(){
        Passenger passenger= new Passenger(this.passenger_fname, this.passenger_lname, this.passenger_email, this.passenger_password);
        return passenger;
    }




}