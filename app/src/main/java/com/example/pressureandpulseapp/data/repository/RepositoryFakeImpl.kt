package com.example.pressureandpulseapp.data.repository

import com.example.pressureandpulseapp.domain.FakeRepo
import com.example.pressureandpulseapp.domain.Item
import io.reactivex.rxjava3.core.Single

class RepositoryFakeImpl(private val repo: FakeRepo = FakeRepo()) : Repository {

    override fun getItems(): Single<List<Item>> {
        return repo.getList()
    }

    override fun addItem(item: Item): Single<List<Item>> {
        return repo.addItem(item)
    }
}