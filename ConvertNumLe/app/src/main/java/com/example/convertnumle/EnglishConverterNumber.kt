package com.example.convertnumle

class EnglishConvertNumber : ConvertNumber() {
    override val unitsMap = mapOf(
        0 to "zero",
        1 to "one",
        2 to "two",
        3 to "three",
        4 to "four",
        5 to "five",
        6 to "six",
        7 to "seven",
        8 to "eight",
        9 to "nine"
    )

    override val tensMap = mapOf(
        10 to "ten",
        11 to "eleven",
        12 to "twelve",
        13 to "thirteen",
        14 to "fourteen",
        15 to "fifteen",
        16 to "sixteen",
        17 to "seventeen",
        18 to "eighteen",
        19 to "nineteen",
        20 to "twenty",
        30 to "thirty",
        40 to "forty",
        50 to "fifty",
        60 to "sixty",
        70 to "seventy",
        80 to "eighty",
        90 to "ninety"
    )

    override val numbersMap = mapOf(
        0L to "zero",
        1L to "one",
        2L to "two",
        3L to "three",
        4L to "four",
        5L to "five",
        6L to "six",
        7L to "seven",
        8L to "eight",
        9L to "nine",
        10L to "ten",
        11L to "eleven",
        12L to "twelve",
        13L to "thirteen",
        14L to "fourteen",
        15L to "fifteen",
        16L to "sixteen",
        17L to "seventeen",
        18L to "eighteen",
        19L to "nineteen",
        20L to "twenty",
        30L to "thirty",
        40L to "forty",
        50L to "fifty",
        60L to "sixty",
        70L to "seventy",
        80L to "eighty",
        90L to "ninety",
        100L to "hundred",
        1_000L to "thousand",
        1_000_000L to "million",
        1_000_000_000L to "billion",
        1_000_000_000_000L to "trillion"
    )

    override fun convert(number: Long): String {
        val dNum = decomposeNumber(number)
        var res = ""

        if (number < 10L) {
            res = unitsMap[number.toInt()].toString()
        } else if (number < 100L) {
            val d = dNum["ten"]!!
            res = convertTens(d)
        } else if (number < 1000L) {
            val c = dNum["hundred"]!!
            val d = dNum["ten"]!!
            res = convertHundreds(c, d)
        } else if (number < 1_000_000L) {
            val m = dNum["thousand"]!!
            val c = dNum["hundred"]!!
            val d = dNum["ten"]!!
            res = convertThousands(m, c, d)
        } else if (number < 1_000_000_000L) {
            val mi = dNum["million"]!!
            val m = dNum["thousand"]!!
            val c = dNum["hundred"]!!
            val d = dNum["ten"]!!
            res = convertMillion(mi, m, c, d)
        } else if (number < 1_000_000_000_000L) {
            val b = dNum["billion"]!!
            val mi = dNum["million"]!!
            val m = dNum["thousand"]!!
            val c = dNum["hundred"]!!
            val d = dNum["ten"]!!
            res = convertMilliard(b, mi, m, c, d)
        } else if (number <= 999_999_999_999_999L) {
            val t = dNum["trillion"]!!
            val b = dNum["billion"]!!
            val mi = dNum["million"]!!
            val m = dNum["thousand"]!!
            val c = dNum["hundred"]!!
            val d = dNum["ten"]!!
            res = convertBillion(t, b, mi, m, c, d)
        } else {
            res = "The number is too large!"
        }

        return res
    }

    override fun decomposeNumber(number: Long): Map<String, Int> {
        var res = mutableMapOf<String, Int>()

        val trillion = (number / 1_000_000_000_000L).toInt()
        val billion = ((number - trillion * 1_000_000_000_000L) / 1_000_000_000L).toInt()
        val million = ((number - trillion * 1_000_000_000_000L - billion * 1_000_000_000L) / 1_000_000L).toInt()
        val thousand = ((number - trillion * 1_000_000_000_000L - billion * 1_000_000_000L - million * 1_000_000L) / 1_000L).toInt()
        val hundred = ((number - trillion * 1_000_000_000_000L - billion * 1_000_000_000L - million * 1_000_000L - thousand * 1_000L) / 100L).toInt()
        val ten = (number - trillion * 1_000_000_000_000L - billion * 1_000_000_000L - million * 1_000_000L - thousand * 1_000L - hundred * 100L).toInt()

        mapOf(
            "trillion" to trillion,
            "billion" to billion,
            "million" to million,
            "thousand" to thousand,
            "hundred" to hundred,
            "ten" to ten
        ).also { res = it as MutableMap<String, Int> }

        return res
    }

