package com.ssj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssj.commons.pojo.EasyUINodeTree;
import com.ssj.service.ItemCatService;

@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUINodeTree> getItemCatList(@RequestParam(value="id",defaultValue="0") Long parentId){
		List<EasyUINodeTree> list = itemCatService.getItemCatList(parentId);
		return list;
	}
}