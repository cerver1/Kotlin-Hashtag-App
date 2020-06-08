package com.fair.hastag_randomizer.repository.storage

import android.content.Context
import com.fair.hastag_randomizer.repository.Randomize
import com.fair.hastag_randomizer.repository.toast

class SharedQuery(private val context: Context) {
    var rand = Randomize()
    var storage = SharedPreference(context)



    fun searchHash(HashTags: MutableList<String>){
        val savedData = storage.getStringValue("randomizeHashTagDataKey")
        if(savedData.isNullOrEmpty()) {
            context.toast("There aren't any saved Hashtags")
        } else {
            val savedHashtags = storage.getStringValue("randomizeHashTagDataKey")
            val savedHashTagList = rand.validateHashTagList(savedHashtags.toString())

            for (i in HashTags){
                if (i in savedHashTagList) continue else savedHashTagList.add(i)
            }
        }

        return

    }



}



fun storeHash()