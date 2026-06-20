import { apiRequest } from './apiClient.js'

export function getSuppliers() {
    return apiRequest('/api/suppliers')
}