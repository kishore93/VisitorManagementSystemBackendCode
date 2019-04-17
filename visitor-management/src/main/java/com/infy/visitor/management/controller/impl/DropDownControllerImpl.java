package com.infy.visitor.management.controller.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.infy.visitor.management.controller.DropDownController;
import com.infy.visitor.management.entity.CardType;
import com.infy.visitor.management.entity.Location;
import com.infy.visitor.management.entity.VisitorType;
import com.infy.visitor.management.repository.CardTypeRepository;
import com.infy.visitor.management.repository.LocationRepository;
import com.infy.visitor.management.repository.VisitorTypeRepository;

@RestController
public class DropDownControllerImpl implements DropDownController{

	@Autowired
	private CardTypeRepository cardTypeRepository;
	@Autowired
	private VisitorTypeRepository visitorTypeRepository;
	@Autowired
	private LocationRepository locationRepository;
	
	@Override
	public ResponseEntity<?> cardTypes() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<CardType>>(cardTypeRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> visitorTypes() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<VisitorType>>(visitorTypeRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> locations() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<Location>>(locationRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> dropdown() {
		HashMap<String, List<?>> allDropDown=new HashMap<>();
		allDropDown.put("locations", locationRepository.findAll());
		allDropDown.put("visitorTypes", visitorTypeRepository.findAll());
		allDropDown.put("cardTypes", cardTypeRepository.findAll());
		return new ResponseEntity<HashMap<String, List<?>>>(allDropDown, HttpStatus.OK);
	}

}
