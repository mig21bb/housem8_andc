package tk.housem8.Entities;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

/**
 * Created by Administrador on 26/09/2017.
 */

public class CostRest {

    private Integer id;
    private String description;
    private Integer period;
    private Date datetime;
    //
    private Double amount;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Date fechaBorrado;
    private boolean activo;
    private int mateId;
    private String mateName;
    private int houseId;
    private String houseName;
    private int costFamilyId;
    private Integer commerceId;
    private String commerceName;
    private String commerceLogo;

    public CostRest(JSONObject costRestJson) throws JSONException, ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        this.id = (Integer) costRestJson.get("id");
        if(!costRestJson.get("period").equals(JSONObject.NULL)){
            this.period = (Integer) costRestJson.get("period");
        }
        if(!costRestJson.get("amount").equals(JSONObject.NULL)){
            this.amount = (Double) costRestJson.get("amount");
        }

        if(!costRestJson.get("datetime").equals(JSONObject.NULL)){
            this.datetime = format.parse((String) costRestJson.get("datetime"));
        }
        if(!costRestJson.get("fechaCreacion").equals(JSONObject.NULL)){
            this.fechaCreacion = format.parse((String) costRestJson.get("fechaCreacion"));
        }
        if(!costRestJson.get("fechaModificacion").equals(JSONObject.NULL)){
            this.fechaModificacion =  format.parse((String)costRestJson.get("fechaModificacion"));
        }
        if(!costRestJson.get("fechaBorrado").equals(JSONObject.NULL)){
            this.fechaBorrado =  format.parse((String) costRestJson.get("fechaBorrado"));
        }
        this.activo = (boolean) costRestJson.get("activo");
        if(!costRestJson.get("mateId").equals(JSONObject.NULL)) {
            this.mateId = (int) costRestJson.get("mateId");
        }
        if(!costRestJson.get("houseId").equals(JSONObject.NULL)) {
            this.houseId = (int) costRestJson.get("houseId");
        }
        if(!costRestJson.get("mateName").equals(JSONObject.NULL)) {
            this.mateName = (String) costRestJson.get("mateName");
        }
        if(!costRestJson.get("houseName").equals(JSONObject.NULL)) {
            this.houseName = (String) costRestJson.get("houseName");
        }
        if(!costRestJson.get("costFamilyId").equals(JSONObject.NULL)) {
            this.costFamilyId = (int) costRestJson.get("costFamilyId");
        }
        if(!costRestJson.get("commerceId").equals(JSONObject.NULL)) {
            this.commerceId = (Integer) costRestJson.get("commerceId");
        }
        if(!costRestJson.get("commerceLogo").equals(JSONObject.NULL)) {
            this.commerceName = (String) costRestJson.get("commerceName");
        }

        if(!costRestJson.get("commerceLogo").equals(JSONObject.NULL)) {
            this.commerceLogo = (String) costRestJson.get("commerceLogo");
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getFechaBorrado() {
        return fechaBorrado;
    }

    public void setFechaBorrado(Date fechaBorrado) {
        this.fechaBorrado = fechaBorrado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getMateId() {
        return mateId;
    }

    public void setMateId(int mateId) {
        this.mateId = mateId;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public int getCostFamilyId() {
        return costFamilyId;
    }

    public void setCostFamilyId(int costFamilyId) {
        this.costFamilyId = costFamilyId;
    }

    public Integer getCommerceId() {
        return commerceId;
    }

    public void setCommerceId(Integer commerceId) {
        this.commerceId = commerceId;
    }

    public String getCommerceName() {
        return commerceName;
    }

    public void setCommerceName(String commerceName) {
        this.commerceName = commerceName;
    }

    public String getCommerceLogo() {
        return commerceLogo;
    }

    public void setCommerceLogo(String commerceLogo) {
        this.commerceLogo = commerceLogo;
    }

    public String getMateName() {
        return mateName;
    }

    public void setMateName(String mateName) {
        this.mateName = mateName;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
}
