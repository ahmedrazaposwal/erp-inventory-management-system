import { useEffect, useState } from 'react'
import {
    CircleCheckBig,
    ClipboardList,
    Package,
    Truck,
} from 'lucide-react'

import StatCard from '../components/dashboard/StatCard.jsx'
import { getProducts } from '../services/productService.js'
import { getSuppliers } from '../services/supplierService.js'
import { getPurchaseOrders } from '../services/purchaseOrderService.js'

import './DashboardPage.css'

function DashboardPage() {
    const [dashboardData, setDashBoardData] = useState({
        totalProducts: 0,
        totalSuppliers: 0,
        activeSuppliers: 0,
        openPurchaseOrders: 0,
    })

    const [isLoading, setIsLoading] = useState(true)
    const [errorMessage, setErrorMessage] = useState('')

    useEffect(() => {
        async function loadDashboardData() {
            setIsLoading(true)
            setErrorMessage('')
            try {
                const [products, suppliers, purchaseOrders] = await Promise.all([
                    getProducts(0, 1),
                    getSuppliers(),
                    getPurchaseOrders(),
                ])

                const activeSuppliers = suppliers.filter((supplier) => supplier.active,).length
                const openPurchaseOrders = purchaseOrders.filter((po) => ['PENDING', 'APPROVED'].includes(po.status),).length

                setDashBoardData({
                    totalProducts: products.totalElements ?? 0,
                    totalSuppliers: suppliers.length,
                    activeSuppliers,
                    openPurchaseOrders,
                })
            } catch (error) {
                const message =
                    error instanceof Error
                        ? error.message : 'Unable to load dashboard data. Please try again.'
                setErrorMessage(message)
            } finally {
                setIsLoading(false)
            }
        }
        loadDashboardData()
    }, [])

    if (isLoading) {
        return (
            <div className="dashboard-state">
                <span className="loading-spinner" aria-hidden="true"></span>
                <p>Loading dashboard…</p>
            </div>
        )
    }

    if (errorMessage) {
        return (
            <div className="dashboard-state dashboard-state-error" role="alert">
                <p>{errorMessage}</p>
            </div>
        )
    }




    return (
        <section className="dashboard-page">
            <div className="dashboard-heading">
                <div>
                    <p className="page-eyebrow">Overview</p>
                    <h1>Inventory dashboard</h1>
                    <p>Monitor your products, suppliers, and purchasing activity.</p>
                </div>
            </div>

            <div className="stat-grid">
                <StatCard
                    title="Total products"
                    value={dashboardData.totalProducts}
                    description="Products recorded in the catalogue"
                    icon={Package}
                    tone="blue"
                />

                <StatCard
                    title="Total suppliers"
                    value={dashboardData.totalSuppliers}
                    description="Supplier accounts in the system"
                    icon={Truck}
                    tone="violet"
                />

                <StatCard
                    title="Active suppliers"
                    value={dashboardData.activeSuppliers}
                    description="Suppliers currently available"
                    icon={CircleCheckBig}
                    tone="green"
                />

                <StatCard
                    title="Open purchase orders"
                    value={dashboardData.openPurchaseOrders}
                    description="Pending or approved orders"
                    icon={ClipboardList}
                    tone="amber"
                />

            </div>

        </section>
    )
}

export default DashboardPage