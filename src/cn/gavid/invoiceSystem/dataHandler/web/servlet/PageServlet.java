package cn.gavid.invoiceSystem.dataHandler.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gavid.invoiceSystem.dataHandler.domain.InputTax;
import cn.gavid.invoiceSystem.dataHandler.domain.OutputTax;
import cn.gavid.invoiceSystem.dataHandler.domain.PageBean;
import cn.gavid.invoiceSystem.dataHandler.service.PageService;
import cn.itcast.servlet.BaseServlet;

public class PageServlet extends BaseServlet {
	PageService pageService=new PageService();
	public String getFirstPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean<InputTax> firstPage=new PageBean<InputTax>();
		firstPage.setPc(1);
		firstPage.setPs(10);
		firstPage.setTr(pageService.getInputTaxTr());
		System.out.println(firstPage.getTr());
		firstPage.setTs();
		firstPage.setBeanList(pageService.getPageContext(1, 10));
		request.setAttribute("Page",firstPage);
		return "f:/inputtaxdata.jsp";
	}
	public String getFirstOutPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean<OutputTax> firstPage=new PageBean<OutputTax>();
		firstPage.setPc(1);
		firstPage.setPs(10);
		firstPage.setTr(pageService.getOutputTaxTr());
		System.out.println(firstPage.getTr());
		firstPage.setTs();
		firstPage.setBeanList(pageService.getPageContext1(1, 10));
		request.setAttribute("Page",firstPage);
		return "f:/outputtaxdata.jsp";
	}
	public String getPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PageBean<InputTax> page=new PageBean<InputTax>();
		page.setPc(Integer.parseInt(request.getParameter("page")));
		page.setPs(10);
		page.setTr(pageService.getInputTaxTr());
		page.setTs();
		if(page.getPc()<1){
			page.setPc(1);
		}
		if(page.getPc()>page.getTs()){
			page.setPc(page.getTs());
		}
		page.setBeanList(pageService.getPageContext(page.getPc(), page.getPs()));
		request.setAttribute("Page", page);
		return "f:/inputtaxdata.jsp";
	}
	public String getOutPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PageBean<OutputTax> page=new PageBean<OutputTax>();
		page.setPc(Integer.parseInt(request.getParameter("page")));
		page.setPs(10);
		page.setTr(pageService.getOutputTaxTr());
		page.setTs();
		if(page.getPc()<1){
			page.setPc(1);
		}
		if(page.getPc()>page.getTs()){
			page.setPc(page.getTs());
		}
		page.setBeanList(pageService.getPageContext1(page.getPc(), page.getPs()));
		request.setAttribute("Page", page);
		return "f:/outputtaxdata.jsp";
	}
}
