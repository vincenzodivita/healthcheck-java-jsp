package it.contrader.dto;

public class UrineTestDTO {
    private Integer id;
    private Float color;
    private Float ph;
    private Float protein;
    private Float hemoglobin;
    private Integer idAdmin;
    private Integer idUser;
    private Boolean isChecked;
    private String dataInsert;
    private String doctor;
    private String patience;

    public UrineTestDTO(Integer id, Float color, Float ph, Float protein, Float hemoglobin, Integer idAdmin, Integer idUser, Boolean isChecked, String dataInsert, String doctor, String patience) {
        this.id = id;
        this.color = color;
        this.ph = ph;
        this.protein = protein;
        this.hemoglobin = hemoglobin;
        this.idAdmin = idAdmin;
        this.idUser = idUser;
        this.isChecked = isChecked;
        this.dataInsert = dataInsert;
        this.doctor = doctor;
        this.patience = patience;
    }



    public UrineTestDTO(Integer id, Float color, Float ph, Float protein, Float hemoglobin, Integer idAdmin, Integer idUser, Boolean isChecked, String dataInsert) {
        this.id = id;
        this.color = color;
        this.ph = ph;
        this.protein = protein;
        this.hemoglobin = hemoglobin;
        this.idAdmin = idAdmin;
        this.idUser = idUser;
        this.isChecked = isChecked;
        this.dataInsert = dataInsert;
    }

    public UrineTestDTO(Float color, Float ph, Float protein, Float hemoglobin, Integer idAdmin, Integer idUser, Boolean isChecked, String dataInsert) {
        this.color = color;
        this.ph = ph;
        this.protein = protein;
        this.hemoglobin = hemoglobin;
        this.idAdmin = idAdmin;
        this.idUser = idUser;
        this.isChecked = isChecked;
        this.dataInsert = dataInsert;
    }

    public Integer getId() {return id; }
    public void setId(Integer id) {this.id = id; }
    public Float getColor() {return color; }
    public void setColor(Float color) {this.color = color; }
    public Float getPh() {return this.ph; }
    public void setPh(Float ph) {this.ph = ph; }
    public Float getProtein() {return protein; }
    public void setProtein(Float protein) {this.protein = protein; }
    public Float getHemoglobin() {return hemoglobin; }
    public void setHemoglobin(Float hemoglobin) {this.hemoglobin = hemoglobin;}
    public Integer getIdAdmin() {return idAdmin; }
    public void setIdAdmin(Integer idAdmin) {this.idAdmin = idAdmin; }
    public Integer getIdUser() {return idUser; }
    public void setIdUser(Integer idUser) {this.idUser = idUser; }
    public Boolean getIsChecked() {return isChecked; }
    public void setIsChecked(Boolean isChecked) {this.isChecked = isChecked; }
    public String getDataInsert() {return dataInsert; }
    public void setIsChecked(String dataInsert) {this.dataInsert = dataInsert; }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getPatience() {
        return patience;
    }

    public void setPatience(String patience) {
        this.patience = patience;
    }

    public String toString() {
        return  id + "\t"  + color +"\t\t" +   ph + "\t\t" + protein+ "\t\t" + hemoglobin+ "\t\t" + idAdmin+ "\t\t" + idUser+ "\t\t" + isChecked+ "\t\t" + dataInsert;
    }
}