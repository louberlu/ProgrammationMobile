package com.example.convertnumle

class FrenchConvertNumber : ConvertNumber() {
    override val unitsMap = mapOf(
        0 to "zero",
        1 to "un",
        2 to "deux",
        3 to "trois",
        4 to "quatre",
        5 to "cinq",
        6 to "six",
        7 to "sept",
        8 to "huit",
        9 to "neuf"
    )

    override val tensMap = mapOf(
        10 to "dix",
        11 to "onze",
        12 to "douze",
        13 to "treize",
        14 to "quatorze",
        15 to "quinze",
        16 to "seize",
        17 to "dix-sept",
        18 to "dix-huit",
        19 to "dix-neuf",
        20 to "vingt",
        30 to "trente",
        40 to "quarante",
        50 to "cinquante",
        60 to "soixante",
        70 to "soixante-dix",
        80 to "quatre-vingt",
        90 to "quatre-vingt-dix"
    )

    override val numbersMap = mapOf(
        0L to "zero",
        1L to "un",
        2L to "deux",
        3L to "trois",
        4L to "quatre",
        5L to "cinq",
        6L to "six",
        7L to "sept",
        8L to "huit",
        9L to "neuf",
        10L to "dix",
        11L to "onze",
        12L to "douze",
        13L to "treize",
        14L to "quatorze",
        15L to "quinze",
        16L to "seize",
        17L to "dix-sept",
        18L to "dix-huit",
        19L to "dix-neuf",
        20L to "vingt",
        30L to "trente",
        40L to "quarante",
        50L to "cinquante",
        60L to "soixante",
        70L to "soixante-dix",
        80L to "quatre-vingt",
        90L to "quatre-vingt-dix",
        100L to "cent",
        1000L to "mille",
        1_000_000L to "million",
        1_000_000_000L to "milliard",
        1_000_000_000_000L to "billion"
    )

    override fun convert(number: Long): String {
        val dNum = decomposeNumber(number)
        var res = ""


        if(number < 10L){
            res = unitsMap[number.toInt()].toString()
        }else if(number < 100L){
            var d = dNum["dix"]!!
            res = convertTens(d)
        }else if(number < 1000L){
            var c = dNum["cent"]!!
            var d = dNum["dix"]!!
            res = convertHundreds(c, d)
        }else if(number <1_000_000L){
            var m = dNum["mille"]!!
            var c = dNum["cent"]!!
            var d = dNum["dix"]!!
            res = convertThousands(m, c, d)
        }else if(number < 1_000_000_000L){
            var mi = dNum["million"]!!
            var m = dNum["mille"]!!
            var c = dNum["cent"]!!
            var d = dNum["dix"]!!
            res = convertMillion(mi,m,c,d)
        }else if(number < 1_000_000_000_000L){
            var M= dNum["milliard"]!!
            var mi = dNum["million"]!!
            var m = dNum["mille"]!!
            var c = dNum["cent"]!!
            var d = dNum["dix"]!!
            res = convertMilliard(M,mi,m,c,d)
        }else if(number <= 999_999_999_999_999L){
            var b = dNum["billion"]!!
            var M= dNum["milliard"]!!
            var mi = dNum["million"]!!
            var m = dNum["mille"]!!
            var c = dNum["cent"]!!
            var d = dNum["dix"]!!
            res = convertBillion(b,M,mi,m,c,d)
        }else{
            res = "Le nombre est beaucoup trop grand!!!"
        }

        return res
    }

    override fun decomposeNumber(number: Long): Map<String, Int> {
        var res = mutableMapOf<String, Int>()

        var billion = (number/1_000_000_000_000L).toInt()
        var milliard = ((number - billion*1_000_000_000_000L)/1_000_000_000L).toInt()
        var million = ((number-billion*1_000_000_000_000L-milliard*1_000_000_000L)/1_000_000L).toInt()
        var mille = ((number-billion*1_000_000_000_000L-milliard*1_000_000_000L-million*1_000_000L)/1000L).toInt()
        var cent = ((number-billion*1_000_000_000_000L-milliard*1_000_000_000L-million*1_000_000L-mille*1000L)/100L).toInt()
        var dix = (number-billion*1_000_000_000_000L-milliard*1_000_000_000L-million*1_000_000L-mille*1000L-cent*100L).toInt()

        mapOf(
            "billion" to billion,
            "milliard" to milliard,
            "million" to million,
            "mille" to mille,
            "cent" to cent,
            "dix" to dix
        ).also { res = it as MutableMap<String, Int> }

        return res
    }


