package com.uragiljay.spring052401.Command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.uragiljay.spring052401.Dao.*;
import com.uragiljay.spring052401.Dto.*;

public class BListCommand implements BCommand {

	@Override
	public void excute(Model model) {
		// TODO Auto-generated method stub

		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		model.addAttribute("list", dtos);
		
	}

}
