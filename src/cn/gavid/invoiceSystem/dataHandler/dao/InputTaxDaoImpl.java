package cn.gavid.invoiceSystem.dataHandler.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.gavid.invoiceSystem.dataHandler.domain.InputTax;
import cn.itcast.jdbc.TxQueryRunner;

/**
 * 持久层
 * @author lenove
 *
 */
public class InputTaxDaoImpl implements InputTaxDao {

	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 查询所有进项数据
	 */
	@Override
	public List<InputTax> findAll() {
		try {
			String sql = "select * from tb_inputTax";
			return qr.query(sql, new BeanListHandler<InputTax>(InputTax.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加进项数据
	 */
	@Override
	public void addInputTax(InputTax inputTax) {
		try{
			String sql = "insert into tb_inputTax values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object[] params = {inputTax.getUuid(),inputTax.getItemName(),inputTax.getStandard(),inputTax.getUnit(),
					inputTax.getCount(),inputTax.getPrice_includeTax(),inputTax.getPrice_unincludeTax(),
					inputTax.getTotalMoney_includeTax(),inputTax.getTotalMoney_unincludeTax(),
					inputTax.getLeviable(),inputTax.getAmountTax(),inputTax.getFromPath(),inputTax.getTimer()};
			qr.update(sql, params);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除指定进项数据
	 */
	@Override
	public void deleInputTaxById(String uuid) {
		try {
			String sql = "";
			Object[] params = {};
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改指定进项数据
	 */
	@Override
	public void modifyInputTaxById(String uuid) {
		try {
			String sql = "";
			Object[] params = {};
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<InputTax> findByLimit(int start, int ps) {
		// TODO Auto-generated method stub
		try{
			String sql="select * from tb_inputTax limit ?,?";
			Object[] params = {start,ps};
			return qr.query(sql, new BeanListHandler<InputTax>(InputTax.class),params);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		try{
			String sql="select count(*) from tb_inputTax";
			Number number =  (Number) qr.query(sql,new ScalarHandler());
			return number.intValue();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
