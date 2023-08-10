/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author My laptop
 */
public class Contants {
    public static String UserRegex = new String("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$");
    public static String NienKhoaRegex = new String("^[0-9]{4}-[0-9]{4}$");
    public static String DateRegex = new String("^[0-9]{2}-[0-9]{2}-[0-9]{4}$");
    public static final String TenLopHocRegex = new String("^[a-zA-Z0-9]{3,50}$");
    public static final String SiSoLopRegex = new String("^[1-9][0-9]$");
    public static final String HocPhiRegex = new String("^[1-9]\\d*|\\d+(?:\\.\\d+)?$");
}
