package com.ssj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssj.commons.pojo.EasyUINodeTree;
import com.ssj.dao.TbItemCatMapper;
import com.ssj.pojo.TbItemCat;
import com.ssj.pojo.TbItemCatExample;
import com.ssj.pojo.TbItemCatExample.Criteria;
import com.ssj.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<EasyUINodeTree> getItemCatList(long parentid) {
		// TODO Auto-generated method stub
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentid);
		List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(example);
		List<EasyUINodeTree> resultList = new ArrayList<>();
		
		for(TbItemCat tbItemCat:tbItemCats) {
			EasyUINodeTree nodeTree = new EasyUINodeTree();
			nodeTree.setId(tbItemCat.getId());
			nodeTree.setText(tbItemCat.getName());
			nodeTree.setState(tbItemCat.getIsParent()?"closed":"open");
			resultList.add(nodeTree);
		}
		
		return resultList;
	}

}
