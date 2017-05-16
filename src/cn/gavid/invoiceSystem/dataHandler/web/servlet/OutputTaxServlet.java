package cn.gavid.invoiceSystem.dataHandler.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import cn.gavid.invoiceSystem.dataHandler.service.OutputTaxService;
import cn.itcast.servlet.BaseServlet;

/**
 * 销项数据web层
 * @author bjh
 *
 */
public class OutputTaxServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutputTaxService outputTaxService = new OutputTaxService();
	
	/**
	 * 给出税额的JSON数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getAmountJson(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 向客户端发送json串
		 */
		Map<String, BigDecimal> amountTaxDate = outputTaxService.amountTaxDate();
		Set<String> keys = amountTaxDate.keySet();
		List<String> values = new ArrayList<String>();
		for(String str:keys){
			values.add(amountTaxDate.get(str).toString());
		}
		Map<String ,Collection<String>> map = new HashMap<String, Collection<String>>();
		map.put("keys", keys);
		map.put("values", values);
		JSONObject jo = JSONObject.fromObject(map);
		response.getWriter().print(jo.toString());
	}
	
	/**
	 * 给出含税总额的JSON数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getTotalJson(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 向客户端发送json串
		 */
		Map<String, BigDecimal> includeTotalDate = outputTaxService.includeTaxTotalDate();
		Set<String> keys = includeTotalDate.keySet();
		List<String> values = new ArrayList<String>();
		for(String str:keys){
			values.add(includeTotalDate.get(str).toString());
		}
		Map<String ,Collection<String>> map = new HashMap<String, Collection<String>>();
		map.put("keys", keys);
		map.put("values", values);
		JSONObject jo = JSONObject.fromObject(map);
		response.getWriter().print(jo.toString());
	}
	
	/**
	 * 给出月度税额的JSON数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getMonthOutData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 向客户端发送json串
		 */
		Map<Integer,BigDecimal> monthOutData = outputTaxService.getMonthOutTaxData();
		List<String> monthkeys = new ArrayList<String>();
		List<String> monthvalues = new ArrayList<String>();
		boolean flag = true;
		for(int i = 1;i<=12;i++){
			monthkeys.add(i+"月");
			for(Integer integer : monthOutData.keySet()){
				if(integer.intValue() == i){
					monthvalues.add(monthOutData.get(integer).toString());
					flag = false;
					break;
				}
			}
			if(flag){
				monthvalues.add("0");
			}
			flag = true;
		}
		Map<String ,List<String>> map = new HashMap<String, List<String>>();
		map.put("keys", monthkeys);
		map.put("values", monthvalues);
		JSONObject jo = JSONObject.fromObject(map);
		response.getWriter().print(jo.toString());
	}
	
	/**
	 * 给出周度税额的JSON数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getWeekOutData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 向客户端发送json串
		 */
		Map<Integer,BigDecimal> weekOutData = outputTaxService.getWeekOutTaxData();
		List<String> weekkeys = new ArrayList<String>();
		List<String> weekvalues = new ArrayList<String>();
		boolean flag = true;
		for(int i = 1;i<=52;i++){
			weekkeys.add("第"+i+"周");
			for(Integer integer : weekOutData.keySet()){
				if(integer.intValue() == i){
					weekvalues.add(weekOutData.get(integer).toString());
					flag = false;
					break;
				}
			}
			if(flag){
				weekvalues.add("0");
			}
			flag = true;
		}
		Map<String ,List<String>> map = new HashMap<String, List<String>>();
		map.put("keys", weekkeys);
		map.put("values", weekvalues);
		JSONObject jo = JSONObject.fromObject(map);
		response.getWriter().print(jo.toString());
	}
	
	/**
	 * 给出季度税额的JSON数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getQuarterOutData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 向客户端发送json串
		 */
		Map<Integer,BigDecimal> monthOutData = outputTaxService.getMonthOutTaxData();
		List<String> quarterkeys = new ArrayList<String>();
		quarterkeys.add("第一季度");
		quarterkeys.add("第二季度");
		quarterkeys.add("第三季度");
		quarterkeys.add("第四季度");
		List<String> quartervalues = new ArrayList<String>();
		int flag1 = 3;//第一季度从3月份开始
		for(int i = 0;i<quarterkeys.size();i++){
			quartervalues.add("0");
			//第四季度特殊处理
			if(flag1 == 12){
				for(Integer integer : monthOutData.keySet()){
					if(integer.intValue() == 1 || integer.intValue() == 2 || integer.intValue() == 12){
						quartervalues.set(i, (new BigDecimal(quartervalues.get(i)))
								.add(monthOutData.get(integer)).toString());
					}
				}
				break;
			}
			for(Integer integer : monthOutData.keySet()){
				if(integer.intValue() >= flag1 && integer.intValue() < flag1+3){
					quartervalues.set(i, (new BigDecimal(quartervalues.get(i)))
							.add(monthOutData.get(integer)).toString());
				}
			}
			flag1 = flag1+3;
		}
		Map<String ,List<String>> map = new HashMap<String, List<String>>();
		map.put("keys", quarterkeys);
		map.put("values", quartervalues);
		JSONObject jo = JSONObject.fromObject(map);
		response.getWriter().print(jo.toString());
	}
}
