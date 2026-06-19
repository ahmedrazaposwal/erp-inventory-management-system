const API_BASE_URL = import.meta.env.VITE_API_BASE_URL

export async function login(credentials) {
    if (!API_BASE_URL) {
        throw new Error('API base URL is not configured')
    }

    const response = await fetch(`${API_BASE_URL}/api/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(credentials),
    })

    const responseBody = await response.text()

    if (!response.ok) {
        throw new Error(responseBody || 'Unable to sign in')
    }

    return responseBody

}