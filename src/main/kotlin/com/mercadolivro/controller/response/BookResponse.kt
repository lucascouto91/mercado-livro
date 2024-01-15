package com.mercadolivro.controller.response

import com.mercadolivro.enums.BooksStatus
import com.mercadolivro.model.CustomerModel
import java.math.BigDecimal

data class BookResponse(

    var id: Int? = null,
    var name: String,
    var price: BigDecimal,
    var customer: CustomerModel? = null,
    var status: BooksStatus? = null
)
