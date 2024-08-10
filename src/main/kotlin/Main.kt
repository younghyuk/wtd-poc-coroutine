package com.ethan

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

data class User(val name: String)
data class Product(val name: String)

class ApiClient {
    suspend fun loadUser(): User {
        delay(1000)
        return User("ethan")
    }
    suspend fun loadProduct(): Product {
        delay(500)
        return Product("macbook")
    }
    suspend fun orderProduct(user: User, product: Product): String {
        delay(1000)
        return "${user.name} / ${product.name}";
    }
}

val api = ApiClient()

fun main() = runBlocking {
    println("Started")

    // Hand on #1-1. 순서대로 비동기 호출을 실행하기
    val user = api.loadUser()
    val product = api.loadProduct()
    val order = api.orderProduct(user, product)
    println("order: $order")

    println("finished")
}