package com.green.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddressController {
	//heap 메모리에 주소데이터를 담을 리스트가 필요
	//ArrayList<E>
	private List<AddressDTO> addressList = new ArrayList<>();
	//실제 DB는 이거안쓰고 @autowired해서 연결함. 지금은 임시저장으로 arraylist
	
	//1. 주소록 목록화면
	@GetMapping("/address")
	public String list(Model model) {
		model.addAttribute("list",addressList);//이 list를 html의 th:each에 써야됨
		return "address-list";
	}
	//2. 주소 등록화면
	@PostMapping("/add-address")
	public String addr(AddressDTO adto) {
		//ArrayList 삽입
		//삽입메소드 : add(value)
		addressList.add(adto);
		//현재 url은 add-address인데 => address로 이사함.
		return "redirect:/address";
	}
	
}
