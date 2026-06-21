import { useEffect, useRef } from 'react'
import { TriangleAlert } from 'lucide-react'
import './ConfirmDialog.css'

function ConfirmDialog({
    title,
    message,
    confirmLabel = 'Confirm',
    isConfirming,
    onConfirm,
    onCancel
}) {

    const dialogReference = useRef(null)

    useEffect(() => {
        const dialog = dialogReference.current

        if (dialog && !dialog.open) {
            dialog.showModal()
        }

        return () => {
            if (dialog?.open) {
                dialog.close()
            }
        }
    }, [])

    function handleCancel(event) {
        event.preventDefault()

        if (!isConfirming) {
            onCancel()
        }
    }

    return (
        <dialog
            ref={dialogReference}
            className="confirm-dialog"
            onCancel={handleCancel}
        >
            <div className="confirm-dialog-icon" aria-hidden="true">
                <TriangleAlert size={24} />
            </div>

            <div className="confirm-dialog-content">
                <h2>{title}</h2>
                <p>{message}</p>
            </div>

            <div className="confirm-dialog-actions">
                <button
                    className="dialog-cancel-button"
                    type="button"
                    disabled={isConfirming}
                    onClick={onCancel}
                >
                    Cancel
                </button>

                <button
                    className="dialog-confirm-button"
                    type="button"
                    disabled={isConfirming}
                    onClick={onConfirm}
                >
                    {isConfirming ? 'Deleting…' : confirmLabel}
                </button>

            </div>

        </dialog>
    )

}

export default ConfirmDialog