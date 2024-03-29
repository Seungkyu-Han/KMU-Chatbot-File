package CoBo.Chatbotfile.Service

import CoBo.Chatbotfile.Data.Dto.Category.Res.CategoryGetAllRes
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface CategoryService {
    fun getAll(): ResponseEntity<List<CategoryGetAllRes>>
    fun post(category: String): ResponseEntity<HttpStatus>
    fun patch(oldCategory: String, newCategory: String): ResponseEntity<HttpStatus>
    fun delete(category: String): ResponseEntity<HttpStatus>
}