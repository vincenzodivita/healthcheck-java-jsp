package it.contrader.model;

public class UrineTest {
    private Integer id;
    private Float color;
    private Float ph;
    private Float protein;
    private Float hemoglobin;
    private Integer idAdmin;
    private String doctor;
    private String patience;
    private Integer idUser;
    private Boolean isChecked;
    private String dataInsert;

    public UrineTest(Integer id, Float color, Float ph, Float protein, Float hemoglobin, Integer idAdmin, Integer idUser, Boolean isChecked, String dataInsert, String doctor, String patience) {
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




    public UrineTest(Integer id, Float color, Float ph, Float protein, Float hemoglobin, Integer idAdmin, Integer idUser, Boolean isChecked, String dataInsert) {
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

    public UrineTest(Float color, Float ph, Float protein, Float hemoglobin, Integer idAdmin, Integer idUser, Boolean isChecked, String dataInsert) {
        this.color = color;
        this.ph = ph;
        this.protein = protein;
        this.hemoglobin = hemoglobin;
        this.idAdmin = idAdmin;
        this.idUser = idUser;
        this.isChecked = isChecked;
        this.dataInsert = dataInsert;
    }

    public UrineTest(Float color, Float ph, Float protein, Float hemoglobin, Integer idAdmin, Integer idUser, Boolean isChecked, String dataInsert, String doctor, String patience) {
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

    public Integer getId() {return id; }
    public void setId(Integer id) {this.id = id; }
    public Float getColor() {return color;}
    public void setColor(Float color) {this.color = color; }
    public Float getPh() {return ph; }
    public void setPh(Float ph) {this.ph = ph; }
    public Float getProtein() {return protein; }
    public void setProtein(Float protein) {this.protein = protein; }
    public Float getHemoglobin() {return hemoglobin; }
    public void setHemoglobin(Float hemoglobin) {this.hemoglobin = hemoglobin; }
    public Integer getIdAdmin() {return idAdmin; }
    public void setIdAdmin(Integer idAdmin) {this.idAdmin = idAdmin; }
    public Integer getIdUser() {return idUser; }
    public void setIdUser(Integer idUser) {this.idUser = idUser; }
    public Boolean getIsChecked() {return isChecked; }
    public void setIsChecked(Boolean isChecked) {this.isChecked = isChecked; }
    public String getDataInsert() {return dataInsert; }
    public void setDataInsert(String dataInsert) {this.dataInsert = dataInsert; }


    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public String getDoctor() {
        String output = doctor.substring(0,1).toUpperCase() + doctor.substring(1);
        return output;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getPatience() {
        String output = patience.substring(0,1).toUpperCase() + patience.substring(1);
        return output;
    }

    public void setPatience(String patience) {
        this.patience = patience;
    }
}