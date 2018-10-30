package com.alex.springbootboilerplate.service;

import java.util.List;

import com.alex.springbootboilerplate.entity.DemoInfo;

public interface DemoInfoService {

	List<DemoInfo> insertDemoInfo(DemoInfo demo);
	
	DemoInfo findDemoInfo(String id);
	
	List<DemoInfo> updateDemoInfo(DemoInfo demo);
	
	List<DemoInfo> deleteDemoInfo(DemoInfo demo);
	
	List<DemoInfo> findAll();
}
