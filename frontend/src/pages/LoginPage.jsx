import { Link } from 'react-router'
import LoginForm from '../components/LoginForm.jsx'

function LoginPage() {
    return (
        <section className="welcome-card auth-card">
            <p className="eyebrow">Secure access</p>
            <h1>Login to your account</h1>
            <p className="subtitle">Enter your credentials to access the ERP dashboard.</p>

            <LoginForm />

            <Link className="back-line" to="/">
                Back to Home
            </Link>
        </section>
    )
}

export default LoginPage