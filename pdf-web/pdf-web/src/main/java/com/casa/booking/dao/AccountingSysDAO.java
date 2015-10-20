package com.casa.booking.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class AccountingSysDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<List<String>> getAccountData(String pnr, String startDt, String endDt, String customerCode) throws DataAccessException, ParseException, SQLException{
		
		String sql2="SELECT bookings.tktg_id,bookings.issueddate,bookings.customercode,bookings.alertaccounting, "
				+ "detail.advticket,bookings.agency,bookings.pnr,bookings.achamt,bookings.nsfamt,"
				+ "bookings.ccamt,bookings.bankdeposit,bookings.nsf,"
				+ "bookings.lastissuer,bookings.createtime,bookings.updatetime,bookings.agencymarkup,"
				+ "bookings.exchange,bookings.commissioncc,bookings.segments,detail.profit,bookings.issuer,"
				+ "bookings.salesrep,bookings.airline,bookings.void,bookings.comments,bookings.afterhours,"
				+ "bookings.reconciled,bookings.bookingagent,bookings.store,bookings.sfop,detail.passengername,"
				+ "bookings.advticket,detail.eticketnbr FROM tktgbookings bookings, tktgdetails detail "
				+ "where  bookings.tktg_id = detail.tktg_id and bookings.customercode = '"+customerCode+"' ";
					//	+ "and bookings.issueddate >= '"+startDt+"' and bookings.issueddate <= '"+endDt+"'" ;
		
		if(startDt!=null && !"".equals(startDt)){
			sql2=sql2+"and bookings.issueddate >= '"+startDt+"' and bookings.issueddate <= '"+endDt+"' ";
		}
		if(pnr!=null && !"".equals(pnr)){
			sql2=sql2+"and bookings.pnr = '"+pnr+"' ";
		}
		List<List<String>> data = new ArrayList<List<String>>();
		sql2=sql2+"order by bookings.issuer,bookings.issueddate,bookings.afterhours,bookings.pnr,detail.passengername";
		Connection conn = null;
		try{
			System.out.println(sql2);
			conn = jdbcTemplate.getDataSource().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql2);
			String lastPnr="";
			if(rs!=null){
				while (rs.next()) {
					List<String> recs = new ArrayList<String>();
					recs.add(getStringDate(rs.getDate("issueddate")));
					recs.add((String) rs.getString("agency"));
					String currPnr = rs.getString("pnr");
					recs.add(currPnr);
					recs.add((String) rs.getString("eticketnbr"));
					recs.add((String) rs.getString("passengername"));
					recs.add((String) rs.getString("airline"));
					//recs.add(""+(Double) rs.getDouble("achamt"));
					//recs.add(""+(Double) rs.getDouble("ccamt"));
					//recs.add(""+(Double) rs.getDouble("commissioncc"));
					
					if (!lastPnr.equals(currPnr)) {
						recs.add(""+(Double) rs.getDouble("achamt"));
						recs.add(""+(Double) rs.getDouble("ccamt"));
						recs.add(""+(Double) rs.getDouble("commissioncc"));
						//agencyMarkup = (Double) rs.getDouble("agencymarkup");
						//bankdeposit = (Double) rs.getDouble("bankdeposit");
					} else {
						recs.add(""+new Double(0.00));
						recs.add(""+new Double(0.00));
						recs.add(""+new Double(0.00));
						//agencyMarkup = new Double(0.00);
						//bankdeposit = new Double(0.00);
					}
					recs.add(""+(Double) rs.getDouble("profit"));
					lastPnr = rs.getString("pnr");
					data.add(recs);
					//System.out.println("got rec");
				}
			}else{
				System.out.println("RS is null");
			}
		}catch(Exception e){
			
		}finally{
			if(conn!=null)
			conn.close();
		}
		//List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql, new Object[] {new java.sql.Date(formatter.parse(startDt).getTime()), new java.sql.Date(formatter.parse(endDt).getTime())});
		
		//System.out.println(rows.size());
		return data;
	}
	
	private String getStringDate(java.sql.Date sqltDate) {

		String stringDate = null;
		if (sqltDate != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");//Date format
		    java.util.Date utilDate = new java.util.Date(sqltDate.getTime());// your Sql date value
		    stringDate=formatter.format(utilDate);
		}    
		return stringDate;
		
	}
	
	public static void main(String[] args){
		AccountingSysDAO a = new AccountingSysDAO();
		try {
			a.getAccountData(null, "2015-05-01", "2015-10-11", "CAPLAZA");
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
