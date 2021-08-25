package com.wallstreet.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "University")
data class University(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @Expose
    var id: Int,

    @ColumnInfo(name = "country")
    @SerializedName("country")
    @Expose
    val country : String,

    @ColumnInfo(name = "stateprovince")
    @SerializedName("stateprovince")
    @Expose
    val stateprovince : String?,

    @SerializedName("web_pages")
    @ColumnInfo(name = "web_pages")
    @Expose
    val web_pages : List<String>,

    @ColumnInfo(name = "alpha_two_code")
    @SerializedName("alpha_two_code")
    @Expose
    val alpha_two_code : String?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    val name : String,

    @SerializedName("domains")
    @ColumnInfo(name = "domains")
    @Expose
    val domains : List<String>
)