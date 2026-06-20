import { apiRequest } from './apiClient.js'

export function getPurchaseOrders() {
    return apiRequest('/api/purchase-orders')
}