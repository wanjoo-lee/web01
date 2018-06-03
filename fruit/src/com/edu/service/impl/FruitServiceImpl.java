package com.edu.service.impl;

import java.util.ArrayList;

import com.edu.beans.BuyerVo;
import com.edu.beans.SellerVo;
import com.edu.dao.FruitDao;
import com.edu.service.FruitService;

public class FruitServiceImpl implements FruitService {
	private FruitDao dao = new FruitDao();
	@Override
	public void updateBuyer(BuyerVo bvo) {
		dao.updateBuyer(bvo);
	}

	@Override
	public void updateSeller(SellerVo svo) {
		dao.updateSeller(svo);
	}

	@Override
	public ArrayList<SellerVo> getSellerList() {
		return dao.getSellerList();
	}
	
	@Override
	public void fruitBuy(BuyerVo bvo, SellerVo svo) {
		dao.updateBuyerFruitBuy(bvo);
		dao.updateSellerFruitBuy(svo);
	}


	@Override
	public void insertBuyer(BuyerVo vo) {
		dao.insertBuyer(vo);
	}

	@Override
	public void insertSeller(SellerVo vo) {
		dao.insertSeller(vo);
	}

	@Override
	public BuyerVo getBuyer(BuyerVo vo) {
		return dao.getBuyer(vo);
	}

	@Override
	public SellerVo getSeller(SellerVo vo) {
		return dao.getSeller(vo);
	}

}
