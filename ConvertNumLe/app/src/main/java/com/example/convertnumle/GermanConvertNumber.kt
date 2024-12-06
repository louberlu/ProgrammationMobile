package com.example.convertnumle

class GermanConvertNumber : ConvertNumber() {
    override val unitsMap = mapOf(
        0 to "null",
        1 to "eins",
        2 to "zwei",
        3 to "drei",
        4 to "vier",
        5 to "fünf",
        6 to "sechs",
        7 to "sieben",
        8 to "acht",
        9 to "neun"
    )

    override val tensMap = mapOf(
        10 to "zehn",
        11 to "elf",
        12 to "zwölf",
        13 to "dreizehn",
        14 to "vierzehn",
        15 to "fünfzehn",
        16 to "sechzehn",
        17 to "siebzehn",
        18 to "achtzehn",
        19 to "neunzehn",
        20 to "zwanzig",
        30 to "dreißig",
        40 to "vierzig",
        50 to "fünfzig",
        60 to "sechzig",
        70 to "siebzig",
        80 to "achtzig",
        90 to "neunzig"
    )

    override val numbersMap = mapOf(
        0L to "null",
        1L to "eins",
        2L to "zwei",
        3L to "drei",
        4L to "vier",
        5L to "fünf",
        6L to "sechs",
        7L to "sieben",
        8L to "acht",
        9L to "neun",
        10L to "zehn",
        11L to "elf",
        12L to "zwölf",
        13L to "dreizehn",
        14L to "vierzehn",
        15L to "fünfzehn",
        16L to "sechzehn",
        17L to "siebzehn",
        18L to "achtzehn",
        19L to "neunzehn",
        20L to "zwanzig",
        30L to "dreißig",
        40L to "vierzig",
        50L to "fünfzig",
        60L to "sechzig",
        70L to "siebzig",
        80L to "achtzig",
        90L to "neunzig",
        100L to "hundert",
        1_000L to "tausend",
        1_000_000L to "Million",
        1_000_000_000L to "Milliarde",
        1_000_000_000_000L to "Billion"
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
            res = "Die Zahl ist zu groß!"
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
            res = unitsMap[u] + "und" + tensMap[d]
        }

        return res
    }

    override fun convertHundreds(c: Int, d: Int): String {
        var res = ""

        if (c == 1) {
            res = numbersMap[100L].toString()
        }else{
            res = unitsMap[c] + numbersMap[100L]
        }
        if (d != 0) {
            res += convertTens(d)
        }

        return res
    }

    override fun convertThousands(m: Int, c: Int, d: Int): String {
        var res = ""

        if(m==1) {
            res = numbersMap[1_000L].toString()
        }else if (m < 10) {
            res = unitsMap[m] + numbersMap[1_000L]
        } else if (m < 100) {
            res = convertTens(m) + numbersMap[1_000L]
        } else if (m < 1000) {
            val mNum = decomposeNumber(m.toLong())
            res = convertHundreds(mNum["hundred"]!!, mNum["ten"]!!) + numbersMap[1_000L]
        }

        if (c == 0 && d != 0) {
            res += convertTens(d)
        } else if (c != 0) {
            res += convertHundreds(c, d)
        }

        return res
    }

    override fun convertMillion(mi: Int, m: Int, c: Int, d: Int): String {
        var res = ""

        if(mi==1) {
            res = numbersMap[1_000_000L].toString()
        }else if (mi < 10) {
            res = unitsMap[mi] + numbersMap[1_000_000L]
        } else if (mi < 100) {
            res = convertTens(mi) + numbersMap[1_000_000L]
        } else if (mi < 1000) {
            val mNum = decomposeNumber(mi.toLong())
            res = convertHundreds(mNum["hundred"]!!, mNum["ten"]!!) + numbersMap[1_000_000L]
        }

        if(m==0 && c==0 && d!=0){
            res += convertTens(d)
        }else if(m==0 && c!=0){
            res += convertHundreds(c,d)
        }else if(m!=0){
            res += convertThousands(m,c,d)
        }

        return res
    }

    override fun convertMilliard(M: Int, mi: Int, m: Int, c: Int, d: Int): String{
        var res = ""

        if(M==1) {
            res = numbersMap[1_000_000_000L].toString()
        }else if(M<10) {
            res = unitsMap[M]+numbersMap[1_000_000_000L]
        }else if(M<100) {
            res = convertTens(M)+numbersMap[1_000_000_000L]
        }else if(M<1000) {
            var mNum = decomposeNumber(M.toLong())
            res = convertHundreds(mNum["hundred"]!!, mNum["ten"]!!)+numbersMap[1_000_000_000L]
        }

        if(mi==0 && m==0 && c==0 && d!=0){
            res += convertTens(d)
        }else if(mi==0 && m==0 && c!=0){
            res += convertHundreds(c,d)
        }else if(mi==0 && m!=0){
            res += convertThousands(m,c,d)
        }else if(mi!=0){
            res += convertMillion(mi,m,c,d)
        }

        return res
    }

    override fun convertBillion(b: Int, M: Int, mi: Int, m: Int, c: Int, d: Int): String {
        var res = ""

        if(b==1) {
            res = numbersMap[1_000_000_000_000L].toString()
        }else if(b<10) {
            res = unitsMap[b]+numbersMap[1_000_000_000_000L]
        }else if(b<100) {
            res = convertTens(b)+numbersMap[1_000_000_000_000L]
        }else if(b<1000) {
            var mNum = decomposeNumber(b.toLong())
            println(b)
            res = convertHundreds(mNum["hundred"]!!, mNum["ten"]!!)+numbersMap[1_000_000_000_000L]
        }

        if(b==0 && mi==0 && m==0 && c==0 && d!=0){
            res += convertTens(d)
        }else if(b==0 && mi==0 && m==0 && c!=0){
            res += convertHundreds(c,d)
        }else if(b==0 && mi==0 && m!=0){
            res += convertThousands(m,c,d)
        }else if(b==0 && mi!=0){
            res += convertMillion(mi,m,c,d)
        }else if(b!=0){
            res += convertMilliard(M,mi,m,c,d)
        }

        return res
    }
}
