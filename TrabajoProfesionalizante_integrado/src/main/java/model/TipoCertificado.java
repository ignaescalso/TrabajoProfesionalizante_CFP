package model;

public enum TipoCertificado {
	
	ACTUALIZACION("actualizacion.pdf"),
    CAPACITACION("capacitacion.pdf"),
	MINISTERIALES("ministeriales.pdf"),
	TRAYECTORIA("trayectoria.pdf");

    private String archivoPdf;

    TipoCertificado(String archivoPdf) {
        this.archivoPdf = archivoPdf;
    }

    public String getArchivoPdf() {
        return archivoPdf;
    }

}
