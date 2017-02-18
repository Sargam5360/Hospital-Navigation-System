/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hai.bean;

/**
 *
 * @author Nisarg
 */
public class DataBean {

   
    
    private int age;
    private int sex;
    private float length_of_stay;  
    private String d_code;
    private int national_provider_id;
    private int medpar_provider_no;
    private int pharmacy_indicator;
    private int discharge_status;
    private String provider_city_name;
    private String prvider_is_organization_subpart;
    private int at_least_one_hospital_acquired_disease;
    private int provider_postal_code;
    private float avg_stay;
     private float avg_cost;
    private float mortality_ratio;
    private String rating;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    
    public float getAvg_stay() {
        return avg_stay;
    }

    public void setAvg_stay(float avg_stay) {
        this.avg_stay = avg_stay;
    }

    public float getAvg_cost() {
        return avg_cost;
    }

    public void setAvg_cost(float avg_cost) {
        this.avg_cost = avg_cost;
    }

    public float getMortality_ratio() {
        return mortality_ratio;
    }

    public void setMortality_ratio(float mortality_ratio) {
        this.mortality_ratio = mortality_ratio;
    }    

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public float getLength_of_stay() {
        return length_of_stay;
    }

    public void setLength_of_stay(float length_of_stay) {
        this.length_of_stay = length_of_stay;
    }

    public String getD_code() {
        return d_code;
    }

    public void setD_code(String d_code) {
        this.d_code = d_code;
    }

    public int getNational_provider_id() {
        return national_provider_id;
    }

    public void setNational_provider_id(int national_provider_id) {
        this.national_provider_id = national_provider_id;
    }

    public int getMedpar_provider_no() {
        return medpar_provider_no;
    }

    public void setMedpar_provider_no(int medpar_provider_no) {
        this.medpar_provider_no = medpar_provider_no;
    }

    public int getPharmacy_indicator() {
        return pharmacy_indicator;
    }

    public void setPharmacy_indicator(int pharmacy_indicator) {
        this.pharmacy_indicator = pharmacy_indicator;
    }

    public int getDischarge_status() {
        return discharge_status;
    }

    public void setDischarge_status(int discharge_status) {
        this.discharge_status = discharge_status;
    }

    public String getProvider_city_name() {
        return provider_city_name;
    }

    public void setProvider_city_name(String provider_city_name) {
        this.provider_city_name = provider_city_name;
    }

    public String getPrvider_is_organization_subpart() {
        return prvider_is_organization_subpart;
    }

    public void setPrvider_is_organization_subpart(String prvider_is_organization_subpart) {
        this.prvider_is_organization_subpart = prvider_is_organization_subpart;
    }

    public int getAt_least_one_hospital_acquired_disease() {
        return at_least_one_hospital_acquired_disease;
    }

    public void setAt_least_one_hospital_acquired_disease(int at_least_one_hospital_acquired_disease) {
        this.at_least_one_hospital_acquired_disease = at_least_one_hospital_acquired_disease;
    }

    public int getProvider_postal_code() {
        return provider_postal_code;
    }

    public void setProvider_postal_code(int provider_postal_code) {
        this.provider_postal_code = provider_postal_code;
    }
    
}
