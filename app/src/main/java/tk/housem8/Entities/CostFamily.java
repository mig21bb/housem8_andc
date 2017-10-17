package tk.housem8.Entities;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by Administrador on 26/09/2017.
 */

public class CostFamily {

    private Integer id;
    private String name;
    private String description;
    private Integer period;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Date fechaBorrado;
    private boolean activo;

    public CostFamily(Integer id, String name, String description, Integer period, Date fechaCreacion, Date fechaModificacion, boolean activo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.period = period;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.activo = activo;
    }

    public CostFamily(JSONObject jsonObject) throws JSONException {

        this.id = (Integer) jsonObject.get("id");
        this.description =(String) jsonObject.get("description");
        if(!jsonObject.get("period").equals(JSONObject.NULL)) {
            this.period = (int) jsonObject.get("period");
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return description;
    }
}
