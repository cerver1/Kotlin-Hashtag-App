package com.fair.hastag_randomizer.repository

import kotlin.random.Random

class Randomize {

    fun finalizeHashTags(randomizedHashTags: String): String{

        return randomizedHashTags.replace("\\s".toRegex(), "").replace("[", "#").replace(",", "#")
            .replace("\\s".toRegex(), "").removeSuffix("]")

    }

    fun validateHashTagList(hashTags: String): MutableList<String>{

        val withSet = hashTags.split("#").toSet().toMutableList()
        withSet.remove("")

        return withSet
    }


    fun randomizedHashTagList(list: MutableList<String>, size: String ): Pair<Int,String> {

        val randomizedList = list.sorted().reversed().shuffled()

        val adjustSize = if (randomizedList.size >= 30) 30 else randomizedList.size

        val random = Random.nextInt(0, 30)

        // radio button group for to select size
        return when(size) {
            "small" -> Pair((adjustSize / 4), randomizedList.take(adjustSize / 4).toString())
            "medium" -> Pair((adjustSize / 2), randomizedList.take( adjustSize / 2).toString())
            "full" -> Pair(adjustSize, randomizedList.take(adjustSize).toString())
            "random" -> Pair(random, randomizedList.take(random).toString())
            else -> Pair(0, "Something went wrong :(")
        }

    }

}