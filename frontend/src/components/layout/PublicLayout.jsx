import { Outlet } from 'react-router'

function PublicLayout() {
    return (
        <main className="app-shell">
            <Outlet />
        </main>
    )
}

export default PublicLayout