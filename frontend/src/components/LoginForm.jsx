import { useState } from 'react'
import { useNavigate } from 'react-router'
import { login } from '../services/authService.js'

function LoginForm() {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [errorMessage, setErrorMessage] = useState('')
    const [isSubmitting, setIsSubmitting] = useState(false)

    const navigation = useNavigate()

    async function handleSubmit(event) {
        event.preventDefault()
        setErrorMessage('')
        setIsSubmitting(true)

        try {
            const token = await login({
                email: email.trim(),
                password,
            })

            sessionStorage.setItem('accessToken', token)
            navigation('/dashboard')

        } catch (error) {
            const message = error instanceof Error ? error.message : 'Unable to sign in. Please try again.'
            setErrorMessage(message)
        } finally {
            setIsSubmitting(false)
        }
    }

    return (
        <form className='login-form' onSubmit={handleSubmit}>
            {errorMessage && (
                <p className='error-message' role='alert'>
                    {errorMessage}
                </p>
            )}


            <div className='form-field'>
                <label htmlFor='email'>Email address</label>
                <input
                    id='email'
                    type='email'
                    name='email'
                    value={email}
                    onChange={(event) => setEmail(event.target.value)}
                    autoComplete='email'
                    placeholder='you@example.com'
                    disabled={isSubmitting}
                    required
                />
            </div>

            <div className='form-field'>
                <label htmlFor='password'>Password</label>
                <input
                    id='password'
                    type='password'
                    name='password'
                    value={password}
                    onChange={(event) => setPassword(event.target.value)}
                    autoComplete='current-password'
                    placeholder='Enter your password'
                    disabled={isSubmitting}
                    required
                />
            </div>

            <button
                className="primary-button login-button"
                type="submit"
                disabled={isSubmitting}>
                {isSubmitting ? 'Sigining in...' : 'Sign In'}
            </button>

        </form>
    )
}

export default LoginForm