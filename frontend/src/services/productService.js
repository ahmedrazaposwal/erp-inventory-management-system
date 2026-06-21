import { apiRequest } from './apiClient.js'

export function getProducts(page = 0, size = 10) {
    return apiRequest(`/api/products?page=${page}&size=${size}`)
}

export function createProduct(productData) {
    return apiRequest('/api/products', {
        method: 'POST',
        body: JSON.stringify(productData),
    })
}

export function searchProducts(keyword, page = 0, size = 10) {
    const parameters = new URLSearchParams({
        keyword,
        page: String(page),
        size: String(size),
    })

    return apiRequest(`/api/products/search?${parameters.toString()}`)

}

export function deleteProduct(productId) {
    return apiRequest(`/api/products/${productId}`, {
        method: 'DELETE',
    })
}