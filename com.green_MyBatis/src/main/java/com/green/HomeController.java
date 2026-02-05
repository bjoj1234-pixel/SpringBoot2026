package com.green;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.green.carproduct.CarProductDTO;
import com.green.carproduct.CarProductService;

@Controller
public class HomeController {
	
	@Autowired
	CarProductService carProductservice; 
	
	//http://localhost:8090 또는
	//http://localhost:8090/ 
	@GetMapping({"","/"})
	public String home(Model model) {
		//syso => log찍는 용도임, 반드시 필요
		System.out.println("HomeController 확인");
		
		//List<CarProductDTO>
		//carProductservice.getAllCarProduct()는
		//	1	Veyron	2000000000	Bugatti	1.jpg	고성능 슈퍼카입니다.
		// 위의 자료를 DB에서 꺼내와 List => ArrayList<>배열에 저장
		List<CarProductDTO> carlist =  carProductservice.getAllCarProduct();
		
		//cartlist를 model로 담아 home.html로 내보낸다.
		//단, model은 한번 담아 내보내면 다른 페이지로 이동해도 자료를 가지고 갈수없다. 
		model.addAttribute("carlist",carlist);
		
		return "home";
	}
}
