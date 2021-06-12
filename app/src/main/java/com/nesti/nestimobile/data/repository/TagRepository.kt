package com.nesti.nestimobile.data.repository
import com.nesti.nestimobile.data.base.NestiDataSource
import com.nesti.nestimobile.data.model.Tag
import io.reactivex.Single

/**
 * Fetches tags from data source
 */
class TagRepository(private val dataSource: NestiDataSource) {

    /**
     * Get all tags
     * @return List of tags wrapped in an observable Single
     */
    fun findAll(): Single<List<Tag>> {
        return dataSource.findAllTags()
    }
}