    override fun convertTens(number: Int) : String{
        var u = number%10
        var d = number-u

        var res = ""

        if(number in tensMap.keys) {
            res = tensMap[number].toString()
        }else if(number == 80){
            res = tensMap[number]+"s"
        }else if(u == 1) {
            res = tensMap[d] + " et " + unitsMap[u]
        }else if(number > 70 && number < 80){
            res = tensMap[d-10]+"-"+tensMap[u+10]
        }else if(number > 90) {
                res = tensMap[d-10]+"-"+tensMap[u+10]
        }else{
            res = tensMap[d]+"-"+unitsMap[u]
        }
        return res
    }

    override fun convertHundreds(c: Int, d: Int): String {
        var res = ""

        if(c==1){
            res = numbersMap[100L].toString()
        }else if(d==0){
            res = unitsMap[c]+"-"+numbersMap[100L]+"s"
        }else{
            res = unitsMap[c]+"-"+numbersMap[100L]
        }

        if(d!=0){
            res = res+"-"+convertTens(d)
        }

        return res
    }

    override fun convertThousands(m: Int, c: Int, d: Int): String {
        var res = ""

        if(m==1) {
            res = numbersMap[1_000L].toString()
        }else if(m<10) {
            res = unitsMap[m]+"-"+numbersMap[1_000L]
        }else if(m<100){
            res = convertTens(m)+"-"+numbersMap[1_000L]
        }else if(m<1000) {
            var mNum = decomposeNumber(m.toLong())
            res = convertHundreds(mNum["cent"]!!, mNum["dix"]!!)+"-"+numbersMap[1_000L]
        }

        if(c==0 && d!=0){
            res = res+"-"+convertTens(d)
        }else if(c!=0){
            res = res+"-"+convertHundreds(c,d)
        }

        return res
    }

    override fun convertMillion(mi: Int, m: Int, c: Int, d: Int): String{
        var res = ""

        if(mi==1) {
            res = numbersMap[1_000_000L].toString()
        }else if(mi<10) {
            res = unitsMap[mi]+"-"+numbersMap[1_000_000L]
        }else if(mi<100) {
            res = convertTens(mi)+"-"+numbersMap[1_000_000L]
        }else if(mi<1000) {
            var mNum = decomposeNumber(mi.toLong())
            res = convertHundreds(mNum["cent"]!!, mNum["dix"]!!)+"-"+numbersMap[1_000_000L]
        }

        if(m==0 && c==0 && d!=0){
            res = res+"-"+convertTens(d)
        }else if(m==0 && c!=0){
            res = res+"-"+convertHundreds(c,d)
        }else if(m!=0){
            res = res+"-"+convertThousands(m,c,d)
        }

        return res
    }

    override fun convertMilliard(M: Int, mi: Int, m: Int, c: Int, d: Int): String{
        var res = ""

        if(M==1) {
            res = numbersMap[1_000_000_000L].toString()
        }else if(M<10) {
            res = unitsMap[M]+"-"+numbersMap[1_000_000_000L]
        }else if(M<100) {
            res = convertTens(M)+"-"+numbersMap[1_000_000_000L]
        }else if(M<1000) {
            var mNum = decomposeNumber(M.toLong())
            res = convertHundreds(mNum["cent"]!!, mNum["dix"]!!)+"-"+numbersMap[1_000_000_000L]
        }

        if(mi==0 && m==0 && c==0 && d!=0){
            res = res+"-"+convertTens(d)
        }else if(mi==0 && m==0 && c!=0){
            res = res+"-"+convertHundreds(c,d)
        }else if(mi==0 && m!=0){
            res = res+"-"+convertThousands(m,c,d)
        }else if(mi!=0){
            res = res+"-"+convertMillion(mi,m,c,d)
        }

        return res
    }

    override fun convertBillion(b: Int, M: Int, mi: Int, m: Int, c: Int, d: Int): String {
        var res = ""

        if(b==1) {
            res = numbersMap[1_000_000_000_000L].toString()
        }else if(b<10) {
            res = unitsMap[b]+"-"+numbersMap[1_000_000_000_000L]
        }else if(b<100) {
            res = convertTens(b)+"-"+numbersMap[1_000_000_000_000L]
        }else if(b<1000) {
            var mNum = decomposeNumber(b.toLong())
            println(b)
            res = convertHundreds(mNum["cent"]!!, mNum["dix"]!!)+"-"+numbersMap[1_000_000_000_000L]
        }

        if(b==0 && mi==0 && m==0 && c==0 && d!=0){
            res = res+"-"+convertTens(d)
        }else if(b==0 && mi==0 && m==0 && c!=0){
            res = res+"-"+convertHundreds(c,d)
        }else if(b==0 && mi==0 && m!=0){
            res = res+"-"+convertThousands(m,c,d)
        }else if(b==0 && mi!=0){
            res = res+"-"+convertMillion(mi,m,c,d)
        }else if(b!=0){
            res = res+"-"+convertMilliard(M,mi,m,c,d)
        }

        return res
    }
}