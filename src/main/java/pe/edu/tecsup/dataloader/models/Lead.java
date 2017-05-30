package pe.edu.tecsup.dataloader.models;

/**
 * Created by ebenites on 29/05/2017.
 */
public class Lead {

    private String id;
    private String sisid;
    private String lastname;
    private String firstname;
    private String company;
    private String mobilephone;
    private String phone;
    private String email;
    private String status;
    private String leadsource;
    private String ownerid;
    private String apematerno;
    private String cargo;
    private String fechalpd;
    private String leyproteccion;
    private String area;
    private String documento;
    private String sexo;
    private String distrito;
    private String nacimiento;
    private String tipodocpersona;
    private String tipodocempresa;
    private String numdocpersona;
    private String numdocempresa;
    private String tipo;
    private String sede;
    private String oficina;
    private String conocido;
    private String areainteres;
    private String familia;
    private String producto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSisid() {
        return sisid;
    }

    public void setSisid(String sisid) {
        this.sisid = sisid;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLeadsource() {
        return leadsource;
    }

    public void setLeadsource(String leadsource) {
        this.leadsource = leadsource;
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

    public String getApematerno() {
        return apematerno;
    }

    public void setApematerno(String apematerno) {
        this.apematerno = apematerno;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFechalpd() {
        return fechalpd;
    }

    public void setFechalpd(String fechalpd) {
        this.fechalpd = fechalpd;
    }

    public String getLeyproteccion() {
        return leyproteccion;
    }

    public void setLeyproteccion(String leyproteccion) {
        this.leyproteccion = leyproteccion;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getTipodocpersona() {
        return tipodocpersona;
    }

    public void setTipodocpersona(String tipodocpersona) {
        this.tipodocpersona = tipodocpersona;
    }

    public String getTipodocempresa() {
        return tipodocempresa;
    }

    public void setTipodocempresa(String tipodocempresa) {
        this.tipodocempresa = tipodocempresa;
    }

    public String getNumdocpersona() {
        return numdocpersona;
    }

    public void setNumdocpersona(String numdocpersona) {
        this.numdocpersona = numdocpersona;
    }

    public String getNumdocempresa() {
        return numdocempresa;
    }

    public void setNumdocempresa(String numdocempresa) {
        this.numdocempresa = numdocempresa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getConocido() {
        return conocido;
    }

    public void setConocido(String conocido) {
        this.conocido = conocido;
    }

    public String getAreainteres() {
        return areainteres;
    }

    public void setAreainteres(String areainteres) {
        this.areainteres = areainteres;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Lead{" +
                "id='" + id + '\'' +
                ", sisid='" + sisid + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", company='" + company + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", leadsource='" + leadsource + '\'' +
                ", ownerid='" + ownerid + '\'' +
                ", apematerno='" + apematerno + '\'' +
                ", cargo='" + cargo + '\'' +
                ", fechalpd='" + fechalpd + '\'' +
                ", leyproteccion='" + leyproteccion + '\'' +
                ", area='" + area + '\'' +
                ", documento='" + documento + '\'' +
                ", sexo='" + sexo + '\'' +
                ", distrito='" + distrito + '\'' +
                ", nacimiento='" + nacimiento + '\'' +
                ", tipodocpersona='" + tipodocpersona + '\'' +
                ", tipodocempresa='" + tipodocempresa + '\'' +
                ", numdocpersona='" + numdocpersona + '\'' +
                ", numdocempresa='" + numdocempresa + '\'' +
                ", tipo='" + tipo + '\'' +
                ", sede='" + sede + '\'' +
                ", oficina='" + oficina + '\'' +
                ", conocido='" + conocido + '\'' +
                ", areainteres='" + areainteres + '\'' +
                ", familia='" + familia + '\'' +
                ", producto='" + producto + '\'' +
                '}';
    }
}
