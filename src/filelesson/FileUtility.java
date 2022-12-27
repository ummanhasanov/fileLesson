/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filelesson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Umman Hasan
 */
public class FileUtility
{

    private static void write(String fileName, String text, boolean append) throws Exception {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, append));) {
            bw.write(text);
        }
    }

    public static void writeIntoFile(String fileName, String text) throws Exception {
        write(fileName, text, false); // text silib uzerine yenisini yazir
    }

    public static void appendIntoFile(String fileName, String text) throws Exception {
        write(fileName, text, true); // text silmeden ardiyca yenisini yazir
    }

    public static void writeToFile2(String fileName, byte[] data) throws Exception {

        File file = new File(fileName);
        FileOutputStream fop = new FileOutputStream(file);

        fop.write(data);
        fop.flush();
        fop.close();

        System.out.println("Done");

    }

    public static String read(String fileName) throws Exception {
        InputStream in = new FileInputStream(fileName);
        InputStreamReader r = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(r);

        String line = null;
        String result = "";
        while ((line = reader.readLine()) != null) {
            result += line + "\n";
        }
        return result;
    }

    public static byte[] readBytes(String fileName) throws Exception {
        // File() closeable olmadigi ucun try-dan cole atiriq
        File file = new File(fileName);
        try ( FileInputStream fileInputStream = new FileInputStream(file);) {

            byte[] bytesArray = new byte[(int) file.length()];

            // read file into bytes[]
            fileInputStream.read(bytesArray);
            return bytesArray;
        }
    }

    public static void writeBytes(String fileName, byte[] data) throws Exception {

        FileOutputStream fop = new FileOutputStream(fileName);

        // get the content in byte
        fop.write(data);
        fop.flush();
        fop.close();

        System.out.println("Done");

    }

    public static Object readFileDeserialize(String name) throws Exception {
        Object obj = null;
        // name -den istifade ederek FileInputStream duzelir ve bunu gonderirik ObjectInputStream-e
        try ( ObjectInputStream in = new ObjectInputStream(new FileInputStream(name))) {
            obj = in.readObject();
        } finally {
            return obj;
        }

    }

// Serializable olmalidi mutleq obyektini gonderirik ve faylin adini gonderirik ki, bu fayla yaz
    public static void writeObjectToFile(Serializable object, String name) throws Exception {

        try ( // faylin adindan FileOutputStream  duzeldir
                 FileOutputStream fout = new FileOutputStream(name); // fout-dan da bir ObjectOutputStream duzeldir
                  ObjectOutputStream oos = new ObjectOutputStream(fout);) {
            // ObjectOutputStream deyirik object-i write ele
            oos.writeObject(object);
        }

    }

    public static void writeBytesNio(byte[] data, String fileName) throws Exception {
//        Path filePath = FileSystems.getDefault().getPath(fileName);// kohne method
        // fileName-den istifade ederek Path duzeldirik
        Path filePath = Paths.get(fileName);
        //  Files-in write() methodundan istifade ederek datani bura yaziriq
        Files.write(filePath, data);

    }

    public static byte[] readBytesNio(String fileName) throws Exception {
        // fileName-den istifade ederek Path duzeldirik
        Path filePath = Paths.get(fileName);
        // byte-lari read edib gonderir geriye
        return Files.readAllBytes(filePath);
    }

    public static void download(String fromFile, String toFile) throws Exception {
        // fromFile -dan bir URL duzelir website adinda
        URL website = new URL(fromFile);

        URL url = new URL(fromFile);

        URLConnection con = url.openConnection();
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);
        InputStream in = con.getInputStream();

        ReadableByteChannel rbc = Channels.newChannel(in);

        FileOutputStream fos = new FileOutputStream(toFile);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();

    }
}
