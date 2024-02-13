package com.study.data_array.service;

import com.study.data_array.entity.DataObj;
import com.study.data_array.repository.DataRepository;

public class DataService {
	private static DataService instance;
	private DataRepository dataRepository;
	private DataService() {
		dataRepository = DataRepository.getInstance();
	}
	public static DataService getInstance() {
		if(instance == null) {
			instance = new DataService();
		}
		return instance;
	}
	
	public int addData(DataObj dataObj) {
		return dataRepository.save(dataObj);
	}
}
