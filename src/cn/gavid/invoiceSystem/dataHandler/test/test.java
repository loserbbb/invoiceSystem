package cn.gavid.invoiceSystem.dataHandler.test;

import java.util.ArrayList;
import java.util.List;

import cn.gavid.invoiceSystem.dataHandler.domain.InputTax;
import cn.gavid.invoiceSystem.dataHandler.service.InputTaxService;
import cn.gavid.invoiceSystem.dataHandler.service.PageService;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<InputTax> list=new ArrayList<InputTax>();
		PageService pageService=new PageService();
		list=pageService.getPageContext(1, 10);
		System.out.println(list.toString());
	}

}
