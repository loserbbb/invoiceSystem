package cn.gavid.invoiceSystem.dataHandler.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import cn.gavid.invoiceSystem.dataHandler.service.InputTaxService;
import cn.gavid.invoiceSystem.dataHandler.service.OutputTaxService;
import cn.itcast.servlet.BaseServlet;

public class ContrastServlet extends BaseServlet {

	private InputTaxService inputTaxService = new InputTaxService();
	private OutputTaxService outputTaxService = new OutputTaxService();
	
	/**
	 * 进销项季度税额对比的JSON数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getQuarterData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 进项季度数据
		 */
		Map<Integer,BigDecimal> monthInData = inputTaxService.getMonthInTaxData();
		List<String> quarterkeys = new ArrayList<String>();
		quarterkeys.add("第一季度");
		quarterkeys.add("第二季度");
		quarterkeys.add("第三季度");
		quarterkeys.add("第四季度");
		List<String> inquartervalues = new ArrayList<String>();
		int flag1 = 3;//第一季度从3月份开始
		for(int i = 0;i<quarterkeys.size();i++){
			inquartervalues.add("0");
			//第四季度特殊处理
			if(flag1 == 12){
				for(Integer integer : monthInData.keySet()){
					if(integer.intValue() == 1 || integer.intValue() == 2 || integer.intValue() == 12){
						inquartervalues.set(i, (new BigDecimal(inquartervalues.get(i)))
								.add(monthInData.get(integer)).toString());
					}
				}
				break;
			}
			for(Integer integer : monthInData.keySet()){
				if(integer.intValue() >= flag1 && integer.intValue() < flag1+3){
					inquartervalues.set(i, (new BigDecimal(inquartervalues.get(i)))
							.add(monthInData.get(integer)).toString());
				}
			}
			flag1 = flag1+3;
		}
		Map<String ,List<String>> map = new HashMap<String, List<String>>();
		map.put("keys", quarterkeys);
		map.put("invalues", inquartervalues);
		
		/*
		 * 销项季度数据
		 */
		Map<Integer,BigDecimal> monthOutData = outputTaxService.getMonthOutTaxData();
		List<String> outquartervalues = new ArrayList<String>();
		flag1 = 3;//第一季度从3月份开始
		for(int i = 0;i<quarterkeys.size();i++){
			outquartervalues.add("0");
			//第四季度特殊处理
			if(flag1 == 12){
				for(Integer integer : monthOutData.keySet()){
					if(integer.intValue() == 1 || integer.intValue() == 2 || integer.intValue() == 12){
						outquartervalues.set(i, (new BigDecimal(outquartervalues.get(i)))
								.add(monthOutData.get(integer)).toString());
					}
				}
				break;
			}
			for(Integer integer : monthOutData.keySet()){
				if(integer.intValue() >= flag1 && integer.intValue() < flag1+3){
					outquartervalues.set(i, (new BigDecimal(outquartervalues.get(i)))
							.add(monthOutData.get(integer)).toString());
				}
			}
			flag1 = flag1+3;
		}
		map.put("outvalues", outquartervalues);
		JSONObject jo = JSONObject.fromObject(map);
		response.getWriter().print(jo.toString());
	}
	
	/**
	 * 进销项月度税额对比的JSON数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getMonthData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 向客户端发送json串
		 */
		Map<Integer,BigDecimal> monthInData = inputTaxService.getMonthInTaxData();
		List<String> monthkeys = new ArrayList<String>();
		List<String> inmonthvalues = new ArrayList<String>();
		boolean flag = true;
		for(int i = 1;i<=12;i++){
			monthkeys.add(i+"月");
			for(Integer integer : monthInData.keySet()){
				if(integer.intValue() == i){
					inmonthvalues.add(monthInData.get(integer).toString());
					flag = false;
					break;
				}
			}
			if(flag){
				inmonthvalues.add("0");
			}
			flag = true;
		}
		Map<String ,List<String>> map = new HashMap<String, List<String>>();
		map.put("keys", monthkeys);
		map.put("invalues", inmonthvalues);
		
		Map<Integer,BigDecimal> monthOutData = outputTaxService.getMonthOutTaxData();
		List<String> outmonthvalues = new ArrayList<String>();
		flag = true;
		for(int i = 1;i<=12;i++){
			for(Integer integer : monthOutData.keySet()){
				if(integer.intValue() == i){
					outmonthvalues.add(monthOutData.get(integer).toString());
					flag = false;
					break;
				}
			}
			if(flag){
				outmonthvalues.add("0");
			}
			flag = true;
		}
		map.put("outvalues", outmonthvalues);
		JSONObject jo = JSONObject.fromObject(map);
		System.out.println(jo.toString());
		response.getWriter().print(jo.toString());
	}
	
	/**
	 * 进销项周度税额对比的JSON数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getWeekData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 进项周度数据
		 */
		Map<Integer,BigDecimal> weekInData = inputTaxService.getWeekInTaxData();
		List<String> weekkeys = new ArrayList<String>();
		List<String> inweekvalues = new ArrayList<String>();
		boolean flag = true;
		for(int i = 1;i<=52;i++){
			weekkeys.add("第"+i+"周");
			for(Integer integer : weekInData.keySet()){
				if(integer.intValue() == i){
					inweekvalues.add(weekInData.get(integer).toString());
					flag = false;
					break;
				}
			}
			if(flag){
				inweekvalues.add("0");
			}
			flag = true;
		}
		Map<String ,List<String>> map = new HashMap<String, List<String>>();
		map.put("keys", weekkeys);
		map.put("invalues", inweekvalues);
		
		/*
		 * 销项周度数据
		 */
		Map<Integer,BigDecimal> weekOutData = outputTaxService.getWeekOutTaxData();
		List<String> outweekvalues = new ArrayList<String>();
		flag = true;
		for(int i = 1;i<=52;i++){
			for(Integer integer : weekOutData.keySet()){
				if(integer.intValue() == i){
					outweekvalues.add(weekOutData.get(integer).toString());
					flag = false;
					break;
				}
			}
			if(flag){
				outweekvalues.add("0");
			}
			flag = true;
		}
		map.put("outvalues", outweekvalues);
		JSONObject jo = JSONObject.fromObject(map);
		response.getWriter().print(jo.toString());
	}
}
