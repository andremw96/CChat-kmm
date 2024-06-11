package util

import android.util.Base64
import android.util.Log
import de.frank_durr.ecdh_curve25519.ECDHCurve25519
import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets
import java.security.SecureRandom
import java.security.spec.KeySpec
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

actual fun loadECDHLibrary() {
    try {
        System.loadLibrary("ecdhcurve25519");
        Log.i(
            "Load ecdh",
            "Loaded ecdhcurve25519 library."
        )
    } catch (e: UnsatisfiedLinkError) {
        Log.i(
            "Load ecdh",
            "Load failed ecdhcurve25519 library. $e"
        )
    }
}

actual fun generateSecureRandomBytes(size: Int): ByteArray {
    val random = SecureRandom()
    return ByteArray(size).apply { random.nextBytes(this) }
}

actual fun generateUserPrivateKey(secureRandom: ByteArray): ByteArray {
    return ECDHCurve25519.generate_secret_key(SecureRandom(secureRandom))
}

actual fun generateUserPublicKey(privateKey: ByteArray): ByteArray {
    return ECDHCurve25519.generate_public_key(privateKey)
}

actual fun generateKeyAES(email: String, salt: ByteArray): Any {
    val spec: KeySpec = PBEKeySpec(email.toCharArray(), salt, 65536, 128)
    val f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
    val key = f.generateSecret(spec).encoded

    return SecretKeySpec(key, "AES")
}

actual fun encryptAES(data: String, email: String): String {
    val random = SecureRandom()
    val salt = ByteArray(16)
    random.nextBytes(salt)

    val key = generateKeyAES(email, salt) as SecretKeySpec
    val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
    c.init(Cipher.ENCRYPT_MODE, key)
    val params = c.parameters
    val iv = params.getParameterSpec(IvParameterSpec::class.java).iv
    val encryptedText = c.doFinal(data.toByteArray(StandardCharsets.UTF_8))

    // concatenate salt + iv + ciphertext
    val outputStream = ByteArrayOutputStream()
    outputStream.write(salt)
    outputStream.write(iv)
    outputStream.write(encryptedText)

    return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
}
