object Math {
    fun abs(v: Int): Int {
        return if (v >= 0) v else (-1 * v)
    }

    fun abs(v: Double): Double {
        return if (v >= 0.0) v else (-1 * v)
    }
}
