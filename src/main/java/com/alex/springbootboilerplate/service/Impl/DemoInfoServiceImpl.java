package com.alex.springbootboilerplate.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import com.alex.springbootboilerplate.dao.DemoInfoDAO;
import com.alex.springbootboilerplate.entity.DemoInfo;
import com.alex.springbootboilerplate.service.DemoInfoService;
import org.springframework.stereotype.Service;

@Service
public class DemoInfoServiceImpl implements DemoInfoService {

	@Resource
	DemoInfoDAO dao;

	@Override
	public List<DemoInfo> insertDemoInfo(DemoInfo demo) {
		// TODO Auto-generated method stub
		return dao.insertDemoInfo(demo);
	}

	@Override
	public DemoInfo findDemoInfo(String id) {
		// TODO Auto-generated method stub
		return dao.findDemoInfo(id);
	}

	@Override
	public List<DemoInfo> updateDemoInfo(DemoInfo demo) {
		// TODO Auto-generated method stub
		return dao.updateDemoInfo(demo);
	}

	@Override
	public List<DemoInfo> deleteDemoInfo(DemoInfo demo) {
		// TODO Auto-generated method stub
		return dao.deleteDemoInfo(demo);
	}

	@Override
	public List<DemoInfo> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

}
