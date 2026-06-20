const API_BASE_URL = import.meta.env.VITE_API_BASE_URL

export async function apiRequest(endpoint, options = {}) {
    if (!API_BASE_URL) {
        throw new Error('The API base URL is not configured.')
    }

    const token = sessionStorage.getItem('accessToken')
    const headers = new Headers(options.headers)

    if (options.body && !headers.has('Content-Type')) {
        headers.set('Content-Type', 'application/json')
    }

    if (token) {
        headers.set('Authorization', `Bearer ${token}`)
    }

    const response = await fetch(`${API_BASE_URL}${endpoint}`, {
        ...options,
        headers,
    })

    const contentType = response.headers.get('Content-Type') || ''

    const responseBody = contentType.includes('application/json') ? await response.json() : await response.text()

    if (!response.ok) {
        const message =
            typeof responseBody === 'string'
                ? responseBody : responseBody.message ||
                responseBody.detail ||
                'The request could not be completed.'

        throw new Error(message)
    }

    return responseBody
}