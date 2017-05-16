package cn.gavid.dataHandler;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import cn.gavid.invoiceSystem.dataHandler.dao.InputTaxDaoImpl;
import cn.gavid.invoiceSystem.dataHandler.domain.InputTax;
import cn.itcast.commons.CommonUtils;

public class InputDaoImplTest {

	@Test
	public void addInputTaxTest(){
		Random rd = new Random();
		Calendar calendar = Calendar.getInstance();
		for(int i = 0;i<100;i++){
			InputTax inputTax = new InputTax();
			inputTax.setUuid(CommonUtils.uuid());
			inputTax.setItemName("商品"+i);
			inputTax.setStandard("sdas");
			inputTax.setUnit("个");
			inputTax.setCount(new BigDecimal(rd.nextInt(90)+1));
			/**
			 * 注意：
			 * 		在设置单价前，必须先将税率设置好！！！
			 */
			inputTax.setLeviable(new BigDecimal(0.17));
			inputTax.setPrice_includeTax((new BigDecimal(rd.nextInt(80)+20)));
			inputTax.setFromPath("佳豪公司");
			calendar.set(2017, rd.nextInt(12)+1, rd.nextInt(30)+1, rd.nextInt(24)+1, rd.nextInt(60)+1);
			inputTax.setTimer(new Timestamp(calendar.getTime().getTime()));
			InputTaxDaoImpl inputTaxDaoImpl = new InputTaxDaoImpl();
			inputTaxDaoImpl.addInputTax(inputTax);
		}
	}
	@Test
	public void findallTest(){
		InputTaxDaoImpl inputTaxDaoImpl = new InputTaxDaoImpl();
		List<InputTax> findAll = inputTaxDaoImpl.findAll();
		for(InputTax in: findAll){
			System.out.println(in);
		}
	}
}
