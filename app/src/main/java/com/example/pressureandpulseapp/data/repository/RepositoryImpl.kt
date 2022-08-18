package com.example.pressureandpulseapp.data.repository

import com.example.pressureandpulseapp.data.retrofit.ApiService
import com.example.pressureandpulseapp.data.retrofit.Retrofit
import com.example.pressureandpulseapp.domain.Item
import io.reactivex.rxjava3.core.Single

class RepositoryImpl(private val api: ApiService = Retrofit().getService()) : Repository {

    override fun getItems(): Single<List<Item>> {
        return api.getItems()
    }

    override fun addItem(item: Item): Single<List<Item>> {
        return api.postItem(
            item.date,
            item.time,
            item.firstPressure,
            item.secondPressure,
            item.pulse
        )
    }
}