package com.aiweapps.dinsurance.network

sealed class ApiResponse<out T, out E> {
    /**  
     * Represents successful network responses (2xx).     
     */    
  data class Success<T>(val body: T) : ApiResponse<T, Nothing>()
  
    sealed class Error<E> : ApiResponse<Nothing, E>() {
        /**  
         * Represents server errors.         
         * @param code HTTP Status code  
         * @param errorBody Response body  
         * @param errorMessage Custom error message  
         */        
        data class HttpError<E>(  
            val code: Int,  
            val errorBody: String? = null,
            val errorMessage: String? = null,
        ) : Error<E>()
  
        /**  
         * Represent SerializationExceptions.         
         * @param message Detail exception message  
         * @param errorMessage Formatted error message  
         */        
        data class SerializationError(  
            val message: String? = null,
            val errorMessage: String? = null,
        ) : Error<Nothing>()
  
        /**  
         * Represent other exceptions.         
         * @param message Detail exception message  
         * @param errorMessage Formatted error message  
         */        
        data class GenericError(  
            val message: String? = null,
            val errorMessage: String? = null,
        ) : Error<Nothing>()
    }  
}