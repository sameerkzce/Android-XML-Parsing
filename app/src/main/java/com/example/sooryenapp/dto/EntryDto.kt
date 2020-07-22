package com.example.sooryenapp.dto

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sooryenapp.core.domain.Entry

/***
 * DTO class which contain with database table annotations
 * */
@Entity(tableName = "entry")
class EntryDto(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "imageUrl") var imageUrl: String?,
    @ColumnInfo(name = "audioLink") var audioLink: String?,
    @ColumnInfo(name = "content") var content: String?
) : Parcelable {
    constructor(title: String, imageUrl: String?, audioLink: String?, content: String?) :
            this(0, title, imageUrl, audioLink, content)

    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(title)
        writeString(imageUrl)
        writeString(audioLink)
        writeString(content)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<EntryDto> = object : Parcelable.Creator<EntryDto> {
            override fun createFromParcel(source: Parcel): EntryDto = EntryDto(source)
            override fun newArray(size: Int): Array<EntryDto?> = arrayOfNulls(size)
        }
    }
}

//extension function to covert model to DTO and change image path
fun Entry.mapDto() = EntryDto(
    title = "$title", imageUrl = "$imageUrl",
    audioLink = "${link.audioLink}",
    content = "$content"
)