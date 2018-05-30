package com.ssj.service;

import com.ssj.commons.pojo.EasyUIResult;
import com.ssj.commons.utils.EmallResult;
import com.ssj.pojo.TbItem;
import com.ssj.pojo.TbItemDesc;

public interface ItemService {

	// 根据商品ID查询商品
	public TbItem geTbItemById(long itemId);

	// 根据商品ID查询商品描述信息
	public TbItemDesc geTbItemDescById(long itemId);

	// 分页查询商品信息
	public EasyUIResult<TbItem> getTbItemList(int page, int rows);

	// 添加商品到数据库
	public EmallResult addItem(TbItem tbItem, String desc);

	// 更新商品
	public EmallResult updateItem(TbItem tbItem, String desc);

	// 从数据库中删除商品
	public EmallResult deleteItem(long[] ids);

	// 商品下架
	public EmallResult dowmItem(long[] ids);

	// 商品上架
	public EmallResult upItem(long[] ids);
}
