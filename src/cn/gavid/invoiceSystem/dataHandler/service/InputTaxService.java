package cn.gavid.invoiceSystem.dataHandler.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gavid.invoiceSystem.dataHandler.dao.InputTaxDaoImpl;
import cn.gavid.invoiceSystem.dataHandler.domain.InputTax;

/**
 * 进项数据业务层
 * @author lenove
 *
 */
public class InputTaxService {

	private InputTaxDaoImpl inputTaxDaoImpl = new InputTaxDaoImpl();
	
	/**
	 * 返回商品及其对应的总税额
	 * @return
	 */
	public Map<String,BigDecimal> amountTaxDate(){
		List<InputTax> intputTaxList = inputTaxDaoImpl.findAll();
		Map<String,BigDecimal> map = new HashMap<String, BigDecimal>();
		boolean flag = true;
		for(InputTax in : intputTaxList){
			//如果map键相同，进行值相加
			for(String iname : map.keySet()){
				if(in.getItemName().equals(iname)){
					map.put(iname, map.get(iname).add(in.getAmountTax()));
					flag = false;
				}
			}
			if(flag)
				map.put(in.getItemName(), in.getAmountTax());
			flag = true;
		}
		return map;
	}
	
	/**
	 * 返回商品及其对应的含税总额
	 * @return
	 */
	public Map<String,BigDecimal> includeTaxTotalDate(){ 
		List<InputTax> intputTaxList = inputTaxDaoImpl.findAll();
		Map<String,BigDecimal> map = new HashMap<String, BigDecimal>();
		boolean flag = true;
		for(InputTax in : intputTaxList){
			//如果map键相同，进行值相加
			for(String iname : map.keySet()){
				if(in.getItemName().equals(iname)){
					map.put(iname, map.get(iname).add(in.getTotalMoney_includeTax()));
					flag = false;
				}
			}
			if(flag)
				map.put(in.getItemName(), in.getTotalMoney_includeTax());
			flag = true;
		}
		return map;
	}

	/**
	 * 返回企业进项数据的月税额数据
	 * @return
	 */
	public Map<Integer,BigDecimal> getMonthInTaxData() { 
		Map<Integer,BigDecimal> map = new HashMap<Integer, BigDecimal>();
		List<InputTax> inlist = inputTaxDaoImpl.findAll();
		Calendar calendar = Calendar.getInstance();
		boolean flag = true;
		for(InputTax in : inlist){
			for(Integer integer : map.keySet()){
				calendar.setTime(new Date(in.getTimer().getTime()));
				int month = calendar.get(Calendar.MONTH)+1;
				if(month == integer.intValue()){
					map.put(month, map.get(month).add(in.getAmountTax()));
					flag = false;
				}
			}
			if(flag)
				calendar.setTime(new Date(in.getTimer().getTime()));
				map.put(calendar.get(Calendar.MONTH)+1, in.getAmountTax());
			flag = true;
		}
		return map;
	}
	
	/**
	 * 返回企业进项数据的周税额数据
	 * @return
	 */
	public Map<Integer,BigDecimal> getWeekInTaxData() { 
		Map<Integer,BigDecimal> map = new HashMap<Integer, BigDecimal>();
		List<InputTax> inlist = inputTaxDaoImpl.findAll();
		boolean flag = true;
		for(InputTax in : inlist){
			for(Integer integer : map.keySet()){
				int week = getYear_Week(in);
				if(week == integer.intValue()){
					map.put(week, map.get(week).add(in.getAmountTax()));
					flag = false;
				}
			}
			if(flag)
				map.put(getYear_Week(in), in.getAmountTax());
			flag = true;
		}
		return map;
	}
	
	/**
	 * 返回该对象的日期所对应的所在年的第几周
	 * @param in
	 * @return
	 */
	public int getYear_Week(InputTax in){
		Date date = new Date(in.getTimer().getTime());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
}
