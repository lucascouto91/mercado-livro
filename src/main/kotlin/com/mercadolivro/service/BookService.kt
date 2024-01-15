package com.mercadolivro.service

import com.mercadolivro.enums.BooksStatus
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    fun findAll(pageable: Pageable): Page<BookModel> {
        return bookRepository.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<BookModel> {
        return bookRepository.findByStatus(BooksStatus.ATIVO, pageable)
    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow{ NotFoundException("Book [${id}] not exists", "ML-0001")}
    }

    fun delete(id: Int) {
        val book = findById(id)
        book.status = BooksStatus.CANCELADO

        update(book)

    }

    fun update(book: BookModel) {
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        for (book in books) {
            book.status = BooksStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }

}
