import { useEffect, useState } from 'react'
import { CheckCircle2, ChevronLeft, ChevronRight, Plus, Search, X } from 'lucide-react'
import { getProducts, searchProducts } from '../services/productService.js'
import { Link, useLocation, useNavigate } from 'react-router'

import './ProductsPage.css'

function ProductsPage() {
    const [productPage, setProductPage] = useState(null)
    const [currentPage, setCurrentPage] = useState(0)
    const [isLoading, setIsLoading] = useState(true)
    const [errorMessage, setErrorMessage] = useState('')
    const [searchTerm, setSearchTerm] = useState('')
    const [debouncedSearchTerm, setDebouncedSearchTerm] = useState('')

    const location = useLocation()
    const navigate = useNavigate()

    const successMessage = location.state?.successMessage

    function dismissSuccessMessage() {
        navigate(location.pathname, {
            replace: true,
            state: null,
        })
    }

    useEffect(() => {
        const timeoutId = window.setTimeout(() => {
            setDebouncedSearchTerm(searchTerm.trim())
            setCurrentPage(0)
        }, 350)

        return () => {
            window.clearTimeout(timeoutId)
        }
    }, [searchTerm])

    useEffect(() => {
        async function loadProducts() {
            setIsLoading(true)
            setErrorMessage('')

            try {
                const data = debouncedSearchTerm
                    ? await searchProducts(debouncedSearchTerm, currentPage, 10)
                    : await getProducts(currentPage, 10)

                setProductPage(data)
            } catch (error) {
                const message = error instanceof Error ? error.message : 'Unable to load products.'
                setErrorMessage(message)

            } finally {
                setIsLoading(false)
            }

        }
        loadProducts()
    }, [currentPage, debouncedSearchTerm])

    return (
        <section className='products-page'>

            <div className="products-heading">
                <div>
                    <p className="page-eyebrow">Catalogue</p>
                    <h1>Products</h1>
                    <p className="products-description">
                        Manage product details, prices, categories, and stock levels.
                    </p>
                </div>

                <Link className="primary-button add-product-button" to="/products/new">
                    <Plus size={18} aria-hidden="true" />
                    Add product
                </Link>
            </div>

            {successMessage && (
                <div className="products-success" role="status">
                    <CheckCircle2 size={20} aria-hidden="true" />

                    <p>{successMessage}</p>

                    <button
                        type="button"
                        aria-label="Dismiss success message"
                        onClick={dismissSuccessMessage}
                    >
                        <X size={18} aria-hidden="true" />
                    </button>
                </div>
            )}

            <div className="product-toolbar">
                <div className="product-search">
                    <Search size={19} aria-hidden="true" />

                    <input
                        type="search"
                        value={searchTerm}
                        onChange={(event) => setSearchTerm(event.target.value)}
                        placeholder="Search products by name"
                        aria-label="Search products by name"
                    />
                </div>

                {debouncedSearchTerm && (
                    <p className="search-summary">
                        Results for “{debouncedSearchTerm}”
                    </p>
                )}
            </div>


            {isLoading && (
                <div className="products-state">
                    <span className="loading-spinner" aria-hidden="true"></span>
                    <p>Loading products…</p>
                </div>

            )}

            {errorMessage && (
                <div className="products-state products-state-error" role="alert">
                    <p>{errorMessage}</p>
                </div>
            )}

            {!isLoading && !errorMessage && productPage && (
                <div className='product-table-card'>
                    {productPage.content.length === 0 ? (
                        <div className="products-state">
                            <p>No products have been added yet.</p>
                        </div>
                    ) : (
                        <div className='table-scroll'>
                            <table className='product-table'>
                                <thead>
                                    <tr>
                                        <th scope="col">SKU</th>
                                        <th scope="col">Product</th>
                                        <th scope="col">Category</th>
                                        <th scope="col">Selling price</th>
                                        <th scope="col">Stock</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    {productPage.content.map((product) => (
                                        <tr key={product.id}>
                                            <td>
                                                <span className='sku-value'>{product.sku}</span>
                                            </td>

                                            <td>
                                                <strong>{product.name}</strong>
                                            </td>

                                            <td>{product.category || 'Uncategorized'}</td>

                                            <td>
                                                {Number(product.sellingPrice).toFixed(2)}
                                            </td>

                                            <td>
                                                <span className={
                                                    product.quantity > 0 ? 'stock-badge in-stock' : 'stock-badge out-of-stock'
                                                }>
                                                    {product.quantity > 0 ? `${product.quantity} available` : 'Out of stock'}
                                                </span>
                                            </td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    )}
                    <footer className='pagination'>
                        <p>
                            Page {productPage.number + 1} of{' '}
                            {Math.max(productPage.totalPages, 1)}
                        </p>

                        <div className="pagination-actions">
                            <button
                                type='button'
                                aria-label='Previous page'
                                disabled={productPage.first}
                                onClick={() =>
                                    setCurrentPage((page) => Math.max(page - 1, 0))
                                }
                            >
                                <ChevronLeft size={18} aria-hidden="true" />
                                Previous
                            </button>

                            <button
                                type='button'
                                aria-label='Next page'
                                disabled={productPage.last}
                                onClick={() => setCurrentPage((page) => page + 1)}
                            >
                                Next
                                <ChevronRight size={18} aria-hidden="true" />
                            </button>
                        </div>

                    </footer>
                </div>
            )}


        </section>
    )
}

export default ProductsPage