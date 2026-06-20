import { Route, Routes } from 'react-router'
import HomePage from './pages/HomePage.jsx'
import LoginPage from './pages/LoginPage.jsx'
import DashboardPage from './pages/DashboardPage.jsx'
import ProtectedRoute from './components/ProtectedRoute.jsx'
import AppLayout from './components/layout/AppLayout.jsx'
import PublicLayout from './components/layout/PublicLayout.jsx'
import ProductsPage from './pages/ProductsPage.jsx'

import './App.css'

function App() {

  return (
    <main className="app-shell">
      <Routes>

        <Route element={<PublicLayout />}>

          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<LoginPage />} />
        </Route>

        <Route element={<ProtectedRoute />}>
          <Route element={<AppLayout />}>
            <Route path="/dashboard" element={<DashboardPage />} />
            <Route path="/products" element={<ProductsPage />} />
          </Route>
        </Route>

      </Routes>
    </main >
  )
}

export default App
