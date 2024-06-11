package util

expect fun loadECDHLibrary()
expect fun generateSecureRandomBytes(size: Int): ByteArray
expect fun generateUserPrivateKey(secureRandom: ByteArray): ByteArray
expect fun generateUserPublicKey(privateKey: ByteArray): ByteArray

expect fun generateKeyAES(email: String, salt: ByteArray): Any
expect fun encryptAES(data: String, email: String): String

