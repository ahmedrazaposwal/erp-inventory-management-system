function StatCard({
    title,
    value,
    description,
    icon: Icon,
    tone = 'blue',
}) {
    return (
        <article className={`stat-card stat-card-${tone}`}>
            <div className="stat-card-header">
                <div>
                    <p>{title}</p>
                    <strong>{value}</strong>
                </div>

                <span className="stat-card-icon" aria-hidden="true">
                    <Icon size={22} />
                </span>
            </div>

            <p className="stat-card-description">{description}</p>
        </article>
    )
}

export default StatCard