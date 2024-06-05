package model

data class Message(
    val message: String,
    val type: String,
    val time: Long,
    val seen: Boolean,
    val from: String,
)
