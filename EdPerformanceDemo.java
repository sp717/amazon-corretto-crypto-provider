import java.security.*;
import java.util.Base64;
import com.amazon.corretto.crypto.provider.AmazonCorrettoCryptoProvider;

public class EdPerformanceDemo {
    private static final String NATIVE_PROVIDER = AmazonCorrettoCryptoProvider.PROVIDER_NAME;
    private static final String SUN_EC_PROVIDER = "SunEC";

    private static final int NUM_OPERATIONS = 10_000;

    public static void main(String[] args) throws Exception {
        AmazonCorrettoCryptoProvider.install();
        String RESET = "\u001B[0m";
        String CYAN = "\u001B[36m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m"; 

        System.out.println(RESET);
        System.out.println("Ed25519 Performance Comparison");
        System.out.println("Number of operations: " + NUM_OPERATIONS);

        byte[] message = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        // Key Generation
        System.out.println(CYAN + "\nKey Generation:");
        testKeyGeneration(NATIVE_PROVIDER, message);
        testKeyGeneration(SUN_EC_PROVIDER, message);

        // Signing
        System.out.println(RED + "\nSigning:");
        testSigning(NATIVE_PROVIDER, message);
        testSigning(SUN_EC_PROVIDER, message);

        // Verification
        System.out.println(GREEN + "\nVerification:");
        testVerification(NATIVE_PROVIDER, message);
        testVerification(SUN_EC_PROVIDER, message);

        System.out.println(RESET);
    }

    private static void testKeyGeneration(String provider, byte[] message) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("Ed25519", provider);
        long startTime = System.nanoTime();
        for (int i = 0; i < NUM_OPERATIONS; i++) {
            kpg.generateKeyPair();
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.printf("Provider: %s, Elapsed time: %.3f ms%n", provider, elapsedTime / 1_000_000.0);
    }

    private static void testSigning(String provider, byte[] message) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("Ed25519", provider);
        KeyPair kp = kpg.generateKeyPair();
        PrivateKey privKey = kp.getPrivate();
        Signature signer = Signature.getInstance("Ed25519", provider);

        long startTime = System.nanoTime();
        for (int i = 0; i < NUM_OPERATIONS; i++) {
            signer.initSign(privKey);
            signer.update(message);
            signer.sign();
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.printf("Provider: %s, Elapsed time: %.3f ms%n", provider, elapsedTime / 1_000_000.0);
    }

    private static void testVerification(String provider, byte[] message) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("Ed25519", provider);
        KeyPair kp = kpg.generateKeyPair();
        PublicKey pubKey = kp.getPublic();
        PrivateKey privKey = kp.getPrivate();

        Signature signer = Signature.getInstance("Ed25519", provider);
        signer.initSign(privKey);
        signer.update(message);
        byte[] signature = signer.sign();

        Signature verifier = Signature.getInstance("Ed25519", provider);

        long startTime = System.nanoTime();
        for (int i = 0; i < NUM_OPERATIONS; i++) {
            verifier.initVerify(pubKey);
            verifier.update(message);
            verifier.verify(signature);
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.printf("Provider: %s, Elapsed time: %.3f ms%n", provider, elapsedTime / 1_000_000.0);
    }
}