package com.ifywork.ifywork_ssm.bean;

public class Drug {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrug_no() {
        return drug_no;
    }

    public void setDrug_no(String drug_no) {
        this.drug_no = drug_no;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    private int id;
    private String drug_no;
    private String drug_name;
    private int quanlity;
    private String unit;
    private double money;

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", drug_no='" + drug_no + '\'' +
                ", drug_name='" + drug_name + '\'' +
                ", quanlity=" + quanlity +
                ", unit='" + unit + '\'' +
                ", money=" + money +
                '}';
    }
}
