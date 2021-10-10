package com.goda.pageword

import com.goda.data.models.WordPresentationModel
import com.goda.data.network.models.WordsResponse


fun getDataResponse(): WordsResponse {
    return WordsResponse(response = " HTML  title Ship Quality Apps with Instabug Instabug Apps Apps with  title   HTML ")


}fun getResponseMap(): HashMap<String,Int> {
    return hashMapOf("Apps" to 3,"HTML" to 2,   "Instabug" to 2,"Quality" to 1,"Ship" to 1,"title" to 2,"with" to 2)


}
fun getContent(): String {
    return "<HTML><title>Ship Quality Apps with Instabug Instabug Apps Apps with</title></HTML>"

}fun getResponsewordPresentationModel(): WordPresentationModel {
    return  WordPresentationModel(getResponseMap())

}
