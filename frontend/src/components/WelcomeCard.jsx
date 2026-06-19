import { Link } from 'react-router'

function WelcomeCard({ eyebrow, title, description }) {

    return (
        <section className="welcome-card">
            <p className="eyebrow">{eyebrow}</p>
            <h1>{title}</h1>
            <p className="subtitle">{description}</p>

            <Link className="primary-button" to="/login">
                Sign in to continue
            </Link>

        </section>
    )
}

export default WelcomeCard