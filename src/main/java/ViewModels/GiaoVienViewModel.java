package ViewModels;

public class GiaoVienViewModel {
    private String Ma;
    private String HoTen;

    private String LopPhuTrach;

    public GiaoVienViewModel(String ma, String hoTen, String lopPhuTrach) {
        Ma = ma;
        HoTen = hoTen;

        LopPhuTrach = lopPhuTrach;
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String ma) {
        Ma = ma;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }



    public String getLopPhuTrach() {
        return LopPhuTrach;
    }

    public void setLopPhuTrach(String lopPhuTrach) {
        LopPhuTrach = lopPhuTrach;
    }
}
