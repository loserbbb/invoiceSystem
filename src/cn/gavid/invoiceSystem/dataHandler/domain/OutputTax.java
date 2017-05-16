package cn.gavid.invoiceSystem.dataHandler.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 销项数据领域对象
 * @author lenove
 *
 */
public class OutputTax {

	private String uuid;//货物数据编号
	private String itemName;//货物名称
	private String standard;//规格型号
	private String unit;//计量单位
	private BigDecimal count;//数量
	private BigDecimal price_includeTax;//单价(含税)
	private BigDecimal price_unincludeTax;//单价(不含税)
	private BigDecimal totalMoney_includeTax;//总金额(含税)
	private BigDecimal totalMoney_unincludeTax;//总金额(不含税)
	private BigDecimal leviable;//征收率
	private BigDecimal amountTax;//税额
	private String toPath;//销售路径
	private Timestamp timer;//销售时间
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public BigDecimal getCount() {
		return count;
	}
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	public String getToPath() {
		return toPath;
	}
	public void setToPath(String toPath) {
		this.toPath = toPath;
	}
	public Timestamp getTimer() {
		return timer;
	}
	public void setTimer(Timestamp timer) {
		this.timer = timer;
	}
	/*
	 * 单价（含税）
	 * 
	 */
	public BigDecimal getPrice_includeTax() {
		if(price_unincludeTax != null && price_includeTax == null)
		{
			return price_unincludeTax.multiply(leviable.add(new BigDecimal(1)));
		}
		else{
			return price_includeTax;
		}
	}
	public void setPrice_includeTax(BigDecimal price_includeTax) {
		if(price_includeTax != null)
		{
			this.price_includeTax = price_includeTax;
			return;
		}
		if(this.price_includeTax.divide(leviable.add(new BigDecimal(1)),2,BigDecimal.ROUND_HALF_UP) != null)
			this.price_unincludeTax = this.price_includeTax.divide(leviable.add(new BigDecimal(1)),2,BigDecimal.ROUND_HALF_UP);
	}
	/*
	 * 
	 * 单价（不含税）
	 */
	public BigDecimal getPrice_unincludeTax() {
		if(price_includeTax != null && price_unincludeTax == null){
			return price_includeTax.divide(leviable.add(new BigDecimal(1)),2,BigDecimal.ROUND_HALF_UP);
		}else{
			return price_unincludeTax;
		}
	}
	
	public void setPrice_unincludeTax(BigDecimal price_unincludeTax) {
		if(price_unincludeTax != null)
		{
			this.price_unincludeTax = price_unincludeTax;
			return;
		}
		
		if(this.price_unincludeTax.multiply(leviable.add(new BigDecimal(1))) != null)
			this.price_includeTax = this.price_unincludeTax.multiply(leviable.add(new BigDecimal(1)));
	}
	/*
	 * 
	 * 总金额（含税）
	 */
	public BigDecimal getTotalMoney_includeTax() {
		if(this.price_includeTax != null)
			return this.price_includeTax.multiply(this.count);
		else if(this.price_unincludeTax != null)
			return this.price_unincludeTax.multiply(leviable.add(new BigDecimal(1))).multiply(this.count);
		else
			return null;
	}
	/*
	 * 
	 * 总金额（不含税）
	 */
	public BigDecimal getTotalMoney_unincludeTax() {
		if(this.price_unincludeTax != null)
			return this.price_unincludeTax.multiply(this.count);
		else if(this.price_includeTax != null)
			return this.price_includeTax.divide(leviable.add(new BigDecimal(1)),2,BigDecimal.ROUND_HALF_UP).multiply(this.count);
		else
			return null;
	}
	/*
	 * 
	 * 税率
	 */
	public BigDecimal getLeviable() {
		return leviable;
	}
	public void setLeviable(BigDecimal leviable) {
		this.leviable = leviable;
	}
	/*
	 * 计算税额
	 */
	public BigDecimal getAmountTax() {
		if(this.price_includeTax != null){
			return this.price_includeTax.multiply(count).divide(leviable.add(new BigDecimal(1)),2,BigDecimal.ROUND_HALF_UP).multiply(leviable);
		}
		else if(this.price_unincludeTax != null){
			return this.price_unincludeTax.multiply(count).multiply(leviable);
		} 
		return null;
	}
	/******************后期修改(为了使以下数据自动绑定到查询出的List集合中)*****************************/
	public void setTotalMoney_includeTax(BigDecimal totalMoney_includeTax) {
		this.totalMoney_includeTax = totalMoney_includeTax;
	}
	public void setTotalMoney_unincludeTax(BigDecimal totalMoney_unincludeTax) {
		this.totalMoney_unincludeTax = totalMoney_unincludeTax;
	}
	public void setAmountTax(BigDecimal amountTax) {
		this.amountTax = amountTax;
	}
	/***********************************************/
	@Override
	public String toString() {
		return "OutputTax [uuid=" + uuid + ", itemName=" + itemName
				+ ", standard=" + standard + ", unit=" + unit + ", count="
				+ count + ", price_includeTax=" + price_includeTax
				+ ", price_unincludeTax=" + price_unincludeTax
				+ ", totalMoney_includeTax=" + totalMoney_includeTax
				+ ", totalMoney_unincludeTax=" + totalMoney_unincludeTax
				+ ", leviable=" + leviable + ", amountTax=" + amountTax
				+ ", toPath=" + toPath + ", timer=" + timer + "]";
	}
}
