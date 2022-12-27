/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filelesson;

import java.io.Serializable;

/**
 *
 * @author Umman Hasan
 */
public class User implements Serializable
{

    /* Her defe fayli yazib ve oxuyanda bir serialVersionUID olur ve her defe 
    yoxlanilir eger ust uste dusurse demek hemin fayldir ust uste dusmurse basqa
    fayldir
     */
    
    private static final long serialVersionUID = 1L;

    public String name;
    //transient  yazmaqla password fayla yazilmasinin qarwisi alinir
    public transient String password;
    
    public int i;
    
}
