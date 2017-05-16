package cn.gavid.dataHandler;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import cn.gavid.invoiceSystem.dataHandler.dao.OutputTaxDaoImpl;
import cn.gavid.invoiceSystem.dataHandler.domain.OutputTax;
import cn.itcast.commons.CommonUtils;

public class OutputDaoImplTest {
	@Test
	public void addInputTaxTest(){
		Random rd = new Random();
		Calendar calendar = Calendar.getInstance();
		for(int i = 0;i<100;i++){
		OutputTax outputTax = new OutputTax();
		outputTax.setUuid(CommonUtils.uuid());
		outputTax.setItemName("商品"+i);
		outputTax.setStandard("sdas");
		outputTax.setUnit("个");
		outputTax.setCount(new BigDecimal(rd.nextInt(30)+1));
		/**
		 * 注意：
		 * 		在设置单价前，必须先将税率设置好！！！
		 */
		outputTax.setLeviable(new BigDecimal(0.17));
		outputTax.setPrice_unincludeTax(new BigDecimal(rd.nextInt(90)+20));
		outputTax.setToPath("Gavid公司");
		calendar.set(2017, rd.nextInt(12)+1, rd.nextInt(30)+1, rd.nextInt(24)+1, rd.nextInt(60)+1);
		outputTax.setTimer(new Timestamp(calendar.getTime().getTime()));
		
		OutputTaxDaoImpl outputTaxDaoImpl = new OutputTaxDaoImpl();
		outputTaxDaoImpl.addOutputTax(outputTax);
		}
	}
}
