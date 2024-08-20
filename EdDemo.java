import java.security.*;
import java.util.Set;

import com.amazon.corretto.crypto.provider.AmazonCorrettoCryptoProvider;

class EdDemo {
    
    static final String NATIVE_PROVIDER = AmazonCorrettoCryptoProvider.PROVIDER_NAME;
    public static void main(String[] args) throws Exception {
        try {
            AmazonCorrettoCryptoProvider.install();

            // Provider provider = Security.getProvider(NATIVE_PROVIDER);
            // Set<Provider.Service> services = provider.getServices();
            // System.out.println("List of Services");
            // for (Provider.Service service : services) {
            //     String algorithm = service.getAlgorithm();
            //     String type = service.getType();
            //     System.out.println("Algorithm: " + algorithm + ", Type: " + type);
            // }

            // // Key Generation
            // KeyPairGenerator kpg = KeyPairGenerator.getInstance("Ed25519", NATIVE_PROVIDER);
            // KeyPair kp = kpg.generateKeyPair();
            // System.out.println("Successfully Generated Keys.");
            // PrivateKey privKey = kp.getPrivate();
            // PublicKey pubKey = kp.getPublic();
            // System.out.println(bytesToHex(privKey.getEncoded()));
            // System.out.println(bytesToHex(pubKey.getEncoded()));
            
            // // Signing
            // byte[] message = new byte[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
            // Signature signer = Signature.getInstance("Ed25519", NATIVE_PROVIDER);
            // System.out.println("Signing Message");
            // signer.initSign(privKey);
            // signer.update(message);
            // byte[] signature = signer.sign();
            // System.out.println("Successfully signed message:");
            // String sigString = bytesToHex(signature);
            // System.out.println(sigString);

            // // Verification
            // Signature verifier = Signature.getInstance("Ed25519", NATIVE_PROVIDER);
            // verifier.initVerify(pubKey);
            // verifier.update(message);
            // System.out.println("Verifying Signature: " + sigString);
            // if (verifier.verify(signature)) {
            //     System.out.println("Verification Successful.");
            // } else {
            //     System.out.println("Verification Failed");
            // }
        } catch (Exception e) {
            throw e;
        }  
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}