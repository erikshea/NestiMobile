package com.nesti.nestimobile.ui.base

/**
 * For asynchronous operations such as fetching a list of entities from an API, our view
 * needs to know the status of the operation (for example to show or hide a loading indicator).
 * We therefore wrap the data we're preparing (for example a List<Recipe>) into a StatusContainer
 * that can report a status (SUCCESS, LOADING...) , which will then be observed inside a mutable
 * observable object that will notify the view of any StatusContainer change (and therefore
 * of status and data and possible message).
 * @param data data wrapped in StatusContainer
 * @param status current status of data (ie StatusContainer.LOADING if not yet fetched)
 * @param message in case of an error
 */
data class StatusContainer<out T>(val status: Status, val data: T?, val message: String?) {

    /**
     * enum with statuses
     */
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    /**
     * will be used for static-like methods to create new StatusContainers with one of the 3 statuses
     */
    companion object {
        /**
         * called when data has finished fetching without errors
         * @param data data fetched
         */
        fun <T> success(data: T?): StatusContainer<T> {
            return StatusContainer(Status.SUCCESS, data, null)
        }

        /**
         * called when an error occured fetching data
         * @param message message to pass to view
         */
        fun <T> error(message: String): StatusContainer<T> {
            return StatusContainer(Status.ERROR, null, message)
        }

        /**
         * called when data is loading
         */
        fun <T> loading(): StatusContainer<T> {
            return StatusContainer(Status.LOADING, null, null)
        }
    }
}