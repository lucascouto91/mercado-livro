package com.mercadolivro.validation

import com.mercadolivro.service.CustomerService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class EmailAvailableValidator(private var customerService: CustomerService): ConstraintValidator<EmailAvailable, String> {
    override fun isValid(p0: String?, p1: ConstraintValidatorContext?): Boolean {
        if(p0.isNullOrEmpty())
            return false
        return customerService.emailAvailable(p0)
    }

}
