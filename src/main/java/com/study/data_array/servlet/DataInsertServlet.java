package com.study.data_array.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.google.gson.Gson;
import com.study.data_array.entity.DataObj;
import com.study.data_array.service.DataService;


@WebServlet("/data/addition")
public class DataInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataService dataService;
	
	public DataInsertServlet() {
		dataService = DataService.getInstance();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuilder json = new StringBuilder();//데이터 파라미터가 아닌 문자열
		String requestData = null;
		//문자열의 경우 stream을 통해 받는다.
		//문자열을 하나하나 잘라서 보낸다.
		BufferedReader reader = request.getReader(); //잘린 문자열을 쌓아서 줄바꿈을 기준으로 데이터를 저장 = json 데이터를 그대로 가져온다.
		while((requestData = reader.readLine()) != null) {//끝나는 경우 null 즉 마지막까지 불러온다	
			json.append(requestData);
		}
		Gson gson = new Gson();
		//json의 키와 dataObj의 필드 명칭을 통일해야한다. 
		DataObj dataObj =  gson.fromJson(json.toString(), DataObj.class);
		System.out.println(gson.toJson(dataObj));
		int responseBody = dataService.addData(dataObj);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("data", responseBody);
		responseMap.put("responseMessage", "데이터 추가 완료");
		response.setContentType("application/json");
		response.setStatus(201);
		response.getWriter().println(gson.toJson(responseMap));
	}
}
