package com.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.edu.beans.BuyerVo;
import com.edu.beans.SellerVo;
import com.edu.common.JDBCUtil;

public class FruitDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private final String BUYER_INSERT = "insert into buyer(id, passwd) values (?,?)";
	private final String SELLER_INSERT = "insert into seller(id, passwd) values (?,?)";
	private final String BUYER_GET = "select * from buyer where id=?";
	private final String SELLER_GET = "select * from seller where id=?";
	private final String BUYER_MONEY_REGIST = "update buyer set money=money+? where id=?";
	private final String SELLER_FRUIT_REGIST = "update seller set apple_count=?, apple_price=? where id=?";
	private final String FRUIT_BUY_SELLER_LIST = "select * from seller";
	private final String FRUIT_BUY_SELLER_UPDATE = "update seller set apple_count=apple_count-?, money=money+apple_price*? where id=?";
	private final String FRUIT_BUY_BUYER_UPDATE = "update buyer set apple_count=apple_count+?, money=money-apple_price*? where id=?";

	public void updateBuyer(BuyerVo bvo) {
		System.out.println("==> Update Buyer");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BUYER_MONEY_REGIST);
			pstmt.setInt(1, bvo.getMoney());
			pstmt.setString(2, bvo.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}

	public void updateSeller(SellerVo svo) {
		System.out.println("==> Update Seller");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(SELLER_FRUIT_REGIST);
			pstmt.setInt(1, svo.getApple_count());
			pstmt.setInt(2, svo.getApple_price());
			pstmt.setString(3, svo.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}

	public void updateBuyerFruitBuy(BuyerVo bvo) {
		System.out.println("==> Fruit Buy : update Buyer Info");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(FRUIT_BUY_BUYER_UPDATE);
			pstmt.setInt(1, bvo.getApple_count());
			pstmt.setInt(2, bvo.getApple_count());
			pstmt.setString(3, bvo.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}

	public void updateSellerFruitBuy(SellerVo svo) {
		System.out.println("==> Fruit Buy : update Seller Info");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(FRUIT_BUY_SELLER_UPDATE);
			pstmt.setInt(1, svo.getApple_count());
			pstmt.setInt(2, svo.getApple_count());
			pstmt.setString(3, svo.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}

	public ArrayList<SellerVo> getSellerList() {
		ArrayList<SellerVo> sellerList = new ArrayList<SellerVo>();
		System.out.println("==> Fruit Buy : Seller List ");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(FRUIT_BUY_SELLER_LIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SellerVo seller = new SellerVo();
				seller.setId(rs.getString("ID"));
				seller.setPasswd(rs.getString("PASSWD"));
				seller.setMoney(rs.getInt("MONEY"));
				seller.setApple_count(rs.getInt("APPLE_COUNT"));
				seller.setApple_price(rs.getInt("APPLE_PRICE"));
				sellerList.add(seller);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return sellerList;
	}

	public void insertBuyer(BuyerVo vo) {
		System.out.println("==> insert Buyer : " + vo.getId() + "," + vo.getPasswd());
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BUYER_INSERT);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPasswd());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}

	public void insertSeller(SellerVo vo) {
		System.out.println("==> insert Seller : " + vo.getId() + "," + vo.getPasswd());
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(SELLER_INSERT);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPasswd());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}

	public BuyerVo getBuyer(BuyerVo vo) {
		BuyerVo buyer = null;
		System.out.println("==> Buyer Info ");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BUYER_GET);
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				buyer = new BuyerVo();
				buyer.setId(rs.getString("ID"));
				buyer.setPasswd(rs.getString("PASSWD"));
				buyer.setMoney(rs.getInt("MONEY"));
				buyer.setApple_count(rs.getInt("APPLE_COUNT"));
				buyer.setApple_price(rs.getInt("APPLE_PRICE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
		return buyer;
	}

	public SellerVo getSeller(SellerVo vo) {
		SellerVo seller = null;
		System.out.println("==> Seller Info ");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(SELLER_GET);
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				seller = new SellerVo();
				seller.setId(rs.getString("ID"));
				seller.setPasswd(rs.getString("PASSWD"));
				seller.setMoney(rs.getInt("MONEY"));
				seller.setApple_count(rs.getInt("APPLE_COUNT"));
				seller.setApple_price(rs.getInt("APPLE_PRICE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return seller;
	}

}
