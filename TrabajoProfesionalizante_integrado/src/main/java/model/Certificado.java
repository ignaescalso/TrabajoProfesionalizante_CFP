package model;

public class Certificado {

    // =====================================================
    // Campos usados por CAPACITACION / modelo original
    // =====================================================
    private String serie;
    private String identificador_1;
    private String identificador_2;
    private String identificador_3;
    private String cfp_numero;
    private String nombre;
    private String dni;
    private String provincia;
    private String fecha_nacimiento;
    private String curso;
    private String area;
    private String anexo;
    private String duracion_hs;
    private String fecha_egreso;
    private String localidad;
    private String dia_emision;
    private String mes_emision;
    private String anio_emision;

    // =====================================================
    // Campos agregados desde ModificadorModeloV1
    // ACTUALIZACION / TRAYECTORIA
    // =====================================================
    private String numero;
    private String numero2;
    private String numero3;

    private String provinciaNacimiento;
    private String fechaNacimiento;

    private String cfpnumero;
    private String capacitacionCursada;
    private String cantidadHoras;
    private String trayectoFormativo;

    private String fechaEgreso;
    private String ciudadEgreso;

    private String diaCertificado;
    private String mesCertificado;
    private String anioCertificado;

    private String idComponente1;
    private String capacitacion1;
    private String horasReloj1;
    private String numeroCarton1;

    private String idComponente2;
    private String capacitacion2;
    private String horasReloj2;
    private String numeroCarton2;

    private String cargaHorariaAcumulada;

    public Certificado() {
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getIdentificador_1() {
        return identificador_1;
    }

    public void setIdentificador_1(String identificador_1) {
        this.identificador_1 = identificador_1;
    }

    public String getIdentificador_2() {
        return identificador_2;
    }

    public void setIdentificador_2(String identificador_2) {
        this.identificador_2 = identificador_2;
    }

    public String getIdentificador_3() {
        return identificador_3;
    }

    public void setIdentificador_3(String identificador_3) {
        this.identificador_3 = identificador_3;
    }

    public String getCfp_numero() {
        return cfp_numero;
    }

    public void setCfp_numero(String cfp_numero) {
        this.cfp_numero = cfp_numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

    public String getDuracion_hs() {
        return duracion_hs;
    }

    public void setDuracion_hs(String duracion_hs) {
        this.duracion_hs = duracion_hs;
    }

    public String getFecha_egreso() {
        return fecha_egreso;
    }

    public void setFecha_egreso(String fecha_egreso) {
        this.fecha_egreso = fecha_egreso;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDia_emision() {
        return dia_emision;
    }

    public void setDia_emision(String dia_emision) {
        this.dia_emision = dia_emision;
    }

    public String getMes_emision() {
        return mes_emision;
    }

    public void setMes_emision(String mes_emision) {
        this.mes_emision = mes_emision;
    }

    public String getAnio_emision() {
        return anio_emision;
    }

    public void setAnio_emision(String anio_emision) {
        this.anio_emision = anio_emision;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumero2() {
        return numero2;
    }

    public void setNumero2(String numero2) {
        this.numero2 = numero2;
    }

    public String getNumero3() {
        return numero3;
    }

    public void setNumero3(String numero3) {
        this.numero3 = numero3;
    }

    public String getProvinciaNacimiento() {
        return provinciaNacimiento;
    }

    public void setProvinciaNacimiento(String provinciaNacimiento) {
        this.provinciaNacimiento = provinciaNacimiento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCfpnumero() {
        return cfpnumero;
    }

    public void setCfpnumero(String cfpnumero) {
        this.cfpnumero = cfpnumero;
    }

    public String getCapacitacionCursada() {
        return capacitacionCursada;
    }

    public void setCapacitacionCursada(String capacitacionCursada) {
        this.capacitacionCursada = capacitacionCursada;
    }

    public String getCantidadHoras() {
        return cantidadHoras;
    }

    public void setCantidadHoras(String cantidadHoras) {
        this.cantidadHoras = cantidadHoras;
    }

    public String getTrayectoFormativo() {
        return trayectoFormativo;
    }

    public void setTrayectoFormativo(String trayectoFormativo) {
        this.trayectoFormativo = trayectoFormativo;
    }

    public String getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(String fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public String getCiudadEgreso() {
        return ciudadEgreso;
    }

    public void setCiudadEgreso(String ciudadEgreso) {
        this.ciudadEgreso = ciudadEgreso;
    }

    public String getDiaCertificado() {
        return diaCertificado;
    }

    public void setDiaCertificado(String diaCertificado) {
        this.diaCertificado = diaCertificado;
    }

    public String getMesCertificado() {
        return mesCertificado;
    }

    public void setMesCertificado(String mesCertificado) {
        this.mesCertificado = mesCertificado;
    }

    public String getAnioCertificado() {
        return anioCertificado;
    }

    public void setAnioCertificado(String anioCertificado) {
        this.anioCertificado = anioCertificado;
    }

    public String getIdComponente1() {
        return idComponente1;
    }

    public void setIdComponente1(String idComponente1) {
        this.idComponente1 = idComponente1;
    }

    public String getCapacitacion1() {
        return capacitacion1;
    }

    public void setCapacitacion1(String capacitacion1) {
        this.capacitacion1 = capacitacion1;
    }

    public String getHorasReloj1() {
        return horasReloj1;
    }

    public void setHorasReloj1(String horasReloj1) {
        this.horasReloj1 = horasReloj1;
    }

    public String getNumeroCarton1() {
        return numeroCarton1;
    }

    public void setNumeroCarton1(String numeroCarton1) {
        this.numeroCarton1 = numeroCarton1;
    }

    public String getIdComponente2() {
        return idComponente2;
    }

    public void setIdComponente2(String idComponente2) {
        this.idComponente2 = idComponente2;
    }

    public String getCapacitacion2() {
        return capacitacion2;
    }

    public void setCapacitacion2(String capacitacion2) {
        this.capacitacion2 = capacitacion2;
    }

    public String getHorasReloj2() {
        return horasReloj2;
    }

    public void setHorasReloj2(String horasReloj2) {
        this.horasReloj2 = horasReloj2;
    }

    public String getNumeroCarton2() {
        return numeroCarton2;
    }

    public void setNumeroCarton2(String numeroCarton2) {
        this.numeroCarton2 = numeroCarton2;
    }

    public String getCargaHorariaAcumulada() {
        return cargaHorariaAcumulada;
    }

    public void setCargaHorariaAcumulada(String cargaHorariaAcumulada) {
        this.cargaHorariaAcumulada = cargaHorariaAcumulada;
    }
}
