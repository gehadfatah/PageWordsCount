package com.goda.data

import com.goda.data.local.models.Word
import com.goda.data.models.WordPresentationModel
import com.goda.data.network.models.WordsResponse


fun getDataResponse(): WordsResponse {
    return WordsResponse(response = " HTML  title Ship Quality Apps with Instabug Instabug Apps Apps with  title   HTML ")


}fun getResponseMap(): HashMap<String,Int> {
    return hashMapOf("Apps" to 3,"HTML" to 2,   "Instabug" to 2,"Quality" to 1,"Ship" to 1,"title" to 2,"with" to 2)


}
fun getwordsList(): List<Word>{
    return listOf(Word("with" ,2),Word("Quality" ,1),   Word("Instabug" , 2),Word("HTML" , 2),Word("Ship" ,1),Word("title" , 2),Word("Apps" ,3))


}
fun getContent(): String {
    return "<HTML><title>Ship Quality Apps with Instabug Instabug Apps Apps with</title></HTML>"

}fun getResponsewordPresentationModel(): WordPresentationModel {
    return  WordPresentationModel(getResponseMap())

}
