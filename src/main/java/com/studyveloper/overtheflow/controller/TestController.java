package com.studyveloper.overtheflow.controller;


import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.studyveloper.overtheflow.mapper.TestMapper;

@Controller
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	private TestMapper testMapper;
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(Locale locale, Model model){
		logger.info("welcome home!");
		List<String> result = this.testMapper.search();
		logger.info(result.size()+"");
		return "test";
	}
}