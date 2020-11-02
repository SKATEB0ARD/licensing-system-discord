import me.brennan.licensesystem.LicenseSystem;

/**
 * @author Brennan
 * @since 10/28/2020
 **/
public class Main {

    public static void main(String[] args) {
        try {
            LicenseSystem.INSTANCE.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
