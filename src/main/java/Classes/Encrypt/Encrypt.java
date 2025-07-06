package Classes.Encrypt;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class Encrypt {
    private int iterations = 4;
    int memory = 131072;
    int parallelism = 2;

    public String HashWithArgon2(String password) {
        Argon2 engine = Argon2Factory.create();
        char[] pass_Array = password.toCharArray();
        try {
            return engine.hash(iterations, memory, parallelism, pass_Array);
        } finally {
            engine.wipeArray(pass_Array);
        }
    }

    public boolean verifyPass(String hash, String password) {
        Argon2 engine = Argon2Factory.create();
        char[] pass_Array = password.toCharArray();
        try {
            return engine.verify(hash, pass_Array);
        } finally {
            engine.wipeArray(pass_Array);
        }
    }
}
