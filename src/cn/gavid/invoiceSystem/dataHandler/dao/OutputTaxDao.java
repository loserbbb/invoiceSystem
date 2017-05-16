package cn.gavid.invoiceSystem.dataHandler.dao;

import java.util.List;

import cn.gavid.invoiceSystem.dataHandler.domain.InputTax;
import cn.gavid.invoiceSystem.dataHandler.domain.OutputTax;

public interface OutputTaxDao {
	public List<OutputTax> findAll();
	public void addOutputTax(OutputTax outputTax);
	public void deleOutputTaxById(String uuid);
	public void modifyOutputTaxById(String uuid);
	public List<OutputTax> findByLimit(int start,int ps);
	public int getCount();
}
