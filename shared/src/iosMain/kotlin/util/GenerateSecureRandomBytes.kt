package util

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.refTo
import platform.Security.SecRandomCopyBytes
import platform.Security.kSecRandomDefault

@OptIn(ExperimentalForeignApi::class)
actual fun generateSecureRandomBytes(size: Int): ByteArray {
    val bytes = ByteArray(size)
    SecRandomCopyBytes(kSecRandomDefault, size.toULong(), bytes.refTo(0))
    return bytes
}

actual fun generateUserPrivateKey(secureRandom: ByteArray): ByteArray {
    return ByteArray(2)
}

actual fun generateUserPublicKey(privateKey: ByteArray): ByteArray {
    return ByteArray(2)
}

actual fun generateKeyAES(email: String, salt: ByteArray): Any {
    return ""
}

actual fun encryptAES(data: String, email: String): String {
    return ""
}