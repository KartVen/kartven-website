import './Navbar.css'
import React, {useState} from 'react'
import {Link} from "react-router-dom";
import {FaBars, FaTimes} from "react-icons/fa";

const Navbar = () => {
    const mobileMenuBtnSize = 30;

    const [isPrimNavOpen, setIsPrimNavOpen] = useState(false);
    const handleMenuToggle = () => setIsPrimNavOpen(state => !state);

    const [isScrolledPage, setIsScrolledPage] = useState(false);
    const handleChangeBg = () => setIsScrolledPage(window.scrollY >= 25);
    window.addEventListener("scroll", handleChangeBg);

    const PrimNavMenu = [
        {
            value: "Home",
            path: "/",
        },
        /*{
            value: "About",
            path: "/#about",
        },
        {
            value: "Projects",
            path: "/#project",
        },
        {
            value: "Contact",
            path: "/#contact",
        },*/
        {
            value: "UniversityOfTechnology",
            path: "/uot",
        },
    ];

    return (
        <header className={isScrolledPage ? "header-scroll" : ""}>
            <Link to="/">
                <h1 className="logo-text">KartVen</h1>
            </Link>

            <button
                className="mobile-menu-btn"
                style={{
                    color: "var(--aqua-color)",
                    width: "fit-content",
                }}
                aria-controls="primary-nav"
                onClick={handleMenuToggle}
            >
                {isPrimNavOpen ? (
                    <FaTimes size={mobileMenuBtnSize}/>
                ) : (
                    <FaBars size={mobileMenuBtnSize}/>
                )}
            </button>

            <nav className={isPrimNavOpen ? "primary-nav active" : "primary-nav"}>
                <ol className="menu">
                    {PrimNavMenu.map(({value, path}) => (
                        <li key={value}>
                            <Link to={path}>{value}</Link>
                        </li>
                    ))}
                </ol>
            </nav>
        </header>
    );
}

export default Navbar;