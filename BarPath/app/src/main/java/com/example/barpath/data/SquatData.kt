package com.example.barpath.data

data class SquatRep(
    val repNumber: Int,
    val totalDuration: Long,
    val maxDepth: Float
)

data class SquatSet(
    val reps: List<SquatRep>,
    val totalReps: Int,
    val duration: Long
)

fun getFastestRep(set: SquatSet): SquatRep? {
    return set.reps.minByOrNull { it.totalDuration }
}

fun getSlowestRep(set: SquatSet): SquatRep? {
    return set.reps.maxByOrNull { it.totalDuration }
}