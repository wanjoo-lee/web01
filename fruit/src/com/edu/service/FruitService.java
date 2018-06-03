package com.edu.service;

import java.util.ArrayList;

import com.edu.beans.BuyerVo;
import com.edu.beans.SellerVo;

public interface FruitService {

	void updateBuyer(BuyerVo bvo);

	void updateSeller(SellerVo svo);

	public ArrayList<SellerVo> getSellerList();
	
	void fruitBuy(BuyerVo bvo, SellerVo svo);

	void insertBuyer(BuyerVo vo);

	void insertSeller(SellerVo vo);

	BuyerVo getBuyer(BuyerVo vo);

	SellerVo getSeller(SellerVo vo);

}