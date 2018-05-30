package com.ssj.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssj.commons.pojo.EasyUIResult;
import com.ssj.commons.utils.EmallResult;
import com.ssj.commons.utils.IDUtils;
import com.ssj.dao.TbItemDescMapper;
import com.ssj.dao.TbItemMapper;
import com.ssj.pojo.TbItem;
import com.ssj.pojo.TbItemDesc;
import com.ssj.pojo.TbItemExample;
import com.ssj.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;

	
	 @Override
	public TbItem geTbItemById(long itemId) {
		// TODO Auto-generated method stub
		// TbItemKey tbItemKey = new TbItemKey();
		// tbItemKey.setId(itemId);
		TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
		return tbItem;
	}

	@Override
	public EasyUIResult<TbItem> getTbItemList(int page, int rows) {

		// 设置分页信息
		PageHelper.startPage(page, rows);
		// 执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		// 取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		// 返回查询结果
		EasyUIResult<TbItem> result = new EasyUIResult<>();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	@Override
	public EmallResult addItem(TbItem tbItem, String desc) {
		// 生成商品ID
		long itemId = IDUtils.genItemId();
		// 设置商品属性
		tbItem.setId(itemId);
		tbItem.setStatus((byte) 1);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		tbItemMapper.insert(tbItem);

		// 添加商品描述信息到商品描述表
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
		tbItemDescMapper.insert(tbItemDesc);

		return EmallResult.ok();
	}

	@Override
	public TbItemDesc geTbItemDescById(long itemId) {
		TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
		return tbItemDesc;
	}

	@Override
	public EmallResult updateItem(TbItem tbItem, String desc) {
		// TODO Auto-generated method stub
		tbItem.setUpdated(new Date());
		int updateItem = tbItemMapper.updateByPrimaryKeySelective(tbItem);
				//updateByPrimaryKey(tbItem);
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(tbItem.getId());
		tbItemDesc.setUpdated(new Date());
		tbItemDesc.setItemDesc(desc);
		int updateDesc = tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
		return EmallResult.ok();
	}

	@Override
	public EmallResult deleteItem(long[] ids) {
		// 1 上架  2：下架  3：删除 
		
//		
//		for(long id : ids) {
//		int deleteResult = tbItemMapper.deleteByPrimaryKey(id);
//		int deletedescResult = tbItemDescMapper.deleteByPrimaryKey(id);
//		}
		
		for(long id : ids) {
			TbItem tbItem = geTbItemById(id);
			// 1 上架  2：下架  3：删除 
			tbItem.setStatus((byte)3);
			tbItemMapper.updateByPrimaryKeySelective(tbItem);
			}
		return EmallResult.ok();
	}

	@Override
	public EmallResult dowmItem(long[] ids) {
		for(long id : ids) {
			TbItem tbItem = geTbItemById(id);
			// 1 上架  2：下架  3：删除 
			tbItem.setStatus((byte)2);
			tbItemMapper.updateByPrimaryKeySelective(tbItem);
			}
		return EmallResult.ok();
	}

	@Override
	public EmallResult upItem(long[] ids) {
		for(long id : ids) {
			TbItem tbItem = geTbItemById(id);
			// 1 上架  2：下架  3：删除 
			tbItem.setStatus((byte)1);
			tbItemMapper.updateByPrimaryKeySelective(tbItem);
			}
		return EmallResult.ok();
	}

}
