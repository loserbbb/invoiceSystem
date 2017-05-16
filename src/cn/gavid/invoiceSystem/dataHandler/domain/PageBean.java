package cn.gavid.invoiceSystem.dataHandler.domain;

import java.util.List;

public class PageBean<T> {
	private int pc;//当前页码
	private int tr;//总记录数
	private int ts;//总页数
	private int ps;//每页记录数
	private List<T> BeanList;//当前页的记录；
	public void setTs(){
		ts=tr/ps+(tr%ps==0?0:1);
	}
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public List<T> getBeanList() {
		return BeanList;
	}
	public void setBeanList(List<T> beanList) {
		BeanList = beanList;
	}
	public int getTs() {
		return ts;
	}
	public void setTs(int ts) {
		this.ts = ts;
	}
}
