package com.adhrox.gymplanner.utils

import com.adhrox.gymplanner.domain.model.Set

object GeneratorUtils {
    fun generateSets(numSets: Int, planId: Long = 0): List<Set> {
        val sets = mutableListOf<Set>()

        if(numSets != 0){
            for (i in 1..numSets){
                sets.add(Set(planId = planId))
            }
        }
        return sets
    }

}