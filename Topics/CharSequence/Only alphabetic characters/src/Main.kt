fun containsOnlyAlphabets(charSequence: CharSequence): Boolean {
    return charSequence.matches("^\\p{IsAlphabetic}*$".toRegex())
}