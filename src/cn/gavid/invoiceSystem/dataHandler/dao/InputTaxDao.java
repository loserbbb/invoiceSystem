package cn.gavid.invoiceSystem.dataHandler.dao;

import java.util.List;

import cn.gavid.invoiceSystem.dataHandler.domain.InputTax;

public interface InputTaxDao {
	public List<InputTax> findAll();
	public void addInputTax(InputTax inputTax);
	public void deleInputTaxById(String uuid);
	public void modifyInputTaxById(String uuid);
	public List<InputTax> findByLimit(int start,int ps);
	public int getCount();
}
