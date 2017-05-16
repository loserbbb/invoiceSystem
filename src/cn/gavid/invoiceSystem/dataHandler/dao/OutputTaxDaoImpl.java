package cn.gavid.invoiceSystem.dataHandler.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.gavid.invoiceSystem.dataHandler.domain.InputTax;
import cn.gavid.invoiceSystem.dataHandler.domain.OutputTax;
import cn.itcast.jdbc.TxQueryRunner;

public class OutputTaxDaoImpl implements OutputTaxDao{

	private QueryRunner qr = new TxQueryRunner();
	/**
	 * 查询所有进项数据
	 */
	@Override
	public List<OutputTax> findAll() {
		try {
			String sql = "select * from tb_outputTax";
			return qr.query(sql, new BeanListHandler<OutputTax>(OutputTax.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addOutputTax(OutputTax outputTax) {
		try{
			System.out.println(outputTax.getItemName());
			String sql = "insert into tb_outputTax values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object[] params = {outputTax.getUuid(),outputTax.getItemName(),outputTax.getStandard(),outputTax.getUnit(),
					outputTax.getCount(),outputTax.getPrice_includeTax(),outputTax.getPrice_unincludeTax(),
					outputTax.getTotalMoney_includeTax(),outputTax.getTotalMoney_unincludeTax(),
					outputTax.getLeviable(),outputTax.getAmountTax(),outputTax.getToPath(),outputTax.getTimer()};
			qr.update(sql, params);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleOutputTaxById(String uuid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyOutputTaxById(String uuid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		try{
			String sql="select count(*) from tb_outputTax";
			Number number =  (Number) qr.query(sql,new ScalarHandler());
			return number.intValue();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List<OutputTax> findByLimit(int start, int ps) {
		// TODO Auto-generated method stub
		try{
			String sql="select * from tb_outputTax limit ?,?";
			Object[] params = {start,ps};
			return qr.query(sql, new BeanListHandler<OutputTax>(OutputTax.class),params);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
