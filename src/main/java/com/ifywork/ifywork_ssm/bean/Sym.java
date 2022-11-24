package com.ifywork.ifywork_ssm.bean;

public class Sym {
    private int id;
    private String sym_no;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSym_no() {
        return sym_no;
    }

    public void setSym_no(String sym_no) {
        this.sym_no = sym_no;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private String symptom;
    private int level;

    @Override
    public String toString() {
        return "Sym{" +
                "id=" + id +
                ", sym_no='" + sym_no + '\'' +
                ", symptom='" + symptom + '\'' +
                ", level=" + level +
                '}';
    }
}
