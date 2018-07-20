package com.dxc.application.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dxc.application.model.GimHeader;
import com.dxc.application.services.HomeService;

import th.co.toyota.sc2.client.CSC22110Constant;
import th.co.toyota.sc2.client.model.simple.CSC22110UserInfo;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	HomeService homeService;

	@RequestMapping("/home")
	public String home(HttpServletRequest request) {
		logger.debug("555555555555");
		CSC22110UserInfo simpleUser = (CSC22110UserInfo) request.getSession()
				.getAttribute(CSC22110Constant.SESSION_USER_INFO);
		logger.info("simpleUser.getCountry() >>>>> " + simpleUser.getCountry());
		logger.info("simpleUser.getCompanyType() >>>> " + simpleUser.getCompanyType());
		logger.info("simpleUser.getCompany() >>>> " + simpleUser.getCompany());
		logger.info("simpleUser.getLocation() >>>> " + simpleUser.getLocation());
		logger.info("simpleUser.getUserId() >>>> " + simpleUser.getUserId());

		List<GimHeader> allGimHeaderData = homeService.selectAllGimHeader();
		for (GimHeader gimHeader : allGimHeaderData) {
			logger.info("GIM_TYPE >>>>> " + gimHeader.getGimType());
			logger.info("CREATED BY >>>>> " + gimHeader.getCreatedBy());
			logger.info("CREATED DT >>>>> " + gimHeader.getCreatedDt());
		}
		GimHeader gimHeaderData = new GimHeader();
		gimHeaderData.setGimType("ACL_BUTTON");
		gimHeaderData = homeService.selectGimHeaderByGimType(gimHeaderData);
		logger.info("GIM_TYPE >>>>> " + gimHeaderData.getGimType());
		logger.info("GIM_DESC >>>>> " + gimHeaderData.getGimDesc());
		logger.info("GIM_CD_LENGTH >>>>> " + gimHeaderData.getCdLength());

		return "pages/view/home.html";
	}

	@RequestMapping("/home/js/home.js")
	public String js() {
		logger.debug("777777");
		return "pages/js/home.js";
	}
}
