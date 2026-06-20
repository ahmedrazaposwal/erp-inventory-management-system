import { Boxes, LayoutDashboard, Package } from 'lucide-react'
import { NavLink } from 'react-router'

function Sidebar() {
    return (
        <aside className="sidebar">
            <div className="sidebar-brand">
                <span className="brand-icon">
                    <Boxes size={24} strokeWidth={2} aria-hidden="true" />
                </span>

                <div>
                    <p className="brand-name">ERP Inventory</p>
                    <span className="brand-description">Management System</span>
                </div>

            </div>

            <nav className="sidebar-navigation" aria-label="Primary navigation">
                <p className="navigation-label">Workspace</p>

                <NavLink
                    className={({ isActive }) => isActive ? 'navigation-link active' : 'navigation-link'} to="/dashboard">
                    <LayoutDashboard size={20} aria-hidden="true" />
                    <span>Dashboard</span>
                </NavLink>

                <NavLink className={({ isActive }) => isActive ? 'navigation-link active' : 'navigation-link'} to="/products"
                >
                    <Package size={20} aria-hidden="true" />
                    <span>Products</span>
                </NavLink>

            </nav>

        </aside>
    )
}

export default Sidebar