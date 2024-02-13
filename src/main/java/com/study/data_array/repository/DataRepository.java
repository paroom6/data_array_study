package com.study.data_array.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.study.data_array.config.DBConnectionMgr;
import com.study.data_array.entity.DataObj;

public class DataRepository {
	private static DataRepository instance;
	private DBConnectionMgr pool;
	private DataRepository() {
		pool = DBConnectionMgr.getInstance();
	}
	public static DataRepository getInstance() {
		if(instance == null) {
			instance = new DataRepository();
		}
		return instance;
	}
	
	public int save(DataObj dataObj) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int successCount = 0;
		try {
			con = pool.getConnection();
			String sql = "insert into data_array_tb(content) values(?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dataObj.getTextContent());
			successCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return successCount;
	}
}
