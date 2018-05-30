package com.ssj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssj.commons.pojo.EasyUIResult;
import com.ssj.commons.utils.EmallResult;
import com.ssj.pojo.TbItem;
import com.ssj.pojo.TbItemDesc;
import com.ssj.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	// @RequestMapping("/rest/page/item-edit")
	// @ResponseBody
	// public TbItem getItemById1(long _) {
	// TbItem tbItem = itemService.geTbItemById(_);
	// return tbItem;
	// }
	@RequestMapping("/rest/item/query/item/desc/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable long itemId) {
		TbItem tbItem = itemService.geTbItemById(itemId);
		return tbItem;
	}

	@RequestMapping("/rest/item/param/item/query/{itemId}")
	@ResponseBody
	public TbItemDesc getItemDescById(@PathVariable long itemId) {
		TbItemDesc tbItemDesc = itemService.geTbItemDescById(itemId);
		return tbItemDesc;
	}

	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIResult<TbItem> getItemList(Integer page, Integer rows) {
		EasyUIResult<TbItem> result = itemService.getTbItemList(page, rows);
		return result;
	}

	@RequestMapping(value = "/item/save", method = RequestMethod.POST)
	@ResponseBody
	public EmallResult addItem(TbItem tbItem, String desc) {
		EmallResult result = itemService.addItem(tbItem, desc);
		return result;
	}

	/*
	 * 提交更新后的信息
	 */
	@RequestMapping(value = "/rest/item/update", method = RequestMethod.POST)
	@ResponseBody
	public EmallResult updateItem(TbItem tbItem, String desc) {
		EmallResult result = itemService.updateItem(tbItem, desc);
		return result;
	}

	/*
	 * 删除商品，假删除，将状态置为3
	 */
	@RequestMapping(value = "/rest/item/delete", method = RequestMethod.POST)
	@ResponseBody
	public EmallResult deleteItem(long[] ids) {
		EmallResult result = itemService.deleteItem(ids);
		return result;
	}

	/*
	 * 下架商品，将状态置为2
	 */
	@RequestMapping(value = "/rest/item/instock", method = RequestMethod.POST)
	@ResponseBody
	public EmallResult downItem(long[] ids) {
		EmallResult result = itemService.dowmItem(ids);
		return result;
	}

	/*
	 * 上架商品，将状态置为1
	 */
	@RequestMapping(value = "/rest/item/reshelf", method = RequestMethod.POST)
	@ResponseBody
	public EmallResult upItem(long[] ids) {
		EmallResult result = itemService.upItem(ids);
		return result;
	}
}
