package com.mercadolivro.repository

import com.mercadolivro.enums.BooksStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<BookModel, Int> {
    fun findByStatus(status: BooksStatus): List<BookModel>


}