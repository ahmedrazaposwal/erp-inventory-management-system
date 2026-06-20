import { useState } from 'react'

const initialFormData = {
    sku: '',
    name: '',
    description: '',
    quantity: '0',
    costPrice: '',
    sellingPrice: '',
    category: '',
}

function ProductForm({ onSubmit, isSubmitting }) {
    const [formData, setFormData] = useState(initialFormData)

    function handleChange(event) {
        const { name, value } = event.target

        setFormData((currentFormData) => ({
            ...currentFormData,
            [name]: value
        }))
    }

    function handleSubmit(event) {
        event.preventDefault()

        onSubmit({
            sku: formData.sku.trim(),
            name: formData.name.trim(),
            description: formData.description.trim(),
            quantity: Number(formData.quantity),
            costPrice: Number(formData.costPrice),
            sellingPrice: Number(formData.sellingPrice),
            category: formData.category.trim(),
        })
    }

    return (
        <form className="product-form" onSubmit={handleSubmit}>
            <fieldset className="product-form-fields" disabled={isSubmitting}>
                <div className="product-form-grid">
                    <div className="form-field">
                        <label htmlFor="sku">SKU</label>
                        <input
                            id="sku"
                            name="sku"
                            type="text"
                            value={formData.sku}
                            onChange={handleChange}
                            placeholder="For example: LAP-001"
                            autoComplete="off"
                            required
                        />
                    </div>

                    <div className="form-field">
                        <label htmlFor="name">Product name</label>
                        <input
                            id="name"
                            name="name"
                            type="text"
                            value={formData.name}
                            onChange={handleChange}
                            placeholder="Enter the product name"
                            autoComplete="off"
                            required
                        />
                    </div>

                    <div className="form-field">
                        <label htmlFor="category">Category</label>
                        <input
                            id="category"
                            name="category"
                            type="text"
                            value={formData.category}
                            onChange={handleChange}
                            placeholder="For example: Electronics"
                            autoComplete="off"
                        />
                    </div>

                    <div className="form-field">
                        <label htmlFor="quantity">Quantity</label>
                        <input
                            id="quantity"
                            name="quantity"
                            type="number"
                            value={formData.quantity}
                            onChange={handleChange}
                            min="0"
                            step="1"
                            required
                        />
                    </div>

                    <div className="form-field">
                        <label htmlFor="costPrice">Cost price</label>
                        <input
                            id="costPrice"
                            name="costPrice"
                            type="number"
                            value={formData.costPrice}
                            onChange={handleChange}
                            min="0"
                            step="0.01"
                            placeholder="0.00"
                            required
                        />
                    </div>

                    <div className="form-field">
                        <label htmlFor="sellingPrice">Selling price</label>
                        <input
                            id="sellingPrice"
                            name="sellingPrice"
                            type="number"
                            value={formData.sellingPrice}
                            onChange={handleChange}
                            min="0"
                            step="0.01"
                            placeholder="0.00"
                            required
                        />
                    </div>

                    <div className="form-field product-description-field">
                        <label htmlFor="description">Description</label>
                        <textarea
                            id="description"
                            name="description"
                            value={formData.description}
                            onChange={handleChange}
                            placeholder="Add useful information about this product"
                            rows="5"
                        />
                    </div>
                </div>
            </fieldset>

            <button
                className="primary-button product-submit-button"
                type="submit"
                disabled={isSubmitting}
            >
                {isSubmitting ? 'Creating product…' : 'Create product'}
            </button>
        </form>
    )
}
export default ProductForm