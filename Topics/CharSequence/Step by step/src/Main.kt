fun removeEveryOtherChar(input: CharSequence): CharSequence {
    // write your code here
    return input.filterIndexed{i, c -> i % 2 == 0 }
}