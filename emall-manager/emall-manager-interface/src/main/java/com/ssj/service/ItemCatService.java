package com.ssj.service;

import java.util.List;

import com.ssj.commons.pojo.EasyUINodeTree;

public interface ItemCatService {

	public List<EasyUINodeTree> getItemCatList(long parentid);
}
