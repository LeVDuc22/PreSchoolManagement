package Models;

public class ChiTietGiaoVien {
    private String MaGiaoVien;
    private String MaLopHoc;

    public ChiTietGiaoVien(String maGiaoVien, String maLopHoc) {
        MaGiaoVien = maGiaoVien;
        MaLopHoc = maLopHoc;
    }

    public String getMaGiaoVien() {
        return MaGiaoVien;
    }

    public void setMaGiaoVien(String maGiaoVien) {
        MaGiaoVien = maGiaoVien;
    }

    public String getMaLopHoc() {
        return MaLopHoc;
    }

    public void setMaLopHoc(String maLopHoc) {
        MaLopHoc = maLopHoc;
    }
}
