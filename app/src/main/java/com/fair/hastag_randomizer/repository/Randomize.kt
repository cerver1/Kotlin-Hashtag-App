package com.fair.hastag_randomizer.repository

import com.fair.hastag_randomizer.repository.storage.RandomizeEntity
import kotlin.random.Random

class Randomize {

    fun finalizeHashTags(randomizedHashTags: String): String{

        return randomizedHashTags.replace("\\s".toRegex(), "").replace("[", "#").replace(",", "#")
            .replace("\\s".toRegex(), "").removeSuffix("]")

    }

    fun validateHashTagList(hashTags: String?): MutableList<String>{

        return if (hashTags.isNullOrEmpty()){
            mutableListOf("") }
        else{
            val withSet = hashTags.split("#").toSet().toMutableList()
            withSet.remove("")
            withSet
        }


    }

    private fun chipConversion(list: MutableList<String>): MutableList<String>{

        val chipList = mutableListOf<String>()
        for(i in list){
            chipList.add("#$i")
        }
        return chipList
    }

    fun randomizedHashTagList(list: MutableList<String>, size: String ): Triple<Int,String,MutableList<String>> {

        val randomizedList = list.sorted().reversed().shuffled()

        val adjustSize = if (randomizedList.size >= 30) 30 else randomizedList.size
        val random = Random.nextInt(0, 30)
        val s = adjustSize / 4
        val m = adjustSize / 2
        val f = adjustSize
        val r = adjustSize/ random



        // radio button group for to select size
        return when(size) {
            "small" -> Triple(s, randomizedList.take(s).toString(), chipConversion(randomizedList.take(s).toMutableList()))
            "medium" -> Triple(m, randomizedList.take(m).toString(), chipConversion(randomizedList.take(m).toMutableList()))
            "full" -> Triple(f, randomizedList.take(f).toString(), chipConversion(randomizedList.take(f).toMutableList()))
            "random" -> Triple(r, randomizedList.take(r).toString(), chipConversion(randomizedList.take(r).toMutableList()))
            else -> Triple(0, "Something went wrong :(", mutableListOf("0,0,0,0"))
        }
    }

    fun newTest(incomingData: String): MutableList<String>{
        return chipConversion(validateHashTagList(incomingData))

    }
    fun compareIncomingStorage(storage: MutableList<RandomizeEntity>, incomingData: String): MutableList<String> {
        val newData = mutableListOf<String>()
        val incomingDataList = chipConversion(validateHashTagList(incomingData))

        for (i in incomingDataList) {
            for(j in storage){
                if (j.hashtag == i.trim()){
                    continue
                } else newData.add(i.trim())
        }

            }
        return newData
    }



}
