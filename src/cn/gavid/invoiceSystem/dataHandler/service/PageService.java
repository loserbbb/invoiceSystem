package cn.gavid.invoiceSystem.dataHandler.service;

import java.util.List;

import cn.gavid.invoiceSystem.dataHandler.dao.InputTaxDaoImpl;
import cn.gavid.invoiceSystem.dataHandler.dao.OutputTaxDaoImpl;
import cn.gavid.invoiceSystem.dataHandler.domain.InputTax;
import cn.gavid.invoiceSystem.dataHandler.domain.OutputTax;

public class PageService {
	private InputTaxDaoImpl inputTaxDaoImpl =new InputTaxDaoImpl();
	private OutputTaxDaoImpl outputTaxDaoImpl=new OutputTaxDaoImpl();
	public int getInputTaxTr(){
		return inputTaxDaoImpl.getCount();
	}
	public int getOutputTaxTr(){
		return outputTaxDaoImpl.getCount();
	}
	public List<InputTax> getPageContext(int pc,int ps){
		int start=(pc-1)*ps;
		
		return inputTaxDaoImpl.findByLimit(start, ps);
	}
	public List<OutputTax>  getPageContext1(int pc,int ps){
		int start=(pc-1)*ps;
		
		return outputTaxDaoImpl.findByLimit(start, ps);
	}
}
