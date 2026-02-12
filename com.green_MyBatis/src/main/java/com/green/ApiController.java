package com.green;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// @RestController는 @Controller + @ResponseBody를 합친 어노테이션이다.
// => 컨트롤러 역활 + 데이터를 JSON으로 응답하여 사용

import com.green.carproduct.CarProductDTO;
import com.green.carproduct.CarProductService;
import com.green.member.MemberDTO;
import com.green.member.MemberService;

import jakarta.servlet.http.HttpSession;

// @ResponseBody는 메소드가 변환하는 데이터를 HTML 뷰를 찾는 용도가 아니라,
// 데이터 그자체(JSON)로 응답받아 직접쓰겠다는 의미

// @RestController 하나만 맨 위에 적어주면 모든 메소드들은
// @ResponseBody를 붙이지 않아도 된다.

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	CarProductService carproductservice;//carList메소드
	
	@Autowired
	MemberService memberservice; 
	
	//자동차 리스트를 JSON으로 변환하는 API
	@GetMapping("/cars")
	public List<CarProductDTO> getCarList(){
		System.out.println("ApiController : 자동차 리스트 요청됨");
		//DB에서 데이터를 가져와서 그대로 리턴(Spring이 자동으로 JSON에 배열로 변환)
		//{no:~, carName:~, ~~}
		return carproductservice.getAllCarProduct();
	}
	//회원가입 API(POST방식)
	//@RequestBody 어노테이션은 리액트에서 보낸 JSON데이터를
	//-> 자바 객체(MemberDTO)로 자동 변환해준다.
	@PostMapping("/member/signup")
	public int signup(@RequestBody MemberDTO mdto) {
		System.out.println("ApiController : signup 요청됨");
		return memberservice.signupConfirm(mdto);
	}
	
	//로그인 메소드
	@PostMapping("/member/login")
	public MemberDTO login(@RequestBody MemberDTO mdto,HttpSession session) {
		System.out.println("ApiController : login 요청됨");
		//loginUser = {no : ~ , id: ~, pw: ~, mail ~~~~}
		MemberDTO loginUser = memberservice.loginConfirm(mdto);
		
		if(loginUser != null) {
			//세션에 로그인 정보담기
			session.setAttribute("loginUser", loginUser.getId());
		}
		//React로 JSON 변환
		return loginUser;
	}
	
	//로그아웃
	@GetMapping("/member/logout")
	public int logout(HttpSession session) {
		System.out.println("ApiController : logout 요청됨");

		session.invalidate();//세션삭제
		return 1;//성공
	}
	
	//한사람의 개인정보를 조회하는 메소드
	//select
	@GetMapping("/member/myinfo")
	public MemberDTO myInfo(HttpSession session) {
		System.out.println("ApiController : myInfo 요청됨");
		//세션에서 로그인한 사용자 꺼내기
		//다운캐스팅한다.
		String loginId = (String)session.getAttribute("loginUser");
		
		if(loginId == null) {
			//로그인이 안되있으면 null로 반환
			return null;			
		}
			//로그인이 되어 있으면 DB조회		
		
		return memberservice.oneSelect(loginId);
	}
	
	//한 사람 개인정보를 삭제하는 컨트롤러
	//삭제하다 => @DeleteMapping()
	@DeleteMapping("/member/delete")
	public int delete(HttpSession session) {
		String loginId = (String)session.getAttribute("loginUser");
		
		if(loginId == null) {
			return 0;
		}
		
		//삭제 서비스 메소드
		//삭제가 완료되면 1(true), 안되면 0(false)
		boolean result = memberservice.oneDelete(loginId);
		
		if(result) {
			//로그아웃
			session.invalidate();//세션삭제로 로그아웃
			return 1;
		}else {
			return 0;
		}		
	}
	
	
	
	
}