    override fun convertTens(number: Int): String {
        val u = number % 10
        val d = number - u
        var res = ""

        if (number in tensMap.keys) {
            res = tensMap[number].toString()
        } else {
            res = tensMap[d]+" "+unitsMap[u]
        }

        return res
    }

    override fun convertHundreds(c: Int, d: Int): String {
        var res = ""

        res = unitsMap[c] + " " + numbersMap[100L]

        if (d != 0) {
            res += " " + convertTens(d)
        }

        return res
    }

    override fun convertThousands(m: Int, c: Int, d: Int): String {
        var res = ""

        if (m < 10) {
            res = unitsMap[m] + " " + numbersMap[1_000L]
        } else if (m < 100) {
            res = convertTens(m) + " " + numbersMap[1_000L]
        } else if (m < 1000) {
            val mNum = decomposeNumber(m.toLong())
            res = convertHundreds(mNum["hundred"]!!, mNum["ten"]!!) + " " + numbersMap[1_000L]
        }

        if (c == 0 && d != 0) {
            res += " " + convertTens(d)
        } else if (c != 0) {
            res += " " + convertHundreds(c, d)
        }

        return res
    }

    override fun convertMillion(mi: Int, m: Int, c: Int, d: Int): String{
        var res = ""

        if(mi<10) {
            res = unitsMap[mi]+" "+numbersMap[1_000_000L]
        }else if(mi<100) {
            res = convertTens(mi)+" "+numbersMap[1_000_000L]
        }else if(mi<1000) {
            var mNum = decomposeNumber(mi.toLong())
            res = convertHundreds(mNum["hundred"]!!, mNum["ten"]!!)+" "+numbersMap[1_000_000L]
        }

        if(m==0 && c==0 && d!=0){
            res = res+" "+convertTens(d)
        }else if(m==0 && c!=0){
            res = res+" "+convertHundreds(c,d)
        }else if(m!=0){
            res = res+" "+convertThousands(m,c,d)
        }

        return res
    }

    override fun convertMilliard(M: Int, mi: Int, m: Int, c: Int, d: Int): String{
        var res = ""

        if(M<10) {
            res = unitsMap[M]+" "+numbersMap[1_000_000_000L]
        }else if(M<100) {
            res = convertTens(M)+" "+numbersMap[1_000_000_000L]
        }else if(M<1000) {
            var mNum = decomposeNumber(M.toLong())
            res = convertHundreds(mNum["hundred"]!!, mNum["ten"]!!)+" "+numbersMap[1_000_000_000L]
        }

        if(mi==0 && m==0 && c==0 && d!=0){
            res = res+" "+convertTens(d)
        }else if(mi==0 && m==0 && c!=0){
            res = res+" "+convertHundreds(c,d)
        }else if(mi==0 && m!=0){
            res = res+" "+convertThousands(m,c,d)
        }else if(mi!=0){
            res = res+" "+convertMillion(mi,m,c,d)
        }

        return res
    }

    override fun convertBillion(b: Int, M: Int, mi: Int, m: Int, c: Int, d: Int): String {
        var res = ""

        if(b<10) {
            res = unitsMap[b]+" "+numbersMap[1_000_000_000_000L]
        }else if(b<100) {
            res = convertTens(b)+" "+numbersMap[1_000_000_000_000L]
        }else if(b<1000) {
            var mNum = decomposeNumber(b.toLong())
            println(b)
            res = convertHundreds(mNum["hundred"]!!, mNum["ten"]!!)+" "+numbersMap[1_000_000_000_000L]
        }

        if(b==0 && mi==0 && m==0 && c==0 && d!=0){
            res = res+" "+convertTens(d)
        }else if(b==0 && mi==0 && m==0 && c!=0){
            res = res+" "+convertHundreds(c,d)
        }else if(b==0 && mi==0 && m!=0){
            res = res+" "+convertThousands(m,c,d)
        }else if(b==0 && mi!=0){
            res = res+" "+convertMillion(mi,m,c,d)
        }else if(b!=0){
            res = res+" "+convertMilliard(M,mi,m,c,d)
        }

        return res
    }
}