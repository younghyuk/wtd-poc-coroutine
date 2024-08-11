package com.ethan

import kotlinx.coroutines.*

data class User(val name: String)
data class Product(val name: String)

class ApiClient {
    suspend fun loadUser(): User {
        delay(1000)
        return User("ethan")
    }
    suspend fun loadProduct(): Product {
        delay(500)
        throw IllegalStateException("Network is not working")
    }
    suspend fun orderProduct(user: User, product: Product): String {
        delay(1000)
        return "${user.name} / ${product.name}";
    }
}
val api = ApiClient()

fun main() = runBlocking {
    println("Started")

    // Hand on #2. 에러 처리
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }
    val scope = CoroutineScope(Dispatchers.Default)
    val job = scope.launch(handler) {
        val user = api.loadUser()
        val product = api.loadProduct()
        val order = api.orderProduct(user, product)
        println("order: $order")
    }
    job.join()

    println("finished")
}
