package com.green;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {
	@GetMapping("/product/order")
	public String orderInput() {
		System.out.println("orderInput()");
		return "order";
	}
	
	@GetMapping("/product/orderResult")
	public ModelAndView orderResult(
			@RequestParam("productName") String productName,
			@RequestParam("price") String price,
			@RequestParam("quantity") String quantity,
			@RequestParam("ordererName") String ordererName
			) {
		
			ModelAndView model = new ModelAndView();
			
			model.addObject("productName", productName);
			model.addObject("price", price);
			model.addObject("quantity", quantity);
			model.addObject("ordererName", ordererName);
			model.addObject("total", Integer.parseInt(price)*Integer.parseInt(quantity));
			
			System.out.println("orderResult()");
			
			model.setViewName("orderResult");
		
			return model;
	};
	
}
