/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hai.dao;

import com.hai.bean.DataBean;
import com.hai.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nisarg
 */
public class ContentDao {
    
    private static Connection con = null;
    private static Statement stmt = null;
    private static Statement stmt1 = null;
    
    public static List<DataBean> retriveContent(DataBean dataBean){
    List<DataBean> content = new ArrayList<>();
    DataBean beanData;    
    ResultSet rs;
    ResultSet rs1;
    
    //System.out.println("cfshgjkl;sljkjclxvjsdhbakcx;vljsbhdkxlvjsdb"+dataBean.getAge());
        try {
            con = ConnectionUtil.getCon();
            stmt = con.createStatement();
            
            rs = stmt.executeQuery("select avg(length_of_stay),avg(TOTAL_CHARGES) from project_2 where age='"+dataBean.getAge()+"' and ADMISSION_DIAGNOSIS_CODE='"+dataBean.getD_code()+"';");
            
            stmt1 = con.createStatement();
            rs1 = stmt1.executeQuery("select count(*)*100/(select count(*) from project_2 where age='"+dataBean.getAge()+"' and ADMISSION_DIAGNOSIS_CODE='"+dataBean.getD_code()+"') as Mortality_ratio from  project_2 where age='"+dataBean.getAge()+"' and DISCHARGE_STATUS='1' and ADMISSION_DIAGNOSIS_CODE='"+dataBean.getD_code()+"'; ");
           
            
            
            while(rs.next() && rs1.next()){
            beanData = new DataBean();
            //System.out.println("////////////////////////////////////////////"+rs.getFloat(1)+rs.getFloat(2));
            beanData.setAvg_stay(rs.getFloat(1));
            beanData.setAvg_cost(rs.getFloat(2));
            beanData.setMortality_ratio(rs1.getFloat(1));
            beanData.setD_code(dataBean.getD_code());
            content.add(beanData);
            }
            if (con != null) {
                stmt.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error in Stmt " + e);
        }
        
        return content;
    }
    
    public static List<DataBean> retriveContent1(DataBean dataBean){
    List<DataBean> content = new ArrayList<>();
    DataBean beanData;    
    ResultSet rs;
    ResultSet rs1;
    float max,min,avg;
    //System.out.println("cfshgjkl;sljkjclxvjsdhbakcx;vljsbhdkxlvjsdb"+dataBean.getAge());
        try {
            con = ConnectionUtil.getCon();
            stmt = con.createStatement();
            
            rs = stmt.executeQuery("select avg(length_of_stay) as l,avg(TOTAL_CHARGES),national_provider_id from project_2 where age='"+dataBean.getAge()+"' and ADMISSION_DIAGNOSIS_CODE='"+dataBean.getD_code()+"' GROUP BY NATIONAL_PROVIDER_ID order by l ASC;");
            
            stmt1 = con.createStatement();
            rs1 = stmt1.executeQuery("select max(l),min(l) from (select avg(length_of_stay) as l from project_2 where age='"+dataBean.getAge()+"' and ADMISSION_DIAGNOSIS_CODE='"+dataBean.getD_code()+"' group by NATIONAL_PROVIDER_ID) as a");
            rs1.next();
            max = rs1.getFloat(1);
            min = rs1.getFloat(2);
            avg = (max-min)/5;
            
            while(rs.next()){
            beanData = new DataBean();
            if(max>=rs.getFloat(1) && rs.getFloat(1)>(max-avg))
                beanData.setRating("1");           
            else if((max-avg)>=rs.getFloat(1) && rs.getFloat(1)>(max-2*avg))
                beanData.setRating("2");
            else if((max-2*avg)>=rs.getFloat(1) && rs.getFloat(1)>(max-3*avg))
                beanData.setRating("3");
            else if((max-3*avg)>=rs.getFloat(1) && rs.getFloat(1)>(max-4*avg))
                beanData.setRating("4");
            else if((max-4*avg)>=rs.getFloat(1) && rs.getFloat(1)>=min)
                beanData.setRating("5");
            else
                 beanData.setRating("Rating Not Available");   
            //System.out.println("////////////////////////////////////////////"+rs.getFloat(1)+rs.getFloat(2));
            beanData.setAvg_stay(rs.getFloat(1));
            beanData.setAvg_cost(rs.getFloat(2));
            beanData.setNational_provider_id(rs.getInt(3));
          //  beanData.setMortality_ratio(rs1.getFloat(1));
            beanData.setD_code(dataBean.getD_code());
            content.add(beanData);
            }
            if (con != null) {
                stmt.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error in Stmt " + e);
        }
        
        return content;
    }
    
    public static List<DataBean> retriveContent2(DataBean dataBean){
    List<DataBean> content = new ArrayList<>();
    DataBean beanData;    
    ResultSet rs;
    ResultSet rs1;
    float max,min,avg;
    //System.out.println("cfshgjkl;sljkjclxvjsdhbakcx;vljsbhdkxlvjsdb"+dataBean.getAge());
        try {
            con = ConnectionUtil.getCon();
            stmt = con.createStatement();
            
            rs = stmt.executeQuery("select avg(length_of_stay),avg(TOTAL_CHARGES) as l,national_provider_id from project_2 where age='"+dataBean.getAge()+"' and ADMISSION_DIAGNOSIS_CODE='"+dataBean.getD_code()+"' GROUP BY NATIONAL_PROVIDER_ID order by l ASC;");
            
            stmt1 = con.createStatement();
            rs1 = stmt1.executeQuery("select max(l),min(l) from (select avg(TOTAL_CHARGES) as l from project_2 where age='"+dataBean.getAge()+"' and ADMISSION_DIAGNOSIS_CODE='"+dataBean.getD_code()+"' group by NATIONAL_PROVIDER_ID) as a");
            rs1.next();
            max = rs1.getFloat(1);
            min = rs1.getFloat(2);
            avg = (max-min)/5;
            
            while(rs.next()){
            beanData = new DataBean();
            if(max>=rs.getFloat(2) && rs.getFloat(2)>(max-avg))
                beanData.setRating("1");           
            else if((max-avg)>=rs.getFloat(2) && rs.getFloat(2)>(max-2*avg))
                beanData.setRating("2");
            else if((max-2*avg)>=rs.getFloat(2) && rs.getFloat(2)>(max-3*avg))
                beanData.setRating("3");
            else if((max-3*avg)>=rs.getFloat(2) && rs.getFloat(2)>(max-4*avg))
                beanData.setRating("4");
            else if((max-4*avg)>=rs.getFloat(2) && rs.getFloat(2)>=min)
                beanData.setRating("5");
            else
                 beanData.setRating("Rating Not Available");   
            //System.out.println("////////////////////////////////////////////"+rs.getFloat(1)+rs.getFloat(2));
            beanData.setAvg_stay(rs.getFloat(1));
            beanData.setAvg_cost(rs.getFloat(2));
            beanData.setNational_provider_id(rs.getInt(3));
          //  beanData.setMortality_ratio(rs1.getFloat(1));
            beanData.setD_code(dataBean.getD_code());
            content.add(beanData);
            }
            if (con != null) {
                stmt.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error in Stmt " + e);   
        }
        
        return content;
    }
    
    
}
