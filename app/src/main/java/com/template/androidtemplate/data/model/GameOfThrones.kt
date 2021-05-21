package com.template.androidtemplate.data.model

import com.google.gson.annotations.SerializedName

data class GameOfThrones(
    @SerializedName("members")
    var members: List<Member>,
    @SerializedName("name")
    var name: String,
    @SerializedName("slug")
    var slug: String
) {
    data class Member(
        @SerializedName("name")
        var name: String,
        @SerializedName("slug")
        var slug: String
    )

}

