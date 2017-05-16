package cn.gavid.invoiceSystem.dataHandler.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gavid.invoiceSystem.dataHandler.dao.OutputTaxDaoImpl;
import cn.gavid.invoiceSystem.dataHandler.domain.OutputTax;

/**
 * 销项数据业务层
 * @author lenove
 *
 */
public class OutputTaxService {

	private OutputTaxDaoImpl outputTaxDaoImpl = new OutputTaxDaoImpl();
	/**
	 * 返回商品及其对应的总税额
	 * @return
	 */
	public Map<String,BigDecimal> amountTaxDate(){
		List<OutputTax> outputTaxList = outputTaxDaoImpl.findAll();
		Map<String,BigDecimal> map = new HashMap<String, BigDecimal>();
		boolean flag = true;
		for(OutputTax out : outputTaxList){
			//如果map键相同，进行值相加
			for(String iname : map.keySet()){
				if(out.getItemName().equals(iname)){
					map.put(iname, map.get(iname).add(out.getAmountTax()));
					flag = false;
				}
			}
			if(flag)
				map.put(out.getItemName(), out.getAmountTax());
			flag = true;
		}
		return map;
	}
	
	/**
	 * 返回商品及其对应的含税总额
	 * @return
	 */
	public Map<String,BigDecimal> includeTaxTotalDate(){ 
		List<OutputTax> intputTaxList = outputTaxDaoImpl.findAll();
		Map<String,BigDecimal> map = new HashMap<String, BigDecimal>();
		boolean flag = true;
		for(OutputTax out : intputTaxList){
			//如果map键相同，进行值相加
			for(String iname : map.keySet()){
				if(out.getItemName().equals(iname)){
					map.put(iname, map.get(iname).add(out.getTotalMoney_includeTax()));
					flag = false;
				}
			}
			if(flag)
				map.put(out.getItemName(), out.getTotalMoney_includeTax());
			flag = true;
		}
		return map;
	}

	/**
	 * 返回企业进项数据的月税额数据
	 * @return
	 */
	public Map<Integer,BigDecimal> getMonthOutTaxData() { 
		Map<Integer,BigDecimal> map = new HashMap<Integer, BigDecimal>();
		List<OutputTax> outlist = outputTaxDaoImpl.findAll();
		Calendar calendar = Calendar.getInstance();
		boolean flag = true;
		for(OutputTax out : outlist){
			for(Integer integer : map.keySet()){
				calendar.setTime(new Date(out.getTimer().getTime()));
				int month = calendar.get(Calendar.MONTH)+1;
				if(month == integer.intValue()){
					map.put(month, map.get(month).add(out.getAmountTax()));
					flag = false;
				}
			}
			if(flag)
				calendar.setTime(new Date(out.getTimer().getTime()));
				map.put(calendar.get(Calendar.MONTH)+1, out.getAmountTax());
			flag = true;
		}
		return map;
	}
	
	/**
	 * 返回企业进项数据的周税额数据
	 * @return
	 */
	public Map<Integer,BigDecimal> getWeekOutTaxData() { 
		Map<Integer,BigDecimal> map = new HashMap<Integer, BigDecimal>();
		List<OutputTax> outlist = outputTaxDaoImpl.findAll();
		boolean flag = true;
		for(OutputTax out : outlist){
			for(Integer integer : map.keySet()){
				int week = getYear_Week(out);
				if(week == integer.intValue()){
					map.put(week, map.get(week).add(out.getAmountTax()));
					flag = false;
				}
			}
			if(flag)
				map.put(getYear_Week(out), out.getAmountTax());
			flag = true;
		}
		return map;
	}
	
	/**
	 * 返回该对象的日期所对应的所在年的第几周
	 * @param in
	 * @return
	 */
	public int getYear_Week(OutputTax out){
		Date date = new Date(out.getTimer().getTime());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
}
