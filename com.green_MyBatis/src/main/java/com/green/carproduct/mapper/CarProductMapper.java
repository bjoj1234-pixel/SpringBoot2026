package com.green.carproduct.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.carproduct.CarProductDTO;

@Mapper
public interface CarProductMapper {
	//추상메소드
	//carProduct 상품 모두 검색하는 메소드
	//getAllCarProduct 메소드는  - mapper 연결하여 select 쿼리문 작성
	public List<CarProductDTO> getAllCarProduct();
	
}
