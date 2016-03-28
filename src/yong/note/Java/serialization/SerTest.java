package yong.note.Java.serialization;

import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

/**
 * Created by yong on 28/03/16.
 */
public class SerTest {
    @Test
    public void serializeToDisk() {
        try {
            Person ted = new Person("Ted", "Neward", 39);
            Person charl = new Person("Charlotte",
                    "Neward", 38);

            ted.setSpouse(charl);
            charl.setSpouse(ted);

            FileOutputStream fos = new FileOutputStream("tempdata.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ted);
            oos.close();
        } catch (Exception ex) {
            fail("Exception thrown during test: " + ex.toString());
        }

        try {
            FileInputStream fis = new FileInputStream("tempdata.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Person ted = (Person) ois.readObject();
            ois.close();

            assertEquals(ted.getFirstName(), "Ted");
            assertEquals(ted.getSpouse().getFirstName(), "Charlotte");

            // Clean up the file
            new File("tempdata.ser").delete();
        } catch (Exception ex) {
            fail("Exception thrown during test: " + ex.toString());
        }
    }
}