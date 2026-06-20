import { apiRequest } from './apiClient.js'

export function getProducts(page = 0, size = 10) {
    return apiRequest(`/api/products?page=${page}&size=${size}`)
}