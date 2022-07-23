package com.example.vehiclesharing.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.vehiclesharing.constants.*;
import com.example.vehiclesharing.model.*;

public class VehicleController {
	@Autowired
	Driver driver;

	@Autowired
	IVehicle vehicle;

	@Autowired
	IRide ride;

	@PostMapping("/create-ride")
	public String createNewRide(IRide rideData, BindingResult result, Model model, HttpSession httpSession) {
		if (ride.createRide(rideData)) {
			model.addAttribute("messageStatus", VehicleStringMessage.SUCCESSFUL);
			model.addAttribute("message", VehicleStringMessage.CREATED_NEW_RIDE);
		} else {
			model.addAttribute("messageStatus", VehicleStringMessage.FAILED);
			model.addAttribute("message", VehicleStringMessage.ERROR);
		}
		int driver_id = vehicle.getVehicleDetails(rideData.getVehicle_id()).getDriver_id();
		Driver dr = driver.getDriverById(driver_id);
		model.addAttribute("driver", dr);
		model.addAttribute("listOfVehicle", vehicle.getVehicles(dr.getDriver_id()));
		List<Ride> previousridesFordriver = ride.pastRidesOfDriver(dr.getDriver_id());
		httpSession.setAttribute("previousRides", previousridesFordriver);
		List<Ride> upcomingridesFordriver = ride.upcomingRidesOfDriver(dr.getDriver_id());
		httpSession.setAttribute("upcomingRides", upcomingridesFordriver);
		return "dashboardOwner";
	}

	@PostMapping("/add-vehicle")
	public String addVehicle(IVehicle vehicle, Model model, HttpSession session) {
		if (vehicle.NewVehicle(vehicle)) {
			model.addAttribute("messageStatus", VehicleStringMessage.SUCCESSFUL);
			model.addAttribute("message", VehicleStringMessage.ADDED_NEW_CAR);
		} else {
			model.addAttribute("messageStatus", VehicleStringMessage.FAILED);
			model.addAttribute("message", VehicleStringMessage.ERROR);
		}
		Driver dr = driver.getDriverById(vehicle.getDriver_id());
		session.setAttribute("driver", dr);
		session.setAttribute("listOfVehicle", vehicle.getVehicles(dr.getDriver_id()));
		return "owner-dashboard";
	}

	@RequestMapping("/ride-history")
	public String showRideHistory(HttpSession session, Model model) {
		return "ride-history";
	}

	@RequestMapping("/dashboard_owner")
	public String showOwnerDashboard(HttpSession session, Model model) {
		return "dashboardOwner";
	}
}