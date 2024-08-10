package com.ethan

import kotlinx.coroutines.async
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

    // Hand on #1-2. User와 Product를 동시에 불러오고 결과로 Product 주문하기
    val user = async { api.loadUser() }
    val product = async { api.loadProduct() }
    val order = api.orderProduct(user.await(), product.await())
    println("order: $order")

    println("finished")
}