package com.mirea.randomfacts.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FactsRepository {
    private val facts = listOf(
        "Pandas eat up to 14 hours a day.",
        "Elephants are the only animals that cannot jump.",
        "Octopuses have three hearts.",
        "Giraffes sleep only 30 minutes a day.",
        "Cats spend 70% of their lives sleeping.",
        "Dolphins call each other by names.",
        "Hummingbirds are the only birds that can fly backwards.",
        "Ants never sleep.",
        "Polar bears have black skin.",
        "Horses cannot breathe through their mouths.",
        "Dogs can understand up to 250 words and gestures.",
        "Kangaroos cannot walk backwards.",
        "Lions sleep up to 20 hours a day.",
        "Zebras cannot see the color orange.",
        "Camels can go without water for up to 2 weeks."
    )

    fun getRandomFact(): Flow<String> = flow {
        emit("")
        delay((1500..3000).random().toLong())
        emit(facts.random())
    }
}