import { Outlet } from 'react-router'
import Sidebar from './Sidebar.jsx'
import Header from './Header.jsx'
import './AppLayout.css'

function AppLayout() {
    return (
        <div className="dashboard-shell">

            <Sidebar />

            <div className="workspace">
                <Header />

                <main className="page-content">
                    <Outlet />
                </main>
            </div>
        </div>
    )
}

export default AppLayout