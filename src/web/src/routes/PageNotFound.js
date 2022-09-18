import React from 'react'

var style = {
    h1: {
        color: "var(--aqua-color)",
        fontSize: "clamp(50px, 20vw, 150px)",
        margin: "20px 0 0 0",
        fontWeight: 600,
        lineHeight: 1,
    },
    h2: {
        fontSize: "clamp(20px, 2.5vw, 40px)",
        margin: 0,
        fontWeight: 400,
        lineHeight: 1.1,
    }
}

const PageNotFound = () => {
    return (
        <section>
            <h1 style={style.h1}>404</h1>
            <h2 style={style.h2}>Page Not Found</h2>
        </section>
    );
}

export default PageNotFound;