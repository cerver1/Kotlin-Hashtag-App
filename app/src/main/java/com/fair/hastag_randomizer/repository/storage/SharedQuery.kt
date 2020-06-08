package com.fair.hastag_randomizer.repository.storage

import android.content.Context
import android.widget.TextView
import com.fair.hastag_randomizer.repository.Randomize
import com.fair.hastag_randomizer.repository.toast

class SharedQuery(private val context: Context) {

    var rand = Randomize()
    var storage = SharedPreference(context)
    var savedHashTag = storage.getStringValue("randomizeHashTagDataKey")



    fun searchHash(newHashTag: String, storedDisplay: TextView){

        val savedList = rand.validateHashTagList(savedHashTag)
        val newList = rand.validateHashTagList(newHashTag)

        if(savedList.isNullOrEmpty()) {
            context.toast("There aren't any saved Hashtags")
        } else {
            for (i in newList){
                if (i in savedList) continue else savedList.add(i)

            storage.saveString("randomizeHashTagDataKey", rand.finalizeHashTags(savedList.toString()))
            storedDisplay.text = savedHashTag
            }
        }

        return

    }




}

