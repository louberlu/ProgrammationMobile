package com.example.convertnumle

abstract class ConvertNumber {
    protected abstract val unitsMap: Map<Int, String>
    protected abstract val tensMap: Map<Int, String>
    protected abstract val numbersMap: Map<Long, String>

    abstract fun convert(number: Long): String
    abstract fun decomposeNumber(number: Long): Map<String, Int>

    protected abstract fun convertTens(number: Int): String
    protected abstract fun convertHundreds(c: Int, d: Int): String
    protected abstract fun convertThousands(m: Int, c: Int, d: Int): String
    protected abstract fun convertMillion(mi: Int, m: Int, c: Int, d: Int):String
    protected abstract fun convertMilliard(M: Int, mi: Int, m: Int, c: Int, d: Int):String
    protected abstract fun convertBillion(b: Int, M: Int, mi: Int, m: Int, c: Int, d: Int):String
}