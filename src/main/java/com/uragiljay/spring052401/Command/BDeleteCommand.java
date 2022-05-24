package com.uragiljay.spring052401.Command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.uragiljay.spring052401.Dao.BDao;

public class BDeleteCommand implements BCommand {

	@Override
	public void excute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		

		String bid = request.getParameter("bid");
		
		BDao dao = new BDao(); 
		dao.delete(bid);	
	
	}

}
