package model;

public class Schedule {
    private String nama;
    private String ukm;
    private String namaKegiatan;
    private String waktuKegiatan;

    public Schedule(String nama, String ukm, String namaKegiatan, String waktuKegiatan) {
        this.nama = nama;
        this.ukm = ukm;
        this.namaKegiatan = namaKegiatan;
        this.waktuKegiatan = waktuKegiatan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUkm() {
        return ukm;
    }

    public void setUkm(String ukm) {
        this.ukm = ukm;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public String getWaktuKegiatan() {
        return waktuKegiatan;
    }

    public void setWaktuKegiatan(String waktuKegiatan) {
        this.waktuKegiatan = waktuKegiatan;
    }
}
