package com.example.pressureandpulseapp.data.repository

import com.example.pressureandpulseapp.domain.Item
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getItems(): Single<List<Item>>
    fun addItem(item: Item): Single<List<Item>>
}