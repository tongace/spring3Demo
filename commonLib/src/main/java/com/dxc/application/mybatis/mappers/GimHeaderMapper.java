package com.dxc.application.mybatis.mappers;

import java.util.List;

import com.dxc.application.model.GimHeader;

public interface GimHeaderMapper {
	 List<GimHeader> selectAllGimHeader();
	 GimHeader selectGimHeaderByGimType(GimHeader gimHeader);
	 void save(GimHeader gimHeader);
}
