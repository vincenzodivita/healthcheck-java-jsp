package it.contrader.dto;

public class BloodTestDTO {

    private Integer id;
    private Float redBloodCell;
    private Float whiteBloodCell;
    private Float platelets;
    private Float hemoglobin;
    private Integer idAdmin;
    private Integer idUser;
    private Boolean isChecked;
    private String dataInsert;
    private String doctor;
    private String patience;

    public BloodTestDTO(Integer id, Float redBloodCell, Float whiteBloodCell, Float platelets, Float hemoglobin, Integer idAdmin, Integer idUser, Boolean isChecked, String dataInsert, String doctor, String patience) {
        this.id = id;
        this.redBloodCell = redBloodCell;
        this.whiteBloodCell = whiteBloodCell;
        this.platelets = platelets;
        this.hemoglobin = hemoglobin;
        this.idAdmin = idAdmin;
        this.idUser = idUser;
        this.isChecked = isChecked;
        this.dataInsert = dataInsert;
        this.doctor = doctor;
        this.patience = patience;
    }

    public BloodTestDTO(Integer id, Float redBloodCell, Float whiteBloodCell, Float platelets, Float hemoglobin, Integer idAdmin, Integer idUser, Boolean isChecked, String dataInsert) {
        this.id = id;
        this.redBloodCell = redBloodCell;
        this.whiteBloodCell = whiteBloodCell;
        this.platelets = platelets;
        this.hemoglobin = hemoglobin;
        this.idAdmin = idAdmin;
        this.idUser = idUser;
        this.isChecked= isChecked;
        this.dataInsert = dataInsert;
    }

    public BloodTestDTO(Float redBloodCell, Float whiteBloodCell, Float platelets, Float hemoglobin, Integer idAdmin, Integer idUser,String dataInsert) {
        this.redBloodCell = redBloodCell;
        this.whiteBloodCell = whiteBloodCell;
        this.platelets = platelets;
        this.hemoglobin = hemoglobin;
        this.idAdmin = idAdmin;
        this.idUser = idUser;
        this.dataInsert = dataInsert;
    }

    public BloodTestDTO(Integer id, Float redBloodCell, Float whiteBloodCell, Float platelets, Float hemoglobin, String dataInsert) {
        this.id = id;
        this.redBloodCell = redBloodCell;
        this.whiteBloodCell = whiteBloodCell;
        this.platelets = platelets;
        this.hemoglobin = hemoglobin;
        this.dataInsert = dataInsert;
    }

    public BloodTestDTO(Integer id, Boolean isChecked) {
        this.id = id;
        this.isChecked = isChecked;
    }

    public BloodTestDTO(Float redBloodCell, Float whiteBloodCell, Float platelets, Float hemoglobin, Integer idAdmin, Integer idUser, Boolean isChecked, String dataInsert) {
        this.redBloodCell = redBloodCell;
        this.whiteBloodCell = whiteBloodCell;
        this.platelets = platelets;
        this.hemoglobin = hemoglobin;
        this.idAdmin = idAdmin;
        this.idUser = idUser;
        this.isChecked= isChecked;
        this.dataInsert = dataInsert;
    }

    public BloodTestDTO(Float redBloodCell, Float whiteBloodCell, Float platelets, Float hemoglobin, String dataInsert) {
        this.redBloodCell = redBloodCell;
        this.whiteBloodCell = whiteBloodCell;
        this.platelets = platelets;
        this.hemoglobin = hemoglobin;
        this.dataInsert = dataInsert;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getRedBloodCell() {
        return redBloodCell;
    }

    public void setRedBloodCell(Float redBloodCell) {
        this.redBloodCell = redBloodCell;
    }

    public Float getWhiteBloodCell() {
        return whiteBloodCell;
    }

    public void setWhiteBloodCell(Float whiteBloodCell) {
        this.whiteBloodCell = whiteBloodCell;
    }

    public Float getPlatelets() {
        return platelets;
    }

    public void setPlatelets(Float platelets) {
        this.platelets = platelets;
    }

    public Float getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(Float hemoglobin) {
        this.hemoglobin = hemoglobin;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Boolean getisChecked() {
        return isChecked;
    }

    public void setisChecked(Boolean checked) {
        isChecked = checked;
    }

    public String getDataInsert() {
        return dataInsert;
    }

    public void setDataInsert(String dataInsert) {
        this.dataInsert = dataInsert;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

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
        return  id + "\t"  + redBloodCell +"\t\t" +   whiteBloodCell + "\t\t" + platelets+ "\t\t" + hemoglobin+ "\t\t" + idAdmin+ "\t\t" + idUser+ "\t\t" + isChecked+ "\t\t" + dataInsert;
    }
}