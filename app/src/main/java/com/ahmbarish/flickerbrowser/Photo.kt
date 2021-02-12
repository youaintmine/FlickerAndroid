package com.ahmbarish.flickerbrowser

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import java.io.*
import java.io.DataInputStream.readUTF

class Photo(var title: String, var author: String,var authorId: String,var link: String,var tags: String,var image: String) : Parcelable {

//    companion object {
//        private const val serialVersionUID = 1L
//    }

    constructor(parcel: Parcel) : this(
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString()) {
    }

    override fun toString(): String {
        return "Photo(title='$title', author='$author', authorId='$authorId', link='$link', tags='$tags', image='$image')"
    }


//    @Throws(IOException::class)
//    private fun writeObject(out : java.io.ObjectOutputStream) {
//        Log.d("Photo","writeObject called")
//        out.writeUTF(title)
//        out.writeUTF(author)
//        out.writeUTF(authorId)
//        out.writeUTF(link)
//        out.writeUTF(tags)
//        out.writeUTF(image)
//    }
//
//    @Throws(IOException::class, ClassNotFoundException::class)
//    private fun readObject(inputStream : java.io.ObjectInputStream) {
//        Log.d("Photo", "readObject called")
//        title = inputStream.readUTF()
//        author = inputStream.readUTF()
//        authorId = inputStream.readUTF()
//        link = inputStream.readUTF()
//        tags = inputStream.readUTF()
//        image = inputStream.readUTF()
//    }
//
//    @Throws(ObjectStreamException::class)
//    private fun readObjectNoData() {
//        Log.d("Photo","readObjectNoData called")
//    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(authorId)
        parcel.writeString(link)
        parcel.writeString(tags)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }
}