package com.dxc.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.application.model.GimHeader;
import com.dxc.application.mybatis.mappers.GimHeaderMapper;

@Service
public class HomeService {

	@Autowired
	GimHeaderMapper gimHeaderMapper;

	public List<GimHeader> selectAllGimHeader() {
		return gimHeaderMapper.selectAllGimHeader();
	}
	public GimHeader selectGimHeaderByGimType(GimHeader gimHeader) {
		return gimHeaderMapper.selectGimHeaderByGimType(gimHeader);
	}
}
