/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ultity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author My laptop
 */
public class RegExpInputVerifier{
    private String expression;

    public RegExpInputVerifier(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
        

   
    public boolean verify(String value) {
        if(value.isEmpty()){
            return false;
        }
        Pattern pattern1 = Pattern.compile(expression);
        Matcher matcher1 = pattern1.matcher(value);
        return matcher1.find();
       
    }
    
}
