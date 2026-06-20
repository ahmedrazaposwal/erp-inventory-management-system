import { LogOut } from 'lucide-react'
import { useNavigate } from 'react-router'

function Header() {
    const navigate = useNavigate()
    const userEmail = sessionStorage.getItem('userEmail') || 'Signed-in user'
    const userInitial = userEmail.charAt(0).toUpperCase()

    function handleLogout() {
        sessionStorage.removeItem('accessToken')
        sessionStorage.removeItem('userEmail')
        navigate('/login', { replace: true })
    }

    return (
        <header className="topbar">
            <div className="topbar-title">
                <span>Workspace</span>
                <h2>Inventory Management</h2>
            </div>


            <div className="user-menu">
                <span className="user-avatar" aria-hidden="true">
                    {userInitial}
                </span>


                <div className="user-details">
                    <span>Signed in as</span>
                    <strong>{userEmail}</strong>
                </div>

                <button className="logout-button" type="button" onClick={handleLogout}>
                    <LogOut size={18} aria-hidden="true" />
                    <span>Sign out</span>
                </button>


            </div>

        </header>

    )
}
export default Header