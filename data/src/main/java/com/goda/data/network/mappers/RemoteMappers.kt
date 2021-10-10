package com.goda.data.network.mappers


fun String.toStringList(): List<String> = this.trim().split("\\s+".toRegex())

fun String.replaceSpaceSpecialCharacters(): String {
    //regex exclude character
    //you can add _ or \\- if you think header__title or -moz-flex as one word
    val regex = "[^A-Za-z ]".toRegex()
    return regex.replace(this, " ")
}
//this fun to replace space for converted string image to base64 ,and make words more readable
fun String.replacecSpaceSpecialCharacters(): String {
    var string = this
    val guess = "base64,"
    var temp: String = string
    string = ""
    while (temp.indexOf(guess) != -1) {
        val index = temp.indexOf(guess)
        val indexOf_ = temp.indexOf(")", index + 7)
        //string=string.substring(0, index+7) +  string.substring(indexOf_)
        string += temp.substring(0, index + 7)
        temp = temp.substring(indexOf_)
    }
    //regex exclude character
    return string + temp
}
