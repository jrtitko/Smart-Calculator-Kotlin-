fun verificationCode(): String {
    // use these letters to create the code
    val letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789"

    // complete the function and then return the code
    return letters.toList().shuffled().toString().substring(0..9)
}