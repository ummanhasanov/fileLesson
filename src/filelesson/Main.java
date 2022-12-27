/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filelesson;


/**
 *
 * @author Umman Hasan
 */
public class Main
{

    public static void main(String[] args) throws  Exception {
//        FileUtility2.writeIntoFile("test.txt", "Salam Dunya");
        FileUtility.appendIntoFile("test.txt", "Necesen");
        String str = FileUtility.read("test.txt");
        System.out.println(str);
        byte[] str1 = FileUtility.readBytes("test.txt");
        System.out.println(new String(str1));
        
        // byte - larla capa vermek
        for(int i = 0; i<str1.length; i++){
            System.out.println(str1[i]+"="+(char)str1[i]);
        }
        
        // bir sekili byte oxuyucusu ile oxuyub
        byte[] data = FileUtility.readBytes("test.png");
        // byte yazicisi ile basqa bir fayla yazacagiq
        FileUtility.writeBytes("test2.png", data);
        
        
        User uw = new User();
        uw.name = "Ummantest";
        uw.password = "testnammU";
        
        FileUtility.writeObjectToFile(uw, "test.obj");
        
        User ur = (User) FileUtility.readFileDeserialize("test.obj");
        System.out.println(ur.name);
        System.out.println(ur.password);
    }
}
