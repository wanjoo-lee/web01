package test;

import java.util.ArrayList;

import com.edu.beans.BuyerVo;
import com.edu.beans.SellerVo;
import com.edu.dao.FruitDao;

public class DaoTest {
	public static void main(String[] args) {
		BuyerVo bvo = new BuyerVo();
		SellerVo svo = new SellerVo();
		FruitDao dao = new FruitDao();
		ArrayList<SellerVo> sellerList = dao.getSellerList();

		sellerList.forEach((seller) -> {
			System.out.println(seller.getId() + ", " + seller.getMoney() + ", " + seller.getApple_count() + ", " + seller.getApple_price());
		});

		svo.setId("s02");
		svo.setApple_count(13);
		bvo.setId("b02");
		bvo.setApple_count(13);
		bvo.setApple_price(1200);
		dao.updateBuyerFruitBuy(bvo);
		dao.updateSellerFruitBuy(svo);
		sellerList = dao.getSellerList();
		sellerList.forEach((seller) -> {
			System.out.println(seller.getId() + ", " + seller.getMoney() + ", " + seller.getApple_count() + ", " + seller.getApple_price());
		});
		
		try {
			bvo.setId("b02");
			bvo = dao.getBuyer(bvo);

			System.out.println("Id: " + bvo.getId());
			System.out.println("Passwd: " + bvo.getPasswd());
			System.out.println("Money: " + bvo.getMoney());
			System.out.println("Count: " + bvo.getApple_count());
			System.out.println("Price: " + bvo.getApple_price());
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}

		
		svo.setId("s02");
		svo = dao.getSeller(svo);
		
		System.out.println("Id: " + svo.getId());
		System.out.println("Passwd: " + svo.getPasswd());
		System.out.println("Money: " + svo.getMoney());
		System.out.println("Count: " + svo.getApple_count());
		System.out.println("Price: " + svo.getApple_price());
	}
}
