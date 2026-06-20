import { useState } from 'react'
import { ArrowLeft } from 'lucide-react'
import { Link, useNavigate } from 'react-router'
import ProductForm from '../components/products/ProductForm.jsx'
import { createProduct } from '../services/productService.js'

import './AddProductPage.css'

function AddProductPage() {
    const [isSubmitting, setIsSubmitting] = useState(false)
    const [errorMessage, setErrorMessage] = useState('')

    const navigate = useNavigate()

    async function handleCreateProduct(productData) {
        setIsSubmitting(true)
        setErrorMessage('')

        try {
            await createProduct(productData)
            navigate('/products', {
                replace: true,
                state: {
                    successMessage: 'Product created successfully.'
                },
            })
        } catch (error) {
            const message = error instanceof Error ? error.message : 'Unable to create the product.'
            setErrorMessage(message)
        } finally {
            setIsSubmitting(false)
        }
    }

    return (
        <section className="add-product-page">
            <div className="add-product-heading">
                <Link className="product-back-link" to="/products">
                    <ArrowLeft size={18} aria-hidden="true" />
                    Back to products
                </Link>

                <p className="page-eyebrow">Catalogue</p>
                <h1>Add product</h1>
                <p className="add-product-description">
                    Create a new product record for your inventory catalogue.
                </p>

            </div>

            {errorMessage && (
                <div className="product-form-error" role="alert">
                    <p>{errorMessage}</p>
                </div>
            )}

            <div className="product-form-card">
                <ProductForm
                    isSubmitting={isSubmitting}
                    onSubmit={handleCreateProduct}
                />
            </div>
        </section>
    )
}

export default AddProductPage